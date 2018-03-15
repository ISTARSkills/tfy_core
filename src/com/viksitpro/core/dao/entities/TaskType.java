package com.viksitpro.core.dao.entities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.hibernate.annotations.GenericGenerator;

/**
 * TaskType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "task_type", schema = "public")

public class TaskType implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private String name;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private String category;
	private String workflowFile;
	private Set<Task> tasks = new HashSet<Task>(0);
	private Set<Job> jobs = new HashSet<Job>(0);

	// Constructors

	/** default constructor */
	public TaskType() {
	}

	/** full constructor */
	public TaskType(IstarUser istarUser, String name, Timestamp createdAt, Timestamp updatedAt, String category,
			String workflowFile, Set<Task> tasks, Set<Job> jobs) {
		this.istarUser = istarUser;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.category = category;
		this.workflowFile = workflowFile;
		this.tasks = tasks;
		this.jobs = jobs;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "id", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator")

	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@Column(name = "name")

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "created_at", length = 29)

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at", length = 29)

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "category")

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "workflow_file")

	public String getWorkflowFile() {
		return this.workflowFile;
	}

	public void setWorkflowFile(String workflowFile) {
		this.workflowFile = workflowFile;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taskType")

	public Set<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taskType")

	public Set<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}
	@Transient
	public PmWorkflow getWorkflow()
	{
		PmWorkflow workflow = new PmWorkflow();
		
		try {
			
			Properties properties = new Properties();
			String propertyFileName = "app.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			if (inputStream != null) {
				properties.load(inputStream);
				String workflowPath = properties.getProperty("mediaPath") + File.separator + "workflows/";
				File file = new File(workflowPath+this.getWorkflowFile()+"");  				
				JAXBContext jaxbContext = JAXBContext.newInstance(PmWorkflow.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				workflow = (PmWorkflow) jaxbUnmarshaller.unmarshal(file);
			} else {
				throw new FileNotFoundException("property file '" + propertyFileName + "' not found in the classpath");
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		
		return workflow; 
	}

}