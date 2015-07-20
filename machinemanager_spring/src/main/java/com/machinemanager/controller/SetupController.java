package com.machinemanager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.machinemanager.common.SetupWriteLeasePager;
import com.machinemanager.common.SetupWriteMachinePager;
import com.machinemanager.common.ThePager;
import com.machinemanager.common.TheSetupPager;
import com.machinemanager.model.dao.Lease_Dao;
import com.machinemanager.model.dao.MachineDao;
import com.machinemanager.model.dao.SetupDao;
import com.machinemanager.model.dto.Lease;
import com.machinemanager.model.dto.Machine;
import com.machinemanager.model.dto.Setup;

@Controller
@RequestMapping(value = "machine")
public class SetupController {
	
	private SetupDao setupDao;
	@Autowired
	@Qualifier("setupDao")
	public void setSetupDao(SetupDao setupDao) {
		this.setupDao = setupDao;
	}
	
	private MachineDao machineDao;
	@Autowired
	@Qualifier("machineDao")
	public void setMachineDao(MachineDao machineDao) {
		this.machineDao = machineDao;
	}
	
	private Lease_Dao leaseDao;
	@Autowired
	@Qualifier("leaseDao")
	public void setLeaseDao(Lease_Dao leaseDao) {
		this.leaseDao = leaseDao;
	}
	
	@RequestMapping(value = "setuplist.action", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView setupList(HttpServletRequest req) {		
		
		//페이징 관련 데이터
		int pageNo = 1;//현재 페이지 번호
		int pageSize = 10;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 10;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "setuplist.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로

		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (req.getParameter("pageno") != null) {
			pageNo = Integer.parseInt(req.getParameter("pageno"));
		}
		
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (pageNo - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		//1. 데이터 조회 (DAO객체 이용해서 처리)		
		List<Setup> setups = null; 
		
		String searchType = (String)req.getParameter("searchtype");
		String searchValue = (String)req.getParameter("searchvalue");		
				
		if(searchType != null){
			Integer searchCostFirstValue = Integer.valueOf(req.getParameter("searchcostfirstvalue"));
			Integer searchCostLastValue = Integer.valueOf(req.getParameter("searchcostlastvalue"));
			String searchDateFirstValue = (String)req.getParameter("searchdatefirstvalue");
			String searchDateLastValue = (String)req.getParameter("searchdatelastvalue");
			if("".equals(searchValue)){
				if("setupcost".equals(searchType)){					
					setups = setupDao.getSetupSearchListByCost(searchType, searchCostFirstValue, searchCostLastValue, first, first + pageSize);
					dataCount = setupDao.getSetupSearchListByCostCount(searchType, searchCostFirstValue, searchCostLastValue);
				}else if("setupdate".equals(searchType)){					
					setups = setupDao.getSetupSearchListByDate(searchType, searchDateFirstValue, searchDateLastValue, first, first + pageSize);
					dataCount = setupDao.getSetupSearchListByDateCount(searchType, searchDateFirstValue, searchDateLastValue);
				}					
			}else{
				setups = setupDao.getSetupSearchList(searchType, searchValue, first, first + pageSize);
				dataCount = setupDao.getSetupSearchListCount(searchType, searchValue);
			}
		}else{
			setups = setupDao.getSetupList(first, first + pageSize);
			dataCount = setupDao.getSetupCount();
		}
		
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, url);
						
		ModelAndView mav = new ModelAndView();
		mav.setViewName("machine/setuplist");
		mav.addObject("setups", setups);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		
		return mav;
		
	}

	@RequestMapping(value = "setupwrite.action", method = RequestMethod.GET)
	public ModelAndView setupWriteForm(HttpServletRequest req) {		
		
		//페이징 관련 데이터
		int machinePageNo = 1;//현재 페이지 번호
		int machinePageSize = 5;//한 페이지에 표시할 데이터 갯수
		int machinePagerSize = 10;//번호로 표시할 페이지 갯수
		int machineDataCount = 0;//전체 데이터 갯수
		String url = "setupwrite.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (req.getParameter("pageno") != null) {
			machinePageNo = Integer.parseInt(req.getParameter("pageno"));
		}
				
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int machineFirst = (machinePageNo - 1) * machinePageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		//1. 데이터 조회 (DAO객체 이용해서 처리)
		List<Machine> machines = machineDao.getUsableMachineList(machineFirst, machineFirst + machinePageSize);		
		machineDataCount = machineDao.getUsableMachineCount();//전체 게시물 갯수 조회
		SetupWriteMachinePager machinePager = new SetupWriteMachinePager(machineDataCount, machinePageNo, machinePageSize, machinePagerSize, url);
				
		int leasePageNo = 1;
		int leasePageSize = 5;
		int leasePagerSize = 10;
		int leaseDataCount = 0;
				
		if (req.getParameter("pageno") != null) {
			leasePageNo = Integer.parseInt(req.getParameter("pageno"));
		}
		
		int leaseFirst = (leasePageNo - 1) * leasePageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		List<Lease> leases = leaseDao.getUsableLeaseList(leaseFirst, leaseFirst + leasePageSize);		
		leaseDataCount = leaseDao.getUsableLeaseCount();
		SetupWriteLeasePager leasePager = new SetupWriteLeasePager(leaseDataCount, leasePageNo, leasePageSize, leasePagerSize, url);
					
		ModelAndView mav = new ModelAndView();
		mav.setViewName("machine/setupwriteform");	
		mav.addObject("machines", machines);
		mav.addObject("leases", leases);
		mav.addObject("machinepager", machinePager);
		mav.addObject("leasepager", leasePager);
		mav.addObject("machinepageno", machinePageNo);
		mav.addObject("leasepageno", leasePageNo);
		
		return mav;
		
	}
	
	@RequestMapping(value = "setupwrite.action", method = RequestMethod.POST)
	public ModelAndView setupWrite(HttpServletRequest req) {		
				
		String pageNo = req.getParameter("pageno");				
		String machineNo = req.getParameter("machineno");
		String leaseNo = req.getParameter("leaseno");
		int setupCost = Integer.parseInt(req.getParameter("setupcost"));
		String setupStatus = req.getParameter("setupstatus");	
		
		Setup setup= new Setup();
		
		setup.setMachineNo(Integer.parseInt(machineNo));
		setup.setLeaseNo(Integer.parseInt(leaseNo));
		setup.setSetupCost(setupCost);
		setup.setSetupStatus(setupStatus);		
				
		int isExistMachine = setupDao.isExistMachineCount(setup);
		
		if(isExistMachine == 0){
			setupDao.insertSetup(setup);			
		}else{
			setupDao.updateReSetup(setup);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/machine/setuplist.action?pageno=" + pageNo);		
				
		return mav;
		
	}
	
	@RequestMapping(value = "setupview.action", method = RequestMethod.GET)
	public ModelAndView setupView(@RequestParam("setupno") String clientSetupNo) {	
		
		ModelAndView mav = new ModelAndView();
		
		String setupNo = clientSetupNo;
		if (setupNo  == null || setupNo.length() == 0) {
			mav.setViewName("redirect:/machine/setuplist.action");
			return mav;
		}
		
		Setup setup = setupDao.getSetupBySetupNo(Integer.parseInt(setupNo));
		if (setup == null) {
			mav.setViewName("redirect:/machine/setuplist.action");
			return mav;
		}
		
		mav.addObject("setup", setup);
		mav.setViewName("machine/setupview");		
		
		return mav;
		
	}
	
	@RequestMapping(value = "setupupdate.action", method = RequestMethod.GET)
	public ModelAndView setupUpdateForm(
			@RequestParam("setupno") String clientSetupNo,
			@RequestParam("pageno") String clientPageNo) {	
		
		ModelAndView mav = new ModelAndView();
		
		String setupNo = clientSetupNo;
		if (setupNo == null || setupNo.length() == 0) {
			mav.setViewName("redirect:/machine/setuplist.action");
			return mav;
		}
				
		Setup setup = setupDao.getSetupBySetupNo(Integer.parseInt(setupNo));
		if (setup == null) {
			mav.setViewName("redirect:/machine/setuplist.action");
			return mav;
		}
		
		String pageNo = "1";
		if (clientPageNo != null) {
			pageNo = clientPageNo;
		}
		
		//페이징 관련 데이터
		int machinePageNo = 1;//현재 페이지 번호
		int machinePageSize = 5;//한 페이지에 표시할 데이터 갯수
		int machinePagerSize = 10;//번호로 표시할 페이지 갯수
		int machineDataCount = 0;//전체 데이터 갯수
		String url = "setupwrite.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (pageNo != null) {
			machinePageNo = Integer.parseInt(pageNo);
		}
				
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int machineFirst = (machinePageNo - 1) * machinePageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		//1. 데이터 조회 (DAO객체 이용해서 처리)
		List<Machine> machines = machineDao.getUsableMachineList(machineFirst, machineFirst + machinePageSize);		
		machineDataCount = machineDao.getUsableMachineCount();//전체 게시물 갯수 조회
		SetupWriteMachinePager machinePager = new SetupWriteMachinePager(machineDataCount, machinePageNo, machinePageSize, machinePagerSize, url);
				
		int leasePageNo = 1;
		int leasePageSize = 5;
		int leasePagerSize = 10;
		int leaseDataCount = 0;
				
		if (pageNo != null) {
			leasePageNo = Integer.parseInt(pageNo);
		}
		
		int leaseFirst = (leasePageNo - 1) * leasePageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		List<Lease> leases = leaseDao.getUsableLeaseList(leaseFirst, leaseFirst + leasePageSize);		
		leaseDataCount = leaseDao.getUsableLeaseCount();
		SetupWriteLeasePager leasePager = new SetupWriteLeasePager(leaseDataCount, leasePageNo, leasePageSize, leasePagerSize, url);
		
		mav.addObject("machines", machines);
		mav.addObject("leases", leases);
		mav.addObject("machinepager", machinePager);
		mav.addObject("leasepager", leasePager);
		mav.addObject("machinepageno", machinePageNo);
		mav.addObject("leasepageno", leasePageNo);
		
		mav.addObject("setup", setup);
		mav.addObject("pageno", pageNo);
				
		mav.setViewName("machine/setupupdateform");
				
		return mav;
		
	}
	
	@RequestMapping(value = "setupupdate.action", method = RequestMethod.POST)
	public ModelAndView setupUpdate(		
			@ModelAttribute Setup setup,
			@RequestParam("pageno") String clientPageNo) {
		
		ModelAndView mav = new ModelAndView();
				
		String pageNo = clientPageNo;		
		
		if("회수".equals(setup.getSetupStatus())){
			setupDao.updateWithdraw(setup);			
			mav.setViewName("redirect:/machine/setuplist.action?&pageno=" + pageNo);
		}else{
			setupDao.updateSetup(setup);			
			mav.setViewName("redirect:/machine/setupview.action?setupno=" + setup.getSetupNo() + "&pageno=" + pageNo);
		}		
		
		return mav;
		
	}
	
	@RequestMapping(value = "withdrawlist.action", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView withdrawList(HttpServletRequest req) {
		
		int pageNo = 1;
		int pageSize = 10;
		int pagerSize = 10;
		int dataCount = 0;
		String url = "withdrawlist.action";
	
		if (req.getParameter("pageno") != null) {
			pageNo = Integer.parseInt(req.getParameter("pageno"));
		}

		int first = (pageNo - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		List<Setup> setups = null;
		
		String searchType = (String)req.getParameter("searchtype");
		String searchValue = (String)req.getParameter("searchvalue");
		
		if(searchType != null){
			Integer searchCostFirstValue = Integer.valueOf(req.getParameter("searchcostfirstvalue"));			
			Integer searchCostLastValue = Integer.valueOf(req.getParameter("searchcostlastvalue"));		
			String searchDateFirstValue = (String)req.getParameter("searchdatefirstvalue");
			String searchDateLastValue = (String)req.getParameter("searchdatelastvalue");
			
			if("".equals(searchValue)){
				if("setupcost".equals(searchType)){					
					setups = setupDao.getWithdrawSearchListByCost(searchType, searchCostFirstValue, searchCostLastValue, first, first + pageSize);
					dataCount = setupDao.getWithdrawSearchListByCostCount(searchType, searchCostFirstValue, searchCostLastValue);
				}else if("setupdate".equals(searchType) || "withdrawdate".equals(searchType)){					
					setups = setupDao.getWithdrawSearchListByDate(searchType, searchDateFirstValue, searchDateLastValue, first, first + pageSize);
					dataCount = setupDao.getWithdrawSearchListByDateCount(searchType, searchDateFirstValue, searchDateLastValue);
				}					
			}else{
				setups = setupDao.getWithdrawSearchList(searchType, searchValue, first, first + pageSize);
				dataCount = setupDao.getWithdrawSearchListCount(searchType, searchValue);
			}
		}else{
			setups = setupDao.getWithdrawList(first, first + pageSize);
			dataCount = setupDao.getWithdrawCount();
		}
				
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, url);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("machine/withdrawlist");
		mav.addObject("setups", setups);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		
		return mav;
		
	}
	
	@RequestMapping(value = "withdrawview.action", method = RequestMethod.GET)
	public ModelAndView withdrawView(@RequestParam("setupno") String clientSetupNo) {	
		
		ModelAndView mav = new ModelAndView();
		
		String setupNo = clientSetupNo;
		if (setupNo  == null || setupNo .length() == 0) { 
			mav.setViewName("redirect:/machine/withdrawlist.action");
			return mav;			
		}
				
		Setup setup = setupDao.getSetupBySetupNo(Integer.parseInt(setupNo));
		if (setup == null) {
			mav.setViewName("redirect:/machine/withdrawlist.action");
			return mav;			
		}		
		
		mav.addObject("setup", setup);
		mav.setViewName("machine/withdrawview");		
		
		return mav;
		
	}
	
	@RequestMapping(value = "usablemachineajax.action", method = RequestMethod.GET)
	public void usableMachineAjax(HttpServletRequest req , HttpServletResponse resp)throws IOException {		

		int pageNo = 1;//현재 페이지 번호
		int pageSize = 5;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 10;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "usablemachineajax.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로

		if (req.getParameter("pageno") != null && req.getParameter("pageno").length() != 0) {
			pageNo = Integer.parseInt(req.getParameter("pageno"));
		}
		
		int first = (pageNo - 1) * pageSize + 1;
		
		List<Machine> setups = machineDao.getUsableMachineList(first, first + pageSize);		
		dataCount = machineDao.getUsableMachineCount();
				
		SetupWriteMachinePager pager = new SetupWriteMachinePager(dataCount, pageNo, pageSize, pagerSize, url);
		
		String data = "";		
		
		if(setups != null){
			Gson gson = new Gson();
			data = gson.toJson(setups);						
			data = data.replace("]", ",");
			data += "{\"pager\":\"" + pager.toString() + "\"}]";	 
		}else{
			data = "error";
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter writer = resp.getWriter();
		
		writer.print(data);		
	}
	
	@RequestMapping(value = "usableleaseajax.action", method = RequestMethod.GET)
	public void usableLeaseAjax(HttpServletRequest req , HttpServletResponse resp)throws IOException {		

		int pageNo = 1;//현재 페이지 번호
		int pageSize = 5;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 10;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "usableleaseajax.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로

		if (req.getParameter("pageno") != null && req.getParameter("pageno").length() != 0) {
			pageNo = Integer.parseInt(req.getParameter("pageno"));
		}
		
		int first = (pageNo - 1) * pageSize + 1;
		
		List<Lease> leases = leaseDao.getUsableLeaseList(first, first + pageSize);
		dataCount = leaseDao.getUsableLeaseCount();
				
		SetupWriteLeasePager pager = new SetupWriteLeasePager(dataCount, pageNo, pageSize, pagerSize, url);
						
		String data = "";
		
		if(leases != null){
			Gson gson = new Gson();			
			data = gson.toJson(leases);			
			data = data.replace("]", ",");	
			data += "{\"pager\":\"" + pager.toString() + "\"}]";
		}else{
			data = "error";
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter writer = resp.getWriter();
		
		writer.print(data);
	}
	
}