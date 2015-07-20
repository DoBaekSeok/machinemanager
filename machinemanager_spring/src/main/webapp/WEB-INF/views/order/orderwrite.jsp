<%@ page import="com.machinemanager.model.dto.Product" %>
<%@ page import="com.machinemanager.model.dto.Supply" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>주문서 작성</title>
	<link rel='Stylesheet' href='/machinemanager/resources/styles/default.css' />
	<script src="http://code.jquery.com/jquery-1.5.js"></script>
	<script type="text/javascript">
		
		
		//====================
		//주문총액 만들기
		//====================
		function totalPriceChange() {
			var detailCount1 = document.getElementById('detailCount1');
			var detailCount2 = document.getElementById('detailCount2');
			var detailPrice1 = document.getElementById('detailPrice1');
			var detailPrice2 = document.getElementById('detailPrice2');
			var totalPrice = document.getElementById('totalPrice');
			totalPrice.value = (detailCount1.value * detailPrice1.value) + (detailCount2.value * detailPrice2.value)
		}
			
		//====================
		//null값 체크 후 Submit
		//====================
		function doSubmit() {
			var orderer = document.getElementById("orderer");
			if (orderer.value.length == 0) {
				alert('주문자를 입력하세요');
				orderer.focus();
				return;
			}
			
			var supply = document.getElementById("supply");
			if (supply.value.length == 0) {
				alert('거래처를 입력하세요');
				supply.focus();
				return;
			}
			
			var product = document.getElementById("product");
			if (product.value.length == 0) {
				alert('제품을 입력하세요');
				product.focus();
				return;
			}
			var postform = document.getElementById("postform");
			postform.submit();
		}
	
		//====================
		//비동기 만들기
		//====================
		var proxy = null;
		
		function supplyChange(supplyno){
			//비동기 요청 처리 객체 생성
			//proxy = new XMLHttpRequest();
			
			proxy = getXmlHttpRequest();
			
			if (proxy == null) {
				alert('XMLHttpRequest 객체 생성 실패');
				return;
			}
			
			//응답이 도착했을 때 호출할 함수를 설정 (callback 설정)
			proxy.onreadystatechange = processResultsupply;
			
			//연결 만들기
			proxy.open(
				"GET",														//전송 방식
				"/machinemanager/order/supplyajax.action?supplyno=" + supplyno, 	//요청을 보낼 url
				true);														//비동기 요청 여부
				
			//요청 전송
			proxy.send(null);//get 방식 요청 보낼 때 전달인자 생략 또는 null
				
		}
		
		function processResultsupply() {
			//XMLHttpRequest가 요청-응답 처리과정에 이 메서드를 4번 호출
			//1 : 요청보내기 전, 2 : 요청보낸 후, 3 : 응답이 수신되기 시작할 때, 4 : 응답이 도착했을 때
					
			if (proxy.readyState == 4) {//응답이 도착했을 때
				
				if (proxy.status == 200) {//응답 코드 (200:정상, 404 : 페이지없음, 500 : 서버에러 ...)
				
					var result = proxy.responseText;//텍스트 형식 응답컨텐츠 반환
				
					alert(result);
					
					if (result == "error") {
						alert("검색 실패");
					} else {
						eval("var supply = " + result); //문자열 -> javascript code
						document.getElementById("supplyEmail").value = supply.supplyEmail;
						document.getElementById("supplyName").value = supply.supplyName;
						document.getElementById("supplyer").value = supply.supplier;
						document.getElementById("supplyPhone").value = supply.supplyPhone;
						document.getElementById("supplyAddress").value = supply.supplyAddress;
						document.getElementById("supplyNote").value = supply.supplyNote;
					
					} 
				}			
			}
		}
		var index = -1 ;
		function productChange(productno,i){
			//비동기 요청 처리 객체 생성
			//proxy = new XMLHttpRequest();
			index = i;
			proxy = getXmlHttpRequest();
			
			if (proxy == null) {
				alert('XMLHttpRequest 객체 생성 실패');
				return;
			}
			
			//응답이 도착했을 때 호출할 함수를 설정 (callback 설정)
			proxy.onreadystatechange = processResultproduct;
			
			//연결 만들기
			proxy.open(
				"GET",														//전송 방식
				"/machinemanager/order/productajax.action?productno=" + productno, 	//요청을 보낼 url
				true);														//비동기 요청 여부
				
			//요청 전송
			proxy.send(null);//get 방식 요청 보낼 때 전달인자 생략 또는 null
				
		}
	
		
		function processResultproduct() {
			//XMLHttpRequest가 요청-응답 처리과정에 이 메서드를 4번 호출
			//1 : 요청보내기 전, 2 : 요청보낸 후, 3 : 응답이 수신되기 시작할 때, 4 : 응답이 도착했을 때
					
			if (proxy.readyState == 4) {//응답이 도착했을 때
				
				if (proxy.status == 200) {//응답 코드 (200:정상, 404 : 페이지없음, 500 : 서버에러 ...)
					var result = proxy.responseText;//텍스트 형식 응답컨텐츠 반환
					if (result == "error") {
						alert("검색 실패");
					} else {
						eval("var product = " + result); //문자열 -> javascript code
						document.getElementById("productType"+index).value = product.productType;
						document.getElementById("productCompany"+index).value = product.productCompany;
					
					} 
					
				}	
				index = -1;
			}
		
		}
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
<body>
	<div id="pageContainer">
		<% pageContext.include("/WEB-INF/views/include/header.jsp"); %>
	</div>
	
	<div id="maincontent">	
	
		<!-- 주문자정보 -->
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					주문자정보
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<br />	
	
		<form id="postform" name="postform" action="orderwrite.action" method="post">		
		<table class="order_person" >
			<tr> 
				<td> ㆍ 주문자 :  
					<select id="orderer" name="orderer" class="width_100">
						<option value="" selected="selected" >주문자</option>
						<option value="김형식" >김형식</option>
						<option value="도백석" >도백석</option>
						<option value="신건식" >신건식</option>
						<option value="이현기" >이현기</option>
						<option value="배소이" >배소이</option>
				 	</select>				
				</td>
			</tr>
		</table>	
		<br /><br />
		
		<!-- 거래처 정보 -->
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					거래처정보
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>			
		
		<table class="order_supply">			
			<tr> 
				<td colspan="6">
					<% List<Supply> supplys = (List<Supply>)request.getAttribute("supplys"); %>
					
					<select id="supply" name="supplyNo" onchange='supplyChange(this.value)' class="width_300">
						<option value="" selected="selected"> +++거래처선택+++</option>
						<%if(supplys.size() == 0) {%>
							<option value="">거래처가 없습니다.</option>
						<%}else{ %>
							<%for(Supply s : supplys) { %>
								<option value="<%=s.getSupplyNo()%>"><%=s.getSupplyName() %></option>
							<%} %>
						<%} %>
					</select>				
					 ㆍ 담당사원<input type="text" name="name" size="10" readonly="readonly"/>
				</td>				
			</tr>
			<tr> 
				<th width="10%"> ㆍ 거래처명</th>
				<td width="90%" colspan="5">
					<input type="text" id="supplyName" name="supplyName" size="20" maxlength="100" readonly="readonly"/>
				</td>
			</tr>
			<tr> 
				<th> ㆍ 대표자</th>
				<td>
					<input type="text" id="supplyer" name="supplyer" size="15" readonly="readonly"/>
				</td>
				<th> ㆍ 전화번호</th>
				<td>
					<input type="text" id="supplyPhone" name="supplyPhone" size="13" value="" readonly="readonly"/>
				</td>
				<th> ㆍ 거래처 이메일</th>
				<td>
					<input type="text" id="supplyEmail" name="supplyEmail" size="40" readonly="readonly"/>
				</td>				
			</tr>
			<tr> 
				<th> ㆍ 거래처 주소</th>
				<td colspan="3">
					<input type="text" id="supplyAddress" name="supplyAddress" size="40" maxlength="7" readonly="readonly"/>
				</td>
				<th> ㆍ 거래처 정보</th>
				<td colspan="3">
					<input type="text" id="supplyNote" name="supplyNote" size="40" maxlength="7" readonly="readonly"/>
				</td>
			</tr>
		</table>
		<br /><br />
		
		<!-- 제품정보 신청 -->
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					제품신청정보
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>			
		
		
		<table class="list">
			<tr class="list_title">
				<th width="2%">No</th>
				<th width="15%">제품명</th>
				<th width="10%">제품유형</th>
				<th width="15%">제조사</th>
				<th width="10%">제품수량</th>
				<th width="10%">주문금액</th>
			</tr>
			
			<tr class="list_body">
				<td>01.</td>
				<td>
				<% List<Product> products = (List<Product>)request.getAttribute("products"); %>
					<select id="product" name="orderDetail[0].productNo" onchange='productChange(this.value,1);' >
						<option value="" selected="selected" >+++제품명선택+++</option>
						<% if(products.size() == 0) {%>
							<option value="">제품 품절</option>
						<%}else{ %>
							<%for(Product p : products){ %>
								<option value="<%=p.getProductNo() %>" ><%=p.getProductName() %></option>
							<%}%>
						<%} %>
					</select>				
				</td>
				<td> 
					<input type="text" id="productType1" name="productType1" size="10" value="" readonly/>
				</td>				
				<td> 
					<input type="text" id="productCompany1" name="productCompany1" size="10" value="" readonly/>
				</td>				
				<td>
					<input type="text" id="detailCount1" name="orderDetail[0].orderDetailCount" onchange="totalPriceChange()" onfocus='if(this.value = this.value){this.value = ""}' size="5" maxlength="5" style="ime-mode:disabled"/>
				</td>
				<td>
					<input type="text" id="detailPrice1" name="orderDetail[0].orderDetailPrice" onchange="totalPriceChange()" onfocus='if(this.value = this.value){this.value = ""}' size="11" value="" style="ime-mode:disabled"/> 
				</td>
			</tr>
			
			<tr class="list_body">
				<td>02.</td>
				<td>
					<select id="product" name="orderDetail[1].productNo" onchange='productChange(this.value,2);' >
						<option value="" selected="selected" >+++제품명선택+++</option>
						<% if(products.size() == 0) {%>
							<option value="">제품 품절</option>
						<%}else{ %>
							<%for(Product p : products){ %>
								<option value="<%=p.getProductNo() %>" ><%=p.getProductName() %></option>
							<%}%>
						<%} %>
					</select>				
				</td>
				<td> 
					<input type="text" id="productType2" name="productType2" size="10" value="" readonly/>
				</td>
				<td> 
					<input type="text" id="productCompany2" name="productCompany2" size="10" value="" readonly/>
				</td>
				<td>
					<input type="text" id="detailCount2" name="orderDetail[1].orderDetailCount" onchange="totalPriceChange()" onfocus='if(this.value = this.value){this.value = ""}'   size="5" maxlength="5" style="ime-mode:disabled"/>
				</td>
				<td>
					<input type="text" id="detailPrice2" name="orderDetail[1].orderDetailPrice" onchange="totalPriceChange()" size="11" value="" style="ime-mode:disabled"/> 
				</td>
			</tr>
		</table>
		<br /><br />
		
		<!-- 주문총액 -->
		<table class="title">
			<tr>
				<td>
					<img src="/machinemanager/resources/images/icon_bl_006.gif" />
					&nbsp;
					주문총액
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<br />	
					
		<table class="order_totalprice" >
			<tr> 
				<td> ㆍ 주문총액 :  
					<input type="text" id="totalPrice" name="orderTotalPrice" size="12" value="" />									
				</td>
			</tr>
		</table>
			
		</form>
		
		<br /><br />
				
		<div id="buttons">        
        	<input type="button" value="주문등록" onclick="javascript:doSubmit();" />        	
        </div>        
        
        <br/><br/>
	
</div>
</body>
</html>
