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

@SpringBootTest
class ManagementDataApplicationTests {

	@Test
	void contextLoads() {

	}


}


