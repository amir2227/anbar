package com.javasampleapproach.jqueryboostraptable.model;





import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;



@Entity
@Table(name = "USER")
public class User implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Integer id;
	
	@NotEmpty(message = "*Please provide your personal id")
	@Column(name = "PERSONAL_ID", columnDefinition="nvarchar(10)")
	private String personalId;
	
	@Column(name = "NAME", columnDefinition="nvarchar(20)")
	private String FName;
	
	@Column(name = "LAST_NAME", columnDefinition="nvarchar(20)")
	private String Lname;

	
	@Column(name = "PASSWORD" )
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@JsonIgnore
	private String pass;
	
	@Column(columnDefinition="nvarchar(2)")
	private String finger;
	
	@Column(columnDefinition="nvarchar(20)")
	private String job;
	
	@Column(columnDefinition="nvarchar(100)")
	private String notify;

	@Column(columnDefinition="nvarchar(300)")
	private String token;
	
	@Column(name = "ACTIVE")
	private int active;
	
	@Column(columnDefinition="LONGBLOB")
	@JsonIgnore
	private String emza; 
	
	@Column(columnDefinition="LONGBLOB")
	@JsonIgnore
	private String profile; 
	
	@Column(columnDefinition="nvarchar(25)")
	private String fullname;
	
	@Column(columnDefinition="nvarchar(25)")
	private String email;
	
	@Column(columnDefinition="nvarchar(25)")
	private String mobile;
	
	@Column(columnDefinition="nvarchar(100)")
	private String address;
	
	
	
	@ManyToMany(fetch=FetchType.EAGER,mappedBy = "users")
	@JsonIgnore
	private List<darkhast> darkhastiha;


	
//	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@JsonIgnore
	@ManyToMany
	private List<Role> roles;
	
	
	@ManyToMany
	@JsonIgnore
	private List<Tajhizat> tajhizats;
	
	public User() {}
	
	public User(Integer id, @NotEmpty(message = "*Please provide your personal id") String personalId,
			@NotEmpty(message = "*Please provide your first name") String fName,
			@NotEmpty(message = "*Please provide your last name") String lname,
			@Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String pass,
			String finger, String job, int active, String emza) {
		super();
		this.id = id;
		this.personalId = personalId;
		this.FName = fName;
		this.Lname = lname;
		this.pass = pass;
		this.finger = finger;
		this.job = job;
		this.active = active;
		this.emza = emza;
	}

	



	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNotify() {
		return notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}

	public List<Tajhizat> getTajhizats() {
		return tajhizats;
	}

	public void setTajhizats(List<Tajhizat> tajhizats) {
		this.tajhizats = tajhizats;
	}

	public List<darkhast> getDarkhastiha() {
		return darkhastiha;
	}

	public void setDarkhastiha(List<darkhast> darkhastiha) {
		this.darkhastiha = darkhastiha;
	}

	public Integer getId() {
		return id;
	}


	public String getFullname() {
		return fullname;
	}

	

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getEmza() {
		return emza;
	}

	public void setEmza(String emza) {
		this.emza = emza;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getPersonalId() {
		return personalId;
	}


	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}


	public String getFName() {
		return FName;
	}


	public void setFName(String fName) {
		this.FName = fName;
	}


	public String getLname() {
		return Lname;
	}


	public void setLname(String lname) {
		this.Lname = lname;
	}



	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getFinger() {
		return finger;
	}


	public void setFinger(String finger) {
		this.finger = finger;
	}



	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	@JsonIgnore
	public List<Role> getRoles() {
		return roles;
	}
	@JsonIgnore
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", personalId=" + personalId + ", FName=" + FName + ", Lname=" + Lname + "]";
	}
	


	
}
