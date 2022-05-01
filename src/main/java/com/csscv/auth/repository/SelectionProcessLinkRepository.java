package com.csscv.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.SelectionProcessLink;

public interface SelectionProcessLinkRepository extends JpaRepository<SelectionProcessLink, Long>{

	
//	public Candidate findByCandidate(Candidate candidate);
	
	@Query("select spl from SelectionProcessLink spl where spl.candidate=?1 and spl.sprocess=?2")
	public SelectionProcessLink getSPLForCandidateAndSelectionProcess(Candidate c,SelectionProcess process);
	
	
	@Query("select s from SelectionProcessLink s where s.sprocess=?1 order by score desc")
	public List<SelectionProcessLink> getRankList(SelectionProcess sprocess);
	
	@Query("select s from SelectionProcessLink s where s.sprocess.id=?1 order by score desc")
	public List<SelectionProcessLink> getRankList(Long sprocess);
	
	@Query("select COUNT(s) from SelectionProcessLink s where s.sprocess=?1 order by score desc")
	public long getRankListCount(SelectionProcess sprocess);
	
	
	@Query("select s from SelectionProcessLink s where s.candidate=?1 ")
	public List<SelectionProcessLink> getRankListForCandidate(Candidate candidate);
	
	@Query("select s.sprocess from SelectionProcessLink s where s.id=?1 ")
	public SelectionProcess getsp(long  splid);
	
}
