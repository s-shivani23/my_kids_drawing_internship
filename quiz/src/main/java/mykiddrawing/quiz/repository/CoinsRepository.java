package mykiddrawing.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mykiddrawing.quiz.model.Coins;




public interface CoinsRepository extends JpaRepository<Coins,Long>{

	public List<Coins> findByUserId(Long userId);
	

}
