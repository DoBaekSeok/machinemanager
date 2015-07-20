<%@ page import="com.machinemanager.model.dto.Supply"%>
<%@ page import="com.machinemanager.model.dto.Order"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>자판기 설치 관리</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />
	<script src="http://code.jquery.com/jquery-1.5.js"></script>
	<script type="text/javascript">		
		function init(){			
			var cost = document.getElementById("cost");
			var date = document.getElementById("date");
			cost.style.display = "none";
			date.style.display = "none";			
		}
		
		function selected(value){
			var general = document.getElementById("general");
			var cost = document.getElementById("cost");
			var date = document.getElementById("date");
			var searchform = document.getElementById("searchform");
						
			if(value == "ordertotalprice"){				
				cost.style.display = "inline-block";
				general.style.display = "none";
				date.style.display = "none";				
			}else if(value == "orderdate"){
				date.style.display = "inline-block";
				cost.style.display = "none";
				general.style.display = "none";				
			}else{
				general.style.display = "inline-block";
				date.style.display = "none";
				cost.style.display = "none";				
			}
		}
		
		function searchsubmit() {
			var searchform = document.getElementById("searchform");
			var searchtype = document.getElementById("searchtype");
			var searchvalue = document.getElementById("searchvalue");
						
			if(searchtype.value == 'ordertotalprice'){
				if(searchform.searchcostfirstvalue.value == null || searchform.searchcostfirstvalue.value == "") {
					alert('검색할 금액을 입력해주세요!');					
					return;
				}
				if(searchform.searchcostlastvalue.value == null || searchform.searchcostlastvalue.value == "") {
					alert('검색할 금액을 입력해주세요!');					
					return;
				}
				if(searchform.searchcostfirstvalue.value > searchform.searchcostlastvalue.value) {
					alert('금액 범위가 잘못되었습니다.');
					searchform.searchcostfirstvalue.value = "";
					searchform.searchcostlastvalue.value = "";
					searchform.searchcostfirstvalue.focus();
					return;
				}
			}else if(searchtype.value == 'orderdate'){
				if(searchform.searchdatefirstvalue.value == null || searchform.searchdatefirstvalue.value == "") {
					alert('검색할 날짜를 선택 해주세요!');
					return;
				}
				if(searchform.searchdatelastvalue.value == null || searchform.searchdatelastvalue.value == "") {
					alert('검색할 날짜를 선택 해주세요!');
					return;
				}
				if(searchform.searchdatefirstvalue.value > searchform.searchdatelastvalue.value) {
					alert('입력날짜가 잘못되었습니다.');
					searchform.searchdatefirstvalue.value = "";
					searchform.searchdatelastvalue.value = "";
					searchform.searchdatefirstvalue.focus();
					return;
				}
			}else{
				if(searchvalue.value == null || searchvalue.value == "") {
					alert('검색할 값을 입력해주세요!');
					return;
				}				
			}
						
			searchform.submit();
		}
		
		function searchAll(){
			location.href = 'orderlist.action?pageno=1';
		}
	</script>
</head>
<body onload="javascript:init();">

	<div id="pageContainer">
		<% pageContext.include("/WEB-INF/views/include/header.jsp"); %>
	</div>

	<div id="maincontent">
		
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					주문서 관리
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br />
		
		<form id="searchform" action='orderlist.action?pageno=1' method='post'>
			<input type='hidden' name='pageno' value='<%= request.getAttribute("pageno") %>' />
			
			<select id="searchtype" name="searchtype" class="width_100" onchange="javascript:selected(this.value);">
				<option value="supplyname" selected="selected">거래처</option>
				<option value="orderer">주문자</option>
				<option value="ordertotalprice">주문총액</option>
				<option value="orderdate">주문일자</option>
			</select>
						
			<span id="general">
				<input type='text' id='searchvalue' name='searchvalue' class='width_200'/>
			</span>
						
			<span id="cost">
				<input type='text' name='searchcostfirstvalue' class='width_100'/> 원 &nbsp;&nbsp;-&nbsp;&nbsp;
				<input type='text' name='searchcostlastvalue' class='width_100'/> 원 &nbsp;&nbsp;
			</span>
			
			<span id="date">
				<input type='date' name='searchdatefirstvalue' /> &nbsp;&nbsp;-&nbsp;&nbsp;
				<input type='date' name='searchdatelastvalue' /> &nbsp;&nbsp;
			</span>
						
			<input type='button' value='검색' onclick="searchsubmit();"/>
			<input type='button' value='전체검색' onclick="searchAll();"/>
		</form>	
				
		<br /><br />
		
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					주문 내역
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
				
		<table class="list">
			<tr class="list_title">
				<th width="2%">No</th>
				<th width="10%">주문일자</th>
				<th width="18%">거래처</th>
				<th width="10%">주문총액</th>
				<th width="10%">주문자</th>
				<th width="10%">구분</th>
			</tr>
			<% List<Order> orders = (List<Order>)request.getAttribute("orders"); %>
			<% if(orders.size() > 0) {%>
				<% for(Order order : orders) { %>
			<tr class="list_body">
				<td><%=order.getOrderNo() %></td>
				<td><%=order.getOrderDate() %></td>
				<td><%=order.getSupplyName() %></td>
				<td><%=order.getOrderTotalPrice() %></td>
				<td><%=order.getOrderer() %></td>
				<td>
					<input type="button" value="열람" onclick="location.href='orderview.action?orderno=<%=order.getOrderNo() %>'" />
				</td>
			</tr>
				<% } %>
			<% }else{ %>
			<tr class="list_nothing">
				<td colspan="6">
					[주문 내역이 없습니다.]
				</td>
			</tr>
			<%} %>			
		</table>		
		
		<br /><br />
				
		<div id="pager">
			<%= request.getAttribute("pager").toString() %>
		</div>
				
	</div>	
</body>
</html>
