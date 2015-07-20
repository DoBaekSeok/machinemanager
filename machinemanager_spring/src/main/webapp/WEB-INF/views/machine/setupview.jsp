<%@ page import="java.util.List" %>
<%@ page import="com.machinemanager.model.dto.Setup" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>

	<meta charset="utf-8" />
	<title>설치 상세</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />
	
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
					자판기 설치 상세
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>	

		<br /><br />
       	
        <table class="view">
            <tr>
                <th> ㆍ 순번</th>
                <td>${ setup.setupNo }</td>
                <th> ㆍ 자판기코드</th>
                <td>${ setup.machineNo }</td>
            </tr>
            <tr>
            	<th> ㆍ 임대처코드</th>
				<td>${ setup.leaseNo }</td>
				<th> ㆍ 임대처명</th>
				<td>${ setup.leaseName }</td>
			</tr>
			<tr>
				<th> ㆍ 설치비용</th>
				<td>${ setup.setupCost } 원</td>		                    							
				<th> ㆍ 설치일시</th>
				<td>${ setup.setupDate }</td>		                    							
			</tr>
			<tr>
				<th> ㆍ 상태</th>
				<td colspan="3">${ setup.setupStatus }</td>                 							
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
	        	        		        	
        	<input type="button" value="수정" onclick="location.href='setupupdate.action?setupno=${ setup.setupNo }&pageno=${ pageNo }'" />
        	&nbsp;        			        	
        	<input type="button" value="목록보기" onclick="location.href='setuplist.action?pageno=${ pageNo }'" />
        </div>
	</div>

</body>
</html>