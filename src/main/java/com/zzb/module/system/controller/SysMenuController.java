package com.zzb.module.system.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzb.common.bean.Result;

@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {
	
	private Logger LOG =  LoggerFactory.getLogger(this.getClass());
	
	@PreAuthorize("hasPermission('user', '/sysMenu/toMenuManage')")
	@GetMapping("/toMenuManage")
	public String toMenuManage(){
		return "/system/menuManage";
	}
	
	@GetMapping("/getMenu")
	public Result getMenu(){
		try {
			return Result.success("");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return Result.error();
	}
}
