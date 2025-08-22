package com.SmartContactManager.entities;




import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;

	private String name;
	private String email;
	private String description;
	private String phnno;
	private String work;
	private String role;
	private boolean enabled;
	private String nickname;
	private String image;

	@ManyToOne
	@JsonIgnore
	private User user;

	// Getters and Setters
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhnno() {
		return phnno;
	}
	public void setPhnno(String phnno) {
		this.phnno = phnno;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Contact(int cId, String name, String email, String description, String phnno, String work, String role,
			boolean enabled, String nickname, String image, User user) {
		super();
		this.cId = cId;
		this.name = name;
		this.email = email;
		this.description = description;
		this.phnno = phnno;
		this.work = work;
		this.role = role;
		this.enabled = enabled;
		this.nickname = nickname;
		this.image = image;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Contact [cId=" + cId + ", name=" + name + ", email=" + email + ", description=" + description
				+ ", phnno=" + phnno + ", work=" + work + ", role=" + role + ", enabled=" + enabled + ", nickname="
				+ nickname + ", image=" + image + ", user=" + user + "]";
	}

	// Constructor
	

	public Contact() {
		super();
	}
}