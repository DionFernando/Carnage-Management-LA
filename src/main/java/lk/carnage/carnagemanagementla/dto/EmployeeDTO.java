package lk.carnage.carnagemanagementla.dto;

public class EmployeeDTO {
    String id;
    String name;
    int tel;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String id, String name, int tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}

