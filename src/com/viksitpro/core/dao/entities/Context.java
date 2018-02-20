package com.viksitpro.core.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "context", schema = "public")
public class Context implements java.io.Serializable {
		
		// Fields

		private Integer Id;
		private String title;
		
		// Constructors

		/** default constructor */
		public Context() {
			
		}

		/** full constructor */
		public Context(Integer id, String title) {
			this.title = title;
		}
		
		// Property accessors
		@GenericGenerator(name = "generator", strategy = "increment")
		@Id
		@GeneratedValue(generator = "generator")

		@Column(name = "id", unique = true, nullable = false)
		
		public Integer getId() {
			return Id;
		}

		public void setId(Integer id) {
			Id = id;
		}
		
		
		@Column(name = "title", nullable = false)
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
		
		
}
