package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.db.Category;
import sample.db.Movie;

public class MovieSearchController extends HelloController{

    @FXML
    private Label category1;

    @FXML
    private Label category2;

    @FXML
    private Label category3;

    @FXML
    private Label category4;

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private ImageView image4;

    @FXML
    private AnchorPane movie1;

    @FXML
    private AnchorPane movie2;

    @FXML
    private AnchorPane movie3;

    @FXML
    private AnchorPane movie4;

    @FXML
    private Label title1;

    @FXML
    private Label title2;

    @FXML
    private Label title3;

    @FXML
    private Label title4;

    private void loadMoviesByKey() {
        // Load the movies from the database and display them in movie1, movie2, ..., movie8.
        // For each movie, set the title, category, and image.
        // If there are fewer than 8 movies, hide the remaining movie panes.
        // get user choice
        Movie m  = new Movie();
        Movie[] movies = new Movie[8];

        switch (userSearchFilter) {
            case "Title":
                System.out.println(userSearchWord);
                movies = m.exportMovieByTitle(userSearchWord);
            case "Category":
                movies = m.exportMovieByCategory(userSearchWord);
            case "Duration":
                movies = m.exportMovieByDuration(userSearchWord);
            case "All":
                movies = m.exportMovie();

        }
        // TODO - UI - shir - Load the movies into the UI
        title1.setText(movies[0].getMovieTitle());
        category1.setText(movies[0].getCategory());
        Image initialImage = new Image(getClass().getResourceAsStream(movies[0].getImagePath()));
        image1.setImage(initialImage);
    }
    @FXML
    void enterMovie(MouseEvent event) {
        // which movie was selected??
        // TODO - UI - Shir - it shuld put the selected movie inside selectedMovie.
        selectedMovie = new Movie();
        handleNextScreenButton("resources/movieOrder.fxml");
    }
    @FXML
    void initialize() {
        loadMoviesByKey();
    }
}
