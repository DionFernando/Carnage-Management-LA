package lk.carnage.carnagemanagementla.dto;

public class WomensDTO {
    private String id;
    private String category;
    private Double price;
    private int qty;
    private String season;

    public WomensDTO() {
    }

    public WomensDTO(String id, String category, Double price, int qty, String season) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.qty = qty;
        this.season = season;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSeason() {
        return season;
    }

    public String toString() {
        return "WomensDTO{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", price='" + price + '\'' +
                ", qty='" + qty + '\'' +
                ", season='" + season + '\'' +
                '}';
    }
}
