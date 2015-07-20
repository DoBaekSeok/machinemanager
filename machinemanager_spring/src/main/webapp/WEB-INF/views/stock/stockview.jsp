<%@page import="com.machinemanager.model.dto.ProductDetail"%>
<%@page import="com.machinemanager.model.dto.Product"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>
<meta charset='utf-8' />
<title>재고 상세 조회</title>
<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />
<link rel='Stylesheet' href='/machinemanager/resources/styles/input.css' />
</head>

<script type="text/javascript">
	function deleted(productStockNo, pageno, productNo, viewpageno) {
		//1. 삭제 확인 (사용자 선택)
		var yes = confirm("삭제할까요");
		//2. 1의 결과에 따라 삭제하거나 또는 취소
		if (yes) {
			location.href = 'delete.action?productstockno=' + productStockNo + "&pageno=" + pageno + "&productno=" + productNo;
		}
	}
	</script>
<body>
	
	<div id="pageContainer">
	
		<% pageContext.include("/WEB-INF/views/include/header.jsp"); %>
		
		<div id="content">
			<div style="padding-top:25px;text-align:center">			
				<h1>재고 상세 조회</h1>
			</div>

			<br /><br />
			<table border="1" align="center">
				<tr style="background-color:beige;height:25px;text-align:center">
					<th style="width:50px">순번</th>
					<th style="width:80px">주문번호</th>
					<th style="width:100px">제조사</th>
					<th style="width:80px">제품유형</th>
					<th style="width:150px">제품명</th>
					<th style="width:50px">재고량</th>
					<th style="width:50px">단위</th>
					<th style="width:50px">원가</th>
					<th style="width:50px">판매가</th>
					<th style="width:100px">입고일자</th>
					<th style="width:80px">소모처리</th>
					<th style="width:80px">입고처리</th>
					<th style="width:80px">삭제처리</th>
				</tr> 
			<% String productNo =  request.getAttribute("productno").toString(); %>
			
			<% List<ProductDetail> productDetails = (List<ProductDetail>) request.getAttribute("productDetails"); %>
			<% if(productDetails.size() == 0){ %>
				<tr style="height:50px;text-align:center">
					<td colspan='13'> 데이터 없음 </td>
				</tr>
			<% }else{ %>
			<% for(ProductDetail productDetail : productDetails){ %>
				<tr style="height:20px;text-align:center">
					<td ><%= productDetail.getProductStockNo() %></td>
					<td ><%= productDetail.getOrderNo() %></td>
					<td ><%= productDetail.getProductCompany() %></td>
					<td ><%= productDetail.getProductType() %></td>
					<td ><%= productDetail.getProductName() %></td>
					<td ><%= productDetail.getProductCount() %></td>
					<td ><%= productDetail.getProductMeasure() %></td>
					<td ><%= productDetail.getProductPrice() %></td>
					<td ><%= productDetail.getProductSalePrice() %></td>
					<td ><%= productDetail.getProductStockDate() %></td>
					
					<td ><input type="button" value="소모처리" onclick="location.href='consumeform.action?productStockNo=<%= productDetail.getProductStockNo() %>&orderNo=<%= productDetail.getOrderNo() %>&productCompany=<%= productDetail.getProductCompany() %>&productType=<%= productDetail.getProductType() %>&productName=<%= productDetail.getProductName() %>&productCount=<%= productDetail.getProductCount() %>&productMeasure=<%= productDetail.getProductMeasure() %>&productStockDate=<%= productDetail.getProductStockDate() %>&productNo=<%= productNo %>&pageno=<%= request.getAttribute("pageno") %>&viewpageno=<%= request.getAttribute("viewpageno") %>'" />			
					</td>
					<td ><input type="button" value="입고처리" onclick="location.href='machineinputform.action?productStockNo=<%= productDetail.getProductStockNo() %>&orderNo=<%= productDetail.getOrderNo() %>&productCompany=<%= productDetail.getProductCompany() %>&productType=<%= productDetail.getProductType() %>&productName=<%= productDetail.getProductName() %>&productCount=<%= productDetail.getProductCount() %>&productMeasure=<%= productDetail.getProductMeasure() %>&productStockDate=<%= productDetail.getProductStockDate() %>&productNo=<%= productNo %>&pageno=<%= request.getAttribute("pageno") %>&viewpageno=<%= request.getAttribute("viewpageno") %>'" />			
					</td>
					<td ><input type="button" value="삭제" onclick="location.href='javascript:deleted(<%= productDetail.getProductStockNo() %>, <%= request.getAttribute("pageno") %>, <%= request.getAttribute("productno") %>, <%= request.getAttribute("viewpageno") %>);'" />
					</td>
				</tr> 
			<% } %>
			<% } %>
			</table>
			
			<div style="padding-top:25px;text-align:center">			
				<a href="/machinemanager/stock/list.action?pageno=<%= request.getAttribute("pageno") %>">돌아가기</a>
			</div>
			<br /><br />			
			<div style='text-align:center'>
				<%= request.getAttribute("pager").toString() %>
			</div>
		</div>
	</div>

</body>
</html>