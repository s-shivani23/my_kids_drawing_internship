package com.vnrits.quizz.quizz_app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vnrits.quizz.quizz_app.model.Options;
@Repository
public interface OptionsRepo extends JpaRepository<Options, Long> {
	
	
//	@Query(nativeQuery = true, value = "select option1,option2,option3,option4,answer from options where question_id=?1")
	public Options findByquestionId(int question_id);
}
