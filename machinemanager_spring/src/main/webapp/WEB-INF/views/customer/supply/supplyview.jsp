<%@page import="com.machinemanager.model.dto.Supply"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset='utf-8' />
<title>물품거래처 정보</title>
	<link rel="Stylesheet" href="/machinemanager/resources/styles/default.css" />	
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
					물품 거래처 정보
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		
		<% Supply supply = (Supply)request.getAttribute("supply"); %>
			
		<table class="view">
			<tr>
				<th> ㆍ 거래처명</th>
				<td><%= supply.getSupplyName() %></td>
				<th> ㆍ 주소</th>
				<td><%= supply.getSupplyAddress() %></td>
			</tr>
			<tr>
				<th> ㆍ 전화번호</th>
				<td><%= supply.getSupplyPhone() %></td>
				<th> ㆍ 이메일</th>
				<td><%= supply.getSupplyEmail() %></td>
			</tr>
			<tr>
				<th> ㆍ 담당자명</th>
				<td><%= supply.getSupplier() %></td>
				<th> ㆍ 담당자 전화번호</th>
				<td><%= supply.getSupplierPhone() %></td>
			</tr>
			<tr>
				<th> ㆍ 비고</th>
				<td colspan="3">
					<textarea name="supply_memo" cols="63" rows="7" readonly="readonly"><%= supply.getSupplyNote() %></textarea>
				</td>
			</tr>			
		</table>
			
		<div id='buttons'>
			<input type='button' value='편집' onclick="location.href='update.action?supplyno=<%= supply.getSupplyNo() %>&pageno=<%= request.getAttribute("pageno") %>'" />
			&nbsp;
			<input type='button' value='목록보기' onclick="location.href='list.action?pageno=<%= request.getAttribute("pageno") %>'" />
		</div>
	</div>

</body>
</html>