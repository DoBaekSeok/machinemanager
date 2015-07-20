<%@ page import="com.machinemanager.model.dto.Machine" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>

	<meta charset="utf-8" />
	<title>자판기등록</title>
	<link rel="Stylesheet" href="/machinemanager/resources/styles/default.css" />	
	<script type="text/javascript">
		function deleteMachine(machineNo, pageNo, setupStatus) {
			
			//1. 삭제 확인 (사용자 선택)
			var yes = confirm("삭제할까요");
			//2. 1의 결과에 따라 삭제하거나 또는 취소			
			if(setupStatus != "회수" && setupStatus != "null" && setupStatus != ""){
				alert("현재 가동중인 자판기이므로 삭제가 불가능합니다. 관리자에게 문의 해 주세요!");
				return;				
			}else if(yes) {
				location.href = 'machinedelete.action?machineno=' + machineNo + "&pageno=" + pageNo;
			}
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
					자판기 상세
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>	

		<br /><br />

        <table class="view">
            <tr>
                <th> ㆍ 코드</th>
                <td>${ machine.machineNo }</td>
                <th> ㆍ 자판기 상태</th>
                <td>
                	<c:choose>
                		<c:when test="${ machine.setupStatus eq null }">
                			회수
                		</c:when>
                		<c:otherwise>
                			${ machine.setupStatus }
                		</c:otherwise>
                	</c:choose>                	
                </td>
            </tr>
            <tr>
            	<th> ㆍ 모델명</th>
				<td>${ machine.machineModelName }</td>
				<th> ㆍ 가로(Width)</th>
				<td>${ machine.machineWidth } mm</td>
			</tr>
			<tr>
				<th> ㆍ 제조사</th>
				<td>${ machine.machineCompany }</td>		                    							
				<th> ㆍ 세로(Depth)</th>
				<td>${ machine.machineDepth } mm</td>		                    							
			</tr>
			<tr>
				<th> ㆍ 자판기유형</th>
				<td>${ machine.machineType }</td>		                    							
				<th> ㆍ 높이(Height)</th>
				<td>${ machine.machineHeight } mm</td>		                    							
			</tr>
			<tr>
				<th> ㆍ 소비전력</th>
				<td>${ machine.machinePowerConsumption } W </td>
				<th> ㆍ 무게</th>
				<td>${ machine.machineWeight } kg</td>
			</tr>		     
        </table>
        
        <div id="buttons">
        	<c:choose>
        		<c:when test="${ pageno ne null }">
        			<c:set var="pageNo" property="${ pageno }" />
        		</c:when>
        		<c:otherwise>
        			<c:set var="pageNo" value="1" />
        		</c:otherwise>
        	</c:choose>        			        
	               		        	
        	<input type="button" value="수정" onclick="location.href='machineupdate.action?machineno=${ machine.machineNo }&pageno=${ pageNo }'" />
        	&nbsp;
        	<input type="button" value="삭제" onclick="javascript:deleteMachine(${ machine.machineNo }, ${ pageNo }, '${ machine.setupStatus }');" />
        	&nbsp;		        	
        	<input type="button" value="목록보기" onclick="location.href='machinelist.action?pageno=${ pageNo }'" />
        </div>
	</div>

</body>
</html>