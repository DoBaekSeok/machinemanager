<%@page import="com.machinemanager.model.dto.Supply"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>

<html>
<head>
<meta charset='utf-8' />
<title>거래처 정보 수정</title>
	<link rel="Stylesheet" href="/machinemanager/resources/styles/default.css" />
	<script>
		function doSubmit() {
				document.forms[0].submit();
		}		
	</script>
</head>
<body>

	<div id='pageContainer'>
		<% pageContext.include("/WEB-INF/views/include/header.jsp"); %>
	</div>
	
	<div id="maincontent">
	
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					물품 거래처 편집
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		
		<% Supply supply = (Supply)request.getAttribute("supply"); %>	
			
		<form action="update.action" id='update' method="post">
			<input type="hidden" name="supplyNo" value="<%= supply.getSupplyNo() %>"/>
			<input type="hidden" name="pageno" value="<%= request.getAttribute("pageno") %>" />
			<table class="view">
				<tr>
					<th> ㆍ 거래처명</th>
					<td>
						<input type="text" name="supplyName" value="<%= supply.getSupplyName() %>"/>
					</td>
					<th> ㆍ 주소</th>
					<td>
						<input type="text" name="supplyAddress" value="<%= supply.getSupplyAddress() %>"/>
					</td>
				</tr>
				<tr>
					<th> ㆍ 전화번호</th>
					<td>
						<input type="text" name="supplyPhone" value="<%= supply.getSupplyPhone() %>"/>
					</td>
					<th> ㆍ 이메일</th>
					<td>
						<input type="text" name="supplyEmail" value="<%= supply.getSupplyEmail() %>"/>
					</td>
				</tr>
				<tr>
					<th> ㆍ 담당자명</th>
					<td>
						<input type="text" name="supplier" value="<%= supply.getSupplier() %>"/>
					</td>
					<th> ㆍ 담당자 전화번호</th>
					<td>
						<input type="text" name="supplierPhone" value="<%= supply.getSupplierPhone() %>"/>
					</td>
				</tr>
				<tr>
					<th> ㆍ 비고</th>
					<td colspan="3">		                    
						<textarea name="supplyNote" cols="70" rows="5"><%= supply.getSupplyNote() %></textarea>
					</td>
				</tr>
			</table>
		</form>
		
		<div id='buttons'>
			<input type='button' value='수정' onclick="javascript:doSubmit();" />
			&nbsp;
			<input type='button' value='취소' onclick="location.href='list.action'" />
		</div>
	</div>

</body>
</html>