package com.example.exam2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    @NotEmpty(message = "Enter the ID")
    @Size(min = 4,max = 6,message = "Enter the ID between 4 and 6 digits")
    @Pattern(regexp = "^[0-9]+$",message = "Enter just digits")
    private String id;
    @NotEmpty(message = "Enter your name")
    @Size(min = 4,message = "Enter the name at least 4 character")
    @Pattern(regexp = "^[a-zA-Z ]+$",message = "Enter just character")
    private String name;
    @NotNull(message = "Enter the number of pages")
    @Positive
    @Min(40)
    private int number_of_pages;
    @NotNull(message = "Enter the price")
    @Positive
    private double price;
    @NotEmpty(message = "Enter the category")
    @Size(min = 4,message = "Enter the name at least 4 character")
    @Pattern(regexp = "^(Novel|academic)$",message = "Enter just character")
    private String category;
    @AssertTrue
    private boolean isAvailable;

}
