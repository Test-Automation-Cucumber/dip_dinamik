package com.testautomation.Utility;

import java.io.IOException;
import java.util.Properties;

import com.testautomation.Utility.PropertiesFileReader;


public class Login
{
	PropertiesFileReader obj;
	Properties properties;


	public Login() throws IOException {
		obj = new PropertiesFileReader();
		properties = obj.getProperty();
	}
	
	
	 public String[] mappedUsers(String userRole) {  // daha sonra DB'den getirilmeli..
	   
		 String[] loginInfo = new String[2];
		 
		 switch (userRole.toLowerCase()) {
	            case "hisse_ve_viop"                    :loginInfo[0]="tmtest"; loginInfo[1]="test12345"; return loginInfo;
	            default                                 :loginInfo[0]="tmtest"; loginInfo[1]="test12345"; return loginInfo;
	        }
  
	    }

}
