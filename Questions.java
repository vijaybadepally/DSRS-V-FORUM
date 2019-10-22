package com.virtusa.entities;

import java.sql.Date;

public class Questions {
	
   private int question_id;
   private String question_name;
   private String question_description;
   private String question_status;
   private Date question_posted_date;
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_name() {
		return question_name;
	}
	public void setQuestion_name(String question_name) {
		this.question_name = question_name;
	}
	public String getQuestion_description() {
		return question_description;
	}
	public void setQuestion_description(String question_description) {
		this.question_description = question_description;
	}
	public String getQuestion_status() {
		return question_status;
	}
	public void setQuestion_status(String question_status) {
		this.question_status = question_status;
	}
	public Date getQuestion_posted_date() {
		return question_posted_date;
	}
	public void setQuestion_posted_date(Date question_posted_date) {
		this.question_posted_date = question_posted_date;
	}
}
