package sample.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.control.ToggleGroup;
import sample.db.Movie;
import sample.db.Review;


public class ReviewController extends HelloController {

    @FXML
    private Label categoryLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label durationLabel;

    @FXML
    private ImageView movieImage;

    @FXML
    private RadioButton rate1;

    @FXML
    private RadioButton rate2;

    @FXML
    private RadioButton rate3;

    @FXML
    private RadioButton rate4;

    @FXML
    private RadioButton rate5;

    private int rate = 0;

    @FXML
    private Label titleLabel;

    void setMovieReview(){
        // TODO - UI - shir - insert the real review form the ui into thr text.
        Review review = new Review(-1,rate, "This movie is great", currentUser.getId(), selectedMovie.getMovieID());
        review.insertNewReview();
        handleNextScreenButton("resources/mainPage.fxml");
    }

    @FXML
    void handleRating(ActionEvent event) {
        if (rate1.isSelected()){
            rate = 1;
        }
        else if(rate2.isSelected()){
            rate1.setSelected(true);
            rate = 2;
        }
        else if(rate3.isSelected()){
            rate1.setSelected(true);
            rate2.setSelected(true);
            rate = 3;
        }
        else if(rate4.isSelected()){
            rate1.setSelected(true);
            rate2.setSelected(true);
            rate3.setSelected(true);
            rate = 4;
        }
        else if(rate5.isSelected()){
            rate1.setSelected(true);
            rate2.setSelected(true);
            rate3.setSelected(true);
            rate4.setSelected(true);
            rate = 5;
        }
    }

 // TODO - UI - shir - add  submit button  and creat function for that. use "setMovieReview" function for that.
}
