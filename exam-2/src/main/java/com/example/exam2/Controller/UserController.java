package com.example.exam2.Controller;

import com.example.exam2.Model.User;
import com.example.exam2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){
        ArrayList<User> userList = userService.getUsers();
        return ResponseEntity.status(200).body(userList);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUsers(user);
        return ResponseEntity.status(200).body("user added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id,@RequestBody @Valid User user, Errors errors){
        boolean isUpdated = userService.updateUser(id,user);
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(isUpdated){
            return ResponseEntity.status(200).body("user updated successfully");
        }
        return ResponseEntity.status(400).body("id not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        boolean isDeleted = userService.removeUser(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("user deleted successfully");
        }
        return ResponseEntity.status(400).body("id not found");
    }

    @GetMapping("/get-users-by-balance/{balance}")
    public ResponseEntity getUsersByBalance(@PathVariable double balance){
        ArrayList<User> usersByBalance = userService.getUserByBalance(balance);
        if(usersByBalance == null){
            return ResponseEntity.status(400).body("no users found");
        }
        return ResponseEntity.status(200).body(usersByBalance);
    }

    @GetMapping("/get-users-by-age/{age}")
    public ResponseEntity getUsersByAge(@PathVariable int age){
        ArrayList<User> usersByAge= userService.getUserByAge(age);
        if(usersByAge == null){
            return ResponseEntity.status(400).body("no users found");
        }
        return ResponseEntity.status(200).body(usersByAge);
    }

    @PutMapping("/change-availability/{idu}/{idb}")
    public ResponseEntity changeAvailablity(@PathVariable String idu,@PathVariable String idb){
        String isAvailble = userService.changeAvailablity(idu,idb);
        if(isAvailble.equalsIgnoreCase("true")){
            return ResponseEntity.status(200).body("change availability successfully");
        }else if(isAvailble.equalsIgnoreCase("book not found")){
            return ResponseEntity.status(400).body("book not found");
        }else if(isAvailble.equalsIgnoreCase("You are not librarian")){
            return ResponseEntity.status(400).body("you are not librarian");
        }

        return ResponseEntity.status(400).body("user id not found");
    }



}
