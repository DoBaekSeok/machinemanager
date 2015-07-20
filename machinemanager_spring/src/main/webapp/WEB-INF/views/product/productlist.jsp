<%@page import="com.machinemanager.common.DiffOfDate"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.machinemanager.model.dto.Product"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>제품 목록</title>
	<link rel="Stylesheet" href="/machinemanager/resources/styles/default.css" />
	<script src="http://code.jquery.com/jquery-1.5.js"></script>
	<script type="text/javascript">
		function searchsubmit() {
			var searchform = document.getElementById("searchform");
			var searchtype = document.getElementById("searchtype");
			var searchvalue = document.getElementById("searchvalue");
			
			if(searchvalue.value == null || searchvalue.value == "") {
				alert('검색할 값을 입력해주세요!');
				return;
			}
			
			searchform.submit();
		}
		
		function searchAll(){
			location.href = 'list.action?pageno=1';
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
					제품 목록
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		
		<form id="searchform" action='list.action?pageno=1' method='post'>
			<input type='hidden' name='pageno' value='<%= request.getAttribute("pageno") %>' />
			
			<select id="searchtype" name="searchtype" class='width_100'>
				<option Value="producttype" selected="selected">제품유형</option>
				<option Value="productcompany">제조사</option>
				<option Value="productname">제품명</option>
				<option Value="productexpirationdate">제품유통기한</option>			
			</select>
			
			<input type='text' id = 'searchvalue' name='searchvalue' class="width_200" />			
			<input type='button' value='검색' onclick="searchsubmit();"/>
			<input type='button' value='전체검색' onclick="searchAll();"/>
		</form>	
	
		<table class="list">
			<tr class="list_title">
				<th width="10%" align="center" >제품번호</th>
				<th width="10%" align="center" >제품유형</th>
				<th width="15%" align="center" >제조사</th>
				<th width="15%" align="center" >제품명</th>
				<th width="30%" align="center" >제품유통기한</th>
				<th width="10%" align="center" >제품매입가</th>
				<th width="10%" align="center" >제품판매가</th>
			</tr>
			
			<% List<Product> products = (List<Product>)request.getAttribute("products"); %>
			<% for (Product product : products) { %>
			
			<tr class="list_body">
				<td><%= product.getProductNo() %></td>				
				<td><%= product.getProductType() %></td>
				<td><%= product.getProductCompany() %></td>
				<td>
					<% if (product.isProductUse()) { %>
					<span style='color:gray' onclick="alert('제품이 없습니다.')">
					<%= product.getProductName() %>
					</span>
					
					<% } else { %>
					<a href='view.action?productno=<%= product.getProductNo() %>&pageno=<%= request.getAttribute("pageno") %>'><%= product.getProductName() %></a>
					<% } %>
				</td>
				<% DiffOfDate dod = (DiffOfDate)request.getAttribute("dod"); %>
				<% Date currentDate = new Date(); %>
				<% try { %>
						<% int remainDay = (int)dod.diffOfDate(currentDate, product.getProductExpirationDate()); %>
						<% if (remainDay > 0 && remainDay <= 60) {%>
						<td style="background-color:#ffb937"><%= product.getProductExpirationDate().substring(0, 11) %> [유통기한 만료예정]</td>
						<% } else if (remainDay <= 0) { %>
						<td style="background-color:#ff8c8c"><%= product.getProductExpirationDate().substring(0, 11) %> [유통기한 만료!!!]</td>
						<% } else { %>
						<td ><%= product.getProductExpirationDate().substring(0, 11) %></td>
						<% } %>
				<% } catch (Exception ex){} %>
				
				<td style="color:green"><%= product.getProductPrice() %></td>
				<td style="color:red"><%= product.getProductSalePrice() %></td>
				
			</tr>
			<% } %>	
		
		</table>		
		
		<br />
		
		<table class="footer">
			<tr>
				<td> 
					<input type="button" value="제품 등록" onclick="location.href='write.action?pageno=<%= request.getAttribute("pageno") %>'" />
				</td>
			</tr>
		</table>	
		
		<div id="pager">
			<%= request.getAttribute("pager").toString() %>
		</div>
	</div>

</body>
</html>