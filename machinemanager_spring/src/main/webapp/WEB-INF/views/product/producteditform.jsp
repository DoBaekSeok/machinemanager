<%@page import="com.machinemanager.model.dto.Product"%>
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
			//1. 필요하다면 유효성 검사
			//2. 서버로 전송
			//이 문서 내부에 포함된 여러 개의 <form 요소 중 첫 번째 항목을 submit>
			//-> <input type='submit'을 클릭하는 것과 같은 의미
			document.forms[0].submit();
			//document.getElementById("writeform").submit();
		}
	</script>	
</head>
<body>

	<div id='pageContainer'>
		<% pageContext.include("/WEB-INF/views/include/header.jsp"); %>
	</div>
	
	<div id="maincontent">
	
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					제품 정보 수정
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		
		<% Product product = (Product)request.getAttribute("product"); %>
		
		<form id="updateform" action="update.action" method="post">
			<input type="hidden" name="productno" value="<%= product.getProductNo()%>"/>
			<input type="hidden" name="pageno" value="<%= request.getAttribute("pageno") %>" />
			<table class="view">
				<tr>
					<th> ㆍ 제품명</th>
					<td>
						<input type="text" name="productName" value="<%= product.getProductName() %>" />
					</td>
					<th> ㆍ 제품유형</th>
					<td>
						<input type="text" name="productType" value="<%= product.getProductType() %>" />
					</td>
				</tr>
				<tr>
					<th> ㆍ 제품제조사</th>
					<td>
						<input type="text" name="productCompany" value="<%= product.getProductCompany() %>"/>
					</td>
					<th> ㆍ 제품 유통기한</th>
					<td>
						<input type="date" name="productExpirationDate" value="<%= product.getProductExpirationDate() %>"/>
					</td>
				</tr>
				<tr>
					<th> ㆍ 제품 매입가</th>
					<td>
						<input type="text" name="productPrice" value="<%= product.getProductPrice() %>"/>
					</td>
					<th> ㆍ 제품 판매가</th>
					<td>
						<input type="text" name="productSalePrice" value="<%= product.getProductSalePrice() %>"/>
					</td>
				</tr>	
			</table>
		</form>
		
		<div id="buttons">	        	
			<input type='button' value='수정' onclick="javascript:doSubmit();" />
			&nbsp;
			<input type='button' value='취소' onclick="location.href='list.action'" />					
		</div>
	</div>

</body>
</html>