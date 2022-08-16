package com.example.demo;

import com.example.demo.domain.Shops;
import com.example.demo.domain.ShowingCart;
import com.example.demo.mapper.CartMapper;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private CustomerService customerService;

	@Test
	void contextLoads() {
	}

	@Test
	void t4CartMapper(){
		System.out.println(cartMapper.findAll());
	}

	@Test
	void t4CustomerService(){
//		System.out.println(cartMapper.findAll());
//		List<Shops> shops = customerService.findShops(1);
//		for (Shops s:shops
//			 ) {
//			System.out.println(customerService.findCarts(1, s.getSid()));
//		}
		customerService.removeCarts(1,100);


//		System.out.println();
	}

}
