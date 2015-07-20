package com.machinemanager.common;

import java.text.SimpleDateFormat;
import java.util.Date;
 
public class DiffOfDate {
	
  public long diffOfDate(Date sysdate, String expdate) throws Exception
  {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    Date sysDate = sysdate;
    Date expDate = formatter.parse(expdate);
    
    long diff = expDate.getTime() - sysDate.getTime();
    long diffDays = diff / (24 * 60 * 60 * 1000);
 
    return diffDays;
  }
}