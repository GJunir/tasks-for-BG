package tasks.second.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tasks.second.model.User;
import tasks.second.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class WebController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String greetings() {
        return "Oh Hi";
    }

    @RequestMapping("/all")
    public Iterable<User> allUsers() {
        return userService.showAll();
    }

    //You said that it is necessary to store the password in a secure form, rather than sending (ツ)
    @RequestMapping(value = "add/user/{firstName}/{lastName}/{birthday}/{email}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public String addUser(@PathVariable String firstName,
                              @PathVariable String lastName,
                              @PathVariable String birthday,
                              @PathVariable String email,
                              @PathVariable String password) throws NoSuchAlgorithmException {
        return " New user: " + firstName + " " + lastName + " with id " + userService.addUser(firstName, lastName, LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd.MM.uuuu")), email, password);
    }

    //localhost:8080/add/user/junir/gabidullin/22.11.1994/g.junir@gmail.com/AwwwWhereIsEncryptingBoooi
    //localhost:8080/find/user/g.junir@gmail.com
    //localhost:8080/all
    //localhost:8080/delete/user/

    @RequestMapping(value = "find/user/{email}" , method = RequestMethod.GET)
    public String findUser(@PathVariable  String email) {
        User user = userService.findUser(email);
        if (user != null) return user.getFirstName() + " " + user.getLastName();
        return "Cannot find, sorry";
    }

    @RequestMapping(value = "/delete/user/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
