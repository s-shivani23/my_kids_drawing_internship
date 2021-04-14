package com.vnrits.quizz.quizz_app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})

@Entity
@Table(name = "Questions")

public class Questionsmodel  extends AuditModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int grade;
	private String category;
	private String subcategory;
	private String question;
    private String filename;
    private String tumbnailName;
	
	
    private boolean isImage =true;
    private boolean isTopicComplete=false;
    public boolean isImage() {
		return isImage;
	}

	public void setImage(boolean isImage){
		this.isImage = isImage;
	}

	public boolean isTopicComplete() {
		return isTopicComplete;
	}

	public void setTopicComplete(boolean isTopicComplete) {
		this.isTopicComplete = isTopicComplete;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question1) {
		question = question1;
	}


	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getTumbnailName() {
		return tumbnailName;
	}

	public void setTumbnailName(String tumbnailName) {
		this.tumbnailName = tumbnailName;
	}	
}
