package lk.carnage.carnagemanagementla.bo;

import lk.carnage.carnagemanagementla.bo.custom.impl.*;

public class BOFactory{
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case WOMENS:
                return new WomensBOImpl();
            case MENS:
                return new MensBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case EMPLOYEEATTENDANCE:
                return new EmpAttendBOImpl();
            default :
                return null;
        }
    }

    public enum BOTypes{
        CUSTOMER, WOMENS, MENS, EMPLOYEE, EMPLOYEEATTENDANCE
    }
}

