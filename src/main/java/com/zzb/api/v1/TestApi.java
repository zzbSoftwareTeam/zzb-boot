package com.zzb.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zzb.common.bean.AjaxResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(value="消息业务接口")
@RestController
@RequestMapping("/api/v1/test")
public class TestApi {
	
    @RequestMapping(value = "/show", method=RequestMethod.POST)
    @ApiOperation(value="测试接口", notes="测试接口详细描述")
    public AjaxResponse show(
            @ApiParam(required=true, name="name", value="姓名") @RequestParam(name = "name") String stuName){
    	AjaxResponse ar = new AjaxResponse();
		return ar;
    }
}
