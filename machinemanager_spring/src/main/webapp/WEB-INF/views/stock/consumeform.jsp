<%@page import="com.machinemanager.model.dto.ProductDetail"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>소모처리</title>

	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />
	<link rel='Stylesheet' href='/machinemanager/resources/styles/input.css' />
	<% ProductDetail productDetail = (ProductDetail) request.getAttribute("productDetail"); %>
	<script type='text/javascript'>
	function doSubmit() {
		var consume = document.getElementById("consume");
		var regexp = /^[1-9]{1}[0-9]{0,5}$/;
		if (!consume.value.match(regexp)) {
			alert('1~999999 사이의 값을 입력해주세요');
			return;
		}
		if(consume.value > <%= productDetail.getProductCount() %>){
			alert('소모수량이 재고량보다 큽니다!');
			return;
		}
		
		document.forms[0].submit();
	}
	</script>
</head>
<body>

	<div id='pageContainer'>
		
		<% pageContext.include("/WEB-INF/views/include/header.jsp"); %>
		
		<div id='inputcontent'>
			<br /><br />
		    <div id='inputmain'>
		        <div class='inputsubtitle'>소모처리</div>
 				
		        <form action='consumeform.action' method='post'><!-- 상대경로표시 -->
		        <table>
		            <tr>
		                <th>순번</th>
		                <td>
		                    <%= productDetail.getProductStockNo() %>
		                </td>
		            </tr>
		            <tr>
		                <th>주문번호</th>
		                <td>
		                	<%= productDetail.getOrderNo() %>
		                </td>
		            </tr>
		            <tr>
		                <th>제조사</th>
		                <td>
		                    <%= productDetail.getProductCompany() %>
		                </td>
		            </tr>
		            <tr>
		                <th>제품유형</th>
		                <td>
		                	<%= productDetail.getProductType() %>
		                </td>
		            </tr>
		            <tr>
		                <th>제품명</th>
		                <td>
		                	<%= productDetail.getProductName() %>
		                </td>
		            </tr>
		            <tr>
		                <th>재고량</th>
		                <td>
		                	<%= productDetail.getProductCount() %>
		                </td>
		            </tr>
		            <tr>
		                <th>단위</th>
		                <td>
		                	<%= productDetail.getProductMeasure() %>
		                </td>
		            </tr>
		            <tr>
		                <th>입고일시</th>
		                <td>
		                	<%= productDetail.getProductStockDate() %>
		                </td>
		            </tr>
		            <tr>
		                <th>소모수량</th>
		                <td>
		                	<input type='text' id='consume' name='consume' style='width:280px'/>
		                </td>
		            </tr>
		        </table>
		        <div class='buttons'>
		        	<input type='button' value='소모' style='height:25px' onclick="javascript:doSubmit();"/>
		        	<input type='button' value='취소' style='height:25px'
		        		onclick="location.href='view.action?productno=<%= productDetail.getProductNo() %>&pageno=<%= request.getAttribute("pageno") %>';" />
		        </div>
		        <input type="hidden" name="productstockno" value="<%= productDetail.getProductStockNo() %>" />
		        <input type="hidden" name="productcount" value="<%= productDetail.getProductCount() %>" />
		        <input type="hidden" name="productno" value="<%= productDetail.getProductNo() %>" />
		        <input type="hidden" name="pageno" value="<%= request.getAttribute("pageno") %>" />
		        <input type="hidden" name="viewpageno" value="<%= request.getAttribute("viewpageno") %>" />
		        </form>
		    </div>
		</div>

	</div>

</body>
</html>