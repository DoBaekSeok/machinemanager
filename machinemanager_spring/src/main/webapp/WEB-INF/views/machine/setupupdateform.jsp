<%@ page import="com.machinemanager.model.dto.Lease" %>
<%@ page import="com.machinemanager.model.dto.Machine" %>
<%@ page import="java.util.List" %>
<%@ page import="com.machinemanager.model.dto.Setup" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>설치내역 수정</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />	
	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
	
	<script type='text/javascript'>		
		function init(){			
			var withdrawToggle = document.getElementById("withdrawToggle");;
			withdrawToggle.style.visibility = "hidden";
		}
		
		function doSubmit(){
			var frm = document.getElementById("updateform");
			var confirmValue = true;
			if(frm.machineNo.value.length == 0){
				alert("자판기코드를 입력 해 주세요");
				return;
			}			
			if(frm.setupCost.value.length == 0){
				alert("설치비용을 입력 해 주세요");
				return;
			}						
			if(frm.setupStatus.value == "none"){
				alert("설치상태를 선택 해 주세요");
				return;
			}else if(frm.setupStatus.value == "회수"){				
				confirmValue = confirm("설치된 자판기를 회수하시겠습니까?");
			}
			if(confirmValue == false){
				return;
			}
			frm.submit();
		}
		
		function setMachineNo(machineNo){
			var frm = document.getElementById("updateform");
			frm.machineNo.value = machineNo; 
		}		
		
		function toggle(value){
			if(value == "회수"){
				withdrawToggle.style.visibility = "visible";
			}else{
				withdrawToggle.style.visibility = "hidden";
			}
		}		
		
		//====================
		//비동기 만들기
		//====================
		var proxy = null;
		
		function usableLeaseListChange(pageno){
			//비동기 요청 처리 객체 생성
			//proxy = new XMLHttpRequest();
			
			proxy = getXmlHttpRequest();
			
			if (proxy == null) {
				alert('XMLHttpRequest 객체 생성 실패');
				return;
			}
			//응답이 도착했을 때 호출할 함수를 설정 (callback 설정)
			proxy.onreadystatechange = processResultUsableLease;
			//연결 만들기
			proxy.open(
				"GET",																//전송 방식
				"/machinemanager/machine/usableleaseajax.action?pageno=" + pageno, 	//요청을 보낼 url
				true);																//비동기 요청 여부
			//요청 전송
			proxy.send(null);//get 방식 요청 보낼 때 전달인자 생략 또는 null
		}
	
		function processResultUsableLease() {			
			//XMLHttpRequest가 요청-응답 처리과정에 이 메서드를 4번 호출
			//1 : 요청보내기 전, 2 : 요청보낸 후, 3 : 응답이 수신되기 시작할 때, 4 : 응답이 도착했을 때
			if (proxy.readyState == 4) {//응답이 도착했을 때
				if (proxy.status == 200) {//응답 코드 (200:정상, 404 : 페이지없음, 500 : 서버에러 ...)
					var result = proxy.responseText;//텍스트 형식 응답컨텐츠 반환
					
					if (result == "error") {
						alert("검색 실패");
					} else {					
						eval("var lease = " + result); //문자열 -> javascript code
						
						var th = "<tr class='list_title'>"		
									+ "<th width='7%'>코드</th>"    
									+ "<th width='15%'>거래처명</th>"  
									+ "<th width='13%'>임대료</th>"
									+ "<th width='12%'>임대일</th>"
									+ "<th width='12%'>만료일</th>"
									+ "<th width='11%'>담당자</th>"
									+ "<th width='14%'>연락처</th>"
									+ "<th width='16%'>비고</th>"
								+ "</tr>";					
						
						var tr;						
						
						if(lease.length != 0){
							for(var i = 0 ; i < lease.length -1 ; i++){
								
								tr += "<tr class='list_body'>"
								        + "<td>"
								        + "		<a href='javascript:setLeaseNo(" + lease[i].leaseNo + ");'>" + lease[i].leaseNo + "</a>"
								        + "</td>"				
								        + "<td>"
								        + "		<a href='javascript:setLeaseNo(" + lease[i].leaseNo + ");'>" + lease[i].leaseName + "</a>"
								        + "</td>"				
								        + "<td>" + lease[i].leaseCost + "</td>"								        
								        + "<td>" + lease[i].leaseDate + "</td>"
								        + "<td>" + lease[i].leaseExpirationDate + "</td>"
								        + "<td>" + lease[i].lessor + "</td>"
								        + "<td>" + lease[i].lessorPhone + "</td>"
								        + "<td>" + lease[i].leaseNote + "</td>"
								   + "</tr>";
								
								$("#leasetable").html(th + tr);
							} // end of for		
							
							$("#leasepager").html(lease[lease.length - 1].pager);
						}else{							
							tr = "<tr class='list_nothing'>"
							        + "<td colspan='8'>"
							        + "	[조회된 결과가 존재하지 않습니다.]"
							        + "</td>"				
							   + "</tr>";
								
							$("#leasetable").html(th + tr);
							
							$("#leasepager").html(lease[lease.length - 1].pager);
						} // end of inner if 						
					} // end of outer if
				} // end of status if
			} // end of readyState if
		} // end of processResultUsableLease
				
		function usableMachineListChange(pageno){
			//비동기 요청 처리 객체 생성
			//proxy = new XMLHttpRequest();
			
			proxy = getXmlHttpRequest();
			
			if (proxy == null) {
				alert('XMLHttpRequest 객체 생성 실패');
				return;
			}
			//응답이 도착했을 때 호출할 함수를 설정 (callback 설정)
			proxy.onreadystatechange = processResultUsableMachine;
			//연결 만들기
			proxy.open(
				"GET",																	//전송 방식
				"/machinemanager/machine/usablemachineajax.action?pageno=" + pageno, 	//요청을 보낼 url
				true);																	//비동기 요청 여부
			//요청 전송
			proxy.send(null);//get 방식 요청 보낼 때 전달인자 생략 또는 null
		}
	
		function processResultUsableMachine() {
			//XMLHttpRequest가 요청-응답 처리과정에 이 메서드를 4번 호출
			//1 : 요청보내기 전, 2 : 요청보낸 후, 3 : 응답이 수신되기 시작할 때, 4 : 응답이 도착했을 때
			if (proxy.readyState == 4) {//응답이 도착했을 때
				if (proxy.status == 200) {//응답 코드 (200:정상, 404 : 페이지없음, 500 : 서버에러 ...)
					var result = proxy.responseText;//텍스트 형식 응답컨텐츠 반환
					
					if (result == "error") {
						alert("검색 실패");
					} else {					
						eval("var machine = " + result); //문자열 -> javascript code
						
						var th = "<tr class='list_title'>"		
									+ "<th width='7%'>코드</th>"    
									+ "<th width='27%'>모델명</th>"  
									+ "<th width='15%'>종류</th>"
									+ "<th width='25%'>규격(mm)</th>"
									+ "<th width='13%'>소비전력</th>"
									+ "<th width='13%'>제조회사</th>"
								+ "</tr>";					
						
						var tr;						
						
						if(machine.length != 0){
							for(var i = 0 ; i < machine.length -1 ; i++){
								
								tr += "<tr class='list_body'>"
								        + "<td>"
								        + "		<a href='javascript:setMachineNo(" + machine[i].machineNo + ");'>" + machine[i].machineNo + "</a>"
								        + "</td>"				
								        + "<td>"
								        + "		<a href='javascript:setMachineNo(" + machine[i].machineNo + ");'>" + machine[i].machineModelName + "</a>"
								        + "</td>"				
								        + "<td>" + machine[i].machineType + "</td>"
								        + "<td>"
								        + machine[i].machineWidth + "(W) X "
								        + machine[i].machineDepth + "(D) X "
								        + machine[i].machineHeight + "(H)"
								        + "</td>"
								        + "<td>" + machine[i].machinePowerConsumption + "</td>"
								        + "<td>" + machine[i].machineCompany + "</td>"
								   + "</tr>";
								
								$("#machinetable").html(th + tr);
							} // end of for		
							
							$("#machinepager").html(machine[machine.length - 1].pager);
						}else{							
							tr = "<tr class='list_nothing'>"
							        + "<td colspan='6'>"
							        + "	[조회된 결과가 존재하지 않습니다.]"
							        + "</td>"				
							   + "</tr>";
								
							$("#machinetable").html(th + tr);
							
							$("#machinepager").html(machine[machine.length - 1].pager);
						} // end of inner if 						
					} // end of outer if
				} // end of status if
			} // end of readyState if
		} // end of processResultUsableMachine
		
		function getXmlHttpRequest() {
			//윈도우 내장 객체 형식인 경우
			if (window.XMLHttpRequest) return new XMLHttpRequest();
			
			//ActiveX 형식인 경우 (IE 하위 버전)
			var versions = [
				"MSXML2.XMLHTTP.6.0",
				"MSXML2.XMLHTTP.5.0",
				"MSXML2.XMLHTTP.4.0",
				"MSXML2.XMLHTTP.3.0",
				"MSXML2.XMLHTTP",
				"Microsoft.XMLHTTP"
			];
			for (var i = 0; i < versions.length; i++) {
				try {
					var temp = new ActiveXObject(versions[i]);//ActiveX 객체 생성 구문
					return temp;
				} catch (e) {}
			}
			return null;
		}	
	</script>
</head>
<body onload="javascript:init();">
	<div id="pageContainer">		
		<c:import url="/WEB-INF/views/include/header.jsp" />
	</div>	
	
	<div id="wrapper">
	
		<!-- leftContent start -->
		<div id="leftContent">
			
			<!-- usable_machine start -->
			<div id="usable_machine">	
					
				<table class="title">
					<tr>
						<td>
							<img src="/machinemanager/resources/images/icon_bl_006.gif" />
							&nbsp;
							가용 자판기
						</td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
				
				<br />
				
				<table id="machinetable" class="list">
					<tr class="list_title">					
						<th width="7%">코드</th>
						<th width="27%">모델명</th>
						<th width="15%">종류</th>
						<th width="25%">규격(mm)</th>
						<th width="13%">소비전력</th>
						<th width="13%">제조회사</th>										
					</tr>
								
					<c:choose>
						<c:when test="${ fn:length(machines) ne 0 }">
							<c:forEach var="machine" items="${ machines }">
								<tr class="list_body">
									<td>
										<a href="javascript:setMachineNo('${ machine.machineNo }');">${ machine.machineNo }</a>
									</td>
									<td>
										<a href="javascript:setMachineNo('${ machine.machineNo }');">${ machine.machineModelName }</a>
									</td>
									<td>${ machine.machineType }</td>
									<td>
										${ machine.machineWidth }(W) X
										${ machine.machineDepth }(D) X
										${ machine.machineHeight }(H)
									</td>
									<td>${ machine.machinePowerConsumption } W</td>
									<td>${ machine.machineCompany }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr class="list_nothing">
								<td colspan="6">
									[조회된 결과가 존재하지 않습니다.]
								</td>				
							</tr>
						</c:otherwise>
					</c:choose>
					
				</table>			
				<br />	
			
				<div id="machinepager">
					${ machinepager }
				</div>
				
			</div>
			<!-- usable_machine end -->
			
			<!-- usable_lease start -->
			<div id="usable_lease">
		
				<table class="title">
					<tr>
						<td>
							<img src="/machinemanager/resources/images/icon_bl_006.gif" />
							&nbsp;
							임대 거래처
						</td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
				
				<br />		
						
				<table id="leasetable" class="list">
					<tr class="list_title">					
						<th width="7%">코드</th>
						<th width="15%">거래처명</th>
						<th width="13%">임대료</th>
						<th width="12%">임대일</th>
						<th width="12%">만료일</th>										
						<th width="11%">담당자</th>										
						<th width="14%">연락처</th>										
						<th width="16%">비고</th>										
					</tr>			
						
					<c:choose>
						<c:when test="${ fn:length(leases) ne 0 }">
							<c:forEach var="lease" items="${ leases }">
								<tr class="list_body">
									<td>
										<a href="javascript:setLeaseNo('${ lease.leaseNo }');">${ lease.leaseNo }</a>
									</td>
									<td>
										<a href="javascript:setLeaseNo('${ lease.leaseNo }');">${ lease.leaseName }</a>
									</td>
									<td>${ lease.leaseCost } 원</td>
									<td>${ lease.leaseDate }</td>
									<td>${ lease.leaseExpirationDate }</td>
									<td>${ lease.lessor }</td>
									<td>${ lease.lessorPhone }</td>
									<td>${ lease.leaseNote }</td>	
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
				<br />
				
				<div id="leasepager">
					${ leasepager }
				</div>							
			
			</div>			
			<!-- usable_lease start -->
		</div>
		<!-- leftContent end -->
	
		<!-- rightContent start -->		
		<div id="rightContent">			
			
			<!-- write_setup start -->
			<div id="write_setup">
			
				<table class="title">
					<tr>
						<td>
							<img src="/machinemanager/resources/images/icon_bl_006.gif" />
							&nbsp;
							설치내역 수정
						</td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>	
				<br />
				
	    		<form id="updateform" action="setupupdate.action" method="post">
	    		
		    		<c:choose>
						<c:when test="${ pageno ne null }">
							<c:set var="pageNo" property="pageno" />
						</c:when>
						<c:otherwise>
							<c:set var="pageNo" value="1" />
						</c:otherwise>
					</c:choose>	
	    				        			       
	        		<input type="hidden" name="setupNo" value="${ setup.setupNo }" />
	        		<input type="hidden" name="pageno" value="${ pageNo }" />			        		
	        		<input type="hidden" name="leaseNo" value="${ setup.leaseNo }" />			        					        		
			        
			        <table class="view">
			            <tr>
			                <th> ㆍ 순번</th>
			                <td>${ setup.setupNo }</td>
			            </tr>
			            <tr>
			                <th> ㆍ 자판기코드</th>
			                <td>
			                	<input type="text" name="machineNo" value="${ setup.machineNo }" />
			                </td>
			            </tr>
			            <tr>
			                <th> ㆍ 임대처코드</th>
			                <td>${ setup.leaseNo }</td>
			            </tr>
			            <tr>
			                <th> ㆍ 임대처명</th>
			                <td>${ setup.leaseName }</td>
			            </tr>
			            <tr>
			                <th> ㆍ 설치비용</th>
			                <td>
			                	<input type="text" name="setupCost" value="${ setup.setupCost }" />
			                </td>
			            </tr>
			            <tr>
			                <th> ㆍ 설치일시</th>
			                <td>${ setup.setupDate }</td>
			            </tr>
			            <tr>
			                <th> ㆍ 상태</th>
			                <td>
				                <select name="setupStatus" onchange="javascript:toggle(this.value);">
				                	<option value="${ setup.setupStatus }">${ setup.setupStatus }</option>
									<option value="가동중(정상)">가동중(정상)</option>
									<option value="가동중지(대기중)">가동중지(대기중)</option>
									<option value="가동중지(A/S)">가동중지(A/S)</option>
									<option value="회수">회수</option>
								</select>	                
			                </td>
			            </tr>
			            
			            <tr id="withdrawToggle">
			                <th> ㆍ 회수이유</th>
			                <td>
			                	<input type="text" name="withdrawReason" />
							</td>
			            </tr>          
			            					            
			        </table>
		        </form>
					
				<div id="buttons">
					<input type="button" value="수정" onclick="javascript:doSubmit();" />
		        	&nbsp;
		        	<input type="button" value="취소" onclick="location.href='setupview.action?setupno=${ setup.setupNo }&pageno=${ pageNo }'"/>		        	
				</div>
				
			</div>
			<!-- write_setup end -->
			
		</div>
		<!-- rightContent end -->
			
	</div>
</body>
</html>