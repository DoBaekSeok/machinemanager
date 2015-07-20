<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>자판기등록</title>
	<link rel="Stylesheet" href="/machinemanager/resources/styles/default.css" />	
	<script type='text/javascript'>
		function doSubmit() {
			var frm = document.getElementById("writeform");
			if(frm.machineModelName.value.length == 0){
				alert("모델명을 입력 해 주세요");
				return;
			}
			if(frm.machineWeight.value.length == 0){
				alert("무게를 입력 해 주세요");
				return;
			}
			if(frm.machineWidth.value.length == 0){
				alert("가로를 입력 해 주세요");
				return;
			}
			if(frm.machineDepth.value.length == 0){
				alert("세로를 입력 해 주세요");
				return;
			}
			if(frm.machineHeight.value.length == 0){
				alert("높이를 입력 해 주세요");
				return;
			}
			if(frm.machinePowerConsumption.value.length == 0){
				alert("소비전력를 입력 해 주세요");
				return;
			}
			if(frm.machineCompany.value.length == 0){
				alert("제조사를 입력 해 주세요");
				return;
			}
			if(frm.machineType.value == "none"){
				alert("자판기 유형을 선택 해 주세요");
				return;
			}
			frm.submit();
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
					자판기 등록
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>	

		<br /><br />
		
		<form id="writeform" action="machinewrite.action" method="post">	
			<c:choose>
        		<c:when test="${ pageno ne null }">
        			<c:set var="pageNo" property="${ pageno }" />
        		</c:when>
        		<c:otherwise>
        			<c:set var="pageNo" value="1" />
        		</c:otherwise>
        	</c:choose>     
        	
			<input type="hidden" name="pageno" value="${ pageNo }" />	
	        
	        <table class="view">
	            <tr>
	            	<th>
	            		 ㆍ 모델명
	            	</th>
	                <td style="text-align:left">
	                	<input type="text" name="machineModelName" />
	                </td>
	                <th>
	                	 ㆍ 가로(Width)
	                </th>
	                <td>
	                	<input type="text" name="machineWidth" /> mm
	                </td>
	            </tr>	                
	            <tr>
	            	<th>
	                	 ㆍ 제조사
	                </th>
	                <td>
	                	<input type="text" name="machineCompany" />
	                </td>
	                <th>
	                	 ㆍ 세로(Depth)
	                </th>
	                <td>
	                	<input type="text" name="machineDepth" /> mm
	                </td>
	            </tr>
	            <tr>
	             	<th>
	                	 ㆍ 자판기유형
	                </th>
	                <td>
	                	<select name="machineType">										
							<option value="none">선택해주세요</option>
							<option value="커피(캔)">커피(캔)</option>
							<option value="음료수(PET,캔)">음료수(PET,캔)</option>
							<option value="음료수(PET)">음료수(PET)</option>
							<option value="커피(분말)">커피(분말)</option>
						</select>
	                </td>
	                <th>
	                	 ㆍ 높이(Height)
	                </th>
	                <td>
	                	<input type="text" name="machineHeight" /> mm
	                </td>	                
	            </tr>
	            <tr>
	                <th>
	                	 ㆍ 소비전력
	                </th>
	                <td>
	                	<input type="text" name="machinePowerConsumption" /> W
	                </td>
	                <th>
	                	 ㆍ 무게
	                </th>
	                <td>
	                	<input type="text" name="machineWeight" /> kg
	                </td>	                
	            </tr>	            
	        </table>
	    </form>		
        
        <div id="buttons">       
        	<input type="button" value="등록하기" onclick="javascript:doSubmit();" />
        	&nbsp;
        	<input type="button" value="목록보기" onclick="location.href='machinelist.action?pageno=${ pageNo }'"/>	        	
        </div>
	</div>

</body>
</html>