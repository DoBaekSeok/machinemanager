package com.machinemanager.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
	
	//전달인자에 지정된 내용(데이터베이스 종류)에 따라 연결객체를 만들고 반환하는 메서드
	//static 으로 만든 이유는??? 
	public static Connection getConnection(String dsn) { 
		
		Connection conn = null;;
		
		if (dsn.equals("oracle")) {
			try {
				//직접 연결 객체 만들기
				Class.forName("oracle.jdbc.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@210.16.214.196:1521:xe", "thirty", "oracle");
				//conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "machine", "oracle");
				//커넥션 풀을 이용해서 연결 객체 만들기
//				Context initContext = new InitialContext();
//				Context envContext  = (Context)initContext.lookup("java:/comp/env");
//				DataSource ds = (DataSource)envContext.lookup("jdbc/demoweb");
//				conn = ds.getConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (dsn.equals("mysql")) {
			conn = null;
		} else {
			conn = null;
		}
		return conn;
	}
}