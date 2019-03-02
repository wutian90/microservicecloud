package com.atguigu.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atguigu.springcloud.entities.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController_Consumer {
	
	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/consumer/dept/add")
	public boolean add(Dept dept) {
		return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
	}
	
	@RequestMapping("/consumer/dept/get/{id}")
	@HystrixCommand(fallbackMethod="processHystrix_Get")
	public Dept get(@PathVariable("id") Long id) {
		Dept dept = restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
		if (dept == null) {
			throw new RuntimeException();
		}
		return dept;
	}
	
	@RequestMapping("/consumer/dept/list")
	public List<Dept> list() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
	}
	
	public Dept processHystrix_Get(@PathVariable("id") Long id) {
		Dept errorObj = new Dept();
		errorObj.setDname("该id没有对应的信息");
		errorObj.setDeptno(id);
		errorObj.setDb_source("没有数据源");
		return errorObj;
	}
	
}
