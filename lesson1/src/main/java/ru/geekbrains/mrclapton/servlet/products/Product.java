package ru.geekbrains.mrclapton.servlet.products;


public class Product {

    private final int id;
    private final String title;
    private final float price;

    public Product(int id, String title, float price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getCost() {
        return price;
    }

    @Override
    public String toString() {
        return "Product[" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + String.format("%.2f", price) +
                ']';
    }
}
