package lk.carnage.carnagemanagementla.entity;

public class Mens {
    private String id;
    private String category;
    private Double price;
    private int qty;
    private String season;

    public Mens() {
    }

    public Mens(String id, String category, Double price, int qty, String season) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.qty = qty;
        this.season = season;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    public String toString() {
        return "Mens{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", qty='" + qty + '\'' +
                ", season='" + season + '\'' +
                '}';
    }
}
