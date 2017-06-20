package com.viksitpro.core.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * ProfessionalProfile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "professional_profile", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "user_id"))

public class ProfessionalProfile implements java.io.Serializable {

	// Fields

	private Integer id;
	private IstarUser istarUser;
	private Integer yop10;
	private Float marks10;
	private Long yop12;
	private Float marks12;
	private Boolean hasUnderGraduation;
	private String underGraduationSpecializationName;
	private Float underGradutionMarks;
	private Boolean hasPostGraduation;
	private String postGraduationSpecializationName;
	private Float postGradutionMarks;
	private Boolean isStudyingFurtherAfterDegree;
	private String jobSector;
	private String preferredLocation;
	private String companyName;
	private String position;
	private String duration;
	private String description;
	private String interestedInTypeOfCourse;
	private String areaOfInterest;
	private String marksheet10;
	private String marksheet12;
	private String underGraduateDegreeName;
	private String pgDegreeName;
	private String resumeUrl;
	private Integer underGraduationYear;
	private Integer postGraduationYear;
	private String underGraduationCollege;
	private String postGraduationCollege;
	private String expereinceInYears;
	private String experienceInMonths;
	// Constructors

	/** default constructor */
	public ProfessionalProfile() {
	}

	/** full constructor */
	public ProfessionalProfile(IstarUser istarUser, Integer yop10, Float marks10, Long yop12, Float marks12,
			Boolean hasUnderGraduation, String underGraduationSpecializationName, Float underGradutionMarks,
			Boolean hasPostGraduation, String postGraduationSpecializationName, Float postGradutionMarks,
			Boolean isStudyingFurtherAfterDegree, String jobSector, String preferredLocation, String companyName,
			String position, String duration, String description, String interestedInTypeOfCourse,
			String areaOfInterest, String marksheet10, String marksheet12, String underGraduateDegreeName,
			String pgDegreeName, String resumeUrl, Integer underGraduationYear, Integer postGraduationYear,
			String underGraduationCollege, String postGraduationCollege) {
		this.istarUser = istarUser;
		this.yop10 = yop10;
		this.marks10 = marks10;
		this.yop12 = yop12;
		this.marks12 = marks12;
		this.hasUnderGraduation = hasUnderGraduation;
		this.underGraduationSpecializationName = underGraduationSpecializationName;
		this.underGradutionMarks = underGradutionMarks;
		this.hasPostGraduation = hasPostGraduation;
		this.postGraduationSpecializationName = postGraduationSpecializationName;
		this.postGradutionMarks = postGradutionMarks;
		this.isStudyingFurtherAfterDegree = isStudyingFurtherAfterDegree;
		this.jobSector = jobSector;
		this.preferredLocation = preferredLocation;
		this.companyName = companyName;
		this.position = position;
		this.duration = duration;
		this.description = description;
		this.interestedInTypeOfCourse = interestedInTypeOfCourse;
		this.areaOfInterest = areaOfInterest;
		this.marksheet10 = marksheet10;
		this.marksheet12 = marksheet12;
		this.underGraduateDegreeName = underGraduateDegreeName;
		this.pgDegreeName = pgDegreeName;
		this.resumeUrl = resumeUrl;
		this.underGraduationYear = underGraduationYear;
		this.postGraduationYear = postGraduationYear;
		this.underGraduationCollege = underGraduationCollege;
		this.postGraduationCollege = postGraduationCollege;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public IstarUser getIstarUser() {
		return this.istarUser;
	}

	public void setIstarUser(IstarUser istarUser) {
		this.istarUser = istarUser;
	}

	@Column(name = "yop_10")

	public Integer getYop10() {
		return this.yop10;
	}

	public void setYop10(Integer yop10) {
		this.yop10 = yop10;
	}

	@Column(name = "marks_10", precision = 8, scale = 8)

	public Float getMarks10() {
		return this.marks10;
	}

	public void setMarks10(Float marks10) {
		this.marks10 = marks10;
	}

	@Column(name = "yop_12")

	public Long getYop12() {
		return this.yop12;
	}

	public void setYop12(Long yop12) {
		this.yop12 = yop12;
	}

	@Column(name = "marks_12", precision = 8, scale = 8)

	public Float getMarks12() {
		return this.marks12;
	}

	public void setMarks12(Float marks12) {
		this.marks12 = marks12;
	}

	@Column(name = "has_under_graduation")

	public Boolean getHasUnderGraduation() {
		return this.hasUnderGraduation;
	}

	public void setHasUnderGraduation(Boolean hasUnderGraduation) {
		this.hasUnderGraduation = hasUnderGraduation;
	}

	@Column(name = "under_graduation_specialization_name")

	public String getUnderGraduationSpecializationName() {
		return this.underGraduationSpecializationName;
	}

	public void setUnderGraduationSpecializationName(String underGraduationSpecializationName) {
		this.underGraduationSpecializationName = underGraduationSpecializationName;
	}

	@Column(name = "under_gradution_marks", precision = 8, scale = 8)

	public Float getUnderGradutionMarks() {
		return this.underGradutionMarks;
	}

	public void setUnderGradutionMarks(Float underGradutionMarks) {
		this.underGradutionMarks = underGradutionMarks;
	}

	@Column(name = "has_post_graduation")

	public Boolean getHasPostGraduation() {
		return this.hasPostGraduation;
	}

	public void setHasPostGraduation(Boolean hasPostGraduation) {
		this.hasPostGraduation = hasPostGraduation;
	}

	@Column(name = "post_graduation_specialization_name")

	public String getPostGraduationSpecializationName() {
		return this.postGraduationSpecializationName;
	}

	public void setPostGraduationSpecializationName(String postGraduationSpecializationName) {
		this.postGraduationSpecializationName = postGraduationSpecializationName;
	}

	@Column(name = "post_gradution_marks", precision = 8, scale = 8)

	public Float getPostGradutionMarks() {
		return this.postGradutionMarks;
	}

	public void setPostGradutionMarks(Float postGradutionMarks) {
		this.postGradutionMarks = postGradutionMarks;
	}

	@Column(name = "is_studying_further_after_degree")

	public Boolean getIsStudyingFurtherAfterDegree() {
		return this.isStudyingFurtherAfterDegree;
	}

	public void setIsStudyingFurtherAfterDegree(Boolean isStudyingFurtherAfterDegree) {
		this.isStudyingFurtherAfterDegree = isStudyingFurtherAfterDegree;
	}

	@Column(name = "job_sector")

	public String getJobSector() {
		return this.jobSector;
	}

	public void setJobSector(String jobSector) {
		this.jobSector = jobSector;
	}

	@Column(name = "preferred_location")

	public String getPreferredLocation() {
		return this.preferredLocation;
	}

	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	@Column(name = "company_name")

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "position")

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "duration")

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Column(name = "description")

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "interested_in_type_of_course")

	public String getInterestedInTypeOfCourse() {
		return this.interestedInTypeOfCourse;
	}

	public void setInterestedInTypeOfCourse(String interestedInTypeOfCourse) {
		this.interestedInTypeOfCourse = interestedInTypeOfCourse;
	}

	@Column(name = "area_of_interest")

	public String getAreaOfInterest() {
		return this.areaOfInterest;
	}

	public void setAreaOfInterest(String areaOfInterest) {
		this.areaOfInterest = areaOfInterest;
	}

	@Column(name = "marksheet_10")

	public String getMarksheet10() {
		return this.marksheet10;
	}

	public void setMarksheet10(String marksheet10) {
		this.marksheet10 = marksheet10;
	}

	@Column(name = "marksheet_12")

	public String getMarksheet12() {
		return this.marksheet12;
	}

	public void setMarksheet12(String marksheet12) {
		this.marksheet12 = marksheet12;
	}

	@Column(name = "under_graduate_degree_name")

	public String getUnderGraduateDegreeName() {
		return this.underGraduateDegreeName;
	}

	public void setUnderGraduateDegreeName(String underGraduateDegreeName) {
		this.underGraduateDegreeName = underGraduateDegreeName;
	}

	@Column(name = "pg_degree_name")

	public String getPgDegreeName() {
		return this.pgDegreeName;
	}

	public void setPgDegreeName(String pgDegreeName) {
		this.pgDegreeName = pgDegreeName;
	}

	@Column(name = "resume_url")

	public String getResumeUrl() {
		return this.resumeUrl;
	}

	public void setResumeUrl(String resumeUrl) {
		this.resumeUrl = resumeUrl;
	}

	@Column(name = "under_graduation_year")

	public Integer getUnderGraduationYear() {
		return this.underGraduationYear;
	}

	public void setUnderGraduationYear(Integer underGraduationYear) {
		this.underGraduationYear = underGraduationYear;
	}

	@Column(name = "post_graduation_year")

	public Integer getPostGraduationYear() {
		return this.postGraduationYear;
	}

	public void setPostGraduationYear(Integer postGraduationYear) {
		this.postGraduationYear = postGraduationYear;
	}

	@Column(name = "under_graduation_college", length = 32)

	public String getUnderGraduationCollege() {
		return this.underGraduationCollege;
	}

	public void setUnderGraduationCollege(String underGraduationCollege) {
		this.underGraduationCollege = underGraduationCollege;
	}

	@Column(name = "post_graduation_college", length = 32)

	public String getPostGraduationCollege() {
		return this.postGraduationCollege;
	}

	public void setPostGraduationCollege(String postGraduationCollege) {
		this.postGraduationCollege = postGraduationCollege;
	}
	@Column(name="experience_in_years")
	public String getExpereinceInYears() {
		return expereinceInYears;
	}

	public void setExpereinceInYears(String expereinceInYears) {
		this.expereinceInYears = expereinceInYears;
	}

	@Column(name="experince_in_months")
	public String getExperienceInMonths() {
		return experienceInMonths;
	}

	public void setExperienceInMonths(String experienceInMonths) {
		this.experienceInMonths = experienceInMonths;
	}
}