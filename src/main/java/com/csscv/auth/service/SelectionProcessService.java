package com.csscv.auth.service;

import java.util.List;
import java.util.Optional;

import com.csscv.auth.dto.SelectionProcessDto;
import com.csscv.auth.dto.SelectionProcessLinkDto;
import com.csscv.auth.entities.SelectionProcess;
import com.csscv.auth.entities.SelectionProcessLink;
import com.csscv.auth.entities.User;

public interface SelectionProcessService {

	List<SelectionProcessLink> getRankList(SelectionProcess sprocess);

	List<SelectionProcessLinkDto> getSPLForCurUser();

	List<SelectionProcessDto> getAllSProcessSummaryPageable(int page, int size);

	List<SelectionProcessLinkDto> getRankList(Long sprocessId);

	Optional<SelectionProcessDto> getSProcessSummary(long spid);

	boolean updateRankForSelctionProcess(Long sprocessId);

//	List<SelectionProcessDto> getAllSProcessSummaryPageableRecruiter(int page, int size);

	List<SelectionProcessDto> getAllSProcessSummaryPageableRecruiter(int page, int size, User recruiter);

	boolean createNewSelectionProcessLink(User currentUser, Long spid);

	boolean verifyCreater(Long spid, User user);

	List<SelectionProcessDto> getCandidateNotAppliedSProcessSummaryPageable(int page, int size);

}
