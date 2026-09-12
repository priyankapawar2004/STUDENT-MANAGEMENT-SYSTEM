package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.DashboardService;
import com.example.demo.service.EnrollmentService;

@Controller
public class DashboardController {
	
	private static final Logger Log = LoggerFactory.getLogger(EnrollmentController.class);
	
	
	private final EnrollmentService enrollmentService;
	private final DashboardService  dashboardService;
	
	public DashboardController(EnrollmentService enrollmentService,
			DashboardService  dashboardService) {
		this.enrollmentService = enrollmentService;
		this.dashboardService =dashboardService;
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
	    model.addAttribute("dashboardStats", dashboardService.getDashboardStats());
		model.addAttribute("students", enrollmentService.getRecentlyEnrolledStudents());
		return "dashboard";
	}

}
