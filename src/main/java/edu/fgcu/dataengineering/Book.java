package edu.fgcu.dataengineering;

public class Book {

  private String isbn;
  private String title;
  private String author;
  private String publisher;
  private String store;
  private String location;
  private int bookYear;
  private double bookPrice;

  Book(
          String isbn,
          String title,
          String author,
          String publisher,
          String store,
          String location,
          int bookYear,
          double bookPrice) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.publisher = publisher;
    this.store = store;
    this.location = location;
    this.bookYear = bookYear;
    this.bookPrice = bookPrice;
  }

  @Override
  public String toString() {
    return "Book{"
        + "isbn='"
        + isbn
        + '\''
        + ", title='"
        + title
        + '\''
        + ", author='"
        + author
        + '\''
        + ", publisher='"
        + publisher
        + '\''
        + ", store='"
        + store
        + '\''
        + ", location='"
        + location
        + '\''
        + '}';
  }

  String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getStore() {
    return store;
  }

  public void setStore(String store) {
    this.store = store;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  int getBookYear() {
    return bookYear;
  }

  public void setBookYear(int bookYear) {
    this.bookYear = bookYear;
  }

  double getBookPrice() {
    return bookPrice;
  }

  public void setBookPrice(double bookPrice) {
    this.bookPrice = bookPrice;
  }
}
