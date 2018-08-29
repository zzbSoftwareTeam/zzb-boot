package com.zzb.config.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice
public class ExceptionDefaultController {

	//@ExceptionHandler(Exception.class)
	public ModelAndView exceptionDefault(){
		ModelAndView view = new ModelAndView("500");
		view.addObject("message", "-----------------");//添加一个带名的model对象
		return view;
	}
}
