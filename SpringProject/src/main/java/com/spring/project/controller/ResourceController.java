package com.spring.project.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.project.model.Resource;

/**
 * Servlet implementation class ResourceController
 */

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {
	
	@RequestMapping("/add")
	public String add(Model model){ 
		System.out.println("Invoking add()!!!");
 //To handle errors
		if(1==1){
			throw new RuntimeException("There was an error");
		}
		return "resource_add";
	}
	

 //Annotation used by Spring to handle exceptions automatically!!!	
	@ExceptionHandler(NullPointerException.class)
	public String handlerError(HttpServletRequest request){
		return "controller_error";
	}
	
	@RequestMapping("/request")
	@ResponseBody
	public String request(@RequestBody String resource){
		System.out.println(resource);
		//send out an email for request
		return "The request has been sent for approval";
	}
	
	@RequestMapping("/review")
	public String review(@ModelAttribute Resource resource){ 
		System.out.println("Invoking Review()!!!");
		return "resource_review";
	}
	
	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource, SessionStatus status){
		System.out.println("Invoking Save()!!!");
		System.out.println(resource);
		status.setComplete();
		return "redirect:/resource/add";
	}
	
	@ModelAttribute("resource")
	public Resource getResource(){
		System.out.println("Adding a new Resource to the model!!!");
		return new Resource();
	}
	
	@ModelAttribute("typeOptions")
	public List<String> getTypes(){
		return new LinkedList<>(Arrays.asList(new String[]{
				"Material", "Staff", "Technical Equipment", "Other"}));
	}
	
	@ModelAttribute("radioOptions")
	public List<String> getRadios(){
		return new LinkedList<>(Arrays.asList(new String[]{
				"Hours ", "Pieces ", "Tons" }));
	}
	
	@ModelAttribute("checkOptions")
	public List<String> getChecks(){
		return new LinkedList<>(Arrays.asList(new String[]{
				"Lead Time ", "Special Rate ", "Requires Approval "}));
	}
}