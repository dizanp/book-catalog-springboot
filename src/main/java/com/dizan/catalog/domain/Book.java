package com.dizan.catalog.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Book", indexes = {
		@Index(name = "uk_secure_id", columnList = "secure_id")
})
public class Book implements Serializable {
    
	private static final long serialVersionUID = 2743201915653707101L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "secure_id", nullable = false, unique = true)
	private String secureId=UUID.randomUUID().toString();
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "publisher_id", nullable = false)
	private Publisher publisher;
	
	@ManyToMany
	@JoinTable(name = "book_author", joinColumns = {
			@JoinColumn(name = "book_id", referencedColumnName = "id")},
	inverseJoinColumns = {
			@JoinColumn(name = "author_id", referencedColumnName = "id")
	})
	private List<Author> authors;
	
	
	@ManyToMany
	@JoinTable(name = "book_category", joinColumns = {
			@JoinColumn(name="book_id", referencedColumnName = "id")},
	inverseJoinColumns = {
			@JoinColumn(name="category_code", referencedColumnName = "code")
	})
	private List<Category> categories;
	
	@Column(name="deleted", columnDefinition = "boolean default false")
	private Boolean deleted = false;
}
