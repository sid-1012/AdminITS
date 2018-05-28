package com.Model;

import java.sql.Date;
import java.sql.Time;

public class Interview {

	
	private int interviewProcessID;
	private int applicantID;
	private String applicantName;
	private String interviewWith;
	private Date interviewDate;
	private Time interviewTime;
	private String jobTitle;
	private int jobApplicationID;
	private int interviewerID;
	private String department;
	private String roundName;
	private int roundNumber;
	
	public int getInterviewProcessID() {
		return interviewProcessID;
	}
	
	public void setInterviewProcessID(int interviewProcessID) {
		this.interviewProcessID = interviewProcessID;
	}
	
	public int getApplicantID() {
		return applicantID;
	}
	
	public void setApplicantID(int applicantID) {
		this.applicantID = applicantID;
	}
	
	public String getApplicantName() {
		return applicantName;
	}
	
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
	public String getInterviewWith() {
		return interviewWith;
	}
	
	public void setInterviewWith(String interviewWith) {
		this.interviewWith = interviewWith;
	}
	
	public Date getInterviewDate() {
		return interviewDate;
	}
	
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}
	
	public Time getInterviewTime() {
		return interviewTime;
	}
	
	public void setInterviewTime(Time interviewTime) {
		this.interviewTime = interviewTime;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public int getJobApplicationID() {
		return jobApplicationID;
	}
	
	public void setJobApplicationID(int jobApplicationID) {
		this.jobApplicationID = jobApplicationID;
	}
	
	public int getInterviewerID() {
		return interviewerID;
	}
	
	public void setInterviewerID(int interviewerID) {
		this.interviewerID = interviewerID;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRoundName() {
		return roundName;
	}

	public void setRoundName(String roundName) {
		this.roundName = roundName;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
}
