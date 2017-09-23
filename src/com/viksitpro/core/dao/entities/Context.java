package com.viksitpro.core.dao.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "context", schema = "public")
public class Context implements java.io.Serializable {
		
		// Fields

		private Integer Id;
		private String title;
		private Set<Course> courses = new HashSet<>();
		// Constructors

		/** default constructor */
		public Context() {
			// TODO Auto-generated constructor stub
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

		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinTable(name = "course_context_mapping", schema = "public", joinColumns = {
				@JoinColumn(name = "context_id", nullable = false, updatable = false) }, inverseJoinColumns = {
						@JoinColumn(name = "course_id", nullable = false, updatable = false) })
		public Set<Course> getCourses() {
			return courses;
		}

		public void setCourses(Set<Course> courses) {
			this.courses = courses;
		}
		
		
		
}
