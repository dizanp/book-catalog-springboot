package com.dizan.catalog.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author", indexes = {
		@Index(name = "uk_secure_id", columnList = "secure_id")
})
//@DynamicUpdate\
@SQLDelete(sql = "UPDATE author set deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Author implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -6712367888891226315L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
	@SequenceGenerator(name = "author_generator", sequenceName = "author_id_seq")
	private Long id;
	
	@Column(name = "secure_id", nullable = false, unique = true)
	private String secureId=UUID.randomUUID().toString();
	
	@Column(name = "author_name", nullable = false, columnDefinition = "varchar(300)")
	private String name;
	
	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;	
	
	@Column(name="deleted", columnDefinition = "boolean default false")
	private Boolean deleted;
}
