package core;

public class Product {
    private String nameProduct;
    private int price;
    private String linkProduct;

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLinkProduct() {
        return linkProduct;
    }

    public void setLinkProduct(String linkProduct) {
        this.linkProduct = linkProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                ", linkProduct='" + linkProduct + '\'' +
                '}';
    }
}
