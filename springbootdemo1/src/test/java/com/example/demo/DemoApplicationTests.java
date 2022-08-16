package com.example.demo;

import com.example.demo.domain.Shops;
import com.example.demo.domain.ShowingCart;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.GoodsMapper;
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
	@Autowired
	private GoodsMapper goodsMapper;

	@Test
	void contextLoads() {
	}
	@Test
	void t4GoodsMapper(){
		System.out.println(goodsMapper.findBySid(100,"已上架"));
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
//		customerService.removeCarts(1,100);
//		customerService.pay(1,101,"到店购买");
		customerService.pay(1,101,"快跑配送");
//		customerService.pay(1,103,"快跑配送");


//		System.out.println();
	}

}
