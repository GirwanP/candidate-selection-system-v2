package com.csscv.auth.dto;

import java.time.Period;

import javax.persistence.*;

import org.springframework.web.bind.annotation.RequestParam;

import com.csscv.auth.entities.QualificationType;

public class QualificationEntryDto {

	Long Id;
	String name;
	QualificationType type;
	String fieldOfStudy;
	Period length;
	
	private long typeid;
	private int years;
	private int months;
	private int days;
	
	
	
	public long getTypeid() {
		return typeid;
	}
	public void setTypeid(long typeid) {
		this.typeid = typeid;
	}
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}
	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public int getYears() {
		return years;
	}
	public void setYears(int years) {
		this.years = years;
	}
	public int getMonths() {
		return months;
	}
	public void setMonths(int months) {
		this.months = months;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public QualificationType getType() {
		return type;
	}
	public void setType(QualificationType type) {
		this.type = type;
	}
	public String getFieldOfStdy() {
		return fieldOfStudy;
	}
	public void setFieldOfStdy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}
	public Period getLength() {
		return length;
	}
	public void setLength(Period length) {
		this.length = length;
	}
	
	
	
}
