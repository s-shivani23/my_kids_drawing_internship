package mykiddrawing.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mykiddrawing.quiz.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
