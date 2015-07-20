<%@ page import="com.machinemanager.model.dto.Machine" %>
<%@ page import="com.machinemanager.model.dto.Product" %>
<%@ page import="com.machinemanager.model.dto.Lease" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>자판기 관리</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />	
	<script src="http://code.jquery.com/jquery-1.5.js"></script>
	<script type="text/javascript">
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
			location.href = 'machinelist.action?pageno=1';
		}
	</script>
</head>
<body>

	<div id="pageContainer">		
		<c:import url="/WEB-INF/views/include/header.jsp" />
	</div>

	<div id="maincontent">
		
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					자판기 관리
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		<form id="searchform" action='machinelist.action?pageno=1' method='post'>
			<input type='hidden' name='pageno' value='${ pageno }' />
			
			<select id="searchtype" name="searchtype" class="width_100">
				<option value="machineno" selected="selected">자판기코드</option>
				<option value="machinemodelname">모델명</option>
				<option value="machinetype">종류</option>
				<option value="machinewidth">가로</option>
				<option value="machinedepth">세로</option>
				<option value="machineheight">높이</option>
				<option value="machinepowerconsumption">소비전력</option>
				<option value="machinecompany">제조회사</option>
				<option value="setupstatus">상태</option>				
			</select>
			
			<input type='text' id='searchvalue' name='searchvalue' class="width_200" />			
			<input type='button' value='검색' onclick="searchsubmit();" />
			<input type='button' value='전체검색' onclick="searchAll();" />
		</form>	
		
		<table class="list">
			<tr class="list_title">
				<th width="7%">자판기코드</th>
				<th width="23%">모델명</th>
				<th width="12.5%">종류</th>
				<th width="20%">규격(mm)</th>
				<th width="7.5%">소비전력</th>
				<th width="10%">제조회사</th>
				<th width="10%">상태</th>
				<th width="15%">구분</th>
			</tr>
									
			<c:choose>
				<c:when test="${ fn:length(machines) ne 0 }">
					<c:forEach var="machine" items="${ machines }">					
						<tr class="list_body">
							<td>${ machine.machineNo }</td>
							<td style="text-align:left">&nbsp;&nbsp;&nbsp;${ machine.machineModelName }</td>
							<td>${ machine.machineType }</td>
							<td>
									${ machine.machineWidth }(W) X
									${ machine.machineDepth }(D) X
									${ machine.machineHeight }(H)
							</td>
							<td>${ machine.machinePowerConsumption } W</td>
							<td>${ machine.machineCompany }</td>
							<td>
								<c:choose>
									<c:when test="${ machine.setupStatus eq null || machine.setupStatus eq '회수' }">
										<span style="color:green">회수</span>
									</c:when>
									<c:when test="${ machine.setupStatus eq '가동중(정상)' }">
										<span style="color:blue">${ machine.setupStatus }</span>
									</c:when>
									<c:otherwise>
										<span style="color:red">${ machine.setupStatus }</span>
									</c:otherwise>
								</c:choose>		
							</td>
							<td>
								<input type="button" value="열람" onclick="location.href='machineview.action?machineno=${ machine.machineNo }'" />										
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="list_nothing">
						<td colspan="8">
							[조회된 결과가 존재하지 않습니다.]
						</td>				
					</tr>
				</c:otherwise>
			</c:choose>
					
		</table>
		
		<table class="footer">
			<tr>
				<td> 
					<input type="button" value="자판기 등록" onclick="location.href='machinewrite.action?pageno=${ pageno }'" />					
				</td>
			</tr>
		</table>	
				
		<div id="pager">
			${ pager }			
		</div>
	
	</div>
</body>
</html>
