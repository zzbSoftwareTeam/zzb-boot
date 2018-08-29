package com.zzb.module.workflow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping("/form")
public class formController {

    @GetMapping
    public String toForm(HttpServletRequest res) throws JsonProcessingException{
        return "workflow/form_ditor";
    }
    
    @PostMapping("/existField")
    public @ResponseBody String existField(String key,String fieldName){
        return "false";
    }
    
}
