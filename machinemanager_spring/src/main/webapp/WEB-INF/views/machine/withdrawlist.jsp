<%@page import="com.machinemanager.model.dto.Setup"%>
<%@page import="com.machinemanager.model.dto.Product"%>
<%@page import="com.machinemanager.model.dto.Lease"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>자판기 회수 내역</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />	
	<script src="http://code.jquery.com/jquery-1.5.js"></script>
	<script type="text/javascript">
		function init(){			
			var cost = document.getElementById("cost");
			var date = document.getElementById("date");
			cost.style.display = "none";
			date.style.display = "none";			
		}
		
		function selected(value){
			var general = document.getElementById("general");
			var cost = document.getElementById("cost");
			var date = document.getElementById("date");
			var searchform = document.getElementById("searchform");
						
			if(value == "setupcost"){				
				cost.style.display = "inline-block";
				general.style.display = "none";
				date.style.display = "none";				
			}else if(value == "setupdate" || value == "withdrawdate"){
				date.style.display = "inline-block";
				cost.style.display = "none";
				general.style.display = "none";				
			}else{
				general.style.display = "inline-block";
				date.style.display = "none";
				cost.style.display = "none";				
			}
		}
		
		function searchsubmit() {
			var searchform = document.getElementById("searchform");
			var searchtype = document.getElementById("searchtype");
			var searchvalue = document.getElementById("searchvalue");
									
			var searchcostfirstvalue = Number(searchform.searchcostfirstvalue.value);
			var searchcostlastvalue = Number(searchform.searchcostlastvalue.value);
			
			if(searchtype.value == 'setupcost'){
				if(searchform.searchcostfirstvalue.value == null || searchform.searchcostfirstvalue.value == "") {
					alert('검색할 금액을 입력해주세요!');					
					return;
				}
				if(searchform.searchcostlastvalue.value == null || searchform.searchcostlastvalue.value == "") {
					alert('검색할 금액을 입력해주세요!');					
					return;
				}
				if(searchcostfirstvalue > searchcostlastvalue) {
					alert('금액 범위가 잘못되었습니다.');
					searchform.searchcostfirstvalue.value = "";
					searchform.searchcostlastvalue.value = "";
					searchform.searchcostfirstvalue.focus();
					return;
				}
			}else if(searchtype.value == 'setupdate' || searchtype.value == 'withdrawdate'){
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
			location.href = 'withdrawlist.action?pageno=1';
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
					자판기 회수 내역
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		
		<form id="searchform" action='withdrawlist.action?pageno=1' method='post'>
			<input type='hidden' name='pageno' value='<%= request.getAttribute("pageno") %>' />
						
			<select id="searchtype" name="searchtype" class="width_100" onchange="javascript:selected(this.value);">
				<option value="setupno" selected="selected">회수코드</option>
				<option value="machineno">자판기코드</option>
				<option value="leaseno">임대처코드</option>
				<option value="leasename">임대처명</option>
				<option value="setupcost">설치비용</option>
				<option value="setupdate">설치일시</option>
				<option value="withdrawdate">회수일시</option>								
			</select>
			
			<span id="general">
				<input type='text' id='searchvalue' name='searchvalue' class='width_200'/>
			</span>
						
			<span id="cost">
				<input type='text' name='searchcostfirstvalue' class='width_200'/> 원 &nbsp;&nbsp;-&nbsp;&nbsp;
				<input type='text' name='searchcostlastvalue' class='width_200'/> 원 &nbsp;&nbsp;
			</span>
			
			<span id="date">
				<input type='date' name='searchdatefirstvalue' /> &nbsp;&nbsp;-&nbsp;&nbsp;
				<input type='date' name='searchdatelastvalue' /> &nbsp;&nbsp;
			</span>
									
			<input type='button' value='검색' onclick="searchsubmit();"/>
			<input type='button' value='전체검색' onclick="searchAll();"/>
		</form>	
		
		<table class="list">
			<tr class="list_title">
				<th width="7%" align="center" >회수코드</th>
				<th width="7%" align="center" >자판기코드</th>
				<th width="7%" align="center" >임대처코드</th>
				<th width="15%" align="center" >임대처명</th>
				<th width="13%" align="center" >설치비용</th>
				<th width="16%" align="center" >설치일시</th>
				<th width="16%" align="center" >회수일시</th>
				<th width="9%" align="center" >상태</th>
				<th width="10%" align="center" >구분</th>
			</tr>
			<% List<Setup> setups = (List<Setup>)request.getAttribute("setups"); %>	
			<% if(setups.size() != 0){ %>			
				<% for (Setup setup : setups) { %>				
			<tr class="list_body">
				<td><b><%= setup.getSetupNo() %></b></td>
				<td><%= setup.getMachineNo() %></td>
				<td><%= setup.getLeaseNo() %></td>
				<td><%= setup.getLeaseName() %></td>
				<td><%= setup.getSetupCost() %> 원</td>
				<td><%= setup.getSetupDate() %></td>
				<td><b><%= setup.getWithdrawDate() %></b></td>
				<td>
					<span style="color:red"><%= setup.getSetupStatus() %></span>
				</td>
				<td>
					<input type="button" value="열람" onclick="location.href='withdrawview.action?setupno=<%= setup.getSetupNo() %>'" />					
				</td>
			</tr>
				<% } %>
			<% }else{ %>
			<tr class="list_nothing">
				<td colspan="8">
					[조회된 결과가 존재하지 않습니다.]
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
