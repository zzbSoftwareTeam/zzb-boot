package com.zzb.module.push.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzb.module.push.dao.UserInfoDao;
import com.zzb.module.push.entity.UserInfo;
import com.zzb.module.push.service.UserService;

@Service("usera")
public class UseraServiceImpl implements UserService{

	@Autowired
    private UserInfoDao userInfoDao;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@Override
	public UserInfo findByUsername(String name){
		return userInfoDao.findByUsername(name);
	}

	@Override
	public List<UserInfo> findAll() {
		return userInfoDao.findAll();
	}

	@Override
	public Page<UserInfo> findPage(Map<String,Object> map) {
		PageHelper.startPage((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
		return userInfoDao.findPage();
	}
	
	@Override
	public void save(UserInfo user) {
		userInfoDao.save(user);
		
		IndexQuery indexQuery = new IndexQueryBuilder()
				.withId(user.getId().toString())
				.withObject(user)
				.withIndexName("zzb_boot")
				.withType("userInfo")
				.build();  
        elasticsearchTemplate.index(indexQuery);  
	}
}
