package com.zzb.api.v1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzb.common.bean.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(value="消息业务接口")
@RestController
@RequestMapping("/api/v1")
public class TestApiController {
	
    @GetMapping("/test")
    @ApiOperation(value="测试接口", notes="测试接口详细描述")
    public Result show(
            @ApiParam(required=true, name="name", value="姓名") @RequestParam(name = "name") String stuName){
		return Result.success();
    }
}
