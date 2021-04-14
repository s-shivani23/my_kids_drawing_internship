package mykiddrawing.quiz.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mykiddrawing.quiz.model.AuditModel;



@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})

@Entity
@Table(name = "admin")
public class Admin extends AuditModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
	
	@NotNull
	@Column(name="admin_name")
	@Size(max=50)
	private String admin_name;
	
	@NotNull
	@Column(name="apitoken")
	@Size(max=50)
	private String apitoken;

	@NotNull
	@Email
	@Column(name="admin_mail",unique=true)
	@Size(max=50)
	private String admin_mail;
	
	
	public String getAdmin_mail() {
		return admin_mail;
	}


	public void setAdmin_mail(String admin_mail) {
		this.admin_mail = admin_mail;
	}


	 public Admin()
	 {
		 
	 }
	

	public Admin(String admin_name, String admin_mail) {
		super();
		this.admin_name = admin_name;
		this.admin_mail = admin_mail;
		
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getApitoken() {
		return apitoken;
	}




	public void setApitoken(String apitoken) {
		// TODO Auto-generated method stub
		this.apitoken = apitoken;
	}

}
