package sample.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.db.Movie;
import sample.db.User;


public class HelloController{

    public static User currentUser;
    public static Movie selectedMovie;
    protected static Boolean isAdmin;

    public static String userSearchWord;
    public static String userSearchFilter;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordLogin;

    @FXML
    private PasswordField passwordRegister;

    @FXML
    private PasswordField repeatPassword;

    @FXML
    private TextField usernameLogin;

    @FXML
    private TextField usernameRegister;
    @FXML
    void closeButton(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void pressRegister(ActionEvent event) {
        String email = emailField.getText();
        String password =passwordRegister.getText();
        String password_validation= repeatPassword.getText();
        String username=usernameRegister.getText(); // TODO - delete username from UI
        if (!addUserValidation(email, password, password_validation)) {
            return;
        }
        User new_user = new User(0,username, password);
        new_user.addNewUserInDB();
        showAlert("Success", "User added successfully");
    }
    private boolean addUserValidation(String username, String password, String repeatPassword) {
        if (username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            showAlert("Empty Fields", "Please fill in all fields.");
            return false;
        }
        if (!isValidEmail(username)) {
            showAlert("Invalid Email", "Please enter a valid email address.");
            return false;
        }
        if (!password.equals(repeatPassword)) {
            showAlert("Password", "Please enter matching passwords.");
            return false;
        }
        return true;
    }
    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com");
    }

    void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void pressLogin(ActionEvent event) {
        String username = usernameLogin.getText();
        String password = passwordLogin.getText();
        isAdmin=false;
        currentUser = new User();
        currentUser = currentUser.login(username, password);
        if (currentUser != null) {
            System.out.println("Login successful");
            handleNextScreenButton("resources/mainPage.fxml");
        } else {
            System.out.println("Login failed");
            showAlert("Login Failed", "Invalid username or password");
        }
    }
    protected void handleNextScreenButton(String fxml) {

        try {
            HelloApplication.loadScene(fxml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}