package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUserPassword(@RequestBody User user) {
        User oldUser = userService.findByUserName(user.getUserName());

        if (oldUser != null) {
            oldUser.setUserName(user.getUserName());   // not need to check empty, because userName can't be null.
            oldUser.setPassword(user.getPassword());
            userService.saveEntry(oldUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
