package com.devswa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.devswa.entity.CitizenPlan;
import com.devswa.request.SearchRequest;
import com.devswa.service.ReportService;


@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/")
	public String loadIndexPage(Model model)
	{
		SearchRequest searchObj = new SearchRequest();
		
		List<String> planNames = service.getPlanNames();
		List<String> planStatuses = service.getPlanStatuses();
		
		model.addAttribute("searchObj",searchObj);
		
		model.addAttribute("planNames",planNames);
		model.addAttribute("planStatuses",planStatuses);
		
		return "index";
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute SearchRequest request,Model model)
	{
		System.out.println(request);
		List<CitizenPlan> plans = service.search(request);
		model.addAttribute("searchObj",request);
		model.addAttribute("planNames",service.getPlanNames());
		model.addAttribute("planStatuses",service.getPlanStatuses());
		model.addAttribute("plans",plans);
		return "index";
	}
	
}
