package lk.carnage.carnagemanagementla.dto;

public class CustomerDTO {
    private String id;
    private String name;
    private int tel;
    private String address;

    public CustomerDTO() {
    }
    public CustomerDTO(String id, String name, int tel, String address) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.address = address;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setTel(int tel) {
        this.tel = tel;
    }
    public int getTel() {
        return tel;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
