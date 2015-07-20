package com.machinemanager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.machinemanager.common.SearchPager;
import com.machinemanager.common.ThePager;
import com.machinemanager.model.dao.LeaseDao;
import com.machinemanager.model.dto.Lease;
import com.machinemanager.model.dto.Product;


@Controller
@RequestMapping("lease")
public class LeaseController {
	
	private LeaseDao leaseDao;
	@Autowired
	@Qualifier("leaseDao")
	public void setLeaseDao(LeaseDao leaseDao) {
		this.leaseDao = leaseDao;
	}
	

	@RequestMapping(value = "leasewrite.action", method = RequestMethod.GET)
	public String leaseWriteForm() {		
					
		
		return "customer/lease/leasewriteform";
		
	}
	
	@RequestMapping(value = "leasewrite.action", method = RequestMethod.POST)
	public String leaseWrite(@ModelAttribute Lease lease){
		
		leaseDao.insertLease(lease);
		
		return "redirect:/lease/leaselist.action";
	
	}
	
	@RequestMapping(value = "leaseview.action", method = RequestMethod.GET)
	public ModelAndView machineView(
			@RequestParam("leaseno") String leaseNo) {	
		
		ModelAndView mav = new ModelAndView();
		
		
		if (leaseNo == null || leaseNo.length() == 0) {
			mav.setViewName("redirect:/customer/lease/leaselist.action");
			return mav;
		}				
				
		Lease lease = leaseDao.getLeaseByLeaseNo(Integer.parseInt(leaseNo));
		
		if (lease == null) {
			mav.setViewName("redirect:/customer/lease/leaselist.action");
			return mav;
		}	

		mav.addObject("lease", lease);
		mav.setViewName("customer/lease/leaseview");		
		
		return mav;
		
	}
	
	@RequestMapping(value = "leaseupdate.action", method = RequestMethod.GET)
	public String leaseedit(@RequestParam("leaseno") int leaseNo, Model model){
		
		Lease lease = leaseDao.getLeaseByLeaseNo(leaseNo);
		model.addAttribute("lease", lease);
		
	
		return ("/customer/lease/leaseeditform");
				
		
		
	}
	
	@RequestMapping(value = "leaseupdate.action", method = RequestMethod.POST)
	public String leaseUpdate(@ModelAttribute Lease lease,@RequestParam("leaseNo") int leaseNo) {	
		
		leaseDao.updateLease(lease);
		
		return ("redirect:/lease/leaseview.action?leaseno=" + leaseNo);
	}
	
	
	@RequestMapping(value = "leasedelete.action", method = {RequestMethod.GET, RequestMethod.POST})
	public String leaseDelete(			
			@RequestParam("leaseno") int leaseNo,
			@RequestParam("pageno") int pageNo) {
		
		leaseDao.deleteLease(leaseNo);	
		
		return "redirect:/lease/leaselist.action?pageno=" + pageNo;
		
	}
	
	
	
	@RequestMapping(value = "leaselist.action", method = RequestMethod.GET)
	public ModelAndView listGet(HttpServletRequest req) {
		
		//요청한 페이지가 검색 페이저에서 온 경우 post로 보낸다.
		if (req.getParameter("searchtype") != null) {
			ModelAndView mavPost = new ModelAndView();
			mavPost = leaselistPost(req);	
			return mavPost;
		}
		
		int pageno = 1;//현재 페이지 번호
		int pageSize = 5;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 5;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "leaselist.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (req.getParameter("pageno") != null) {
			pageno = Integer.parseInt(req.getParameter("pageno"));
		}
		
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (pageno - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		List<Lease> leases = leaseDao.getleaseList(first, first + pageSize);
	
		dataCount = leaseDao.getLeaseCount();

		ThePager pager = new ThePager(dataCount, pageno, pageSize, pagerSize, url);
				
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/lease/leaselist");
		mav.addObject("leases", leases);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageno);
		
		return mav;
		
	}
	
	@RequestMapping(value = "leaselist.action", method = RequestMethod.POST)
	public ModelAndView leaselistPost(HttpServletRequest req) {

		int pageno = 1;//현재 페이지 번호
		int pageSize = 5;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 5;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "leaselist.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (req.getParameter("pageno") != null) {
			pageno = Integer.parseInt(req.getParameter("pageno"));
		}
		
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (pageno - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		List<Lease> leases = null;
		String searchType = (String) req.getParameter("searchtype");
		String searchValue = (String) req.getParameter("searchvalue");
		String priceOption = null;
		

		dataCount = 0;
		
		if(searchType != null && searchValue != null){
			if("leaseno".equals(searchType) || "leasename".equals(searchType)|| "lessor".equals(searchType)){
				leases = leaseDao.getLeaseSearchList(searchType, searchValue, first, first + pageSize);
				dataCount = leaseDao.getLeaseSearchListCount(searchType, searchValue);				
			}
			
		}else{
			leases = leaseDao.getLeaseSearchList(searchType, searchValue, first ,first + pageSize);
			dataCount = leaseDao.getLeaseSearchListCount(searchType, searchValue);//전체 게시물 갯수 조회
		}	
		
		
		SearchPager pager = new SearchPager(dataCount, pageno, pageSize, pagerSize, url, searchType, searchValue, priceOption);
				
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/lease/leaselist");
		mav.addObject("leases", leases);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageno);
		
		
		return mav;
		
	}


}















