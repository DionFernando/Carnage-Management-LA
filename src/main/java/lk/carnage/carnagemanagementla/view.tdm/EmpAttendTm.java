package lk.carnage.carnagemanagementla.view.tdm;

import lk.carnage.carnagemanagementla.dto.EmpAttendDTO;

public class EmpAttendTm {
    private String date;

    public EmpAttendTm() {
    }

    public EmpAttendTm(String date) {
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        return "EmpAttendTm{" + "date='" + date + '\'' + '}';
    }
}
