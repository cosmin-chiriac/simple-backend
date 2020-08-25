package com.simple.simplebackend.repo;

import com.simple.simplebackend.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    @Query("select a.email from User a JOIN a.subscriptions p where p= :category")
    Iterable<String> findSubscribersByCategory(String category);
}
