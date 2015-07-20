<%@page import="com.machinemanager.model.dto.Lease"%>
<%@page import="com.machinemanager.model.dto.OrderDetail"%>
<%@page import="com.machinemanager.model.dto.Supply"%>
<%@page import="com.machinemanager.model.dto.Order"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>

	<meta charset='utf-8' />
	<title>거래처 상세 정보</title>
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
	
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					임대거래처 정보
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<% Lease lease = (Lease)request.getAttribute("lease"); %>
		<% Order order = (Order)request.getAttribute("order"); %>
		<% Supply supply = (Supply)request.getAttribute("supply"); %>
		
		<table class="view">
			<tr>
				<th> ㆍ 임대일련번호</th>
				<td><%= lease.getLeaseNo() %></td>
				<th> ㆍ 담당자</th>
				<td><%= lease.getLessor() %></td>
			</tr>
			<tr>
				<th> ㆍ 임대날짜</th>
				<td><%= lease.getLeaseDate().substring(0, 11) %></td>
				<th> ㆍ 만료기간</th>
				<td><%= lease.getLeaseExpirationDate().substring(0, 11) %></td>
			</tr>
		</table>
		
		<br /><br />

		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					임대거래처 상세 정보
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>	
		
		<table class="view">
			<tr>
				<th> ㆍ 거래처명</th>
				<td><%=lease.getLeaseName() %></td>
				<th> ㆍ 거래처주소</th>
				<td><%= lease.getLeaseAddress() %></td>
			</tr>
			<tr>
				<th> ㆍ 임대료</th>
				<td><%= lease.getLeaseCost() %>(원)</td>
				<th> ㆍ 담당자 전화번호</th>
				<td><%= lease.getLessorPhone() %></td>
			</tr>
			<tr>
				<th> ㆍ 담당자 이메일</th>
				<td colspan="3"><%= lease.getLeaseEmail() %></td>
			</tr>
			<tr>
				<th> ㆍ 비고</th>
				<td colspan="3">
					<textarea name="order_memo" cols="63" rows="7" readonly="readonly"><%= lease.getLeaseNote() %></textarea>
				</td>
			</tr>
		</table> 

		<div id="buttons">		        
	        <%
		        String pageNo = "1";
		        if (request.getParameter("pageno") != null) {
		        	pageNo = request.getParameter("pageno");
		        }
	        %>       		        	
        	<input type="button" value="수정" onclick="location.href='leaseupdate.action?leaseno=<%= lease.getLeaseNo() %>'" />
        	&nbsp;     	
        	<input type="button" value="취소" onclick="location.href='leaselist.action?pageno=<%= pageNo %>'" />
        	
        </div>        
	</div>

</body>
</html>
