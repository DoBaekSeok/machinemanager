<%@page import="com.machinemanager.model.dto.MachineStockIO"%>
<%@page import="com.machinemanager.model.dto.Machine"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>자판기 입/출고 내역</title>
	<link rel="Stylesheet" href="/machinemanager/resources/styles/default.css" />	
	<script src="http://code.jquery.com/jquery-1.5.js"></script>	
	<script type="text/javascript">
		function init(){			
			var count = document.getElementById("count");
			var date = document.getElementById("date");
			count.style.display = "none";
			date.style.display = "none";			
		}
		
		function selected(value){
			var general = document.getElementById("general");
			var count = document.getElementById("count");
			var date = document.getElementById("date");
			var searchform = document.getElementById("searchform");
						
			if(value == "machinestockiocount"){				
				count.style.display = "inline-block";
				general.style.display = "none";
				date.style.display = "none";				
			}else if(value == "machinestockiodate"){
				date.style.display = "inline-block";
				count.style.display = "none";
				general.style.display = "none";				
			}else{
				general.style.display = "inline-block";
				date.style.display = "none";
				count.style.display = "none";				
			}
		}
		
		function searchsubmit() {
			var searchform = document.getElementById("searchform");
			var searchtype = document.getElementById("searchtype");
			var searchvalue = document.getElementById("searchvalue");
			
			if(searchtype.value == 'machinestockiocount'){
				if(searchform.searchcountfirstvalue.value == null || searchform.searchcountfirstvalue.value == "") {
					alert('검색할 수량을 입력해주세요!');					
					return;
				}
				if(searchform.searchcountlastvalue.value == null || searchform.searchcountlastvalue.value == "") {
					alert('검색할 수량을 입력해주세요!');					
					return;
				}
				if(searchform.searchcountfirstvalue.value > searchform.searchcountlastvalue.value) {
					alert('수량 범위가 잘못되었습니다.');
					searchform.searchcountfirstvalue.value = "";
					searchform.searchcountlastvalue.value = "";
					searchform.searchcountfirstvalue.focus();
					return;
				}
			}else if(searchtype.value == 'machinestockiodate'){
				if(searchform.searchdatefirstvalue.value == null || searchform.searchdatefirstvalue.value == "") {
					alert('검색할 날짜를 선택 해주세요!');
					return;
				}
				if(searchform.searchdatelastvalue.value == null || searchform.searchdatelastvalue.value == "") {
					alert('검색할 날짜를 선택 해주세요!');
					return;
				}
				if(searchform.searchdatefirstvalue.value > searchform.searchdatelastvalue.value) {
					alert('입력날짜가 잘못되었습니다.');
					searchform.searchdatefirstvalue.value = "";
					searchform.searchdatelastvalue.value = "";
					searchform.searchdatefirstvalue.focus();
					return;
				}
			}else{
				if(searchvalue.value == null || searchvalue.value == "") {
					alert('검색할 값을 입력해주세요!');
					return;
				}				
			}
			
			searchform.submit();
		}
		
		function searchAll(){
			location.href = 'machinestocklist.action?pageno=1';
		}
	</script>
</head>
<body onload="javascript:init();">
	
	<div id="pageContainer">	
		<% pageContext.include("/WEB-INF/views/include/header.jsp"); %>
	</div>
		
	<div id="maincontent">
				
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					자판기 입/출고 내역
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>			
		
		<br /><br />
		<form id="searchform" action='machinestocklist.action?pageno=1' method='post'>
			<input type='hidden' name='pageno' value='<%= request.getAttribute("pageno") %>' />
			
			<select id="searchtype" name="searchtype" onchange="javascript:selected(this.value);" class="witdh_100">
				<option value="machinestockiono" selected="selected">입/출고코드</option>
				<option value="machinestockinputoroutput">입/출고구분</option>
				<option value="machinestockiodate">입/출고일시</option>
				<option value="machineno">자판기코드</option>
				<option value="machinemodelname">자판기모델명</option>
				<option value="productname">물품명</option>
				<option value="machinestockiocount">수량</option>				
			</select>
			
			<span id="general">
				<input type='text' id='searchvalue' name='searchvalue' class="width_200" />
			</span>
						
			<span id="count">
				<input type='text' name='searchcountfirstvalue' class="width-200" /> EA &nbsp;&nbsp;-&nbsp;&nbsp;
				<input type='text' name='searchcountlastvalue' class="width-200" /> EA &nbsp;&nbsp;
			</span>
			
			<span id="date">
				<input type='date' name='searchdatefirstvalue' /> &nbsp;&nbsp;-&nbsp;&nbsp;
				<input type='date' name='searchdatelastvalue' />
			</span>
									
			<input type='button' value='검색' onclick="searchsubmit();"/>
			<input type='button' value='전체검색' onclick="searchAll();"/>
		</form>	
		
		<% List<MachineStockIO> machineStockIOs = (List<MachineStockIO>)request.getAttribute("machinestockios"); %>
		<table class="list">
		<% if(machineStockIOs.size() > 0) {%>
			<tr class="list_title" >								
				<th width="9%" align="center">입/출고코드</th>
				<th width="10%" align="center">입/출고구분</th>
				<th width="15%" align="center">입/출고일시</th>
				<th width="10%" align="center">자판기코드</th>
				<th width="28%" align="center">자판기모델명</th>
				<th width="18%" align="center">물품명</th>
				<th width="10%" align="center">수량</th>										
			</tr>								
			<% for (MachineStockIO machineStockIO : machineStockIOs) { %>				
			<tr class="list_body">					
				<td><%= machineStockIO.getMachineStockIONo() %></td>
				<td>
					<% if("입고".equals(machineStockIO.getMachineStockInputOrOutput())) { %>
						<span style="color:red"><%= machineStockIO.getMachineStockInputOrOutput() %></span>
					<% }else{ %>
						<span style="color:blue"><%= machineStockIO.getMachineStockInputOrOutput() %></span>					
					<% } %>					
				</td>
				<td><%= machineStockIO.getMachineStockIODate() %></td>
				<td><%= machineStockIO.getMachineNo() %></td>
				<td style="text-align:left">&nbsp;&nbsp;&nbsp;<%= machineStockIO.getMachineModelName() %></td>
				<td><%= machineStockIO.getProductName() %></td>
				<td><%= machineStockIO.getMachineStockIOCount() %></td>										
			</tr>
				<% } %>
			<%}else{ %>
			<tr class="list_nothing">
				<td colspan="7">
					[입/출고 내역이 없습니다.]
				</td>
			</tr>
			<%} %>
		</table>
		
		<br /><br />
		<div id="pager">
			<%= request.getAttribute("pager").toString() %>
		</div>				

	</div>
</body>
</html>