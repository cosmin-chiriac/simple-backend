package com.simple.simplebackend.resource;

import com.simple.simplebackend.dto.UserDTO;
import com.simple.simplebackend.model.User;
import com.simple.simplebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public @ResponseBody
    String registerUSer(@Valid @RequestBody UserDTO user) {
        return userService.registerUser(user);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUseres() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/byid/{id}")
    public @ResponseBody
    User getByID(@PathVariable("id") @NotNull int id) {
        return userService.getById(id);
    }

}
