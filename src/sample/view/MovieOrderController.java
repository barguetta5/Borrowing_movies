package sample.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sample.db.Movie;
import sample.db.Order;

public class MovieOrderController extends HelloController {

    private int movieId;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label categoryLabel11;

    @FXML
    private Label dateLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label durationLabel;

    @FXML
    private ImageView movieImage;

    @FXML
    private Label titleLabel;

    @FXML
    private Text order;

    @FXML
    private Text remove;

    @FXML
    private Button orderButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button reviewButton;

    @FXML
    private Text reviewText;

    @FXML
    void orderPress(ActionEvent event) {
        Order order = new Order(-1, selectedMovie.getPrice(), dateLabel.getText(),currentUser.getId(), selectedMovie.getMovieID());
        order.addNewOrderToDB();
        selectedMovie.updateIsAvilable(0);
        // TODO - shani check this
    }
    @FXML
    void removePress(ActionEvent event) {

    }

    @FXML
    void reviewPress(ActionEvent event) {

        handleNextScreenButton("resources/reviewPage.fxml");
    }

    public void initialize(){
        // TODO - UI - shir - load movie details into fxml use selectedMovie.
        if(isAdmin){
            order.setVisible(false);
            orderButton.setVisible(false);
            reviewButton.setVisible(false);
            reviewText.setVisible(false);
        }
        else{
            remove.setVisible(false);
            removeButton.setVisible(false);
        }
    }

}
