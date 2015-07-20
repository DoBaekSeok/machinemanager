<%@page import="com.machinemanager.model.dto.Supply"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>물품거래처 목록</title>
	<link rel="Stylesheet" href="/machinemanager/resources/styles/default.css" />
	<script src="http://code.jquery.com/jquery-1.5.js"></script>
	<script type="text/javascript">
		function supplydelete(supplyNo, pageNo) {
			//1. 삭제 확인
			var yes = confirm("거래중단 하시겠습니까?");
			//2. 1의 결과에 따라 삭제하거나 또는 취소
			if (yes) {
				location.href = 'delete.action?supplyno=' + supplyNo + "&pageno=" + pageNo;
			}
		}
		
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
					물품 거래처 목록
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
				<option Value="supplyname" selected="selected">거래처명</option>
				<option Value="supplier">담당자명</option>												
			</select>
			
			<input type='text' id = 'searchvalue' name='searchvalue' class='width_200' />			
			<input type='button' value='검색' onclick="searchsubmit();"/>
			<input type='button' value='전체검색' onclick="searchAll();"/>
		</form>	
		
		<table class="list">
			<tr class="list_title">
				<th width="5%" align="center" >번호</th>
				<th width="20%" align="center" >거래처명</th>
				<th width="15%" align="center" >담당자명</th>
				<th width="15%" align="center" >담당자 전화번호</th>
				<th width="20%" align="center" >이메일</th>
				<th width="15%" align="center" >구분</th>
			</tr>
			
			<% List<Supply> supplys = (List<Supply>)request.getAttribute("supplys"); %>
			<% for (Supply supply : supplys) { %>
			
			<tr class="list_body">
				<td>
					<%= supply.getSupplyNo() %>
				</td>				
				<td>
					<% if (supply.isSupplyDeleted()) { %>
					<span style='color:gray' onclick="alert('거래가 중단된 거래처입니다.')">
					<%= supply.getSupplyName() %>
					</span>
					&nbsp;[거래 중단]
					
					<% } else { %>
					<a href='view.action?supplyno=<%= supply.getSupplyNo() %>&pageno=<%= request.getAttribute("pageno") %>'>
					<%= supply.getSupplyName() %>
					</a>
					
					<% } %>
				</td>
				<td><%= supply.getSupplier() %></td>
				<td><%= supply.getSupplierPhone() %></td>
				<td style="text-align:left;">&nbsp;&nbsp;&nbsp;&nbsp;<%= supply.getSupplyEmail() %></td>
				<td>
					<input type="button" value="열람" onclick="location.href='view.action?supplyno=<%=supply.getSupplyNo() %>&pageno=<%= request.getAttribute("pageno") %>'" />
					<input type="button" value="거래중단" onclick="javascript:supplydelete(<%= supply.getSupplyNo() %>, <%= request.getAttribute("pageno") %>)" />			
				</td>			
			</tr>
				<% } %>
		</table>		

		<table class="footer">
			<tr>
				<td> 
					<input type="button" value="임대거래처 등록" onclick="location.href='write.action?pageno=<%= request.getAttribute("pageno") %>'" />
				</td>
			</tr>
		</table>	
		
		<div id="pager">
			<%= request.getAttribute("pager").toString() %>
		</div>
	</div>

</body>
</html>