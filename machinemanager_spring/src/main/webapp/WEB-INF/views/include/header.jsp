<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

		<div id="header">
            <div class="left">
                <a href="/machinemanager/home.action">자판기 관리 시스템</a>
            </div>

        </div>        
        <div id="menu">
            <div>
                <ul>
                    <li>
                    	<a href="#">자판기관리</a>   
                    	<ul id="first">             	
	                   		<li><a href="/machinemanager/machine/machinelist.action">자판기관리</a></li>
	                   		<li><a href="/machinemanager/machine/setuplist.action">설치관리</a></li>
	                   		<li><a href="/machinemanager/machine/withdrawlist.action">회수내역</a></li>	                   		
	                   		<li><a href="/machinemanager/machine/machinestocklist.action">입/출고내역</a></li>	   	                   		
	                   	</ul>                    	
                    </li>

                    <li>
                    	<a href="#">주문관리</a>
                    	<ul id="second">
                    		<li><a href="/machinemanager/order/orderlist.action">주문내역</a></li>
                    		<li><a href="/machinemanager/order/orderwrite.action">주문서작성</a></li>
                    	</ul>
                    </li>
					<li>
						<a href="#">재고관리</a>
						<ul id="third">
							<li><a href="/machinemanager/stock/orderinput.action?pageno=1">주문입고</a></li>
							<li><a href="/machinemanager/stock/list.action?pageno=1">재고조회</a></li>
						</ul>
					</li>
					<li>
						<a href="#">거래처관리</a>
						<ul id="fourth">
							<li><a href="/machinemanager/supply/list.action">물품거래처</a>
							<li><a href="/machinemanager/lease/leaselist.action">임대거래처</a>
						</ul>
					</li>
					<li><a href="/machinemanager/product/list.action">제품관리</a></li>
					<!-- <li><a href="#">매출관리</a></li> -->
					<li><a href="/machinemanager/stock/machineoutputform.action">판매시뮬레이션</a></li>
                </ul>
            </div>
		</div>
