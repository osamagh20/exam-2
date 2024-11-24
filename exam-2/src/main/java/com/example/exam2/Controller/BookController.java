package com.example.exam2.Controller;

import com.example.exam2.Model.Book;
import com.example.exam2.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @GetMapping("/get")
    public ResponseEntity getBooks(){
        ArrayList<Book> bookList = bookService.getBooks();
        return ResponseEntity.status(200).body(bookList);
    }

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody @Valid Book book, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        bookService.addBooks(book);

        return ResponseEntity.status(200).body("Book added successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable String id){
        boolean isDeleted = bookService.removeBook(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("user deleted successfully");
        }
        return ResponseEntity.status(400).body("user not found");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable String id,@RequestBody @Valid Book book, Errors errors){
        boolean isUpdated = bookService.updateBook(id,book);
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        if(isUpdated){
            return ResponseEntity.status(200).body("user updated successfully");
        }
        return ResponseEntity.status(400).body("user not found");

    }

    @GetMapping("/get-book-by-name/{name}")
    public ResponseEntity getBookByName(@PathVariable String name){
        Book book = bookService.getBookByName(name);
        if(book == null){
            return ResponseEntity.status(400).body("book not found");
        }
        return ResponseEntity.status(200).body(book);
    }

    @GetMapping("/get-book-by-category/{category}")
    public ResponseEntity getBookByCategory(@PathVariable String category){
        ArrayList<Book> bookList = bookService.getBooksByCategory(category);
        if(bookList == null){
            return ResponseEntity.status(400).body("not have books in this category");
        }
        return ResponseEntity.status(200).body(bookList);
    }

    @GetMapping("/get-book-by-pages/{page}")
    public ResponseEntity getBookByPages(@PathVariable int page){
        ArrayList<Book> bookList = bookService.getBooksByPages(page);
        if(bookList == null){
            return ResponseEntity.status(400).body("not have books same number of pages or above");
        }
        return ResponseEntity.status(200).body(bookList);
    }


}
