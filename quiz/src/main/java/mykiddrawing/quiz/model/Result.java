package mykiddrawing.quiz.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "results")
public class Result extends AuditModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    private String topic;


    @NotNull
    private Long score;

    @NotNull
    private Long coin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

	public Result()
	{
		
	}

	public Result(long id,String topic,Long score,Long coin,User user) {
		super();
		this.id=id;
		this.topic=topic;
		this.score=score;
		this.coin=coin;
		this.user = user;
	}

    // Getters and Setters (Omitted for brevity)
    public Long getId() {
		return id;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Long getCoin() {
		return coin;
	}

	public void setCoin(Long coin) {
		this.coin = coin;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//@Entity
//@Table(name = "Results")
//public class Result extends AuditModel {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "id")
//    private long id;
//
//    
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	@NotBlank
//    private String topic;
//
//	@NotBlank
//    private Long score;
//
//    @NotBlank
//    private Long coin;
//
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private User user;
//	
//	public Result()
//	{
//		
//	}
//
//	public Result(  String topic, int score,int coin, User user) {
//		super();
//		this.topic=topic;
//		this.score=score;
//		this.coin=coin;
//		this.user = user;
//	}
//
//    // Getters and Setters ... (Omitted for brevity)
//
//
//	public void setTopic(String topic) {
//		this.topic = topic;
//	}
//
//	public void setScore(Long score) {
//		this.score = score;
//	}
//
//	public void setCoin(Long coin) {
//		this.coin = coin;
//	}
//
//	
//
//	public String getTopic() {
//		return topic;
//	}
//
//	public Long getScore() {
//		return score;
//	}
//
//	public Long getCoin() {
//		return coin;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//}