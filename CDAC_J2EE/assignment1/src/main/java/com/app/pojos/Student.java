package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "students_tbl")
public class Student extends BaseEntity {
	@Column(length = 20)
	@NotBlank(message = "name must be supplied")
	private String name="abc";// built in data type : value type : Basic value type
	
	@Column(length = 20, unique = true)
	@Email(message = "invalid email format")
	private String email;
	@Transient
	private double cgpa;
	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "DoB must be in past")
	private LocalDate dob;
	// many to one bi dir association
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id") // to specify name of the FK column
	private Course selectedCourse;
	

	public Student() {
		System.out.println("in student ctor");
	}

	public Student(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Course getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(Course selectedCourse) {
		this.selectedCourse = selectedCourse;
	}
	

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", email=" + email + ", cgpa=" + cgpa + ", dob=" + dob + "]";
	}

	
}
