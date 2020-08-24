package com.simple.simplebackend.service;

import com.simple.simplebackend.dao.UserDAO;
import com.simple.simplebackend.dto.UserDTO;
import com.simple.simplebackend.enumtype.OperationTypeEnum;
import com.simple.simplebackend.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    public String registerUser(UserDTO userDTO) {
        User savedUser = handleSaveUser(OperationTypeEnum.CREATE, userDTO);
        return "User saved with id " + savedUser.getId();
    }

    public Iterable<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getById(int id) {
        return userDAO.findById(id);
    }


    private User handleSaveUser(OperationTypeEnum operationType, UserDTO userDTO) {
        if (OperationTypeEnum.CREATE.equals(operationType)) {

            User user = new User();
            user.setName(userDTO.getName());
            user.setEmail(userDTO.getEmail());
            if (userDTO.getAge()!=null){
                user.setAge(userDTO.getAge());
            }
            if (!CollectionUtils.isEmpty(userDTO.getArticles())) {
                user.setArticles(new ArrayList<>());
                userDTO.getArticles().forEach(article -> user.getArticles().add(article));

                            }

            LOGGER.info("User registered");
            return userDAO.save(user);

        }
        // logic to update here
        return null;
    }
}
