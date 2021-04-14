package mykiddrawing.quiz.repository;




import mykiddrawing.quiz.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
	public List<Result> findByUserId(Long userId);
	
	@Query(nativeQuery=true, value = "select u.user_name,sum(r.coin) as sum from user u left join results r on u.id=r.user_id group by user_id order by sum desc")
	public List<String> getbyname();
	@Query(nativeQuery=true, value = "select * from results where user_id=?1")
	public List<String> getId(Long userId);
}