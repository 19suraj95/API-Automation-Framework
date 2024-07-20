package com.automation.TestBase;

import static io.restassured.RestAssured.*;

import com.automation.payloadbean.UserBean;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserServiceModule extends BaseClass{
	
	public static Response createUser(UserBean payload){
		baseURI=Base_URL;
		basePath=Path;
		
		Response response=given()
		.header("accept" , ContentType.JSON)
		.header("Content-Type",ContentType.JSON)
		.pathParam("user", "user")
		.when().body(payload)
		.post("/{user}");
		
		return response;
		
	}
	
	
	public static Response getUser(String userName){
		baseURI=Base_URL;
		basePath=Path;
		
		Response response=given()
		.header("accept" , ContentType.JSON)
		.pathParam("user", "user")
		.pathParam("UserName", userName)
		.get("/{user}/{UserName}");
		
		return response;
		
		
		
	}
	
	public static Response createUserwitharray(UserBean payload){
		baseURI=Base_URL;
		basePath=Path;
		
		Response response=given()
		.header("accept" , ContentType.JSON)
		.header("Content-Type",ContentType.JSON)
		.pathParam("user", "user")
		.pathParam("create", "createWithArray")
		.when().body(payload)
		.post("/{user}/{create}");
		
		return response;
		
	}
	
	
	public static Response UpdateUserName(String username ,UserBean payload){
		baseURI=Base_URL;
		basePath=Path;
		
		Response response=given()
		.header("accept" , ContentType.JSON)
		.header("Content-Type",ContentType.JSON)
		.pathParam("user", "user")
	    .pathParam("username", username)
		.when().body(payload)
		.put("/{user}/{username}");
		
		return response;
		
	}
	
	
	
	
	public static Response deleteUser(String username){
		baseURI=Base_URL;
		basePath=Path;
		
		Response response=given()
		.header("accept" , ContentType.JSON)
		.header("Content-Type",ContentType.JSON)
		.pathParam("user", "user")
		.pathParam("username", username)
		.delete("/{user}/{username}");
		
		return response;
		
	}
	
	
	

}
