package com.Model;

public class JobOpenings {

	
	private int jobOpeningID;
	private String department;
	private String jobTitle;
	private int noOfOpenings;
	
	
	public int getJobOpeningID() {
		return jobOpeningID;
	}
	
	public void setJobOpeningID(int jobOpeningID) {
		this.jobOpeningID = jobOpeningID;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public int getNoOfOpenings() {
		return noOfOpenings;
	}
	
	public void setNoOfOpenings(int noOfOpenings) {
		this.noOfOpenings = noOfOpenings;
	}
}
