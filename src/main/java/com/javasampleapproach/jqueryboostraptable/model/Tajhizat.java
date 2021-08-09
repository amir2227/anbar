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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "Tajhizat")
public class Tajhizat implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(columnDefinition="nvarchar(20)")
	private String name;
	
	@Column(columnDefinition="nvarchar(30)")
	private String amvalid;
	
	@Column(columnDefinition="nvarchar(20)")
	private String serial_number; 
	
	@Column(columnDefinition="nvarchar(20)")
	private String model; 
	
	@Column(columnDefinition="nvarchar(20)")
	private String brand; 
	
	@Column(columnDefinition="nvarchar(20)")
	private String garanti; 
	
	@Column(columnDefinition="nvarchar(80)")
	private String description; 
	
	
	@OneToMany(mappedBy = "tajhizats")
	private List<User> userdeniyed; //users that can't be use this device
	
	@Column(columnDefinition="nvarchar(20)")
	private String state;	//state of devices (free || in use || damaged || available)
	
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy = "tajhizats")
	@JsonIgnore
	private List<darkhast> darkhastiha;
	
	@Column(columnDefinition="LONGBLOB")
	@JsonIgnore
	private String img;
	
	@Column(columnDefinition="nvarchar(800)")
	@JsonIgnore
	private String qrcode;
	
	public Tajhizat() {
	
	}

	public Tajhizat(Integer id, String name, String amvalid, String serial_number,String state) {
		super();
		this.id = id;
		this.name = name;
		this.amvalid = amvalid;
		this.serial_number = serial_number;
	
		this.state = state;
	}

	
	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getGaranti() {
		return garanti;
	}

	public void setGaranti(String garanti) {
		this.garanti = garanti;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmvalid() {
		return amvalid;
	}

	public void setAmvalid(String amvalid) {
		this.amvalid = amvalid;
	}

	public String getSerial_number() {
		return serial_number;
	}

	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}

	public List<User> getUserdeniyed() {
		return userdeniyed;
	}

	public void setUserdeniyed(List<User> userdeniyed) {
		this.userdeniyed = userdeniyed;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<darkhast> getDarkhastiha() {
		return darkhastiha;
	}

	public void setDarkhastiha(List<darkhast> darkhastiha) {
		this.darkhastiha = darkhastiha;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Tajhizat [id=" + id + ", name=" + name + ", amvalid=" + amvalid + ", serial_number=" + serial_number
				+ ", userdeniyed=" + userdeniyed + ", state=" + state + "]";
	}

		
}
