package com.esense.topclass.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.esense.topclass.dbmodel.RandomCity;

@Controller
public class TemplateController
{
	@RequestMapping(value ="/")
    public String getUser(){
        return "home";
    }
}
