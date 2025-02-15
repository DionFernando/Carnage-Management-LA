package lk.carnage.carnagemanagementla.controller;

import animatefx.animation.Pulse;
import animatefx.animation.Wobble;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable{
    public AnchorPane rootNode;
    public JFXButton btnHome;
    public JFXButton btnWomen;
    public JFXButton btnMen;
    public JFXButton btnAcce;
    public JFXButton btnGift;
    public JFXButton btnOrder;
    public ImageView imgCus;
    public ImageView imgEmp;
    public ImageView imgCarnage;
    public ImageView imgBackground;
    public ImageView imgLogout;
    public JFXButton btnEmp;
    public JFXButton btnCus;
    public JFXButton salesbtn;

    private PauseTransition idleTimer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Pulse pulse = new Pulse(imgCarnage);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setSpeed(0.2);
        pulse.play();

        Pulse pulse1 = new Pulse(salesbtn);
        pulse1.setCycleCount(Animation.INDEFINITE);
        pulse1.setSpeed(0.5);
        pulse1.play();

        new Wobble(btnWomen).play();
        new Wobble(btnMen).play();
        new Wobble(btnAcce).play();
        new Wobble(btnGift).play();
        new Wobble(btnOrder).play();
        new Wobble(btnHome).play();


        addHoverEffect(btnHome);
        addHoverEffect(btnAcce);
        addHoverEffect(btnGift);
        addHoverEffect(btnMen);
        addHoverEffect(btnWomen);
        addHoverEffect(btnEmp);
        addHoverEffect(btnCus);
        salesHoverEffect(salesbtn);

        orderHoverEffect(btnOrder);

        automaticLogout();

    }

    private void automaticLogout() {
//        idleTimer = new PauseTransition(Duration.seconds(15));
//        idleTimer.setOnFinished(e -> {
//            try {
//                // Navigate to the login page when the timer finishes
//                AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/welcome_form.fxml"));
//                Scene scene = new Scene(rootNode);
//                Stage stage = (Stage) this.rootNode.getScene().getWindow();
//                stage.setScene(scene);
//                stage.centerOnScreen();
//                stage.setTitle("Login Form");
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        });
//
//        // Start the idle timer
//        idleTimer.playFromStart();
    }

    private void orderHoverEffect(JFXButton button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #005436; -fx-background-radius: 10;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color:  #171717; -fx-background-radius: 10;"));
    }

    private void addHoverEffect(JFXButton button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #005436; -fx-background-radius: 10;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: black; -fx-background-radius: 10;"));
    }

    private void salesHoverEffect(JFXButton button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #e87431; -fx-background-radius: 0 30 0 0;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: black; -fx-background-radius: 0;"));
    }

    public void homeBtnOnAction(ActionEvent actionEvent) {
    }

    public void womenBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/womens_wear_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Women's Wear Form");
    }
    public void menBtnOnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/mens_wear_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Men's Wear Form");
    }
    public void accessoriesBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/accessories_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Accessories Form");
    }
    public void giftcardBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/gift_cards_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Gift Cards Form");
    }
    public void employeeBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/employee_dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Employee Form");
    }
    public void customerBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/customer_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Customer Form");
    }
    public void orderBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/orders_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Orders Form");
    }
    public void logoutBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }
    public void SalesOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/sales_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Sales Form");
    }
}

