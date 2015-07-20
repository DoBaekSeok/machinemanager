package com.machinemanager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

import com.machinemanager.common.SearchPager;
import com.machinemanager.common.StockViewPager;
import com.machinemanager.common.ThePager;
import com.machinemanager.model.dao.MachineStockDao;
import com.machinemanager.model.dao.OracleProductStockDao;
import com.machinemanager.model.dao.ProductStockDao;
import com.machinemanager.model.dto.MachineStock;
import com.machinemanager.model.dto.Order;
import com.machinemanager.model.dto.OrderDetail;
import com.machinemanager.model.dto.Product;
import com.machinemanager.model.dto.ProductDetail;
import com.machinemanager.model.dto.ProductStock;


@Controller
@RequestMapping(value="stock")
public class StockController {
	
	private MachineStockDao machineStockDao;
	@Autowired
	@Qualifier("machineStockDao")
	public void setMachineStockDao(MachineStockDao machineStockDao) {
		this.machineStockDao = machineStockDao;
	}
	
	private OracleProductStockDao productStockDao;
	@Autowired
	@Qualifier("productStockDao")
	public void setProductStockDao(OracleProductStockDao productStockDao) {
		this.productStockDao = productStockDao;
	}
	
	@RequestMapping(value = "list.action", method = RequestMethod.GET)
	public ModelAndView listGet(HttpServletRequest request) {
		
		//요청한 페이지가 검색 페이저에서 온 경우 post로 보낸다.
		if (request.getParameter("searchtype") != null) {
			ModelAndView mavPost = new ModelAndView();
			mavPost = listPost(request);	
			return mavPost;
		}
		
		int pageno = 1;//현재 페이지 번호
		int pageSize = 5;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 5;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "list.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (request.getParameter("pageno") != null) {
			pageno = Integer.parseInt(request.getParameter("pageno"));
		}
		
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (pageno - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		List<Product> products = productStockDao.getProductStockList(first, first + pageSize);
	
		dataCount = productStockDao.getProductStockCount();

		ThePager pager = new ThePager(dataCount, pageno, pageSize, pagerSize, url);
				
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("stock/stocklist");
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageno);
		mav.addObject("products", products);
		
		return mav;
		
	}
	
	@RequestMapping(value = "list.action", method = RequestMethod.POST)
	public ModelAndView listPost(HttpServletRequest request) {

		int pageno = 1;//현재 페이지 번호
		int pageSize = 5;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 5;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "list.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (request.getParameter("pageno") != null) {
			pageno = Integer.parseInt(request.getParameter("pageno"));
		}
		
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (pageno - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		
		List<Product> products = null;
		String searchType = (String) request.getParameter("searchtype");
		String searchValue = (String) request.getParameter("searchvalue");
		String priceOption = (String) request.getParameter("priceoption");

		dataCount = 0;

		if(searchType.equals("productPrice") || searchType.equals("productSalePrice") || searchType.equals("productCount")){
			int price = Integer.parseInt(searchValue);
			products = productStockDao.getProductPriceSearch(searchType, priceOption, price, first, first + pageSize);
			dataCount = productStockDao.getProductPriceSearchCount(searchType, priceOption, price);
		}else{
				products = productStockDao.getProductSearch(searchType, searchValue, first, first + pageSize);
				dataCount = productStockDao.getProductSearchCount(searchType, searchValue);
			}	
				
		SearchPager pager = new SearchPager(dataCount, pageno, pageSize, pagerSize, url, searchType, searchValue, priceOption);
				
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stock/stocklist");
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageno);
		mav.addObject("products", products);
		
		return mav;
		
	}

	@RequestMapping(value = "view.action", method = RequestMethod.GET)
	public ModelAndView viewGet(HttpServletRequest request) {
		int viewpageno = 1;//현재 페이지 번호
		int pageSize = 5;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 5;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "view.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (request.getParameter("viewpageno") != null) {
			viewpageno = Integer.parseInt(request.getParameter("viewpageno"));
		}
		
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (viewpageno - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		int productNo = 1;
		if (request.getParameter("productno") != null) {
			productNo = Integer.parseInt(request.getParameter("productno"));
		}
		
		List<ProductDetail> productDetails = productStockDao.getProductDetailList(productNo, first, first + pageSize);
		
		dataCount = productStockDao.getProductDetailStockCount(productNo);//전체 개시물 갯수 조회
		
		int pageNo = Integer.parseInt(request.getParameter("pageno"));
		
		StockViewPager pager = new StockViewPager(dataCount, viewpageno, pageSize, pagerSize, url, productNo, pageNo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stock/stockview");
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		mav.addObject("viewpageno", viewpageno);
		mav.addObject("productDetails", productDetails);
		mav.addObject("productno", productNo);
		return mav;
	}

	@RequestMapping(value = "view.action", method = RequestMethod.POST)
	public ModelAndView viewPost(HttpServletRequest request) {
		int viewpageno = 1;//현재 페이지 번호
		int pageSize = 5;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 5;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "view.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (request.getParameter("viewpageno") != null) {
			viewpageno = Integer.parseInt(request.getParameter("viewpageno"));
		}
		
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (viewpageno - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		int productNo = 1;
		if (request.getParameter("productno") != null) {
			productNo = Integer.parseInt(request.getParameter("productno"));
		}
		
		List<ProductDetail> productDetails = productStockDao.getProductDetailList(productNo, first, first + pageSize);
		
		dataCount = productStockDao.getProductDetailStockCount(productNo);//전체 개시물 갯수 조회
		
		int pageNo = Integer.parseInt(request.getParameter("pageno"));
		
		StockViewPager pager = new StockViewPager(dataCount, viewpageno, pageSize, pagerSize, url, productNo, pageNo);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("stock/stockview");
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageNo);
		mav.addObject("viewpageno", viewpageno);
		mav.addObject("productDetails", productDetails);
		mav.addObject("productno", productNo);
		return mav;
	}
	
	
	@RequestMapping(value = "consumeform.action", method = RequestMethod.GET)
	public ModelAndView consumeformGet(HttpServletRequest request,
										@ModelAttribute ProductDetail productDetail) {
		
		String pageno = request.getParameter("pageno");
		String viewpageno = request.getParameter("viewpageno");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stock/consumeform");
		mav.addObject("productDetail", productDetail);
		mav.addObject("pageno", pageno);
		mav.addObject("viewpageno", viewpageno);
		return mav;
	}
	
	@RequestMapping(value = "consumeform.action", method = RequestMethod.POST)
	public String consumeformPost(HttpServletRequest request) {
		
		int consume = Integer.parseInt(request.getParameter("consume"));
		int productStockNo = Integer.parseInt(request.getParameter("productstockno"));
		int productCount = Integer.parseInt(request.getParameter("productcount")) - consume;
		int productNo = Integer.parseInt(request.getParameter("productno"));
		int pageno = Integer.parseInt(request.getParameter("pageno"));
		int viewpageno = Integer.parseInt(request.getParameter("viewpageno"));
		
		productStockDao.stockConsume(productStockNo, productCount);
		
		return "redirect:/stock/view.action?productno=" + productNo + "&pageno=" + pageno + "&viewpageno=" + viewpageno;
	}
	
	@RequestMapping(value = "machineinputform.action", method = RequestMethod.GET)
	public ModelAndView machineinputformGet(HttpServletRequest request,
										@ModelAttribute ProductDetail productDetail) {
		
		String pageno = request.getParameter("pageno");
		String viewpageno = request.getParameter("viewpageno");
		
		List<String> machines = machineStockDao.getSetupNoList();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stock/machineinputform");
		mav.addObject("productDetail", productDetail);
		mav.addObject("machines", machines);
		mav.addObject("pageno", pageno);
		mav.addObject("viewpageno", viewpageno);
		return mav;
	}
	
	@RequestMapping(value = "machineinputform.action", method = RequestMethod.POST)
	public String machineinputformPost(HttpServletRequest request,
										HttpServletResponse response,
										@ModelAttribute ProductDetail productDetail) throws IOException {

		
		int machineNo = Integer.parseInt(request.getParameter("machineno"));
		int machineInput = Integer.parseInt(request.getParameter("machineinput"));
		int productCount = productDetail.getProductCount() - machineInput;
		
		int pageno = Integer.parseInt(request.getParameter("pageno"));
		int viewpageno = Integer.parseInt(request.getParameter("viewpageno"));

		MachineStock machineStock = null;
		machineStock =	machineStockDao.getProductStockByMachineNoANDProductNo(machineNo, productDetail.getProductNo());
		int inputmaxcount = 0; //재고 한도값!
		// 재고한도 초과 여부 확인	
		if(machineStock != null){
			inputmaxcount = machineStock.getMachineStockMaxCount() - machineStock.getMachineStockRemain();
		}else{
			machineStockDao.inputProduct(productDetail.getProductNo(), machineNo);
			machineStock = 
					machineStockDao.getProductStockByMachineNoANDProductNo(machineNo, productDetail.getProductNo());
			inputmaxcount = machineStock.getMachineStockMaxCount() - machineStock.getMachineStockRemain();
		}
	    
		if(machineInput > inputmaxcount){

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script language='javascript'>");
			out.println("alert('자판기 재고 한도를 초과합니다!');");
			out.println("location.href='machineinputform.action?productStockNo=" + productDetail.getProductStockNo() + 
					 "&orderNo=" + productDetail.getOrderNo() + 
					 "&productCompany=" + productDetail.getProductCompany() + 
					 "&productType=" + productDetail.getProductType() + 
					 "&productName=" + productDetail.getProductName() + 
					 "&productCount=" + productDetail.getProductCount() +
					 "&productMeasure=" + productDetail.getProductMeasure() +
					 "&productStockDate=" + productDetail.getProductStockDate() +
					 "&productNo=" + productDetail.getProductNo() + 
					 "&pageno=" + pageno + 
					 "&viewpageno=" + viewpageno + "';"); 
			out.println("</script>"); 
			out.close();
			return null;
		}else{
			
			productStockDao.stockConsume(productDetail.getProductStockNo(), productCount); // 재고관리에서 소모처리
																		
			// 자판기 재고에 입고처리
			machineStock = machineStockDao.getProductStockByMachineNoANDProductNo(machineNo, productDetail.getProductNo());
			
			if(machineStock == null){
				 machineStockDao.inputProduct(productDetail.getProductNo(), machineNo);
			}

			int machineProductCount = machineStock.getMachineStockRemain() + machineInput;
			int machineStockNo = machineStock.getMachineStockNo();
			
			machineStockDao.updateMachineStock(machineProductCount, machineStockNo);
			machineStockDao.inputMachineStockIO(machineInput, machineStock.getMachineStockNo());
			
			return "redirect:/stock/view.action?productno=" + productDetail.getProductNo() + "&pageno=" + pageno + "&viewpagrno=" + viewpageno;
		}
	
	}
	
	@RequestMapping(value = "machineoutputform.action", method = RequestMethod.GET)
	public ModelAndView machineoutputformGet() {
		List<String> setupNoList = machineStockDao.getSetupNoList();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stock/machineoutputform");
		mav.addObject("machineStockDao", machineStockDao);
		mav.addObject("setupNoList", setupNoList);
		return mav;
	}
	
	@RequestMapping(value = "machineoutputform.action", method = RequestMethod.POST)
	public ModelAndView machineoutputformPost(HttpServletRequest request) {
		
		int machineStockNo = Integer.parseInt(request.getParameter("machineStockNo"));
			
		MachineStock machineStock = machineStockDao.getProductStockByMachineStockNo(machineStockNo);
		
		machineStockDao.updateMachineStock(machineStock.getMachineStockRemain() - 1, machineStockNo);
		
		machineStockDao.outputMachineStockIO(1, machineStockNo);
		
		List<String> setupNoList = machineStockDao.getSetupNoList();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stock/machineoutputform");
		mav.addObject("machineStockDao", machineStockDao);
		mav.addObject("setupNoList", setupNoList);
		return mav;
	}
	
	@RequestMapping(value = "delete.action", method = RequestMethod.GET)
	public String deleteGet(HttpServletRequest request) {
		
		int productStockNo = Integer.parseInt(request.getParameter("productstockno"));
		String productNo = request.getParameter("productno");
		String pageno = request.getParameter("pageno");
		
		productStockDao.getStockDelete(productStockNo);
		

		return "redirect:/stock/view.action?productno=" + productNo + "&pageno=" + pageno;
	}
	
	@RequestMapping(value = "orderinput.action", method = RequestMethod.GET)
	public ModelAndView orderinputGet(HttpServletRequest request) {
	
		//요청한 페이지가 검색 페이저에서 온 경우 post로 보낸다.
		if (request.getParameter("searchtype") != null) {
			ModelAndView mavPost = new ModelAndView();
			mavPost = orderinputPost(request, "search");	
			return mavPost;
		}
		
		int pageno = 1;//현재 페이지 번호
		int pageSize = 10;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 10;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "orderinput.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (request.getParameter("pageno") != null) {
			pageno = Integer.parseInt(request.getParameter("pageno"));
		}
		
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (pageno - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7

		List<OrderDetail> orderDetails = productStockDao.getOrderDetailList(first, first + pageSize);
	
		dataCount = productStockDao.getOrderDetailCount();
		//dataCount = dao.getProductStockCount();//전체 개시물 갯수 조회
		ThePager pager = new ThePager(dataCount, pageno, pageSize, pagerSize, url);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("stock/orderinput");
		mav.addObject("pager", pager);
		mav.addObject("pageno", pageno);
		mav.addObject("orderDetails", orderDetails);
		return mav;

	}
	
	@RequestMapping(value = "orderinput.action", method = RequestMethod.POST)
	public ModelAndView orderinputPost(HttpServletRequest request, String searchorinput) {
	
		int pageno = 1;//현재 페이지 번호
		int pageSize = 10;//한 페이지에 표시할 데이터 갯수
		int pagerSize = 10;//번호로 표시할 페이지 갯수
		int dataCount = 0;//전체 데이터 갯수
		String url = "orderinput.action";//페이징 관련 링크를 누르면 페이지번호와 함께 요청할 경로
		
		//요청한 페이지 번호가 있다면 읽어서 현재 페이지번호로 설정
		if (request.getParameter("pageno") != null) {
			pageno = Integer.parseInt(request.getParameter("pageno"));
		}
		
		//현재 페이지의 첫 번째 데이터의 순서번호 계산
		int first = (pageno - 1) * pageSize + 1;//1page -> 1, 2page -> 4, 3page -> 7
		 
		List<OrderDetail> orderDetails = null;
		
		String searchType = request.getParameter("searchtype");
		String searchValue = request.getParameter("searchvalue");
		
		if(searchorinput.equals("input")){ // 주문된 제품 입고시

			int orderNo = Integer.parseInt( request.getParameter("orderno"));
			int productNo = Integer.parseInt( request.getParameter("productno"));
			int orderDetailCount = Integer.parseInt( request.getParameter("orderdetailcount"));
	
			productStockDao.updateOrderStatus(orderNo, productNo); //입고처리(상태변화)
			productStockDao.insertProductStock(productNo, orderNo, orderDetailCount);
			
			try {
				orderDetails = productStockDao.getOrderDetailSearch(searchType, searchValue, first, first + pageSize);	
				dataCount = productStockDao.getOrderDetailSearchCount(searchType, searchValue);
			} catch (NullPointerException e) {
				orderDetails =  productStockDao.getOrderDetailList(first, first + pageSize);
				dataCount = productStockDao.getOrderDetailCount();//전체 개시물 갯수 조회
			}
			
		}else if(searchorinput.equals("search")){ // 검색 시
			orderDetails = productStockDao.getOrderDetailSearch(searchType, searchValue, first, first + pageSize);	
			dataCount = productStockDao.getOrderDetailSearchCount(searchType, searchValue);
		}
		
		SearchPager searchPager = new SearchPager(dataCount, pageno, pageSize, pagerSize, url, searchType, searchValue, null);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("stock/orderinput");
		mav.addObject("pager", searchPager);
		mav.addObject("pageno", pageno);
		mav.addObject("orderDetails", orderDetails);
		mav.addObject("searchtype", searchType);
		mav.addObject("searchvalue", searchValue);
		
		return mav;

	}
	
	
}
