package com.example.photoGallery.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
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
@Table(name = "photo")
@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "image", nullable = false)
	private String image;
	
	@Column(name = "shotat", nullable = false)
	private LocalDate shotAt;
	
	@Column(name = "createdat", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdat;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "orders",
		joinColumns = @JoinColumn(name = "photo_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private Set<User> orders = new HashSet<User>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "carts",
		joinColumns = @JoinColumn(name = "photo_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private Set<User> carts = new HashSet<User>();

	
}
