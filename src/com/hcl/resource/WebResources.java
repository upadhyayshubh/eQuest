package com.hcl.resource;

public class WebResources {
	
	public static String IP_ADDRESS="192.168.0.107";// 43.225.0.106       192.168.0.107
	
	//public static String IP_ADDRESS="";// INTERNET
	
	public static String MAIN_URL="http://"+IP_ADDRESS+":8080/OnlineEamWebApp/";
	
	public static String SIGNUP_URL=MAIN_URL+"SignupServlet";
	
	public static String LOGIN_URL=MAIN_URL+"LoginServlet";
	
	public static String GET_ALL_CATEGORY_URL=MAIN_URL+"GetAllCategoryServlet";
	
	public static String GET_ALL_QUESTIONS=MAIN_URL+"GetAllQuestionsServlet";

	public static int marks=3;
	
	public static int negativemarks=0;
	
	
}
