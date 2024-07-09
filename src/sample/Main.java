package sample;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;



public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("resources/hello-view.fxml")));
        FXMLLoader fxmlLoader = new FXMLLoader(sample.Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 460, 400);
        stage.setTitle("Movie Rent");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}
