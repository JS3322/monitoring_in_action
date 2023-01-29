package com.example.management_data;

import com.example.management_data.entity.User;
import com.example.management_data.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Rollback(false)
@SpringBootTest
public class ShardingTest {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Test
    public void insertUser() {
        //IntStream.range(0, 100).forEach(i -> userRepository.save(new User(null, "john-" + i, User.Type.values()[i % User.Type.values().length])));
        userRepository.save(new User(null, "test", User.Type.KAKAO));
    }

    @Transactional(readOnly = true)
    @Test
    public void selectUsers() {
        System.out.println(userRepository.findAll(PageRequest.of(0, 5)).getContent());
        System.out.println(userRepository.findAll(PageRequest.of(5, 5)).getContent());
    }
}
