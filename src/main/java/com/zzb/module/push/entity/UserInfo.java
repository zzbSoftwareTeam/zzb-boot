package com.zzb.module.push.entity;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 
 * indexName;//索引库的名称，个人建议以项目的名称命名
 * type default "";//类型，个人建议以实体的名称命名
 * shards default 5;//默认分区数
 * replicas default 1;//每个分区默认的备份数
 * refreshInterval default "1s";//刷新间隔
 * indexStoreType default "fs";//索引文件存储类型
 */
@Document(indexName="zzb_boot",type="userInfo")
public class UserInfo implements Serializable{

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private String pass;
    /**
    * type default FieldType.Auto;#自动检测属性的类型
    * index default FieldIndex.analyzed;#默认情况下分词
    * format default DateFormat.none;
    * pattern default "";
    * store default false;#默认情况下不存储原文
    * searchAnalyzer default "";#指定字段搜索时使用的分词器
    * indexAnalyzer default "";#指定字段建立索引时指定的分词器
    * ignoreFields default {};#如果某个字段需要被忽略
    * includeInParent default false;
     */
    @Field(type=FieldType.String,ignoreFields={"我","你"})
    private String token;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

    

}
