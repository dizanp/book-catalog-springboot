package com.dizan.catalog.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "publisher", indexes = {
		@Index(name = "uk_secure_id", columnList = "secure_id")
})
public class Publisher implements Serializable{
	
	private static final long serialVersionUID = 4394852825452861938L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_generator")
	@SequenceGenerator(name = "publisher_generator", sequenceName = "publisher_id_seq")
	private Long id;
	
	@Column(name = "secure_id", nullable = false, unique = true)
	private String secureId=UUID.randomUUID().toString();
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "company_name", nullable = false)
	private String companyName;

	@Column(name = "address")
	private String address;
	
	@OneToMany(mappedBy = "publisher")
	private List<Book> books; 
	
	@Column(name="deleted", columnDefinition = "boolean default false")
	private Boolean deleted = false;
}
