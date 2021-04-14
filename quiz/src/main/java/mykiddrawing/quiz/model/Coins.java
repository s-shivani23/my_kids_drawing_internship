package mykiddrawing.quiz.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "coins")
public class Coins implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	@NotNull
	@Column(name="no_of_coins")
	private Long no_of_coins;
	
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	public Coins()
	{
		
	}

	public Coins(long id,Long no_of_coins, User user) {
		super();
		this.id =id;
		this.no_of_coins = no_of_coins;
		this.user = user;
	}

	public Long getid() {
		return id;
	}

	public void setCoins_id(Long id) {
		this.id = id;
	}

	



	public Long getNo_of_coins() {
		return no_of_coins;
	}

	public void setNo_of_coins(Long no_of_coins) {
		this.no_of_coins = no_of_coins;
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
}
