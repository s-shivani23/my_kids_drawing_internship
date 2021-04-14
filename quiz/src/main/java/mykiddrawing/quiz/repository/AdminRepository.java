package mykiddrawing.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mykiddrawing.quiz.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{
	
	@Query(nativeQuery = true, value = "select apitoken from admin where id=?1")
	public String getApiToken(Long userId);
	
	
}
