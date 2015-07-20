<%@ page import="com.machinemanager.model.dto.Machine" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>자판기 수정</title>
	<link rel="Stylesheet" href="/machinemanager/resources/styles/default.css" />
	<script type='text/javascript'>
		function doSubmit() {
			var frm = document.getElementById("updateform");
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
			if(frm.machineType.value == 0){
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
					자판기 수정
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>

		<br /><br />
		
		<form id="updateform" action="machineupdate.action" method="post">	        
	        <input type="hidden" name="machineNo" value="${ machine.machineNo }" />
	        <input type="hidden" name="pageno" value="${ pageno }" />
	        
	        <table class="view">
	            <tr>
	                <th>
	                	 ㆍ 코드
	                </th>
	                <td>
	                	${ machine.machineNo }
	                </td>
	                <th>
	                	 ㆍ 자판기 상태
	                </th>
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
	            	<th>
	                	 ㆍ 모델명
	                </th>
	                <td>
	                	<input type="text" name="machineModelName" value="${ machine.machineModelName }" />
	                </td>
	                <th>
	                	 ㆍ 가로(Width)
	                </th>
	                <td>
	                	<input type="text" name="machineWidth" value="${ machine.machineWidth }" /> mm
	                </td>
	            </tr>	                
	            <tr>
	            	<th>
	                	 ㆍ 제조사
	                </th>
	                <td>
	                	<input type="text" name="machineCompany" value="${ machine.machineCompany }" />
	                </td>
	                <th>
	                	 ㆍ 세로(Depth)
	                </th>
	                <td>
	                	<input type="text" name="machineDepth" value="${ machine.machineDepth }" /> mm
	                </td>
	            </tr>
	            <tr>
	             	<th>
	                	 ㆍ 자판기유형
	                </th>
	                <td>
	                	<select name="machineType">
							<option value="${ machine.machineType }">${ machine.machineType }</option>
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
	                	<input type="text" name="machineHeight" value="${ machine.machineHeight }" /> mm
	                </td>	                
	            </tr>
	            <tr>
	                <th>
	                	 ㆍ 소비전력
	                </th>
	                <td>
	                	<input type="text" name="machinePowerConsumption" value="${ machine.machinePowerConsumption }" /> W
	                </td>
	                <th>
	                	 ㆍ 무게
	                </th>
	                <td>
	                	<input type="text" name="machineWeight" value="${ machine.machineWeight }" /> kg
	                </td>	                
	            </tr>	            
	        </table>
	    </form>		
        
        <div id="buttons">		        
        	<input type="button" value="수정" onclick="javascript:doSubmit();" />
        	&nbsp;
        	<input type="button" value="취소" onclick="location.href='machineview.action?machineno=${ machine.machineNo }&pageno=${ pageno }'" />	        	
        </div>
	</div>

</body>
</html>