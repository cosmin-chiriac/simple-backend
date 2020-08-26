package com.simple.simplebackend.dao;

import com.simple.simplebackend.dto.UserDTO;
import com.simple.simplebackend.enumtype.OperationTypeEnum;
import com.simple.simplebackend.exceptions.UserNotFoundException;
import com.simple.simplebackend.mail.MailHandler;
import com.simple.simplebackend.model.User;
import com.simple.simplebackend.repo.UserRepo;
import com.simple.simplebackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;


@Component
public class UserDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepo userRepo;

    @Autowired
    MailHandler mailHandler;

    /**
     * Find all Users.
     */
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    public User findById(int id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Save user.
     *
     * @param user the user
     * @return the user
     */
    public User save(User user) {
        return userRepo.save(user);
    }

    /**
     * Save user user.
     *
     * @param operationType the operation type
     * @param userDTO       the user dto
     * @return the user
     */
    public User saveUser(OperationTypeEnum operationType, UserDTO userDTO) {
        if (OperationTypeEnum.CREATE.equals(operationType)) {
            return createUser(userDTO);
        } else return updateUser(userDTO);
    }

    /**
     * Gets subscribers email list.
     *
     * @param category the category
     * @return the subscribers email list
     */
    public Iterable<String> getSubscribersEmailList(String category) {
        Iterable<String> subscribedUsers = userRepo.findSubscribersByCategory(category);
        return subscribedUsers;
    }

    private User createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        if (userDTO.getAge() != null) {
            user.setAge(userDTO.getAge());
        }
        User savedUser = userRepo.save(user);
        LOGGER.info("User registered with id: " + savedUser.getId());
        return savedUser;
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
        if (!CollectionUtils.isEmpty(userDTO.getSubscriptions())) {
            if (CollectionUtils.isEmpty(user.getSubscriptions())) {
                user.setSubscriptions(new ArrayList<>());
            }
            user.getSubscriptions().addAll(userDTO.getSubscriptions());
        }
        User savedUser = userRepo.save(user);
        LOGGER.info("User with id: " + user.getId() + " was updated");
        return savedUser;
    }
}
