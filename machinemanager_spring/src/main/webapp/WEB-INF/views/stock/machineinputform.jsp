<%@page import="org.hibernate.validator.internal.util.privilegedactions.GetAnnotationParameter"%>
<%@page import="com.machinemanager.model.dto.ProductDetail"%>
<%@page import="com.machinemanager.model.dto.MachineStock"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.machinemanager.model.dao.MachineStockDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>입고처리</title>

	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />
	<link rel='Stylesheet' href='/machinemanager/resources/styles/input.css' />
	<% ProductDetail productDetail = (ProductDetail) request.getAttribute("productDetail"); %>

	<script type='text/javascript'>
	function doSubmit() {
		var machineNo = document.getElementById("machineNo");
		var machineInput = document.getElementById("machineInput");
		var regexp = /^[1-9][0-9]{0,5}$/;
		if (!machineInput.value.match(regexp)) {
			alert('1~999999 사이의 값을 입력해주세요');
			return;
		}
		if(machineInput.value > <%= productDetail.getProductCount() %>){
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
		        <div class='inputsubtitle'>자판기입고처리</div>

		        <form action='machineinputform.action' method='post'><!-- 상대경로표시 -->
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
		                <th>재고입고일시</th>
		                <td>
		                	<%= productDetail.getProductStockDate() %>
		                </td>
		            </tr>
		            <tr>
		                <th>자판기번호</th>
		                <td>
		                	<SELECT id="machineNo" name="machineno">
							<% List<String> machines = (List<String>) request.getAttribute("machines"); %>
		                	<% for(String machine : machines){ %>
									<OPTION Value="<%= machine %>"><%= machine %>번 자판기</OPTION>
							<% } %>
							</SELECT>
				
		                </td>
		            </tr>
		            <tr>
		                <th>입고수량</th>
		                <td>
		                	<input type='text' id='machineInput' name='machineinput' style='width:280px'/>
		                </td>
		            </tr>
		        </table>
		        <div class='buttons'>
		        	<input type='button' value='입고' style='height:25px' onclick="javascript:doSubmit();"/>
		        	<input type='button' value='취소' style='height:25px'
		        		onclick="location.href='view.action?productno=<%= productDetail.getProductNo() %>&pageno=<%= request.getAttribute("pageno") %>';" />
		        </div>
		        <input type="hidden" name="productStockNo" value="<%= productDetail.getProductStockNo() %>" />
		        <input type="hidden" name="orderNo" value="<%= productDetail.getOrderNo() %>" />
		        <input type="hidden" name="productCompany" value="<%= productDetail.getProductCompany() %>" />
		        <input type="hidden" name="productType" value="<%= productDetail.getProductType() %>" />
		        <input type="hidden" name="productName" value="<%= productDetail.getProductName() %>" />
		        <input type="hidden" name="productCount" value="<%= productDetail.getProductCount() %>" />
		        <input type="hidden" name="productMeasure" value="<%= productDetail.getProductMeasure() %>" />
		        <input type="hidden" name="productStockDate" value="<%= productDetail.getProductStockDate() %>" />
		        <input type="hidden" name="productNo" value="<%= productDetail.getProductNo() %>" />
		        <input type="hidden" name="pageno" value="<%= request.getAttribute("pageno") %>" />
		        <input type="hidden" name="viewpageno" value="<%= request.getAttribute("viewpageno") %>" />
		        </form>
		    </div>
		</div>

	</div>

</body>
</html>