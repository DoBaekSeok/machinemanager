<%@page import="com.machinemanager.common.SearchPager"%>
<%@page import="com.machinemanager.model.dto.Product"%>
<%@page import="com.machinemanager.model.dao.ProductStockDao"%>
<%@page import="java.util.List"%>

<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>재고조회</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />
	<script src="http://code.jquery.com/jquery-1.5.js"></script>
	<script type="text/javascript">
		function searchSubmit() {
			var searchForm = document.getElementById("searchForm");
			var searchType = document.getElementById("searchType");
			var searchValue = document.getElementById("searchValue");
			var priceOption = document.getElementById("priceOption");
			if(searchValue.value == null || searchValue.value == "") {
				alert('검색할 값을 입력해주세요!');
				return;
			}
			if(searchType.value == "productPrice" || searchType.value == "productSalePrice" || searchType.value == "productCount"){
				if(priceOption.value == "nothing"){
					alert('가격검색이나 재고량 검색은 옵션을 지정해야 합니다.');
					return;
				}
			}else{
				if(priceOption.value != "nothing"){
					alert('옵션을 지정할 수 없는 검색입니다.');
					return;
				}
			}
			searchForm.submit();//<input type='submit' ... /> 버튼을 누른 것과 같은 기능 수행
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
					재고 관리
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		
		<form id="searchForm" action='list.action?pageno=1' method='post'>
			<select id="searchType" name="searchtype" class='width_100'>
				<option Value="productCompany" selected="selected">제조사</option>
				<option Value="productType">제품유형</option>
				<option Value="productName">제품명</option>
				<option Value="productCount">재고량</option>
				<option Value="productPrice">원가</option>
				<option Value="productSalePrice">판매가</option>
			</select>
			
			<input type='text' id = 'searchValue' name='searchvalue' class='width_200'/>
			
			<select id="priceOption" name="priceoption">
				<option value="nothing" selected="selected">옵션</option>
				<option value="equal">equal</option>
				<option value="up">이상</option>
				<option value="down">이하</option>
			</select>
			
			<input type='button' value='검색' onclick="searchSubmit();"/>
			<input type='hidden' name='pageno' value='<%= request.getAttribute("pageno") %>' />
			<input type='button' value='전체검색' onclick="searchAll();"/>
		</form>	
			
		<table class="list">
			<tr class="list_title">
				<th width="10%">제조사</th>
				<th width="10%">제품유형</th>
				<th width="10%">제품명</th>
				<th width="10%">재고량</th>
				<th width="5%">단위</th>
				<th width="10%">원가</th>
				<th width="10%">판매가</th>
				<th width="10%">비고</th>
			</tr>
			<% List<Product> products = (List<Product>) request.getAttribute("products"); %>
	
			<% for(Product product : products){ %>
		
			<tr class="list_body">
				<td><%= product.getProductCompany() %></td>
				<td><%= product.getProductType() %></td>
				<td><%= product.getProductName() %></td>
				<td><%= product.getProductCount() %></td>
				<td><%= product.getProductMeasure() %></td>
				<td><%= product.getProductPrice() %></td>
				<td><%= product.getProductSalePrice() %></td>
				<td>
					<input type="button" value="열람" onclick="location.href='view.action?productno=<%= product.getProductNo() %>&pageno=<%= request.getAttribute("pageno") %>'" />
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
