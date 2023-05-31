package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        int totalRecords = userRepository.getCount();
        int numThreads = 20;

        int size = totalRecords / numThreads;

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            final int page = i;
            Thread thread = new Thread(() -> {
                List<User> data = userRepository.findAll(PageRequest.of(page, size)).getContent();
                synchronized (users) {
                    users.addAll(data);
                }
            });
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            try {
                thread.join(); // Đợi mỗi luồng con hoàn thành
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    @Override
    public void save() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            String letters = "abcdefghijklmnopqrstuvwxyz";
            int length = 8;

            StringBuilder randomString = new StringBuilder();

            Random random = new Random();
            for (int y = 0; y < length; y++) {
                int index = random.nextInt(letters.length());
                char randomChar = letters.charAt(index);
                randomString.append(randomChar);
            }

            String result = randomString.toString();
            User user = new User();
            user.setName(result);
            users.add(user);
        }
        userRepository.saveAll(users);
    }
}
