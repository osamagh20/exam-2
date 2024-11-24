package com.example.exam2.Service;


import com.example.exam2.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class UserService {


    private final BookService bookService;
    ArrayList<User> users = new ArrayList<>();

    public UserService(BookService bookService) {
        this.bookService = bookService;
    }


    public ArrayList<User> getUsers() {
       return users;
   }

    public void addUsers(User user) {
        users.add(user);
    }

    public boolean removeUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateUser(String id,User user ) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getUserByBalance(double balance) {
        ArrayList<User> usersByBalance = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getBalance() >= balance){
                usersByBalance.add(users.get(i));
            }
        }
        if(usersByBalance.isEmpty()){
            return null;
        }
        return usersByBalance;
    }

    public ArrayList<User> getUserByAge(int age) {
        ArrayList<User> usersByAge = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getAge() >= age){
                usersByAge.add(users.get(i));
            }
        }
        if(usersByAge.isEmpty()){
            return null;
        }
        return usersByAge;
    }

    public String changeAvailablity(String idu,String idb) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(idu) && users.get(i).getRole().equals("librarian")) {
                for (int j = 0; j < users.size(); j++) {
                    if (bookService.getBooks().get(j).getId().equals(idb)) {
                        bookService.getBooks().get(j).setAvailable(false);
                        return "true";
                    }

                    else{
                        return "book not found";
                    }
                }

            }else {
                return "You are not librarian";
            }
        }
        return "false";
    }


}
