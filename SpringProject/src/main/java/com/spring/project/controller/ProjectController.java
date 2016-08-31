package com.spring.project.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.spring.project.model.Project;
import com.spring.project.services.ProjectService;
import com.spring.project.validators.ProjectValidator;

@Controller
@RequestMapping("/project")
public class ProjectController {
	

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="/{projectId}")
	public String findProject(Model model, @PathVariable("projectId") Long projectId){
		model.addAttribute("project", this.projectService.find(projectId));
		return "project";
	}
	
	@RequestMapping(value="/find")
	public String find(Model model){
		model.addAttribute("projects", this.projectService.findAll());
		return "projects";
	}
	
	@SuppressWarnings("serial")
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addProject(Model model){
		
		model.addAttribute("types", new ArrayList<String>(){

		{
			add("");
			add("Single Year");
			add("Multi Year");
		}});
		
		model.addAttribute("project", new Project());
		return "project_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String saveProject(@Valid @ModelAttribute Project project, Errors errors){
		
		if(!errors.hasErrors()){
			System.out.println("The project validated!!");
		}else{
			System.out.println("The project didn't Validate!!");
			return "project_add";
		}
		
		System.out.println("Invoking saveProject");
		System.out.println(project);
		return "project_add";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new ProjectValidator());
	}
	
//	@RequestMapping(value="/add", method=RequestMethod.GET)
//	public String addProject(HttpSession session){
//		session.setAttribute("token", "001");
//		System.out.println("Invoking addProject");
//		return "project_add"; 
//	}
	
//	@RequestMapping(value="/add", method=RequestMethod.POST,params={"type=multi"})
//	public String saveMultiYearProject(){
//		System.out.println("Invoking saveMultiYearProject");
//		return "project_add";
//	}
//	
//	@RequestMapping(value="/add", method=RequestMethod.POST,params={"type=multi","special"})
//	public String saveSpecialProject(){
//		System.out.println("Invoking saveSpecialProject");
//		return "project_add";
//	}
}