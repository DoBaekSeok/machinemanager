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
import com.google.gson.GsonBuilder;
import com.machinemanager.common.ThePager;
import com.machinemanager.model.dao.OrderDao;
import com.machinemanager.model.dao.ProductDao;
import com.machinemanager.model.dao.SupplyDao;
import com.machinemanager.model.dto.Order;
import com.machinemanager.model.dto.OrderDetail;
import com.machinemanager.model.dto.Product;
import com.machinemanager.model.dto.Supply;

@Controller
@RequestMapping(value = "order")
public class OrderController {
	
	private OrderDao orderDao;
	@Autowired
	@Qualifier("orderDao")
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	private SupplyDao supplyDao;
	@Autowired
	@Qualifier("supplyDao")
	public void setSupplyDao(SupplyDao supplyDao) {
		this.supplyDao = supplyDao;
	}
	
	@Autowired
	@Qualifier("productDao")
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@RequestMapping(value = "orderlist.action", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView orderList(HttpServletRequest req) {		
		
		//페이징 관련 데이터
		int pageNo = 1;//현재 페이지 번호
		int pageSize = 10;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 10;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "orderlist.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (req.getParameter("pageno") != null) {
			pageNo = Integer.parseInt(req.getParameter("pageno"));
		}
		
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (pageNo - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		//1. 데이터 조회 (DAO객체 이용해서 처리)		
		List<Order> orders = null;
		
		String searchType = (String)req.getParameter("searchtype");
		String searchValue = (String)req.getParameter("searchvalue");		
		String searchCostFirstValue = (String)req.getParameter("searchcostfirstvalue");
		String searchCostLastValue = (String)req.getParameter("searchcostlastvalue");
		String searchDateFirstValue = (String)req.getParameter("searchdatefirstvalue");
		String searchDateLastValue = (String)req.getParameter("searchdatelastvalue");
				
		if(searchType != null){
			if("".equals(searchValue)){
				if("ordercost".equals(searchType)){					
					orders = orderDao.getOrderSearchListByCost(searchType, searchCostFirstValue, searchCostLastValue, first, first + pageSize);
					dataCount = orderDao.getOrderSearchListByCostCount(searchType, searchCostFirstValue, searchCostLastValue);
				}else if("orderdate".equals(searchType)){					
					orders = orderDao.getOrderSearchListByDate(searchType, searchDateFirstValue, searchDateLastValue, first, first + pageSize);
					dataCount = orderDao.getOrderSearchListByDateCount(searchType, searchDateFirstValue, searchDateLastValue);
				}					
			}else{
				orders = orderDao.getOrderSearchList(searchType, searchValue, first, first + pageSize);
				dataCount = orderDao.getOrderSearchListCount(searchType, searchValue);
			}
		}else{
			orders = orderDao.getOrderList(first, first + pageSize);
			dataCount = orderDao.getOrderCount();
		}
		
		
		
		ThePager pager = new ThePager(dataCount, pageNo, pageSize, pagerSize, url);
		
		
		
		//2. jsp로 이동
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order/orderlist");
		mav.addObject("orders", orders);
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		
		return mav;
	}
	
	
	@RequestMapping(value = "orderwrite.action", method = RequestMethod.GET)
	public ModelAndView orderWrite()throws IOException {		

		// 주문서 view 에서 거래처 목록 보여주기
		List<Supply> supplys = supplyDao.getSupplyList();
		
		// 주문서 view 에서 제품 목록 보여주기
		List<Product> products = productDao.getProductList();
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order/orderwrite");		
		mav.addObject("supplys", supplys);
		mav.addObject("products", products);
		return mav;
		
	}
	
	@RequestMapping(value = "orderwrite.action", method = RequestMethod.POST)
	public String orderWrite(@ModelAttribute Order order) {		
		
		// 1. 주문은 1개가 들어온다.
		int orderNo = orderDao.insertOrder(order);
		System.out.println("시퀀스 값 뽑아온 orderNo" +orderNo );
		
		// 2. 주문 상세는 N 개가 들어온다. 
		
		for(int i = 1 ; i < order.getOrderDetail().size(); i++){
			
			for(OrderDetail orderDetail : order.getOrderDetail()){
				
				// 3. 주문테이블에서 얻은 orderNo를 주문상세에 1: M 으로 넣어야 한다.
				orderDetail.setOrderNo(orderNo);
				System.out.println("jsp에서 넣은 orderno가 아니라 내가 넣은게 들어가는지 확인 : " + orderDetail.getOrderNo());
				
				if(orderNo > -1){
					
					orderDao.insertOrderDetail(orderDetail);
					
				}	
			}
		}
		
		return "redirect:/order/orderlist.action";
		
	}
	@RequestMapping(value = "supplyajax.action", method = RequestMethod.GET)
	public void supplyAjax(HttpServletRequest req , HttpServletResponse resp)throws IOException {		

		String supplyNo = req.getParameter("supplyno");
		
		// 비동기 처리 요청
		if (supplyNo != null && supplyNo.length() != 0) {
			
			
			Supply supply = supplyDao.getSupplyBySupplyNo(Integer.parseInt(supplyNo));
				
			String data = "";
			if(supply != null){
				Gson gson = new Gson();
				data = gson.toJson(supply);
				
			}else{
				data = "error";
			}
			resp.setContentType("text/plain;charset=utf-8");
			PrintWriter writer = resp.getWriter();
			writer.print(data);
		}
		
	}
	
	@RequestMapping(value = "productajax.action", method = RequestMethod.GET)
	public void productAjax(HttpServletRequest req , HttpServletResponse resp)throws IOException {		

		String productNo = req.getParameter("productno");
		
		// 비동기 처리 요청
		if (productNo != null && productNo.length() != 0) {
			
			Product product = productDao.getproductByProductNo(Integer.parseInt(productNo));
				
			String data = "";
			if(product != null){
				Gson gson = new Gson();
				data = gson.toJson(product);
				
			}else{
				data = "error";
			}
			resp.setContentType("text/plain;charset=utf-8");
			PrintWriter writer = resp.getWriter();
			writer.print(data);
		}
		
	}

	
	@RequestMapping(value = "orderview.action", method = RequestMethod.GET)
	public ModelAndView orderView(@RequestParam("orderno") String orderNo) {	
		
		
		ModelAndView mav = new ModelAndView();
		
		if (orderNo == null || orderNo.length() == 0) {
			mav.setViewName("redirect:/order/orderlist.action");
			return mav;
		}				
				
		Order order = orderDao.getOrderByOrderNo(Integer.parseInt(orderNo));
		
		if (order == null) {
			mav.setViewName("redirect:/order/orderlist.action");
			return mav;
		}	
		
		mav.addObject("order", order);
		mav.setViewName("order/orderview");		
		
		return mav;
		
	}
}