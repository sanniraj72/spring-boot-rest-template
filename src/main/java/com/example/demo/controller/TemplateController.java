package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Employee;

@RestController
public class TemplateController {

	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/employee/get")
	public List<Employee> getAll() {
		
		String url = "http://localhost:8090/";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);
		
		ResponseEntity<List<Employee>> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				entity, 
				new ParameterizedTypeReference<List<Employee>>() {});
		return response.getBody();
	}
	
	@RequestMapping(value = "/employee/add")
	public String saveEmployee(@RequestBody Employee employee) {
		String url = "http://localhost:8090/employee/add";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(employee, headers);
		return restTemplate.exchange(url,  HttpMethod.POST, entity, String.class).getBody();
	}
	
	@RequestMapping(value = "/employee/delete/{id}")
	public String delete(@PathVariable String id) {
		String url = "http://localhost:8090/employee/delete/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Employee> entity = new HttpEntity<Employee>(headers);
		return restTemplate.exchange(url,  HttpMethod.DELETE, entity, String.class).getBody();
	}
}
