package model;

public class Book {
    private int id;
    private String title;
    private String author;
    private float price;
    private int categoryId;
    private String categoryName; // join từ bảng categories

    public Book() {
    }

    // dùng khi select join
    public Book(int id, String title, String author, float price, int categoryId, String categoryName) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // dùng khi insert
    public Book(String title, String author, float price, int categoryId) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
