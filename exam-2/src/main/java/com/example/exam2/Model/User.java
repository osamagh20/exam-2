package com.example.exam2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotEmpty(message = "Enter the id")
    @Size(min = 4,max = 6,message = "Enter the id between 4 and 6 digits")
    @Pattern(regexp = "^[0-9 ]+$",message = "Enter the id with just digits")
    private String id;
    @NotEmpty(message = "Enter the name")
    @Size(min = 4,message = "Enter the name at least 4 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$",message = "Enter the name with just characters")
    private String name;
    @NotNull(message = "Enter the age")
    @Positive
    @Min(18)
    private int age;
    @NotNull(message = "Enter the balance")
    @Positive
    private double balance;
    @NotEmpty(message = "Enter the role")
    @Size(min = 4,message = "Enter the role with just characters")
    @Pattern(regexp = "^(customer|librarian)$",message = "Enter the customer or librarian")
    private String role;

}
