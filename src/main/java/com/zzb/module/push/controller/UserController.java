package com.zzb.module.push.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zzb.module.push.entity.UserInfo;
import com.zzb.module.push.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	
	private Logger log =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Map<String,UserService> userServiceMap;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
    
	
	@RequestMapping(path = "/getUserinfo", method = RequestMethod.GET)  
	public @ResponseBody String getUserinfo(){
		UserInfo user=userServiceMap.get("usera").findByUsername("aa");
		return user.getName();
	}
	
	@GetMapping(path = "/getpage")  
	public @ResponseBody PageInfo<UserInfo> getpage1() throws JsonGenerationException, JsonMappingException, IOException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", 0);
		map.put("pageSize", 3);
		Page<UserInfo> page=userServiceMap.get("usera").findPage(map);
		return page.toPageInfo();
	}
	
	@GetMapping(path = "/getall")  
	public @ResponseBody List<UserInfo> getall() throws JsonGenerationException, JsonMappingException, IOException{
		return userServiceMap.get("usera").findAll();
	}
	
	
	@GetMapping(path = "/setall")  
	public @ResponseBody String setall() throws JsonGenerationException, JsonMappingException, IOException{
		UserInfo user = new UserInfo();
		user.setName("abc");
		user.setPass("ddddddddddd");
		user.setToken("本节我们基于一个发表文章的案例来说明SpringBoot如何elasticsearch集成。elasticsearch本身可以是一个独立的服务，也可以嵌入我们的web应用中，在本案例中，我们讲解如何将elasticsearch嵌入我们的应用中。");
		userServiceMap.get("usera").save(user);
		return "setall";
	}
	
	@GetMapping(path = "/getes")  
	public @ResponseBody String getes() throws JsonGenerationException, JsonMappingException, IOException{
		Pageable pageable = new PageRequest(0, 50);
		QueryBuilder queryBuilder=QueryBuilders.queryStringQuery("文章的案例");
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(queryBuilder)
				.withPageable(pageable)
				.build();  
		List<UserInfo> list = elasticsearchTemplate.queryForList(searchQuery, UserInfo.class);
		ObjectMapper json = new ObjectMapper();
		return json.writeValueAsString(list);
	}
}
