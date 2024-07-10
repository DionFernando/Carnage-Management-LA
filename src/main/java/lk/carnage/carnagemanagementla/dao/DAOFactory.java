package lk.carnage.carnagemanagementla.dao;

import lk.carnage.carnagemanagementla.dao.custom.impl.*;

import static lk.carnage.carnagemanagementla.bo.BOFactory.BOTypes.MENS;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case WOMENS:
                return new WomensDAOImpl();
                case MENS:
                    return new MensDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case EMPLOYEEATTENDANCE:
                return new EmpAttendDAOImpl();

            default :
                return null;
        }
    }

    public enum DAOTypes{
        CUSTOMER, WOMENS, MENS, EMPLOYEE, EMPLOYEEATTENDANCE
    }
}
