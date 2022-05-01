package com.csscv.auth.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.csscv.auth.dto.SelectionProcessDto;
import com.csscv.auth.dto.SelectionProcessLinkDto;
import com.csscv.auth.entities.Candidate;
import com.csscv.auth.entities.QualificationEntry;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.SelectionProcessLink;
import com.csscv.auth.entities.User;
import com.csscv.auth.repository.CandidateRepository;
import com.csscv.auth.repository.QualificationEntryRepository;
import com.csscv.auth.repository.SelectionProcessLinkRepository;
import com.csscv.auth.repository.SelectionProcessRepository;
import com.csscv.auth.service.CandidateService;
import com.csscv.auth.service.PointsService;
import com.csscv.auth.service.SecurityService;
import com.csscv.auth.service.SelectionProcessService;

@Service
public class SelectionProcessServiceImpl implements SelectionProcessService {
	
	
	@Autowired
	CandidateRepository candidateRepository;
	@Autowired
	SelectionProcessRepository selectionProcessRepository;
	@Autowired
	SelectionProcessLinkRepository selectionProcessLinkRepository;
	@Autowired
	QualificationEntryRepository qualificationEntryRepository;
	
	@Autowired
	PointsService pointsService; 
	
	@Autowired
	SecurityService securityService;
	
	
	
	
	@Override
	public List<SelectionProcessLink> getRankList(SelectionProcess sprocess){
		
		List<SelectionProcessLink> list=selectionProcessLinkRepository.getRankList(sprocess);
		
		
		List<SelectionProcessLink> li=
		list.stream().map(spl->{
			spl.setRank(list.indexOf(spl)+1);
			return spl;
		} ).collect(Collectors.toList());

		selectionProcessLinkRepository.saveAll(li);
		
		return li;
	}
	@Override
	public List<SelectionProcessLinkDto> getRankList(Long sprocessId){
		
		List<SelectionProcessLink> list=selectionProcessLinkRepository.getRankList(sprocessId);
		
		
		List<SelectionProcessLink> li=
		list.stream().map(spl->{
			spl.setRank(list.indexOf(spl)+1);
			return spl;
		} ).collect(Collectors.toList());

		li=selectionProcessLinkRepository.saveAll(li);
		
		List<SelectionProcessLinkDto> lidto=li.stream().map((spl)->{
			SelectionProcessLinkDto dto=new SelectionProcessLinkDto();
			
			dto.setCandidate(spl.getCandidate());
			dto.setId(spl.getId());
			dto.setRank(spl.getRank());
			dto.setScore(spl.getScore());
			
			
			SelectionProcess sp=selectionProcessLinkRepository.getsp(spl.getId());
			dto.setSprocess(sp);
			dto.setSpName(sp.getName());

			
			long splcount=selectionProcessLinkRepository.getRankListCount(sp);

			dto.setTotalCandidateCount(splcount);
			
			
			
			return dto;
		}).collect(Collectors.toList());
		
		
		return lidto;
	}
	
	@Override
	public boolean updateRankForSelctionProcess(Long sprocessId) {
		List<SelectionProcessLink> list=selectionProcessLinkRepository.getRankList(sprocessId);
		
		
		List<SelectionProcessLink> li=
		list.stream().map(spl->{
			spl.setRank(list.indexOf(spl)+1);
			return spl;
		} ).collect(Collectors.toList());

		li=selectionProcessLinkRepository.saveAll(li);
		
		
		return true;
	}
	
	@Override
	public List<SelectionProcessLinkDto> getSPLForCurUser(){
		Candidate cs=candidateRepository.findByUser(securityService.getLoggedInUser());

		
		List<SelectionProcessLink> list=selectionProcessLinkRepository.getRankListForCandidate(cs);
		
		List<SelectionProcessLinkDto> listd=new ArrayList<>();

		listd=list.stream().map((m)->{
			long splcount=selectionProcessLinkRepository.getRankListCount(m.getSprocess());
			
			
			
			SelectionProcessLinkDto d=new SelectionProcessLinkDto();
			
			
			d.setTotalCandidateCount(splcount);
			d.setRank(m.getRank());
			d.setScore(m.getScore());
			
			d.setSpName(m.getSprocess().getName());
			
			return d;
			
		}).collect(Collectors.toList());
		
		
		
		
		return listd;
	}
	
	private SelectionProcessLinkDto convertToSelectionProcessLinkDto(SelectionProcessLink spl) {
		SelectionProcessLinkDto spldto=new SelectionProcessLinkDto();
		
		
		
		
		return spldto;
	}
	
	@Override
	public List<SelectionProcessDto	> getAllSProcessSummaryPageable(int page,int size){
		
		PageRequest pg=PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
//		Pageable page=new 
//		Sort s=new Sort();
		List<SelectionProcess> splist=selectionProcessRepository.findAll(pg).toList();
		
		
		List<SelectionProcessDto> spdto=splist.stream().map((sp)->{
			SelectionProcessDto sd=new SelectionProcessDto();
			sd.setName(sp.getName());
			sd.setId(sp.getId());
			sd.setClosingDate(sp.getClosingDate());
			sd.setOpenDate(sp.getOpenDate());
			sd.setUniqueId(sp.getUniqueId());
			
			long splcount=selectionProcessLinkRepository.getRankListCount(sp);
			
			sd.setTotalCandidateCount(splcount);
			
			return sd;
		}).collect(Collectors.toList());
		
		return spdto;
	}
	
	@Override
	public List<SelectionProcessDto	> getAllSProcessSummaryPageableRecruiter(int page,int size,User recruiter){
		
		PageRequest pg=PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
//		Pageable page=new 
//		Sort s=new Sort();
		List<SelectionProcess> splist=selectionProcessRepository.findAllByCreator(recruiter, pg).toList();
		
		
		List<SelectionProcessDto> spdto=splist.stream().map((sp)->{
			SelectionProcessDto sd=new SelectionProcessDto();
			sd.setName(sp.getName());
			sd.setId(sp.getId());
			sd.setClosingDate(sp.getClosingDate());
			sd.setOpenDate(sp.getOpenDate());
			sd.setUniqueId(sp.getUniqueId());
			
			long splcount=selectionProcessLinkRepository.getRankListCount(sp);
			
			sd.setTotalCandidateCount(splcount);
			
			return sd;
		}).collect(Collectors.toList());
		
		return spdto;
	}
	
	@Override
	public Optional<SelectionProcessDto	> getSProcessSummary(long spid){
		
//		PageRequest pg=PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
//		Pageable page=new 
//		Sort s=new Sort();
		Optional<SelectionProcess	>  spo=selectionProcessRepository.findById(spid);
		SelectionProcessDto sd=null;
		if(spo.isPresent()) {
			SelectionProcess sp=spo.get();
			sd=new SelectionProcessDto();
			
			sd.setName(sp.getName());
			sd.setId(sp.getId());
			sd.setClosingDate(sp.getClosingDate());
			sd.setOpenDate(sp.getOpenDate());
			sd.setUniqueId(sp.getUniqueId());
			long splcount=selectionProcessLinkRepository.getRankListCount(sp);
			
			sd.setTotalCandidateCount(splcount);

		}

			Optional<SelectionProcessDto>spdto=Optional.ofNullable(sd);

		return spdto;
	}
	
	
	@Autowired
	CandidateService candidateService;
	
	@Override
	public boolean createNewSelectionProcessLink(User currentUser,Long spid) {
		
		SelectionProcess sprocess=selectionProcessRepository.getOne(spid);
		Candidate c=candidateService.getCandidateForuser(currentUser);
		
		List<SelectionProcessLink> splist=c.getSubscribedSelectionProcessess();
		
		SelectionProcessLink splink=new SelectionProcessLink();
		splink.setSprocess(sprocess);
		splink.setCandidate(c);
		// points and rank calculation
		
		Long score=pointsService.calculatePoints(c.getQualifications(), c.getSkills(), sprocess);
		
		splink.setScore(score);
		
		splink=selectionProcessLinkRepository.save(splink);
		splist.add(splink);
		
		
		c.setSubscribedSelectionProcessess(splist);
		
		candidateRepository.save(c);
		
		splink=selectionProcessLinkRepository.save(splink);
		
		updateRankForSelctionProcess(spid);
		candidateService.updateCandidatePoints(c);
		
		return false;
	}
	
	/*@Override
	public List<SelectionProcessDto	> getAllSProcessSummaryPageable(int page,int size){
		
		PageRequest pg=PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
//		Pageable page=new 
//		Sort s=new Sort();
		List<SelectionProcess> splist=selectionProcessRepository.findAll(pg).toList();
		List<SelectionProcessDto> spdto=splist.stream().map((sp)->{
			SelectionProcessDto sd=new SelectionProcessDto();
			sd.setName(sp.getName());
			sd.setId(sp.getId());
			sd.setClosingDate(sp.getClosingDate());
			sd.setOpenDate(sp.getOpenDate());
			sd.setUniqueId(sp.getUniqueId());
			
			long splcount=selectionProcessLinkRepository.getRankListCount(sp);
			
			sd.setTotalCandidateCount(splcount);
			
			return sd;
		}).collect(Collectors.toList());
		
		return spdto;
	}*/
	
	
	
	public boolean addNewSelectionProcess() {
		
		return false;
	}
	
}
