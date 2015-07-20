package com.machinemanager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.machinemanager.common.DiffOfDate;
import com.machinemanager.common.SearchPager;
import com.machinemanager.common.ThePager;
import com.machinemanager.model.dao.SupplyDao;
import com.machinemanager.model.dto.Supply;

@Controller
@RequestMapping("supply")
public class SupplyController {
	
	private SupplyDao supplyDao;
	
	@Autowired
	@Qualifier("supplyDao")
	public void setSupplyDao(SupplyDao supplyDao) {
		this.supplyDao = supplyDao;
	}
	
	@RequestMapping(value = "list.action", method = RequestMethod.GET)
	public ModelAndView List(HttpServletRequest req) {
		
		//요청한 페이지가 검색 페이저에서 온 경우 post로 보낸다.
		if (req.getParameter("searchtype") != null) {
			ModelAndView mavPost = new ModelAndView();
			mavPost = searchList(req);	
			return mavPost;
		}
		//페이징 관련 데이터
		int pageNo = 1;// 현재 페이지 번호
		int pageSize = 10;// 한 페이지에 표시할 데이터 갯수
		int pagerSize = 3;// 번호로 표시할 페이지 갯수
		int dataCount = 0;// 전체 데이터 갯수
		String url = "list.action";// 페이징 관련 링크를 누르면 페이지 번호와 함께 요청할 경로

		// 요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (req.getParameter("pageno") != null) {
			pageNo = Integer.parseInt(req.getParameter("pageno"));
		}

		// 현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (pageNo - 1) * pageSize + 1;// 1page -> 1, 2page -> 4, 3page
												// -> 7

		// 1. 데이터 조회
		
		List<Supply> supplys = supplyDao.getSupplyList(first, first + pageSize);
		dataCount = supplyDao.getSupplyCount();
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, url);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/supply/supplylist");
		mav.addObject("supplys", supplys);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		
		return mav;
	}
	
	@RequestMapping(value="list.action", method=RequestMethod.POST)
	public ModelAndView searchList(HttpServletRequest req) {
		
		//페이징 관련 데이터
		int pageNo = 1;// 현재 페이지 번호
		int pageSize = 5;// 한 페이지에 표시할 데이터 갯수
		int pagerSize = 3;// 번호로 표시할 페이지 갯수
		int dataCount = 0;// 전체 데이터 갯수
		String url = "list.action";// 페이징 관련 링크를 누르면 페이지 번호와 함께 요청할 경로
		String searchType = (String)req.getParameter("searchtype");
		String searchValue = (String)req.getParameter("searchvalue");
		String priceOption = null;
		// 요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (req.getParameter("pageno") != null) {
			pageNo = Integer.parseInt(req.getParameter("pageno"));
		}

		// 현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (pageNo - 1) * pageSize + 1;// 1page -> 1, 2page -> 4, 3page -> 7
		
		List<Supply> supplys = null;
		
		if (searchType.equals("supplyname") || searchType.equals("supplier")) {
			supplys = supplyDao.getSupplyList(searchType, searchValue, first, first + pageSize);
			dataCount = supplyDao.getSupplySearchCount(searchType, searchValue);	
		}
		
		SearchPager pager = new SearchPager(dataCount, pageNo, pageSize, pagerSize, url, searchType, searchValue, priceOption);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/supply/supplylist");
		mav.addObject("supplys", supplys);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		
		return mav;
	}
	
	@RequestMapping(value="write.action", method=RequestMethod.GET)
	public String writeForm() {
		
		return "customer/supply/supplywriteform";
	}
	
	@RequestMapping(value="write.action", method=RequestMethod.POST)
	public String write(@ModelAttribute Supply supply) {
		
		supplyDao.insertSupply(supply);
		
		return "redirect:/supply/list.action";
	
	}
	
	@RequestMapping(value="view.action", method=RequestMethod.GET)
	public ModelAndView view(@RequestParam("supplyno") int supplyNo, @RequestParam("pageno") int pageNo) {
		
		Supply supply = supplyDao.getSupplyBySupplyNo(supplyNo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/supply/supplyview");
		mav.addObject("supply", supply);
		mav.addObject("pageno", pageNo);
		mav.addObject("supplyno", supplyNo);
		return mav;
	}
	
	@RequestMapping(value = "update.action", method = RequestMethod.GET)
	public String edit(@RequestParam("supplyno") int supplyNo, @RequestParam("pageno") int pageNo, Model model) {
		
		Supply supply = supplyDao.getSupplyBySupplyNo(supplyNo);
		model.addAttribute("supply", supply);
		model.addAttribute("pageno", pageNo);
		model.addAttribute("supplyno", supplyNo);
		
		return "customer/supply/supplyeditform";
	}
	
	@RequestMapping(value = "update.action", method = RequestMethod.POST)
	public String update(
			@ModelAttribute Supply supply, @RequestParam("supplyNo") int supplyNo, @RequestParam("pageno") int pageNo, Model model) {
		
		supplyDao.updateSupply(supply);
		model.addAttribute("pageno", pageNo);
		model.addAttribute("supplyno", supplyNo);
		
		return "redirect:/supply/view.action?supplyno=" + supplyNo + "&pageno=" + pageNo;
	}
	
	@RequestMapping(value="delete.action", method = RequestMethod.GET)
	public String delete(@RequestParam("supplyno") int supplyNo, @RequestParam("pageno") int pageNo) {
		
		supplyDao.deleteSupply(supplyNo);
		
		return "redirect:/supply/list.action?pageno=" + pageNo;
	}
}
