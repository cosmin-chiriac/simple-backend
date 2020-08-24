package com.simple.simplebackend.dao;

import com.simple.simplebackend.dto.UserDTO;
import com.simple.simplebackend.enumtype.OperationTypeEnum;
import com.simple.simplebackend.exceptions.UserNotFoundException;
import com.simple.simplebackend.model.User;
import com.simple.simplebackend.repo.UserRepo;
import com.simple.simplebackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserDAO {
    @Autowired
    UserRepo userRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(int id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User save(User user) {
        return userRepo.save(user);
    }

    public User saveUser(OperationTypeEnum operationType, UserDTO userDTO) {
        if (OperationTypeEnum.CREATE.equals(operationType)) {
            return createUser(userDTO);
        } else return updateUser(userDTO);
    }


    private User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getAge() != null) {
            user.setAge(userDTO.getAge());
        }
        LOGGER.info("User registered with id: " + user.getId());
        return userRepo.save(user);
    }

    private User updateUser(UserDTO userDTO) {
        User user = findById(userDTO.getId());
        if (!StringUtils.isEmpty(userDTO.getName())) {
            user.setName(userDTO.getName());
        }
        if (!StringUtils.isEmpty(userDTO.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getAge() != null) {
            user.setAge(userDTO.getAge());
        }
        LOGGER.info("User updated with id: " + user.getId());
        return userRepo.save(user);
    }
}
