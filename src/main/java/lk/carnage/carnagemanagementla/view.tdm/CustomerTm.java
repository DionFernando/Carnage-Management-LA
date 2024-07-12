package lk.carnage.carnagemanagementla.view.tdm;

public class  CustomerTm {
    private String name;
    private int tel;
    private String address;

    public CustomerTm() {
    }
    public CustomerTm(String name, int tel, String address) {
        this.name = name;
        this.tel = tel;
        this.address = address;
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
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
