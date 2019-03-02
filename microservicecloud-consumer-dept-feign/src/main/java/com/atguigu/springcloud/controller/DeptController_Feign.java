package com.atguigu.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptClientService;

@RestController
public class DeptController_Feign {
	
//	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@Autowired
	private DeptClientService service;
	
	@RequestMapping("/consumer/dept/add")
	public boolean add(Dept dept) {
		return this.service.add(dept);
	}
	
	@RequestMapping("/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return this.service.get(id);
	}
	
	@RequestMapping("/consumer/dept/list")
	public List<Dept> list() {
		return this.service.list();
	}
	
}
