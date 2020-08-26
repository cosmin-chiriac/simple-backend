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

/**
 * The type User controller.
 */
@RestController
@Validated
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;
    /**
     * The User repo.
     */
    @Autowired
    UserRepo userRepo;


    /**
     * Register user string.
     *
     * @param user the user
     * @return the string
     */
    @PostMapping("/register")
    public @ResponseBody
    String registerUser(@Valid @RequestBody UserDTO user) {
        return userService.registerUser(user);
    }

    /**
     * Update user string.
     *
     * @param id      the id
     * @param userDTO the user dto
     */
    @PutMapping("/update/{id}")
    public @ResponseBody
    String updateUser(@PathVariable("id") @NotNull int id, @Valid @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        return userService.updateUser(userDTO);
    }

    /**
     * Gets all useres.
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUseres() {
        return userService.getAllUsers();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     */
    @GetMapping(path = "/byid/{id}")
    public @ResponseBody
    User getByID(@PathVariable("id") @NotNull int id) {
        return userService.getById(id);
    }


}
