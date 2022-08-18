package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	// TODO: 2022/8/16 待发货（用户），isdeli已发货（待取货）（商家），配送中（接单），已送达（配送），已完成（用户）

	// TODO: 2022/8/16 空页提示
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
