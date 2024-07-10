package lk.carnage.carnagemanagementla.dto;

import java.util.Date;

public class EmpAttendDTO {
    private String empAttend_id;
    private String emp_id;
    private Date date;

    public EmpAttendDTO() {
    }

    public EmpAttendDTO(String empAttend_id, String emp_id, Date date) {
        this.empAttend_id = empAttend_id;
        this.emp_id = emp_id;
        this.date = date;
    }

    public String getEmpAttend_id() {
        return empAttend_id;
    }
    public void setEmpAttend_id(String empAttend_id) {
        this.empAttend_id = empAttend_id;
    }
    public String getEmp_id() {
        return emp_id;
    }
    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String toString() {
        return "EmpAttend{" + "empAttend_id='" + empAttend_id + '\'' + ", emp_id='" + emp_id + '\'' + ", date=" + date + '}';
    }
}