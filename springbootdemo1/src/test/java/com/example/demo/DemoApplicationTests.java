package com.example.demo;

import com.example.demo.domain.Goods;
import com.example.demo.domain.Shops;
import com.example.demo.domain.ShowingCart;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.GoodsMapper;
import com.example.demo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.CDATASection;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	void t4Time() throws ParseException {


//		String strTime="1660807830000";
//		Long longTime=Long.parseLong(strTime);
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateString = formatter.format(longTime);
//		Date newTime = formatter.parse(dateString);
//		goodsMapper.updateTime(newTime);
//
//		定时上架
//		Date date=new Date();
//		List<Goods> all = goodsMapper.findAll();
//		for (Goods g:all) {
//			if("仓库中".equals(g.getState())&&g.getTime()!=null){
//				if(g.getTime().before(date)){
//					goodsMapper.updateState(g.getGid(),g.getState());
////					System.out.println(g.getGname()+"上架");
//				}
//			}
//		}

	}
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
//		customerService.removeCarts(1,100);
//		customerService.pay(1,101,"到店购买");
		customerService.pay(1,101,"快跑配送");
//		customerService.pay(1,103,"快跑配送");


//		System.out.println();
	}

}
