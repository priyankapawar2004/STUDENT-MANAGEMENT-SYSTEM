package com.example.demo.dto;

public class DashboardStatsDTO {
	
	private long totalStudents;
	private long totalCourses;
	private String topPerformingCourse;
	private long StudentsEnrolledThisMonth;
	
	public long getTotalStudents() {
		return totalStudents;
	}
	public void setTotalStudents(long totalStudents) {
		this.totalStudents = totalStudents;
	}
	public long getTotalCourses() {
		return totalCourses;
	}
	public void setTotalCourses(long totalCourses) {
		this.totalCourses = totalCourses;
	}
	public String getTopPerformingCourse() {
		return topPerformingCourse;
	}
	public void setTopPerformingCourse(String topPerformingCourse) {
		this.topPerformingCourse = topPerformingCourse;
	}
	public long getStudentsEnrolledThisMonth() {
		return StudentsEnrolledThisMonth;
	}
	public void setStudentsEnrolledThisMonth(long studentsEnrolledThisMonth) {
		StudentsEnrolledThisMonth = studentsEnrolledThisMonth;
	}



	
	

}
