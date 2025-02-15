package lk.carnage.carnagemanagementla.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import animatefx.animation.Wobble;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lk.carnage.carnagemanagementla.bo.BOFactory;
import lk.carnage.carnagemanagementla.bo.custom.EmpAttendBO;
import lk.carnage.carnagemanagementla.dto.EmpAttendDTO;
import lk.carnage.carnagemanagementla.entity.EmpAttend;
import lk.carnage.carnagemanagementla.entity.Employee;
import lk.carnage.carnagemanagementla.view.tdm.EmpAttendTm;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeAttendanceFormController implements Initializable {
    public JFXButton btnUpd;
    public Label lblDay;
//    public TextField txtAttend;
    public Label lblName;
    public TableColumn colAttend;
    public TableColumn colName;
    public TableColumn colID;
    public TableView tblEmpAttend;
    public JFXButton btnAdd;
    public JFXButton btnClear;
    public Label lblAtted;
    public TextField txtID;
    public Label lbln;
    public Label lblID;
    public Label lblEmp;
    public ImageView img;
    public JFXButton btnCurrentDate;
    public Label lblDaysCount;
    public TextField txtAttendDate;
    public JFXComboBox empIdcmb;
    public Label lblETID;
    public Label lblInfo;
    public DatePicker date;

    EmpAttendBO empAttendBO = (EmpAttendBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEEATTENDANCE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addAnimations();
        addHoverEffects();

        getEmployeeID();

        empIdcmb.setEditable(true);

        getCurrentEmpAttendID();

        setCellValueFactory();

        employeeIDOnPressed(null);
    }

    private void setCellValueFactory() {
        colAttend.setCellValueFactory(new PropertyValueFactory<>("date"));

        colAttend.setCellFactory(tc -> {
            TableCell<EmpAttendTm, String> cell = new TableCell<>();
            cell.textProperty().bind(cell.itemProperty());
            cell.setStyle("-fx-alignment: CENTER;");
            return cell;
        });
    }

    private void getCurrentEmpAttendID() {
        try {
            String currentId = (String) empAttendBO.getCurrentEmpAttendID();

            String nextOrderId = generateNextOrderId(currentId);
            lblETID.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("S");
            int idNum = Integer.parseInt(split[1]);
            idNum++;
            return String.format("S%03d", idNum);
        }
        return "S001";
    }

    /*private void getEmployeeID() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> telList = (List<String>) empAttendBO.getCurrentEmpAttendID();

            for (String code : telList) {
                obList.add(code);
            }
            empIdcmb.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/

   /* private void getEmployeeID() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> telList = (List<String>) empAttendBO.getCurrentEmpAttendID();
            for (String s : telList) {
                obList.add(s);
            }
            empIdcmb.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
   private void getEmployeeID() {
       ObservableList<String> obList = FXCollections.observableArrayList();
       try {
           Object result = empAttendBO.getCurrentEmpAttendID();
           System.out.println("Result: " + result); // Debug print
           if (result instanceof List) {
               List<String> telList = (List<String>) result;
               for (String s : telList) {
                   obList.add(s);
               }
               empIdcmb.setItems(obList);
           } else {
               System.out.println("Result is not a List<String>"); // Debug print
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    private void addAnimations() {
        Pulse pulse = new Pulse(img);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setSpeed(0.3);
        pulse.play();

        new Wobble(lbln).play();
        new Wobble(lblAtted).play();
        new Wobble(lblEmp).play();
        new Wobble(lblDay).play();
        new Wobble(lblID).play();
        new Wobble(lblName).play();
        new Wobble(lblDaysCount).play();

        new FadeIn(img).play();
        new Wobble(tblEmpAttend).play();
        new Wobble(btnAdd).play();
        new Wobble(btnClear).play();
        new Wobble(btnUpd).play();
    }

    private void addHoverEffects() {
        addHoverEffect(btnAdd);
        clearHoverEffect(btnClear);
        updateHoverEffect(btnUpd);
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

    }
    public void addBtnOnAction(ActionEvent actionEvent) throws ParseException {
        if (CheckDateDuplication()) {
            lblInfo.setText("You have already entered attendance for this date!");
            lblInfo.setStyle("-fx-text-fill: red;");
            return;
        }

        String AttendID = lblETID.getText();
        String EmpTel = (String) empIdcmb.getValue();
        LocalDate date1 = date.getValue();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date1.format(formatter);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        EmpAttend empAttend = new EmpAttend(AttendID, EmpTel, dateFormat.parse(formattedDate));

        try {
            boolean isSaved = empAttendBO.addEmpAttend(empAttend);
            if(isSaved) {
                getCurrentEmpAttendID();
                setDaysCount();
                lblInfo.setText("Employee Saved Successfully!");
                lblInfo.setStyle("-fx-text-fill: green;");
                loadALlEmployeeAttendances(EmpTel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            lblInfo.setText("Employee Didn't Save");
            lblInfo.setStyle("-fx-text-fill: red;");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean CheckDateDuplication() {
        ObservableList<EmpAttendTm> items = tblEmpAttend.getItems();

        if (items.size() > 0) {
            for (EmpAttendTm item : items) {
                if (item.getDate().equals(date.getValue().toString())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        date.setValue(null);
        empIdcmb.setValue(null);
    }

    public void currentDateBtnOnAction(ActionEvent actionEvent) {
        String currentDate = LocalDate.now().toString();
        txtAttendDate.setText(currentDate);
    }

    public void employeeIDOnPressed(KeyEvent keyEvent) {
        empIdcmb.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                employeeIDOnPressed(null);
            }
        });

        String empId = (String) empIdcmb.getValue();
        loadALlEmployeeAttendances(empId);
    }

    public void cmbEmpOnAction(ActionEvent actionEvent) throws SQLException {
        /*setName();
        setDaysCount();*/

    }

    private void setDaysCount() throws SQLException, ClassNotFoundException {
        String empID = (String) empIdcmb.getValue();
        ArrayList<EmpAttendDTO> count = empAttendBO.getAllEmpAttend(empID);
        int noOfDays = count.size();
        lblDaysCount.setText(String.valueOf(noOfDays));

    }

    private void setName() {
        String id = (String) empIdcmb.getValue();

        try {
            Employee employee = (Employee) empAttendBO.searchByTel(id);

            if (employee != null) {
                lblName.setText(employee.getName());
            } else {
                lblName.setText("No employee found!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadALlEmployeeAttendances(String empId){
        /*ObservableList<EmpAttendTm> obList = FXCollections.observableArrayList();

        try{
            ArrayList<EmpAttendDTO> employeeList = empAttendBO.getAllEmpAttend(empId);

            for(EmpAttendDTO attendance : employeeList) {
                obList.add(new EmpAttendTm(attendance));
            }

            tblEmpAttend.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void filterEmpIds(KeyEvent keyEvent) {
       /* ObservableList<String> filteredList = FXCollections.observableArrayList();
        String enteredText = empIdcmb.getEditor().getText();

        try {
            List<String> idList = EmployeeRepo.getEmpID();

            for(String id : idList) {
                if(id.contains(enteredText)) {
                    filteredList.add(id);
                }
            }

            empIdcmb.setItems(filteredList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }
}
