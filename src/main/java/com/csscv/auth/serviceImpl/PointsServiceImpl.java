package com.csscv.auth.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.QualificationLink;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.Skill;
import com.csscv.auth.entities.SkillLink;
import com.csscv.auth.repository.QualificationLinkRepository;
import com.csscv.auth.repository.SkillLinkRepository;
import com.csscv.auth.service.PointsService;


@Service
public class PointsServiceImpl implements PointsService {
	
	@Autowired
	QualificationLinkRepository qualificationLinkRepository;
	@Autowired
	SkillLinkRepository skillLinkRepository;
	
	 
	@Override
	public long calculatePoints(List<QualificationEntry> qualifications,List<Skill> skills,SelectionProcess process) {
		long tPoints=0;
//		Set<QualificationLink> evdata=process.getEvaluationdata();
		
		List<QualificationLink> evdata2=qualificationLinkRepository.getQualificationLinkListForSProcess(process.getId());
		List<SkillLink> skillLinks=skillLinkRepository.getSkillLinkListForSProcess(process.getId());
		
		if(evdata2!=null && qualifications!=null && skills!=null && evdata2.size()>0 && qualifications.size()>0 && skills.size()>0){
		
			for(QualificationLink ql:evdata2) {
			
			if(qualifications.contains(ql.getQulification())) {
				tPoints+=ql.getPoints();
			}else {
				System.out.println("ommitting score addition as candidate do not have this qualification n");
			}// this if blok didnt work
			
			
//			for( QualificationEntry q:qualifications) {
//				if(q.getId().equals(ql.getQulification().getId())) {
//					tPoints+=ql.getPoints();
//				}
//			}
			
		}
			
			for(SkillLink ql:skillLinks) {
				
				if(skills.contains(ql.getSkill())) {
					tPoints+=ql.getPoints();
				}else {
					System.out.println("ommitting score addition as candidate do not have this skil n");
				}
				
			}
	}
		
		
		
		
		
		return tPoints;
	}
	
	public static void main(String[] args) {
		QualificationLink l1= new QualificationLink();
		QualificationLink l2= new QualificationLink();
		System.out.println(l1.equals(l2));
		
	}
	
	
}
