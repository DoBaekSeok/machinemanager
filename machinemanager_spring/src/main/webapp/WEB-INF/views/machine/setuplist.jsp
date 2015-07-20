<%@ page import="com.machinemanager.model.dto.Setup" %>
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
	<title>자판기 설치 관리</title>
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
			}else if(value == "setupdate"){
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
						
			if(searchtype.value == 'setupcost'){
				if(searchform.searchcostfirstvalue.value == null || searchform.searchcostfirstvalue.value == "") {
					alert('검색할 금액을 입력해주세요!');					
					return;
				}
				if(searchform.searchcostlastvalue.value == null || searchform.searchcostlastvalue.value == "") {
					alert('검색할 금액을 입력해주세요!');					
					return;
				}
				if(searchform.searchcostfirstvalue.value > searchform.searchcostlastvalue.value) {
					alert('금액 범위가 잘못되었습니다.');
					searchform.searchcostfirstvalue.value = "";
					searchform.searchcostlastvalue.value = "";
					searchform.searchcostfirstvalue.focus();
					return;
				}
			}else if(searchtype.value == 'setupdate'){
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
			location.href = 'setuplist.action?pageno=1';
		}
	</script>
</head>
<body onload="javascript:init();">

	<div id="pageContainer">
		<c:import url="/WEB-INF/views/include/header.jsp" />
	</div>

	<div id="maincontent">
		
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					자판기 설치 관리
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		<form id="searchform" action='setuplist.action?pageno=1' method='post'>
			<input type='hidden' name='pageno' value='${ pageNo }' />
			
			<select id="searchtype" name="searchtype" onchange="javascript:selected(this.value);" class="width_100">
				<option value="setupno" selected="selected">설치코드</option>
				<option value="machineno">자판기코드</option>
				<option value="leaseno">임대처코드</option>
				<option value="leasename">임대처명</option>
				<option value="setupcost">설치비용</option>
				<option value="setupdate">설치일시</option>
				<option value="setupstatus">상태</option>		
			</select>
						
			<span id="general">
				<input type='text' id='searchvalue' name='searchvalue' class="width_200"/>
			</span>
						
			<span id="cost">
				<input type='text' name='searchcostfirstvalue' class="width_100"/> 원 &nbsp;&nbsp;-&nbsp;&nbsp;
				<input type='text' name='searchcostlastvalue' class="width_100"/> 원 &nbsp;&nbsp;
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
				<th width="5%" align="center" >설치코드</th>
				<th width="8%" align="center" >자판기코드</th>
				<th width="8%" align="center" >임대처코드</th>
				<th width="18%" align="center" >임대처명</th>
				<th width="13%" align="center" >설치비용</th>
				<th width="18%" align="center" >설치일시</th>
				<th width="17%" align="center" >상태</th>
				<th width="13%" align="center" >구분</th>
			</tr>			
		
			<c:choose>
				<c:when test="${ fn:length(setups) ne 0 }">
					<c:forEach var="setup" items="${ setups }">					
						<tr class="list_body">
							<td>${ setup.setupNo }</td>
							<td>${ setup.machineNo }</td>
							<td>${ setup.leaseNo }</td>
							<td>${ setup.leaseName }</td>												
							<td>${ setup.setupCost } 원</td>
							<td>${ setup.setupDate }</td>				
							<td>
							<c:choose>
								<c:when test="${ setup.setupStatus eq '가동중지(대기중)' || setup.setupStatus eq '가동중지(A/S)' }">
									<span style="color:red">${ setup.setupStatus }</span>
								</c:when>								
								<c:otherwise>
									<span style="color:blue">${ setup.setupStatus }</span>
								</c:otherwise>
							</c:choose>		
							</td>
							<td>
								<input type="button" value="열람" onclick="location.href='setupview.action?setupno=${ setup.setupNo }'" />										
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="list_nothing">
						<td colspan="7">
							[조회된 결과가 존재하지 않습니다.]
						</td>				
					</tr>
				</c:otherwise>
			</c:choose>
					
		</table>		
		<table class="footer">
			<tr>
				<td> 
					<input type="button" value="설치 등록" onclick="location.href='setupwrite.action?pageno=${ pageno }'" />
				</td>
			</tr>
		</table>			
		
		<div id="pager">
			${ pager }
		</div>
	
	</div>
</body>
</html>
