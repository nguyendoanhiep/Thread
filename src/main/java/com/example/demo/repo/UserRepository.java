package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "SELECT * FROM user limit :page,:size", nativeQuery = true)
    List<User> getUsersInRange(int page, int size);

    @Query(value = "SELECT COUNT(ID) FROM user" , nativeQuery = true)
    int getCount();
}
