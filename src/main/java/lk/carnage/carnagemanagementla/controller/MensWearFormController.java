package lk.carnage.carnagemanagementla.controller;

import animatefx.animation.JackInTheBox;
import animatefx.animation.Pulse;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.carnage.carnagemanagementla.bo.BOFactory;
import lk.carnage.carnagemanagementla.bo.custom.MensBO;
import lk.carnage.carnagemanagementla.dto.MensDTO;
import lk.carnage.carnagemanagementla.entity.Mens;
import lk.carnage.carnagemanagementla.view.tdm.MensTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MensWearFormController implements Initializable {
    public AnchorPane rootNode;
    public TableColumn colQty;
    public TableColumn colPrice;
    public TableColumn colCategory;
    public TableColumn ColID;
    public TableView tblMen;
    public JFXButton btnDel;
    public JFXButton btnUpd;
    public JFXButton btnAdd;
    public JFXButton btnClr;
    public TextField txtSeason;
    public TextField txtQty;
    public TextField txtPrice;
    public TextField txtID;
    public TextField txtCategory;
    public Label lbl6;
    public Label lbl5;
    public Label lbl4;
    public Label lbl3;
    public Label lbl2;
    public Label lbl1;
    public ImageView imgHome;
    public ImageView img;
    public ImageView imgCarnage;
    public Label lblInfo;
    public TableColumn colSeason;

    MensBO mensBO = (MensBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MENS);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addAnimations();
        addHoverEffects();

        addTextChangeListener(txtID);
        addTextChangeListener(txtCategory);
        addTextChangeListener(txtPrice);
        addTextChangeListener(txtQty);
        addTextChangeListener(txtSeason);

        setCellValueFactory();
        loadAllMens();

        Platform.runLater(() -> txtID.requestFocus());

        KeyEventHandlersToTextFields();

        txtID.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (txtID.getText().isEmpty() && !event.getCharacter().equalsIgnoreCase("M")) {
                event.consume();
            }
        });

        Validations();
        generateID();
        txtID.setEditable(false);
        setMouseNavigation();

    }
    private void setMouseNavigation() {
        rootNode.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                try {
                    navigateToWomensWearForm();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (event.getButton() == MouseButton.SECONDARY && event.getClickCount() == 2) {
                try {
                    navigateToAccessoriesForm();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void navigateToWomensWearForm() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/womens_wear_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Womens Wear Form");
    }

    private void navigateToAccessoriesForm() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/accessories_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Mens Wear Form");
    }

    private void generateID() {
        try {
            String currentID = mensBO.generateMenID();

            System.out.println(currentID);

            String newID = generateNextID(currentID);
            txtID.setText(newID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextID(String currentID) {
        if (currentID != null) {
            String[] split = currentID.split("M");
            if (split.length > 1) {
                int idNum = Integer.parseInt(split[1]);
                idNum++;
                return String.format("M%03d", idNum);
            }
        }
        return "M001";
    }

    private void Validations() {
        txtPrice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                txtPrice.setText(oldValue);
                lblInfo.setText("Enter Price only in numbers");
                lblInfo.setStyle("-fx-text-fill: red;");
            } else {
                lblInfo.setText("");
            }
        });

        txtQty.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtQty.setText(oldValue);
                lblInfo.setText("Enter Qty only in numbers");
                lblInfo.setStyle("-fx-text-fill: red;");
            } else {
                lblInfo.setText("");
            }
        });
    }

    private void KeyEventHandlersToTextFields() {
        txtID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtCategory.requestFocus();
            }
        });
        txtCategory.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtPrice.requestFocus();
            }
        });
        txtPrice.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtQty.requestFocus();
            }
        });
        txtQty.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtSeason.requestFocus();
            }
        });
        txtSeason.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                addBtOnAction(null);
            }
        });
    }

    private void addTextChangeListener(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            textField.setStyle("-fx-border-color: #005436;");

            if (textField == txtID && !newValue.matches("^M.*$")) {
                lblInfo.setText("ID should start with 'M'");
                lblInfo.setStyle("-fx-text-fill: red;");
            } else {
                lblInfo.setText("");
            }
        });

        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                textField.setStyle("-fx-border-color: black;");
            }
        });
    }

    private void addAnimations() {
        Pulse pulse = new Pulse(img);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setSpeed(0.3);
        pulse.play();

        new JackInTheBox(img).play();
        new JackInTheBox(tblMen).play();
        new JackInTheBox(lbl1).play();
        new JackInTheBox(lbl2).play();
        new JackInTheBox(lbl3).play();
        new JackInTheBox(lbl4).play();
        new JackInTheBox(lbl5).play();
        new JackInTheBox(lbl6).play();
        new JackInTheBox(imgHome).play();
        new JackInTheBox(imgCarnage).play();
        new JackInTheBox(txtID).play();
        new JackInTheBox(txtSeason).play();
        new JackInTheBox(txtQty).play();
        new JackInTheBox(txtCategory).play();
        new JackInTheBox(txtPrice).play();
        new JackInTheBox(btnAdd).play();
        new JackInTheBox(btnClr).play();
        new JackInTheBox(btnDel).play();
        new JackInTheBox(btnUpd).play();
    }

    private void addHoverEffects() {
        addHoverEffect(btnAdd);
        clearHoverEffect(btnClr);
        updateHoverEffect(btnUpd);
        deleteHoverEffect(btnDel);
    }

    private void deleteHoverEffect(JFXButton deleteBtn) {
        deleteBtn.setOnMouseEntered(event -> deleteBtn.setStyle("-fx-background-color: #ba4e4e; -fx-background-radius: 10;"));
        deleteBtn.setOnMouseExited(event -> deleteBtn.setStyle("-fx-background-color: black; -fx-background-radius: 10;"));
    }

    private void updateHoverEffect(JFXButton updateBtn) {
        updateBtn.setOnMouseEntered(event -> updateBtn.setStyle("-fx-background-color: #ba7d4e; -fx-background-radius: 10;"));
        updateBtn.setOnMouseExited(event -> updateBtn.setStyle("-fx-background-color: black; -fx-background-radius: 10;"));
    }

    private void clearHoverEffect(JFXButton clearBtn) {
        clearBtn.setOnMouseEntered(event -> clearBtn.setStyle("-fx-background-color: #bab14e; -fx-background-radius: 10;"));
        clearBtn.setOnMouseExited(event -> clearBtn.setStyle("-fx-background-color: black; -fx-background-radius: 10;"));
    }

    private void addHoverEffect(JFXButton addBtn) {
        addBtn.setOnMouseEntered(event -> addBtn.setStyle("-fx-background-color: #1d991f; -fx-background-radius: 10;"));
        addBtn.setOnMouseExited(event -> addBtn.setStyle("-fx-background-color: black; -fx-background-radius: 10;"));
    }

    public void homeBtnOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("DashBoard Form");
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();

        try {
            boolean isDeleted = mensBO.deleteMen(id);
            if(isDeleted) {
                lblInfo.setText("Product Deleted Successfully!");
                lblInfo.setStyle("-fx-text-fill: green;");
                loadAllMens();
                clearText();
            }
        } catch (SQLException e) {
            lblInfo.setText("Product Didn't Delete!");
            lblInfo.setStyle("-fx-text-fill: red;");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();
        String category = txtCategory.getText();
        String price = txtPrice.getText();
        String qty = txtQty.getText();
        String season = txtSeason.getText();

        MensDTO product = new MensDTO(id, category, Double.parseDouble(price), Integer.parseInt(qty), season);

        try {
            boolean isUpdated = mensBO.updateMen(product);
            if (isUpdated) {
                lblInfo.setText("Product Updated Successfully!");
                lblInfo.setStyle("-fx-text-fill: green;");
                loadAllMens();
            }
        } catch (SQLException e) {
            lblInfo.setText("Product Didn't Update!");
            lblInfo.setStyle("-fx-text-fill: red;");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addBtOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();
        String category = txtCategory.getText();
        String price = txtPrice.getText();
        String qty = txtQty.getText();
        String season = txtSeason.getText();

        if (id.isEmpty() || category.isEmpty() || price.isEmpty() || qty.isEmpty() || season.isEmpty()) {
            lblInfo.setText("Fill all the information");
            lblInfo.setStyle("-fx-text-fill: red;");

            if (id.isEmpty()) {
                txtID.requestFocus();
                txtID.setStyle("-fx-border-color: red;");
            } else if (category.isEmpty()) {
                txtCategory.requestFocus();
                txtCategory.setStyle("-fx-border-color: red;");
            } else if (price.isEmpty()) {
                txtPrice.requestFocus();
                txtPrice.setStyle("-fx-border-color: red;");
            } else if (qty.isEmpty()) {
                txtQty.requestFocus();
                txtQty.setStyle("-fx-border-color: red;");
            } else {
                txtSeason.requestFocus();
                txtSeason.setStyle("-fx-border-color: red;");
            }
            return;
        }

        MensDTO mens = new MensDTO(id, category, Double.parseDouble(price), Integer.parseInt(qty), season);

        try {
            boolean isSaved = mensBO.addMen(mens);
            if (isSaved) {
                lblInfo.setText("Item Saved Successfully!");
                lblInfo.setStyle("-fx-text-fill: green;");
                clearText();
                loadAllMens();
                txtID.requestFocus();
                generateID();
            }
        } catch (SQLException e) {
            lblInfo.setText("ID already taken");
            lblInfo.setStyle("-fx-text-fill: red;");
            txtID.requestFocus();
            txtID.setStyle("-fx-border-color: red;");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearText() {
        txtID.clear();
        txtCategory.clear();
        txtPrice.clear();
        txtQty.clear();
        txtSeason.clear();
    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
        clearText();
        txtID.setDisable(false);
        generateID();
    }

    private void loadAllMens() {
        ObservableList<MensTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<MensDTO> mensList = mensBO.getAllMen();
            for (MensDTO mens : mensList) {
                MensTm tm = new MensTm(mens.getId(), mens.getCategory(), mens.getPrice(), mens.getQty(), mens.getSeason());
                obList.add(tm);
            }
            tblMen.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        ColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSeason.setCellValueFactory(new PropertyValueFactory<>("season"));
    }

    public void tableOnClick(MouseEvent mouseEvent) {

        MensTm mensTm = (MensTm) tblMen.getSelectionModel().getSelectedItem();
        txtID.setText(mensTm.getId());
        txtCategory.setText(mensTm.getCategory());
        txtPrice.setText(String.valueOf(mensTm.getPrice()));
        txtQty.setText(String.valueOf(mensTm.getQty()));
        txtSeason.setText(mensTm.getSeason());

        txtID.setDisable(true);

    }
}
