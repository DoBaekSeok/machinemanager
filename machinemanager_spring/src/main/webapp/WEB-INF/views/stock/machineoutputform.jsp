
<%@page import="com.machinemanager.model.dto.MachineStock"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.machinemanager.model.dao.MachineStockDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>자판기 재고 판매</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />
	<link rel='Stylesheet' href='/machinemanager/resources/styles/input.css' />
	<script type='text/javascript'>
	function doSubmit(machineStockNo){			
		var mop = document.getElementById("machineOutput" + machineStockNo);
		mop.submit();
	}
	</script>
</head>
<body>

	<div id='pageContainer'>
		
		<% pageContext.include("/WEB-INF/views/include/header.jsp"); %>
		
		<div id='inputcontent' style='width: 600px'>
			<br /><br />
		    <div id='inputmain' style='width: 480px'>
		        <div class='inputsubtitle'>자판기출고(판매)처리</div>
	

			<% List<String> setupNoList = (List<String>) request.getAttribute("setupNoList"); %>
			<% List<MachineStock> machineStockList = null; %> 
		        
		         <% for(String setupno : setupNoList){ %>
		         	<% int setupNo = Integer.parseInt(setupno); %>
					<% MachineStockDao dao = (MachineStockDao) request.getAttribute("machineStockDao"); %>
		         	<% machineStockList = dao.getMachineStockListByMachineNo(setupNo);%>
					<% if(machineStockList.size() == 0){
							continue;
						}%>
		        <table>
		          <tr style="height:25px;text-align:center">
		        	<th style='width:120px;text-align:center'>자판기번호</th>
		        	<th style="width:100px;text-align:center">상품명</th>	
		        	<th style="width:80px;text-align:center">재고량</th>
		        	<th style="width:50px;text-align:center">판매가</th>
		        	<th style="width:100px;text-align:center">출고처리</th>
		          </tr>

		          	<%  for(MachineStock machineStock : machineStockList){ %>
		            
		            <tr style="height:25px;text-align:center">   
		                <td style='text-align:center'><%= setupno %></td>
		                <td style='text-align:center'><%= machineStock.getProductName() %></td>
		                <td style='text-align:center'><%= machineStock.getMachineStockRemain() %></td>
		                <td style='text-align:center'><%= machineStock.getProductSalePrice() %></td>
		                <td style='text-align:center'>
		                	<form id="machineOutput<%= machineStock.getMachineStockNo() %>" action='machineoutputform.action' method='post'>
			                	<input type="hidden" name="machineStockNo" value="<%= machineStock.getMachineStockNo() %>" />
			                	<div class='buttons' style='width:100px;height:25px;'>	
			                	<% if(machineStock.getMachineStockRemain() != 0){ %>
			        				<a href="javascript:doSubmit(<%= machineStock.getMachineStockNo() %>);">판매</a> 
			        			<% }else{ %>
			        				매진
			        			<% } %>
			        			</div>
		        			</form>
		                </td>
		            </tr>
		           		 <% } %>
		          </table>
		        <% } %>

		       
		        
		    </div>
		</div>

	</div>

</body>

</html>

