package com.example.exam2.Service;

import com.example.exam2.Model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService {
    ArrayList<Book>  books = new ArrayList<>();
    public ArrayList<Book> getBooks() {
        return books;
    }
    public void addBooks(Book book) {
        books.add(book);
    }
    public boolean removeBook(String id) {
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId().equals(id)) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateBook(String id, Book book) {
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getId().equals(id)) {
                books.set(i,book);
                return true;
            }
        }
        return false;
    }

    public Book getBookByName(String name) {
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getName().equals(name)) {
                return books.get(i);
            }
        }
        return null;
    }

    public ArrayList<Book> getBooksByCategory(String category) {
        ArrayList<Book> bookByCategory = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getCategory().equals(category)) {
                bookByCategory.add(books.get(i));
            }
        }
        if (bookByCategory.isEmpty()){
            return null;
        }
        return bookByCategory;
    }

    public ArrayList<Book> getBooksByPages(int pages) {
        ArrayList<Book> bookByPages = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getNumber_of_pages() >= pages) {
                bookByPages.add(books.get(i));

            }
        }
        if (bookByPages.isEmpty()){
            return null;
        }
        return bookByPages;
    }
}
