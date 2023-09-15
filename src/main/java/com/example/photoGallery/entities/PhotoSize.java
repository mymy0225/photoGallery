package com.example.photoGallery.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photoSize")
@Entity
public class PhotoSize {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", length = 10, nullable = false)
	private String name;
	
	@Column(name = "height", nullable = false)
	private int height;
	
	@Column(name= "width", nullable = false)
	private int width;
	
}
