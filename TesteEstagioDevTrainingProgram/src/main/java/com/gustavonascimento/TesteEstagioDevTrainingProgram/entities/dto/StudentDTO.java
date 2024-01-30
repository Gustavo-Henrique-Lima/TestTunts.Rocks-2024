package com.gustavonascimento.TesteEstagioDevTrainingProgram.entities.dto;

import java.io.Serializable;

import com.gustavonascimento.TesteEstagioDevTrainingProgram.entities.Student;

public class StudentDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Integer absences;
	private Double partialOne;
	private Double partialTwo;
	private Double partialThree;
	private String situation;
	private Double finalApprovalGrade;

	public StudentDTO() {
	}

	public StudentDTO(Long id, String name, Integer absences, Double partialOne, Double partialTwo, Double partialThree,
			String situation, Double finalApprovalGrade) {
		this.id = id;
		this.name = name;
		this.absences = absences;
		this.partialOne = partialOne;
		this.partialTwo = partialTwo;
		this.partialThree = partialThree;
		this.situation = situation;
		this.finalApprovalGrade = finalApprovalGrade;
	}

	public StudentDTO(Student entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.absences = entity.getAbsences();
		this.partialOne = entity.getPartialOne();
		this.partialTwo = entity.getPartialTwo();
		this.partialThree = entity.getPartialThree();
		this.situation = entity.getSituation();
		this.finalApprovalGrade = entity.getFinalApprovalGrade();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAbsences() {
		return absences;
	}

	public void setAbsences(Integer absences) {
		this.absences = absences;
	}

	public Double getPartialOne() {
		return partialOne;
	}

	public void setPartialOne(Double partialOne) {
		this.partialOne = partialOne;
	}

	public Double getPartialTwo() {
		return partialTwo;
	}

	public void setPartialTwo(Double partialTwo) {
		this.partialTwo = partialTwo;
	}

	public Double getPartialThree() {
		return partialThree;
	}

	public void setPartialThree(Double partialThree) {
		this.partialThree = partialThree;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public Double getFinalApprovalGrade() {
		return finalApprovalGrade;
	}

	public void setFinalApprovalGrade(Double finalApprovalGrade) {
		this.finalApprovalGrade = finalApprovalGrade;
	}

}