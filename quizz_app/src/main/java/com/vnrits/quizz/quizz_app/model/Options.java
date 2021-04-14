package com.vnrits.quizz.quizz_app.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "options")
public class Options  extends AuditModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank
    private String option1;
	@NotBlank
    private String option2;
	@NotBlank
	private String option3;
	@NotBlank
	private String option4;
	@NotBlank
	private String answer;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "question_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Questionsmodel question;
	public Options()
	{
		
	}

	public Options(int id,String option1,String option2,String option3,String option4,String answer,Questionsmodel question) {
		super();
		this.id=id;
		this.option1=option1;
		this.option2=option2;
		this.option3=option3;
		this.option4=option4;
		this.answer=answer;
		this.question=question;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Questionsmodel getQuestion() {
		return question;
	}
	public void setQuestion(Questionsmodel question) {
		this.question = question;
	}

}