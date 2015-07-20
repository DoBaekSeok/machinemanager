<%@page import="com.machinemanager.model.dto.OrderDetail"%>
<%@page import="com.machinemanager.model.dao.ProductStockDao"%>
<%@page import="java.util.List"%>

<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>주문입고</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />
	<script src="http://code.jquery.com/jquery-1.5.js"></script>
	<script type='text/javascript'>
		function doSubmit(orderNoAndProductNo){			
			var oi = document.getElementById("orderInput" + orderNoAndProductNo);
			oi.submit();
		}
		
		function searchSubmit() {
			var searchForm = document.getElementById("searchForm");
			var searchType = document.getElementById("searchType");
			var searchValue = document.getElementById("searchValue");
			
			if(searchValue.value == null || searchValue.value == "") {
				alert('검색할 값을 입력해주세요!');
				return;
			}
			
			searchForm.submit();
		}
		
		function searchAll(){
			location.href = 'orderinput.action?pageno=1';
		}
	</script>
</head>
<body>

	<div id="pageContainer">	
		<% pageContext.include("/WEB-INF/views/include/header.jsp"); %>
	</div>
	
	<div id="maincontent">
		
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					주문 상세 조회
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		
		<form id="searchForm" action='orderinput.action?pageno=1' method='post'>
			<input type='hidden' name='pageno' value='<%= request.getAttribute("pageno") %>' />
			<input type="hidden" name="searchorinput" value="search" />
			
			<select id="searchType" name="searchtype" class='width_100'>
				<option Value="orderNo" selected="selected">주문번호</option>
				<option Value="productNo">제품번호</option>
				<option Value="productName">제품명</option>
				<option Value="orderDetailCount">수량</option>
				<option Value="productMeasure">단위</option>
				<option Value="orderStatus">상태</option>								
			</select>
			
			<input type='text' id = 'searchValue' name='searchvalue' class='width_200'/>			
			<input type='button' value='검색' onclick="searchSubmit();"/>
			<input type='button' value='전체검색' onclick="searchAll();"/>
		</form>	
		
		<table class="list">
			<tr class="list_title">
				<th width="10%">주문번호</th>
				<th width="10%">제품번호</th>
				<th width="20%">제품명</th>
				<th width="10%">수량</th>
				<th width="10%">단위</th>
				<th width="20%">상태</th>
				<th width="20%">입고처리</th>
			</tr>
			<% List<OrderDetail> orderDetails = (List<OrderDetail>) request.getAttribute("orderDetails"); %>

			<% for(OrderDetail orderDetail : orderDetails){ %>
		
			<tr class="list_body">
				<td><%= orderDetail.getOrderNo() %></td>
				<td><%= orderDetail.getProductNo() %></td>
				<td><%= orderDetail.getProductName() %></td>
				<td><%= orderDetail.getOrderDetailCount() %></td>
				<td><%= orderDetail.getProductMeasure() %></td>
				<td>
					<% if("주문완료".equals(orderDetail.getOrderStatus())){ %>
						<span style="color:blue"><%= orderDetail.getOrderStatus() %></span>
					<% }else{ %>
						<span style="color:green"><%= orderDetail.getOrderStatus() %></span>
					<% } %>
				</td>
				<td>
				<form id="orderInput<%=orderDetail.getOrderNo()%><%=orderDetail.getProductNo()%>" action='orderinput.action' method='post'>
					<input type="hidden" name="orderno" value="<%= orderDetail.getOrderNo() %>" />
					<input type="hidden" name="productno" value="<%= orderDetail.getProductNo() %>" />
					<input type="hidden" name="orderdetailcount" value="<%= orderDetail.getOrderDetailCount() %>" />
					<input type="hidden" name="orderstatus" value="<%= orderDetail.getOrderStatus() %>" />
					<input type="hidden" name="orderdetails" value="<%= orderDetails %>" />
					<input type="hidden" name="searchorinput" value="input" />
					<input type="hidden" name="pageno" value="<%= request.getAttribute("pageno") %>" />
					<% if(request.getParameter("searchtype") != null){ %>
						<input type="hidden" name="searchtype" value="<%= request.getParameter("searchtype") %>" />
						<input type="hidden" name="searchvalue" value="<%= request.getParameter("searchvalue") %>" />
					<% } %>
					<% if(orderDetail.getOrderStatus().equals("주문완료")){ %>
					<input type="button" value="입고" onclick="location.href='javascript:doSubmit(<%=orderDetail.getOrderNo()%><%=orderDetail.getProductNo()%>);'" />			 
					<% }else{%>
							-
					<% }%>
				</form>
				</td>
			</tr>
			<% } %>
		</table>
		
		<br /><br />
		
		<div id="pager">
			<%= request.getAttribute("pager").toString() %>
		</div>
	</div>
</body>
</html>
