<%@ page import="com.machinemanager.model.dto.Supply"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>물품 거래처 등록</title>
	<link rel="Stylesheet" href="/machinemanager/resources/styles/default.css" />
	<script src="http://code.jquery.com/jquery-1.5.js"></script>
	<script type="text/javascript">
		function doSubmit() {
			//1. 필요하다면 유효성 검사
			//2. 서버로 전송
			//이 문서 내부에 포함된 여러 개의 <form 요소 중 첫 번째 항목을 submit>
			//-> <input type='submit'을 클릭하는 것과 같은 의미
			document.forms[0].submit();
			//document.getElementById("writeform").submit();
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
					물품 거래처 등록
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		
		<% Supply supply = (Supply)request.getAttribute("supply"); %>
		<form id="writeform" action="write.action" method="post">
			<table class="view">
				<tr>
					<th> ㆍ 거래처명</th>
					<td>
						<input type="text" name="supplyName" />
					</td>
					<th> ㆍ 주소</th>
					<td>
						<input type="text" name="supplyAddress" />
					</td>
				</tr>
				<tr>
					<th> ㆍ 전화번호</th>
					<td>
						<input type="text" name="supplyPhone" />
					</td>
					<th> ㆍ 이메일</th>
					<td>
						<input type="text" name="supplyEmail" />
					</td>
				</tr>
				<tr>
					<th> ㆍ 담당자명</th>
					<td>
						<input type="text" name="supplier" />
					</td>
					<th> ㆍ 담당자 전화번호</th>
					<td>
						<input type="text" name="supplierPhone" />
					</td>
				</tr>
				<tr>
					<th> ㆍ 비고</th>
					<td colspan="3">		                    
						<textarea name="supplyNote" cols="70" rows="5"></textarea>
					</td>
				</tr>
			</table>
		</form>
		
		<div id="buttons">        	
			<% 
				String pageno = "1";
				if (request.getParameter("pageno") != null) {
					pageno = request.getParameter("pageno");
				}
			%>
			<input type="button" value="등록" onclick="javascript:doSubmit();" />
			&nbsp;
			<input type="button"  value="목록보기" onclick="location.href='list.action?pageno=<%= pageno %>'" />			
		</div>
	</div>

</body>
</html>