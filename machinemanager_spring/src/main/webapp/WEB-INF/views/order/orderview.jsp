<%@ page import="com.machinemanager.model.dto.Product" %>
<%@ page import="com.machinemanager.model.dto.OrderDetail" %>
<%@ page import="com.machinemanager.model.dto.Supply" %>
<%@ page import="com.machinemanager.model.dto.Order" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>주문내역 상세</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />	
	<script src="http://code.jquery.com/jquery-1.5.js"></script>
	<script type="text/javascript">
	</script>
</head>
<body>

	<div id="pageContainer">
		<% pageContext.include("/WEB-INF/views/include/header.jsp"); %>
	</div>
	
	<div id="maincontent">	
	
		<!-- 주문자정보 -->
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					주문자정보
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<% Order order = (Order)request.getAttribute("order"); %>
		<% List<OrderDetail> oderDetails = order.getOrderDetail(); %>
		<table class="view">
			<tr>
				<th width="15%"> ㆍ 주문번호</th>
				<td width="25%"><%=order.getOrderNo() %></td>
				<th width="15%"> ㆍ 주문자</th>
				<td width="45%"><%=order.getOrderer() %></td>
			</tr>
			<tr>
				<th> ㆍ 주문일자</th>
				<td><%=order.getOrderDate() %></td>
				<th> ㆍ 주문총액</th>
				<td><%=order.getOrderTotalPrice() %></td>
			</tr>
		</table>
		<br />

		<!-- 거래처정보 -->
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					거래처정보
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<% if(order.getSupply() != null) {  %>
			<%for(Supply supply : order.getSupply()) {%>
		<table class="view">
			<tr>
				<th width="15%"> ㆍ 거래처명</th>
				<td width="25%"><%=supply.getSupplyName() %></td>
				<th width="15%"> ㆍ 대표자</th>
				<td width="45%"><%=supply.getSupplier() %></td>
			</tr>
			<tr>
				<th> ㆍ 거래처 전화번호</th>
				<td><%=supply.getSupplyPhone() %></td>
				<th> ㆍ 담당자 전화번호</th>
				<td></td>
			</tr>
			<tr>
				<th> ㆍ 거래처주소</th>
				<td><%=supply.getSupplyAddress() %></td>
				<th> ㆍ 거래처 이메일</th>
				<td><%=supply.getSupplyAddress() %></td>
			</tr>
			<tr>
				<th> ㆍ 비고메모</th>
				<td colspan="3">
					<textarea name="order_memo" cols="63" rows="7" readonly="readonly"><%=supply.getSupplyNote() %></textarea>
				</td>
			</tr>
		</table> 
			<%} %>
		<% }else{ %>
		<div id="nothing">
			[거래처가 없습니다.]
		</div>  			
		<%} %>
		<br />
		
		<!-- 제품정보 -->
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					주문제품정보
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<% if(order.getOrderDetail().size() != 0) {  %>
			<% for (OrderDetail orderDetail : order.getOrderDetail()){ %>
		<table class="view">
			<tr>
				<th width="15%"> ㆍ 제품명</th>
				<td width="25%"><%=orderDetail.getProductName() %></td>
				<th width="15%"> ㆍ 제품단위</th>
				<td width="45%"><%=orderDetail.getProductMeasure() %></td>
			</tr>
			<tr>
				<th> ㆍ 주문수량</th>
				<td><%=orderDetail.getOrderDetailCount() %></td>
				<th> ㆍ 주문금액</th>
				<td><%=orderDetail.getOrderDetailPrice() %></td>
			</tr>			
			<%} %>
		</table>
		<% }else{ %>						
		<div id="nothing">
			[주문 물품이 없습니다.]
		</div>					
		<% } %>
		
		<div id="buttons">        
        	<input type="button" value="창닫기" onclick="javascript:history.go(-1);" />        	
        </div>        
        
        <br/><br/>
	
	</div>
	
</body>
</html>