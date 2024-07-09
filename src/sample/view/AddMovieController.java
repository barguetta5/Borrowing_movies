package sample.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.db.Category;
import sample.db.Movie;

import java.io.File;

public class AddMovieController extends HelloController {
    @FXML
    private TextField addCategory;

    @FXML
    private TextField addDuration;

    @FXML
    private TextField addPrice;

    @FXML
    private TextField addReleaseDate;

    @FXML
    private TextField addTitle;

    @FXML
    private ImageView movieImage;

    @FXML
    private TextField addDescription;

    @FXML
    void addImage(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif","*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            movieImage.setImage(image);
        }
    }


    @FXML
    void pressSubmit(ActionEvent event) {
        String category = addCategory.getText();
        String duration = addDuration.getText();
        String price = addPrice.getText();
        String releaseDate = addReleaseDate.getText();
        String title = addTitle.getText();
        String description = addDescription.getText();
        Movie movie = validateInput(category, duration, price, releaseDate, title, description);
        if (movie == null) {
            showAlert("Error", "Invalid input");
            return;
        }
        movie.addNewMovieToDB();
        handleNextScreenButton("resources/mainPage.fxml");
    }

    private Movie validateInput(String category, String duration, String price, String releaseDate, String title, String description) {
        Movie movie = null;
        Category c = new Category();
        Category movieCategory = c.getCategoryByName(category);
        if (movieCategory==null){
            movieCategory = new Category(-1,category);
            movieCategory.addNewCatogoryToDB();
        }
        if (duration != null && !duration.isEmpty() && duration.matches("[0-9]+")) {
            if (price != null && !price.isEmpty() && price.matches("[0-9]+")) {
                if (releaseDate != null && !releaseDate.isEmpty()) {
                    if (title != null && !title.isEmpty()) {
                        return new Movie(-1, title, description, releaseDate, duration, movieCategory.getCategoryID(), 1, Integer.parseInt(price));
                    }
                }
            }
        }
        return null;
    }


}
