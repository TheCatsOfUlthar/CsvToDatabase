package edu.fgcu.dataengineering;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.lang3.ObjectUtils;
import org.sqlite.SQLiteException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  private PreparedStatement preparedStatement;

  // Authors
  private static void insertAuthorsToDatabase(AuthorParser[] authorParsers)
      throws ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    String url = "jdbc:sqlite:src/Data/BookStore.db";
    for (AuthorParser authors : authorParsers) {
      try {
        String myString =
            "INSERT INTO AUTHOR (AUTHOR_NAME, AUTHOR_EMAIL, AUTHOR_URL) " + "VALUES (?, ?, ?)";
        Connection connection = DriverManager.getConnection(url);
        PreparedStatement preparedStatement = connection.prepareStatement(myString);
        preparedStatement.setString(1, authors.getName());
        preparedStatement.setString(2, authors.getEmail());
        preparedStatement.setString(3, authors.getUrl());
        preparedStatement.execute();
      } catch (SQLException e) {
        System.out.println("Exception thrown");
      }
    }
  }

  // Books
  private static void insertBooksToDatabase(ArrayList<Book> bookArrayList)
      throws ClassNotFoundException {
    Class.forName("org.sqlite.JDBC");
    String url = "jdbc:sqlite:src/Data/BookStore.db";
    for (Book book : bookArrayList) {
      try {
        String myString =
            "INSERT INTO BOOK (ISBN, PUBLISHER_NAME, AUTHOR_NAME, BOOK_YEAR, BOOK_TITLE, BOOK_PRICE) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DriverManager.getConnection(url);
        PreparedStatement preparedStatement = connection.prepareStatement(myString);
        preparedStatement.setString(1, book.getIsbn());
        preparedStatement.setString(2, book.getPublisher());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.setString(4, String.valueOf(book.getBookYear()));
        preparedStatement.setString(5, book.getTitle());
        preparedStatement.setString(6, String.valueOf(book.getBookPrice()));
        preparedStatement.execute();
      } catch (SQLException e) {
        System.out.println("Exception thrown");
      }
    }
  }

  public static void main(String[] args) throws IOException, CsvException, ClassNotFoundException {
    ArrayList<Book> bookList = new ArrayList<>();

    CSVIterator iterator =
        new CSVIterator(new CSVReader(new FileReader("src/Data/bookstore_report2.csv")));
    for (; iterator.hasNext(); ) {
      String[] nextLine = iterator.next();
      bookList.add(
          new Book(
              nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], nextLine[5], 0, 0));
    }
    bookList.remove(0);
    insertBooksToDatabase(bookList);

    Gson gson = new Gson();
    JsonReader jsonReader = new JsonReader(new FileReader("src/Data/authors.json"));
    AuthorParser[] authorParser = gson.fromJson(jsonReader, AuthorParser[].class);

    insertAuthorsToDatabase(authorParser);
  }
}
