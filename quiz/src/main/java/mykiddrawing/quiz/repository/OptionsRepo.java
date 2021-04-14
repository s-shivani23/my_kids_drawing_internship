package mykiddrawing.quiz.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mykiddrawing.quiz.model.Options;
@Repository
public interface OptionsRepo extends JpaRepository<Options, Long> {
	
	
	@Query(nativeQuery = true, value = "select option1,option2,option3,option4 from options where question_id=?1")
	
	List<String> getbyquestionid(int id);
}
