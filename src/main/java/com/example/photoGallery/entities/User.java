package com.example.photoGallery.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", length = 20, nullable = false)
	private String name;
	
	@Column(name = "email", length = 100, nullable = false)
	private String email;
	
	@Column(name = "password", length = 100, nullable = false)
	private String password;
	
	@Column(name = "roles", length = 20)
	private String roles;
	
	@Column(name = "enable_flag", nullable = false)
	private Boolean enable;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "orders",
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "photo_id", referencedColumnName = "id"))
	private Set<Photo> orders;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "carts",
		joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "photo_id", referencedColumnName = "id"))
	private Set<Photo> carts;
	
	@Column(name = "createdat", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdat;
	

}
