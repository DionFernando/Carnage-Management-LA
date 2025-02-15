package lk.carnage.carnagemanagementla.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import animatefx.animation.Wobble;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.carnage.carnagemanagementla.bo.BOFactory;
import lk.carnage.carnagemanagementla.bo.custom.EmployeeBO;
import lk.carnage.carnagemanagementla.dto.EmployeeDTO;
import lk.carnage.carnagemanagementla.entity.Employee;
import lk.carnage.carnagemanagementla.view.tdm.EmployeeTm;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {
    public AnchorPane rootNode;
    public TableColumn colTelephone;
    public TableColumn colName;
    public TableColumn colID;
    public TableView tblEmp;
    public JFXButton btnDel;
    public JFXButton btnUpd;
    public JFXButton btnAdd;
    public JFXButton btnClr;
    public ImageView img;
    public TextField txtTel;
    public TextField txtName;
    public TextField txtID;
    public Label lblTel;
    public Label lbln;
    public Label lblID;
    public Label lblEmp;
    public Label lblInfo;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addAnimations();
        addHoverEffects();

        addTextChangeListener(txtID);
        addTextChangeListener(txtName);
        addTextChangeListener(txtTel);

        Platform.runLater(() -> txtID.requestFocus());

        KeyEventHandlersToTextFields();

        Platform.runLater(() -> txtID.requestFocus());

        setCellValueFactory();
        loadALlEmployees();

        txtID.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            if (txtID.getText().isEmpty() && !event.getCharacter().equalsIgnoreCase("E")) {
                event.consume();
            }
        });

        Validations();

        generateID();
        txtID.setEditable(false);

    }

    private void generateID() {
        try {
            String currentID = (String) employeeBO.generateID();

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
            String[] split = currentID.split("E");
            int idNum = Integer.parseInt(split[1]);
            idNum++;
            return String.format("E%03d", idNum);
        }
        return "E001";
    }

    private void Validations() {
        txtTel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.startsWith("+")) {
                if (!newValue.matches("\\+\\d{0,13}")) {
                    txtTel.setText(oldValue);
                    lblInfo.setText("Only numeric limited to 13 digits");
                    lblInfo.setStyle("-fx-text-fill: red;");
                } else {
                    lblInfo.setText("");
                }
            } else {
                if (!newValue.matches("\\d{0,10}")) {
                    txtTel.setText(oldValue);
                    lblInfo.setText("Only numeric limited to 10 digits");
                    lblInfo.setStyle("-fx-text-fill: red;");
                } else {
                    lblInfo.setText("");
                }
            }
        });
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadALlEmployees() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try{
            ArrayList<EmployeeDTO> employeeList = employeeBO.getAllEmployee();
            for(EmployeeDTO employees: employeeList){
                EmployeeTm tm = new EmployeeTm(
                        employees.getId(),
                        employees.getName(),
                        employees.getTel()
                );
                obList.add(tm);
            }
            tblEmp.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void KeyEventHandlersToTextFields() {
        txtID.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtName.requestFocus();
            }
        });

        txtName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtTel.requestFocus();
            }
        });

        txtTel.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                AddEmpOnAction(null);
            }
        });
    }

    private void addAnimations() {
        Pulse pulse = new Pulse(img);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setSpeed(0.3);
        pulse.play();

        new Wobble(lbln).play();
        new Wobble(lblTel).play();
        new Wobble(lblID).play();
        new Wobble(lblEmp).play();

        new Wobble(txtID).play();
        new Wobble(txtName).play();
        new Wobble(txtTel).play();

        new FadeIn(img).play();

        new Wobble(btnAdd).play();
        new Wobble(btnUpd).play();
        new Wobble(btnDel).play();
        new Wobble(btnClr).play();

        new Wobble(tblEmp).play();
    }
    private void addTextChangeListener(TextField textField) {
        // Change the border color to green when the user types into the TextField
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            textField.setStyle("-fx-border-color: #005436;");

            if (textField == txtID && !newValue.matches("^E.*$")) {
                lblInfo.setText("ID should start with 'E'");
                lblInfo.setStyle("-fx-text-fill: red;");
            } else {
                lblInfo.setText("");
            }
        });

        // Change the border color back to black when the TextField loses focus
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                textField.setStyle("-fx-border-color: black;");
            }
        });
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
    public void updateBtnOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();
        String name = txtName.getText();
        String tel = txtTel.getText();

        EmployeeDTO employee = new EmployeeDTO(id, name, Integer.parseInt(tel));

        try {
            boolean isUpdated = employeeBO.updateEmployee(employee);
            if(isUpdated) {
                lblInfo.setText("Employee Updated Successfully!");
                lblInfo.setStyle("-fx-text-fill: green;");
                clearFields();
                loadALlEmployees();
            }
        } catch (SQLException | ClassNotFoundException e) {
            lblInfo.setText("Failed to update");
            lblInfo.setStyle("-fx-text-fill: red;");
        }
    }
    public void AddEmpOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();
        String name = txtName.getText();
        String tel = txtTel.getText();

        if (id.isEmpty() || name.isEmpty() || tel.isEmpty()){
            lblInfo.setText("Fill all the information");
            lblInfo.setStyle("-fx-text-fill: red;");

            if (id.isEmpty()) {
                txtID.requestFocus();
                txtID.setStyle("-fx-border-color: red;");
            } else if (name.isEmpty()) {
                txtName.requestFocus();
                txtName.setStyle("-fx-border-color: red;");
            } else {
                txtTel.requestFocus();
                txtTel.setStyle("-fx-border-color: red;");
            }
            return;
        }

        EmployeeDTO employee = new EmployeeDTO(id, name, Integer.parseInt(tel));

        try {
            boolean isSaved = employeeBO.addEmployee(employee);
            if(isSaved) {
                lblInfo.setText("Employee Saved Successfully!");
                lblInfo.setStyle("-fx-text-fill: green;");
                clearFields();
                loadALlEmployees();
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
    public void clearBtnOnAction(ActionEvent actionEvent) {
        clearFields();
        txtID.setDisable(false);
        generateID();
    }
    public void deleteBtnOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();

        try {
            boolean isDeleted = employeeBO.deleteEmployee(id);
            if(isDeleted) {
                lblInfo.setText("Item Deleted Successfully!");
                lblInfo.setStyle("-fx-text-fill: green;");
                clearFields();
                loadALlEmployees();
            }
        } catch (SQLException e) {
            lblInfo.setText("Failed to delete");
            lblInfo.setStyle("-fx-text-fill: red;");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearFields() {
        txtID.clear();
        txtName.clear();
        txtTel.clear();
    }

    public void TableOnClick(MouseEvent mouseEvent) {
        EmployeeTm employeeTm = (EmployeeTm) tblEmp.getSelectionModel().getSelectedItem();
        txtID.setText(employeeTm.getId());
        txtName.setText(employeeTm.getName());
        txtTel.setText(String.valueOf(employeeTm.getTel()));

        txtID.setDisable(true);
    }

}
