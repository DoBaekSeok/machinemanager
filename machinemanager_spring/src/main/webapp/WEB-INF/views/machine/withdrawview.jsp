<%@ page import="java.util.List"%>
<%@ page import="com.machinemanager.model.dto.Setup"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>

	<meta charset="utf-8" />
	<title>회수 상세</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />
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
					자판기 회수 상세
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>	

		<br /><br />

       	<% Setup setup = (Setup)request.getAttribute("setup"); %>
        <table class="view">
            <tr>
                <th> ㆍ 순번</th>
                <td><%= setup.getSetupNo() %></td>
                <th> ㆍ 자판기코드</th>
                <td><%= setup.getMachineNo() %></td>
            </tr>
            <tr>
            	<th> ㆍ 임대처코드</th>
				<td><%= setup.getLeaseNo() %></td>
				<th> ㆍ 임대처명</th>
				<td><%= setup.getLeaseName() %></td>
			</tr>
			<tr>
				<th> ㆍ 설치비용</th>
				<td><%= setup.getSetupCost() %> 원</td>		                    							
				<th> ㆍ 설치일시</th>
				<td><%= setup.getSetupDate() %></td>		                    							
			</tr>
			<tr>
				<th> ㆍ 회수일시</th>
				<td><%= setup.getWithdrawDate() %></td>		                    							
				<th> ㆍ 회수이유</th>
				<td><%= setup.getWithdrawReason() %></td>		                    							
			</tr>
			<tr>
				<th> ㆍ 상태</th>
				<td colspan="3"><%= setup.getSetupStatus() %></td>
			</tr>		     
        </table>
        
        <div id="buttons">		        
	        <%
		        String pageNo = "1";
		        if (request.getParameter("pageno") != null) {
		        	pageNo = request.getParameter("pageno");
		        }
	        %>      		        	
        	<input type="button" value="목록보기" onclick="location.href='withdrawlist.action?pageno=<%= pageNo %>'" />
        </div>
	</div>

</body>
</html>