package com.zzb.config.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


//@Order(value=1)
@Component
public class StartServerRunner implements CommandLineRunner{

	@Override
	public void run(String... arg0) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 <<<<<<<<<<<<<");
	}

}
