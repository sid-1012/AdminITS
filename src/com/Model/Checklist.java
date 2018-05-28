package com.Model;

import java.sql.Date;

public class Checklist {

	private int checklistID;
	private int applicantID;
	private String applicantName;
	private String checkedQualifications;
	private String checkedReferences;
	private Date startDate;
	public int getChecklistID() {
		return checklistID;
	}
	public void setChecklistID(int checklistID) {
		this.checklistID = checklistID;
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
	public String getCheckedQualifications() {
		return checkedQualifications;
	}
	public void setCheckedQualifications(String checkedQualifications) {
		this.checkedQualifications = checkedQualifications;
	}
	public String getCheckedReferences() {
		return checkedReferences;
	}
	public void setCheckedReferences(String checkedReferences) {
		this.checkedReferences = checkedReferences;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
}
