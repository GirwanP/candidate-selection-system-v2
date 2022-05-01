package com.csscv.auth.service;

import java.util.List;

import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.Skill;

public interface PointsService {

	long calculatePoints(List<QualificationEntry> qualifications, List<Skill> skills, SelectionProcess process);

}
