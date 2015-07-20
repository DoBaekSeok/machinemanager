package com.machinemanager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.machinemanager.common.MachineSearchPager;
import com.machinemanager.common.MachineStockIOSearchByCountPager;
import com.machinemanager.common.MachineStockIOSearchByDatePager;
import com.machinemanager.common.ThePager;
import com.machinemanager.model.dao.MachineDao;
import com.machinemanager.model.dao.MachineStockDao;
import com.machinemanager.model.dto.Machine;
import com.machinemanager.model.dto.MachineStockIO;

@Controller
@RequestMapping(value = "machine")
public class MachineController {
	
	private MachineDao machineDao;
	@Autowired
	@Qualifier("machineDao")
	public void setMachineDao(MachineDao machineDao) {
		this.machineDao = machineDao;
	}
	
	private MachineStockDao machineStockDao;
	@Autowired
	@Qualifier("machineStockDao")
	public void setMachineStockDao(MachineStockDao machineStockDao) {
		this.machineStockDao = machineStockDao;
	}
	
	@RequestMapping(value = "machinelist.action", method = RequestMethod.GET)
	public ModelAndView machineList(HttpServletRequest req) {		
		
		//요청한 페이지가 검색 페이저에서 온 경우 post로 보낸다.
		if (req.getParameter("searchtype") != null) {
			ModelAndView mavPost = new ModelAndView();
			mavPost = machineListPost(req);	
			return mavPost;
		}
				
		int pageNo = 1;//현재 페이지 번호
		int pageSize = 10;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 10;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "machinelist.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		if (req.getParameter("pageno") != null) {
			pageNo = Integer.parseInt(req.getParameter("pageno"));
		}
		
		int first = (pageNo - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		List<Machine> machines = null;
								
		machines = machineDao.getMachineList(first, first + pageSize);
		dataCount = machineDao.getMachineCount();//전체 게시물 갯수 조회
						
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, url);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("machine/machinelist");
		mav.addObject("machines", machines);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		
		return mav;
	}
	
	@RequestMapping(value = "machinelist.action", method = RequestMethod.POST)
	public ModelAndView machineListPost(HttpServletRequest req) {		
		
		int pageNo = 1;//현재 페이지 번호
		int pageSize = 10;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 10;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "machinelist.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		if (req.getParameter("pageno") != null) {
			pageNo = Integer.parseInt(req.getParameter("pageno"));
		}
		
		int first = (pageNo - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		List<Machine> machines = null;
		
		String searchType = (String)req.getParameter("searchtype");
		String searchValue = (String)req.getParameter("searchvalue");
		
		if(searchValue != null){
			searchValue = searchValue.toLowerCase();
		}
		
		if("machineno".equals(searchType) || "machinemodelname".equals(searchType) || "machinetype".equals(searchType) || "machinepowerconsumption".equals(searchType)
				|| "machinecompany".equals(searchType) || "machinewidth".equals(searchType) || "machinedepth".equals(searchType) 
				|| "machineheight".equals(searchType) || "setupstatus".equals(searchType)){
			machines = machineDao.getMachineSearchList(searchType, searchValue, first, first + pageSize);
			dataCount = machineDao.getMachineSearchListCount(searchType, searchValue);				
		}		
		
		MachineSearchPager pager = new MachineSearchPager(dataCount, pageNo, pageSize, pagerSize, url, searchType, searchValue);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("machine/machinelist");
		mav.addObject("machines", machines);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		
		return mav;
	}
	
	@RequestMapping(value = "machinewrite.action", method = RequestMethod.GET)
	public ModelAndView machineWriteForm() {		
				
		ModelAndView mav = new ModelAndView();
		mav.setViewName("machine/machinewriteform");		
		
		return mav;
		
	}
	
	@RequestMapping(value = "machinewrite.action", method = RequestMethod.POST)
	public ModelAndView machineWrite(
			@ModelAttribute Machine machine,
			@RequestParam("pageno") String pageNo) {		
		
		machineDao.insertMachine(machine);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/machine/machinelist.action?pageno=" + pageNo);		
				
		return mav;
		
	}
	
	@RequestMapping(value = "machineview.action", method = RequestMethod.GET)
	public ModelAndView machineView(
			@RequestParam("machineno") String machineNo) {	
		
		ModelAndView mav = new ModelAndView();
		
		if (machineNo == null || machineNo.length() == 0) {
			mav.setViewName("redirect:/machine/machinelist.action");
			return mav;
		}				
				
		Machine machine = machineDao.getMachineByMachineNo(Integer.parseInt(machineNo));
		
		if (machine == null) {
			mav.setViewName("redirect:/machine/machinelist.action");
			return mav;
		}	
		
		machine.setSetupStatus(machineDao.getSetupStatusByMachineNo(Integer.parseInt(machineNo)));
		
		mav.addObject("machine", machine);
		mav.setViewName("machine/machineview");		
		
		return mav;
		
	}
	
	@RequestMapping(value = "machineupdate.action", method = RequestMethod.GET)
	public ModelAndView machineUpdateForm(
			@RequestParam("machineno") String machineNo,
			@RequestParam("pageno") String clientPageNo) {	
		
		ModelAndView mav = new ModelAndView();
		
		if (machineNo == null || machineNo.length() == 0) {
			mav.setViewName("redirect:/machine/machinelist.action");
			return mav;			
		}
		
		Machine machine = machineDao.getMachineByMachineNo(Integer.parseInt(machineNo));
		if (machine == null) {
			mav.setViewName("redirect:/machine/machinelist.action");
			return mav;
		}
		
		machine.setSetupStatus(machineDao.getSetupStatusByMachineNo(Integer.parseInt(machineNo)));
		
		String pageNo = "1";
		if (clientPageNo != null) {
			pageNo = clientPageNo;
		}
		
		mav.addObject("machine", machine);
		mav.addObject("pageno", pageNo);
				
		mav.setViewName("machine/machineupdateform");
				
		return mav;
		
	}
	
	@RequestMapping(value = "machineupdate.action", method = RequestMethod.POST)
	public ModelAndView machineUpdate(		
			@ModelAttribute Machine machine,
			@RequestParam("pageno") String clientPageNo) {	
		
		ModelAndView mav = new ModelAndView();
		
		String pageNo = "1";		
		if (clientPageNo != null) {
			pageNo = clientPageNo;
		}
		
		machineDao.updateMachine(machine);
		
		mav.setViewName("redirect:/machine/machineview.action?machineno=" + machine.getMachineNo() + "&pageno=" + pageNo);
		
		return mav;
		
	}
	
	@RequestMapping(value = "machinedelete.action", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView machineDelete(			
			@RequestParam("machineno") String clientMachineNo,
			@RequestParam("pageno") String clientPageNo) {
		
		ModelAndView mav = new ModelAndView();
		
		String machineNo = clientMachineNo;
		if (machineNo == null || machineNo.length() == 0) {
			mav.setViewName("redirect:/machine/machinelist.action");
			return mav;
		}
		
		machineDao.deleteMachine(Integer.parseInt(machineNo));
		
		String pageNo = "1";
		if (clientPageNo != null) 
			pageNo = clientPageNo;
		mav.setViewName("redirect:/machine/machinelist.action?pageno=" + pageNo);
		return mav;				
		
	}
	
	@RequestMapping(value = "machinestocklist.action", method = RequestMethod.GET)
	public ModelAndView machineStockList(HttpServletRequest req) {
		
		//요청한 페이지가 검색 페이저에서 온 경우 post로 보낸다.
		if (req.getParameter("searchtype") != null) {
			ModelAndView mavPost = new ModelAndView();
			mavPost = machineStockListPost(req);	
			return mavPost;
		}
		
		int pageNo = 1;//현재 페이지 번호
		int pageSize = 10;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 10;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "machinestocklist.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		if (req.getParameter("pageno") != null) {
			pageNo = Integer.parseInt(req.getParameter("pageno"));
		}
		
		int first = (pageNo - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		List<MachineStockIO> machineStockIOs = null; 
		
		machineStockIOs = machineStockDao.getMachineStockIOList(first, first + pageSize);		
		dataCount = machineStockDao.getMachineStockIOCount();
		
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, url);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("machine/machinestocklist");
		mav.addObject("machinestockios", machineStockIOs);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		
		return mav;
		
	}
	
	@RequestMapping(value = "machinestocklist.action", method = RequestMethod.POST)
	public ModelAndView machineStockListPost(HttpServletRequest req) {
		
		int pageNo = 1;//현재 페이지 번호
		int pageSize = 10;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 10;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "machinestocklist.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		if (req.getParameter("pageno") != null) {
			pageNo = Integer.parseInt(req.getParameter("pageno"));
		}
		
		int first = (pageNo - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		List<MachineStockIO> machineStockIOs = null; 
		
		String searchType = (String)req.getParameter("searchtype");
		String searchValue = (String)req.getParameter("searchvalue");	
		String searchCountFirstValue = (String)req.getParameter("searchcountfirstvalue");
		String searchCountLastValue = (String)req.getParameter("searchcountlastvalue");
		String searchDateFirstValue = (String)req.getParameter("searchdatefirstvalue");
		String searchDateLastValue = (String)req.getParameter("searchdatelastvalue");
		
		if(searchValue != null){
			searchValue = searchValue.toLowerCase();
		}			
		
		MachineSearchPager pager = null;
		MachineStockIOSearchByCountPager countPager = null;
		MachineStockIOSearchByDatePager datePager = null;
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("machine/machinestocklist");
		mav.addObject("pageno", pageNo);
		
		if("machinestockiocount".equals(searchType)){
			machineStockIOs = machineStockDao.getSearchMachineStockIOListByCount(searchType, searchCountFirstValue, searchCountLastValue, first, first + pageSize);
			dataCount = machineStockDao.getSearchMachineStockIOListByCountCount(searchType, searchCountFirstValue, searchCountLastValue);
			countPager = new MachineStockIOSearchByCountPager(dataCount, pageNo, pageSize, pagerSize, url, searchType, searchCountFirstValue, searchCountLastValue);
			mav.addObject("machinestockios", machineStockIOs);
			mav.addObject("pager", countPager);			
		}else if("machinestockiodate".equals(searchType)){
			machineStockIOs = machineStockDao.getSearchMachineStockIOListByDate(searchType, searchDateFirstValue, searchDateLastValue, first, first + pageSize);
			dataCount = machineStockDao.getSearchMachineStockIOListByDateCount(searchType, searchDateFirstValue, searchDateLastValue);
			datePager = new MachineStockIOSearchByDatePager(dataCount, pageNo, pageSize, pagerSize, url, searchType, searchDateFirstValue, searchDateLastValue);
			mav.addObject("machinestockios", machineStockIOs);
			mav.addObject("pager", datePager);			
		}else{
			machineStockIOs = machineStockDao.getSearchMachineStockIOList(searchType, searchValue, first, first + pageSize);
			dataCount = machineStockDao.getSearchMachineStockIOCount(searchType, searchValue);
			pager = new MachineSearchPager(dataCount, pageNo, pageSize, pagerSize, url, searchType, searchValue);
			mav.addObject("machinestockios", machineStockIOs);
			mav.addObject("pager", pager);
		}		
				
		return mav;
		
	}
	
}