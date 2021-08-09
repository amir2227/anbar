package com.javasampleapproach.jqueryboostraptable.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "darkhastha")
public class darkhast implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="nvarchar(20)")
	private String namedarkhastdahande;
	
	
	@Column(columnDefinition="nvarchar(50)")
	private String tarikh;
	
	@Column(columnDefinition="nvarchar(50)")
	private String durationaz;
	
	@Column(columnDefinition="nvarchar(50)")
	private String durationta;
	
	@Column(columnDefinition="nvarchar(50)")
	private String tarikhtahvil;
	
	@Column(columnDefinition="nvarchar(50)")
	private String saatd;
	
	@Column(columnDefinition="nvarchar(50)")
	private String saatt;
	
	
	@Column(columnDefinition="smallint")
	private Integer laghv;
	
	@Column(columnDefinition="smallint")
	private Integer monghazi;
	
	
	@Column(columnDefinition="LONGBLOB")
	@JsonIgnore
	private String emza;
	
	
	@Column(columnDefinition="LONGBLOB")
	@JsonIgnore
	private String taeedemza;
	 
	@Column(columnDefinition="LONGBLOB")
	@JsonIgnore
	private String anbaremza;
	
	@Column(columnDefinition="nvarchar(20)")
	private String darkhastdahandeid;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Tajhizat> tajhizats;
	
	@Column(columnDefinition="nvarchar(50)")
	private String location;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	private List<User> users;
	
	public darkhast() {
		
	}

	public darkhast(Long id, String namedarkhastdahande, String tarikh, String saatD, String saatT, String location,
			 Integer laghv) {
		super();
		this.id = id;
		this.namedarkhastdahande = namedarkhastdahande;
		this.tarikh = tarikh;
		this.saatd = saatD;
		this.saatt = saatT;

		this.laghv = laghv;
	}
	
	public String getTarikhtahvil() {
		return tarikhtahvil;
	}

	public Integer getMonghazi() {
		return monghazi;
	}

	public String getDarkhastdahandeid() {
		return darkhastdahandeid;
	}

	public void setDarkhastdahandeid(String darkhastdahandeid) {
		this.darkhastdahandeid = darkhastdahandeid;
	}

	public void setMonghazi(Integer monghazi) {
		this.monghazi = monghazi;
	}

	public void setTarikhtahvil(String tarikhtahvil) {
		this.tarikhtahvil = tarikhtahvil;
	}

	public String getAnbaremza() {
		return anbaremza;
	}

	public void setAnbaremza(String anbaremza) {
		this.anbaremza = anbaremza;
	}

	public String getDurationaz() {
		return durationaz;
	}

	public void setDurationaz(String durationaz) {
		this.durationaz = durationaz;
	}

	public String getDurationta() {
		return durationta;
	}

	public void setDurationta(String durationta) {
		this.durationta = durationta;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamedarkhastdahande() {
		return namedarkhastdahande;
	}

	public void setNamedarkhastdahande(String namedarkhastdahande) {
		this.namedarkhastdahande = namedarkhastdahande;
	}

	public String getTarikh() {
		return tarikh;
	}

	public void setTarikh(String tarikh) {
		this.tarikh = tarikh;
	}

	public String getSaatd() {
		return saatd;
	}

	public void setSaatd(String saatD) {
		this.saatd = saatD;
	}

	public String getSaatt() {
		return saatt;
	}

	public void setSaatt(String saatT) {
		this.saatt = saatT;
	}


	public List<Tajhizat> getTajhizats() {
		return tajhizats;
	}

	public void setTajhizats(List<Tajhizat> tajhizats) {
		this.tajhizats = tajhizats;
	}

	public Integer getLaghv() {
		return laghv;
	}

	public void setLaghv(Integer laghv) {
		this.laghv = laghv;
	}

	public String getEmza() {
		return emza;
	}

	public void setEmza(String emza) {
		this.emza = emza;
	}

	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTaeedemza() {
		return taeedemza;
	}
 
	public void setTaeedemza(String taeedemza) {
		this.taeedemza = taeedemza;
	}

	@Override
	public String toString() {
		return "darkhast [id=" + id + ", namedarkhastdahande=" + namedarkhastdahande + ", tarikh=" + tarikh + ", saatD="
				+ saatd + ", saatT=" + saatt + ", laghv=" + laghv + "]";
	}

	

	

	
}
