<%@page import="com.machinemanager.model.dto.Product"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>제품 정보 등록</title>
	<link rel="Stylesheet" href="/machinemanager/resources/styles/default.css" />	
	<script type="text/javascript">
		function doSubmit() {
	
			document.forms[0].submit();
			
		}
		
		function deleteproduct(productNo, pageNo) {
			//1. 삭제 확인
			var yes = confirm("삭제할까요");
			//2. 1의 결과에 따라 삭제하거나 또는 취소
			if (yes) {
				location.href = 'delete.action?productno=' + productNo + "&pageno=" + pageNo;
			}
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
					제품 정보
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />

		<% Product product = (Product)request.getAttribute("product"); %>
				
		<table class="view">
			<tr>
				<th> ㆍ 제품명</th>
					<td><%= product.getProductName() %></td>
				<th> ㆍ 제품유형</th>
					<td><%= product.getProductType() %></td>
			</tr>
			<tr>
				<th> ㆍ 제품제조사</th>
				<td><%= product.getProductCompany() %></td>
				<th> ㆍ 제품 유통기한</th>
				<td><%= (String)product.getProductExpirationDate().substring(0, 11) %></td>
			</tr>
			<tr>
				<th> ㆍ 제품 매입가</th>
				<td style="color:green"><%= product.getProductPrice() %></td>
				<th > ㆍ 제품 판매가</th>
				<td style="color:red"><%= product.getProductSalePrice() %></td>
			</tr>	
		</table>
		
		<div class="buttons" style="padding-top:25px;text-align:center">	        	
			<input type='button' value='편집' onclick="location.href='update.action?productno=<%= product.getProductNo() %>&pageno=<%= request.getAttribute("pageno") %>'" />
			&nbsp;
			<input type='button' value='삭제' onclick="javascript:deleteproduct(<%= product.getProductNo() %>, <%= request.getAttribute("pageno") %>)" />
			&nbsp;
			<input type='button' value='목록보기' onclick="location.href='javascript:history.back()'" />
		</div>
	</div>

</body>
</html>