package com.simple.simplebackend.resource;

import com.simple.simplebackend.dto.UserDTO;
import com.simple.simplebackend.model.User;
import com.simple.simplebackend.repo.UserRepo;
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
    @Autowired
    UserRepo userRepo;


    @PostMapping("/register")
    public @ResponseBody
    String registerUser(@Valid @RequestBody UserDTO user) {
        return userService.registerUser(user);
    }

    @PutMapping("/update/{id}")
    public @ResponseBody
    String updateUser(@PathVariable("id") @NotNull int id, @Valid @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        return userService.updateUser(userDTO);
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUseres() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/test")
    public @ResponseBody
    Iterable<String> test() {
        return userRepo.findSubscribersByCategory("TECHNOLOGY");
    }

    @GetMapping(path = "/byid/{id}")
    public @ResponseBody
    User getByID(@PathVariable("id") @NotNull int id) {
        return userService.getById(id);
    }


}
