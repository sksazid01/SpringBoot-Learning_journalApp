package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.MailService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @PostMapping("create-user")
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }

    @Autowired
    MailService mailService;

    @GetMapping("send-mail")
    public void sendMail(){
        mailService.sendMail("ahasanulhaque20@gmail.com","Test Mail","Hi, Sazid");
    }
}
