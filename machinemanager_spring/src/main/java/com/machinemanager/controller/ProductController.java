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

import com.machinemanager.common.DiffOfDate;
import com.machinemanager.common.SearchPager;
import com.machinemanager.common.ThePager;
import com.machinemanager.model.dao.ProductDao;
import com.machinemanager.model.dto.Product;
import com.machinemanager.model.dto.Supply;

@Controller
@RequestMapping("product")
public class ProductController {
	@Autowired
	@Qualifier("productDao")
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@RequestMapping(value="list.action", method=RequestMethod.GET)
	public ModelAndView List(HttpServletRequest req) {
		
		//요청한 페이지가 검색 페이저에서 온 경우 post로 보낸다.
		if (req.getParameter("searchtype") != null) {
			ModelAndView mavPost = new ModelAndView();
			mavPost = searchList(req);	
			return mavPost;
		}
		
		//페이징 관련 데이터
		int pageNo = 1;// 현재 페이지 번호
		int pageSize = 5;// 한 페이지에 표시할 데이터 갯수
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
		DiffOfDate dod = new DiffOfDate();
		
		List<Product> products = productDao.getProductList(first, first + pageSize);
		dataCount = productDao.getProductCount();
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, url);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/productlist");
		mav.addObject("products", products);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		mav.addObject("dod", dod);
		
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
		
		DiffOfDate dod = new DiffOfDate();
		List<Product> products = null;
		
		if (searchType.equals("producttype") || searchType.equals("productcompany") || 
				searchType.equals("productname") || searchType.equals("productexpirationdate")) {
			products = productDao.getProductList(searchType, searchValue, first, first + pageSize);
			dataCount = productDao.getProductSearchCount(searchType, searchValue);	
		}
		
		SearchPager pager = new SearchPager(dataCount, pageNo, pageSize, pagerSize, url, searchType, searchValue, priceOption);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/productlist");
		mav.addObject("products", products);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		mav.addObject("dod", dod);
		
		return mav;
	}
	
	@RequestMapping(value="write.action", method=RequestMethod.GET)
	public String writeForm() {
		
		return "product/productwriteform";
	}
	
	@RequestMapping(value="write.action", method=RequestMethod.POST)
	public String write(@ModelAttribute Product product) {
		
		productDao.insertProduct(product);
		
		return "redirect:/product/list.action";
	}
	
	@RequestMapping(value="view.action", method=RequestMethod.GET)
	public ModelAndView view(@RequestParam("productno") int productNo, @RequestParam("pageno") int pageNo) {
		
		Product product = productDao.getproductByProductNo(productNo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("product/productview");
		mav.addObject("product", product);
		mav.addObject("pageno", pageNo);
		mav.addObject("productno", productNo);
		
		return mav;
	}
	
	@RequestMapping(value="update.action", method=RequestMethod.GET)
	public String edit(
			@RequestParam("productno") int productNo, @RequestParam("pageno") int pageNo, Model model) {
		
		Product product = productDao.getproductByProductNo(productNo);
		model.addAttribute("product", product);
		model.addAttribute("productno", productNo);
		model.addAttribute("pageno", pageNo);
		return "product/producteditform";
	}
	
	@RequestMapping(value="update.action", method=RequestMethod.POST)
	public String update(
			@ModelAttribute Product product, 
			@RequestParam("productno") int productNo, @RequestParam("pageno") int pageNo,
			Model model) {
		
		productDao.updateProduct(product);
		model.addAttribute("pageno", pageNo);
		model.addAttribute("productno", productNo);
		
		return "redirect:/product/view.action?productno=" + productNo + "&pageno=" + pageNo;
	}
	
	@RequestMapping(value="delete.action", method=RequestMethod.GET)
	public String delete(@RequestParam("productno") int productNo, @RequestParam("pageno") int pageNo) {
		
		productDao.deleteproduct(productNo);
		
		return "redirect:/product/list.action?=" + pageNo;
	}
}
