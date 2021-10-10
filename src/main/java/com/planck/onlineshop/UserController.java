package com.planck.onlineshop;
import com.planck.DAO.UserDAO;
import com.planck.Model.User;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    UserDAO userDAO = new UserDAO();

    @RequestMapping("/list")
    public ArrayList<User> listUsers(){
        return userDAO.listUsers();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user) {
        userDAO.createUser(user);
        return user;
    }

// Read = GET
    @GetMapping(value = "{userIdCard}")
    public User getUserById(@PathVariable("userIdCard") long userIdCard) {
        return userDAO.searchUser("cedula_usuario", userIdCard + "");
    }

    @GetMapping("/user/{user}/{password}")
    public String getUser(@PathVariable("user") String user, @PathVariable("password") String password) {
        return userDAO.checkCredentials(user, password);
    }


    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        userDAO.updateUser(user);
        return user;
    }

    @DeleteMapping(value = "{userIdCard}")
    public void deleteUser(@PathVariable("userIdCard") long userIdCard) {
        userDAO.deleteUser(userIdCard);
    }
}
