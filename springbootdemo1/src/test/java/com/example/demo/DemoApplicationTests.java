package com.example.demo;

import com.example.demo.mapper.CartMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private CartMapper cartMapper;

	@Test
	void contextLoads() {
	}

	@Test
	void t4CartMapper(){
		System.out.println(cartMapper.findAll());
	}

}
