<%@page import="com.machinemanager.model.dto.Lease"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset='utf-8' />
	<title>임대거래처 목록</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />
	<script src="http://code.jquery.com/jquery-1.5.js"></script>
	<script type="text/javascript">
		function doSubmit() {
			document.getElementById("writelease").submit();
		}
		
		function leasedelete(leaseNo, pageNo) {
			//1. 삭제 확인
			var yes = confirm("계약만료 하시겠습니까?");
			//2. 1의 결과에 따라 삭제하거나 또는 취소
			if (yes) {
				location.href = 'leasedelete.action?leaseno=' + leaseNo + "&pageno=" + pageNo;
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
			location.href = 'leaselist.action?pageno=1';
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
					임대 거래처 목록
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		
		<form id="searchform" action='leaselist.action?pageno=1' method='post'>
			<input type='hidden' name='pageno' value='<%= request.getAttribute("pageno") %>' />
			
			<select id="searchtype" name="searchtype" class='width_100'>
				<option value="leaseno" selected="selected">No</option>
				<option value="leasename">거래처</option>
				<option value="lessor">담당자</option>												
															
			</select>
			
			<input type='text' id = 'searchvalue' name='searchvalue' class='width_200' />			
			<input type='button' value='검색' onclick="searchsubmit();"/>
			<input type='button' value='전체검색' onclick="searchAll();"/>
		</form>	
	
		<table class="list">
			<tr class="list_title">
				<th width="2%" align="center" >No</th>
				<th width="18%" align="center" >거래처</th>
				<th width="10%" align="center" >임대날짜</th>
				<th width="10%" align="center" >만료기간</th>
				<th width="5%" align="center" >담당자</th>
				<th width="15%" align="center" >전화번호</th>
				<th width="10%" align="center" >구분</th>
			</tr>
			<% List<Lease> leases = (List<Lease>)request.getAttribute("leases"); %>
			<% if(leases.size() != 0){ %>		
				<% for (Lease lease : leases) { %>		
				
			<tr class="list_body">
				<td><%= lease.getLeaseNo() %></td>
				<td>
					<% if (lease.isLeaseDeleted()) { %>
					<span style='color:gray' onclick="alert('계약만료된 거래처입니다.')">
					<%= lease.getLeaseName() %>
					</span>
					&nbsp;[계약만료]
						
					<% } else { %>
					<a href='leaseview.action?leaseno=<%= lease.getLeaseNo() %>&pageno=<%= request.getAttribute("pageno") %>'>
					<%= lease.getLeaseName() %>
					</a>
						
					<% } %>
											
				
				</td>
				<td><%= lease.getLeaseDate().substring(0, 11) %></td>
				<td><%= lease.getLeaseExpirationDate().substring(0, 11) %></td>
				<td><%= lease.getLessor() %></td>
				<td><%= lease.getLessorPhone() %></td>
				<td>
					<input type="button" value="열람" onclick="location.href='leaseview.action?leaseno=<%=lease.getLeaseNo() %>'" />
					<input type="button" value="계약만료" onclick='javascript:leasedelete(<%= lease.getLeaseNo() %>, <%= request.getAttribute("pageno") %>)' />
				</td>
			</tr>
					<% } %>
				<% }else{ %>
					<tr>
						<td colspan="8" class="list_nothing">
							[조회된 결과가 존재하지 않습니다.]
						</td>				
					</tr>
				<% } %>
		</table>
		
		<table class="footer">
			<tr>
				<td> 
					<input type="button" value="임대거래처 등록" onclick="location.href='leasewrite.action?pageno=<%= request.getAttribute("pageno") %>'" />
				</td>
			</tr>
		</table>		
		
		<div id="pager">
			<%= request.getAttribute("pager").toString() %>
		</div>
	</div>
	
</body>
</html>
