package lk.carnage.carnagemanagementla.controller;


import animatefx.animation.JackInTheBox;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import lk.carnage.carnagemanagementla.db.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {
    public JFXButton btnReg;
    public TextField txtRePas;
    public TextField txtPas;
    public Label lblRePas;
    public Label lblPas;
    public TextField txtCred;
    public TextField txtName;
    public Label lblname;
    public Label lblCredential;
    public Label lblRegister;
    public ImageView rootNode;
    public Label lblInfo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addAnimations();
        addActionEvents();
        adminCheck();
    }

    private void generateUserId() {
       /* try {
            String currentID = UserRepo.getCurrentID();

            String newID = generateNextID(currentID);
            txtCred.setText(newID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    private String generateNextID(String currentID) {
        if (currentID != null) {
            String[] split = currentID.split("U");
            int idNum = Integer.parseInt(split[1]);
            idNum++;
            return String.format("U%03d", idNum);
        }
        return "U001";
    }
    private void adminCheck() {
        lblCredential.setText("Admin Pin");
        txtCred.setPromptText("Enter Admin Pin");

        txtName.setVisible(false);
        txtPas.setVisible(false);
        txtRePas.setVisible(false);
        lblname.setVisible(false);
        lblPas.setVisible(false);
        lblRePas.setVisible(false);
        btnReg.setVisible(false);

        txtCred.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (txtCred.getText().equals("12345")) {
                    txtCred.clear();
                    lblInfo.setText("");
                    lblCredential.setText("Credential ID");
                    txtCred.setPromptText("U001");

                    txtName.setVisible(true);
                    txtPas.setVisible(true);
                    txtRePas.setVisible(true);
                    lblname.setVisible(true);
                    lblPas.setVisible(true);
                    lblRePas.setVisible(true);
                    btnReg.setVisible(true);

                    txtCred.setOnKeyPressed(null);
                    txtName.requestFocus();

                    generateUserId();

                } else {
                    lblInfo.setText("Invalid Admin Pin");
                }
            }
        });
    }

    private void addActionEvents() {
        addHoverEffect(btnReg);

        addFocusListener(txtCred);
        addFocusListener(txtName);
        addFocusListener(txtPas);
        addFocusListener(txtRePas);

        txtCred.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtName.requestFocus();
            }
        });

        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPas.requestFocus();
            }
        });

        txtPas.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtRePas.requestFocus();
            }
        });

        txtRePas.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                btnReg.fire();
            }
        });
    }
    private void addAnimations() {
        new JackInTheBox(lblCredential).play();
        new JackInTheBox(lblname).play();
        new JackInTheBox(lblPas).play();
        new JackInTheBox(lblRegister).play();
        new JackInTheBox(lblRePas).play();
        new JackInTheBox(txtCred).play();
        new JackInTheBox(txtName).play();
        new JackInTheBox(txtRePas).play();
        new JackInTheBox(txtPas).play();
        new JackInTheBox(btnReg).play();
    }

    private void addFocusListener(TextField textField) {
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // TextField is focused
                textField.setStyle("-fx-focus-color: #005436;");
            } else {
                // TextField is not focused
                textField.setStyle("-fx-focus-color: black;");
            }
        });
    }
    private void addHoverEffect(JFXButton button) {
        button.setOnMouseEntered(event -> button.setStyle("-fx-background-color: #005436; -fx-background-radius: 10;"));
        button.setOnMouseExited(event -> button.setStyle("-fx-background-color: black; -fx-background-radius: 10;"));
    }
    public void RegBtnOnAction(ActionEvent actionEvent) {
       /* String userId = txtCred.getText();
        String name = txtName.getText();
        String password = txtPas.getText();
        String rePassword = txtRePas.getText();

        // Check if all TextFields are filled
        if (userId.isEmpty() || name.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            lblInfo.setText("Fill all the information");
            lblInfo.setStyle("-fx-text-fill: red;");

            // Request focus on the unfilled TextField
            if (userId.isEmpty()) {
                txtCred.requestFocus();
                txtCred.setStyle("-fx-border-color: red;");
            } else if (name.isEmpty()) {
                txtName.requestFocus();
                txtName.setStyle("-fx-border-color: red;");
            } else if (password.isEmpty()) {
                txtPas.requestFocus();
                txtPas.setStyle("-fx-border-color: red;");
            } else {
                txtRePas.requestFocus();
                txtRePas.setStyle("-fx-border-color: red;");
            }

            return; // Return from the method immediately
        }

        if (!password.equals(rePassword)) {
            lblInfo.setText("Password does not match");
            lblInfo.setStyle("-fx-text-fill: red;");
            return; // Return from the method immediately
        }

        try {
            boolean isSaved = saveUser(userId, name, password);
            if(isSaved) {
                lblInfo.setText("User Saved Successfully!");
                lblInfo.setStyle("-fx-text-fill: green;");
                clearText();
            }
        } catch (SQLException e) {
            lblInfo.setText("ID already taken");
            lblInfo.setStyle("-fx-text-fill: red;");
            txtCred.requestFocus();
            txtCred.setStyle("-fx-border-color: red;");
        }*/
    }
    private void clearText() {
        txtCred.clear();
        txtName.clear();
        txtPas.clear();
        txtRePas.clear();
    }
    private boolean saveUser(String userId, String name, String password) throws SQLException, ClassNotFoundException {
        String checkSql = "SELECT username FROM Credential WHERE username = ?";

        Connection connection = DBConnection.getDbConnection().getConnection();

        PreparedStatement checkPstm = connection.prepareStatement(checkSql);
        checkPstm.setObject(1, userId);

        ResultSet resultSet = checkPstm.executeQuery();
        if (resultSet.next()) {
            // User ID already exists in the database
            lblInfo.setText("ID already taken");
            lblInfo.setStyle("-fx-text-fill: red;");
            txtCred.requestFocus();
            txtCred.setStyle("-fx-border-color: red;");
            return false;
        }

        String insertSql = "INSERT INTO Credential VALUES(?, ?, ?)";

        PreparedStatement insertPstm = connection.prepareStatement(insertSql);
        insertPstm.setObject(1, userId);
        insertPstm.setObject(2, name);
        insertPstm.setObject(3, password);

        return insertPstm.executeUpdate() > 0;
    }

}
