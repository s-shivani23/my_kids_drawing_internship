package com.vnrits.quizz.quizz_app.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vnrits.quizz.quizz_app.model.Questionsmodel;
@Repository


public interface QuestionRepository extends JpaRepository<Questionsmodel,Long>{

	
	@Query(nativeQuery=true, value = "select question from questions where subcategory=?1")
	public List<String> getQuestionByTopic(String topic);
	
	@Query(nativeQuery=true, value="select tumbnail_name from questions where question=?1 and is_image=true")
	public String getImage(String question);

	//To update the question
	@Query(nativeQuery = true,value="select id from questions where question=?1")
	public int getId(String question);
	
	@Query(nativeQuery = true,value="select * from questions where id=?1")
	public Optional<Questionsmodel> findById(int id);
	
	
	
}
