package com.virtusa.entities;

import java.sql.Date;

public class Answers {
	private int question_id;
    private int answer_id;
    private String answer_name;
    private String answer_description;
    private String status;
    private Date ans_posted_date;
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
	public String getAnswer_name() {
		return answer_name;
	}
	public void setAnswer_name(String answer_name) {
		this.answer_name = answer_name;
	}
	public String getAnswer_description() {
		return answer_description;
	}
	public void setAnswer_description(String answer_description) {
		this.answer_description = answer_description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getAns_posted_date() {
		return ans_posted_date;
	}
	public void setAns_posted_date(Date ans_posted_date) {
		this.ans_posted_date = ans_posted_date;
	}
}
