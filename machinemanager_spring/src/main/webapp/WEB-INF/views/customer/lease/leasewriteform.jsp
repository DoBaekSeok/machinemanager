<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>글쓰기</title>
	<link rel="Stylesheet" href="/machinemanager/resources/styles/default.css" />	
	<script type="text/javascript">
		function doSubmit() {
	
		var frm = document.getElementById("writeform");
		if(frm.leaseName.value.length == 0){
			alert("거래처를 입력 해 주세요");
			return;
		}
		if(frm.leaseAddress.value.length == 0){
			alert("거래처 주소를 입력 해 주세요");
			return;
		}
		if(frm.leaseCost.value.length == 0){
			alert("임대료를 입력 해 주세요");
			return;
		}
		if(frm.leaseDate.value.length == 0){
			alert("임대 날짜를 입력 해 주세요");
			return;
		}
		if(frm.leaseExpirationDate.value.length == 0){
			alert("임대만료 날짜를 입력해주세요");
			return;
		}
		if(frm.lessor.value.length == 0){
			alert("담당자를 입력해 주세요");
			return;
		}
		if(frm.lessorPhone.value == 0){
			alert("담당자 연락처를 입력해 주세요");
			return;
		}
		
		if(frm.leaseEmail.value == 0){
			alert("임대처 이메일을 입력해 주세요");
			return;
		}
		

		frm.submit();
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
					임대거래처 등록
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		
		<br /><br />
		
		<form id="writeform" action="leasewrite.action" method="post">
			<table class="view">
	            <tr>
	            	<th>
	            		 ㆍ 거래처명
	            	</th>
	                <td style="text-align:left">
	                	<input type="text" name="leaseName" />
	                </td>
	                <th>
	                	 ㆍ 주소
	                </th>
	                <td>
	                	<input type="text" name="leaseAddress" />
	                </td>
	            </tr>	                
	            <tr>
	            	<th>
	                	 ㆍ 임대료
	                </th>
	                <td>
	                	<input type="text" name="leaseCost"  />(원)
	                </td>
	                <th>
	                	 ㆍ 임대날짜
	                </th>
	                <td>
	                	<input type="date" name="leaseDate"  />
	                </td>
	            </tr>
	            <tr>
	             	<th>
	                	 ㆍ 만료기간
	                </th>
	                <td>
	                	<input type="date" name="leaseExpirationDate"  />
	                </td>
	                <th>
	                	 ㆍ 담당자
	                </th>
	                <td>
	                	<input type="text" name="lessor"  />
	                </td>	                
	            </tr>
	            <tr>
	                <th>
	                	 ㆍ 담당자번호
	                </th>
	                <td>
	                	<input type="text" name="lessorPhone" />
	                </td>
	                <th>
	                	 ㆍ 이메일
	                </th>
	                <td>
	                	<input type="text" name="leaseEmail" />
	                </td>	                
	            </tr>	            
	            <tr>
	                <th>
	                	 ㆍ 비고
	                </th>
	                <td colspan="3">
	                	<textarea name="leaseNote" cols="80" rows="7"></textarea>
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
        	<input type="button" value="취소" onclick="location.href='leaselist.action?pageno=<%= pageno %>'" />
		</div>
	</div>

</body>
</html>