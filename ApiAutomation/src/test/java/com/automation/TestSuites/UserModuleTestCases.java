package com.automation.TestSuites;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.TestBase.UserServiceModule;
import com.automation.payloadbean.UserBean;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class UserModuleTestCases {
	Response response;
	public Logger log;
	
	Faker faker;
	UserBean userbean;
	
	@BeforeClass
	public void setup() {
		
		faker= new Faker();
		userbean = new UserBean();
		
		userbean.setID(faker.number().hashCode());
		userbean.setUsername(faker.name().username());
		userbean.setFirstName(faker.name().firstName());
		userbean.setLastName(faker.name().lastName());
		userbean.setEmail(faker.internet().safeEmailAddress());
		userbean.setPassword(faker.internet().password(5, 10));
		userbean.setPhone(faker.phoneNumber().cellPhone());

		log = LogManager.getLogger(this.getClass());
	}
	
	@Test  (priority=1 , enabled=true , groups="API")
	public void CreateuserTest() {
		
		log.info("********************Creating User*************************");
		
		response=UserServiceModule.createUser(userbean);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test  (priority=2 , enabled=true , groups="API")
	public void getUserTest() {
		log.info("********************Reading User*************************");
		response=UserServiceModule.getUser(userbean.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test  (priority=3 , enabled=true , groups="API")
	public void updateUserTest() {
		log.info("********************Updating User Name*************************");
		userbean.setUsername(faker.name().username());
		String s=userbean.getUsername();
		response=UserServiceModule.UpdateUserName(s, userbean);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	// getting the updated data	
		log.info("********************Printing Updated User Details*************************");
		response=UserServiceModule.getUser(userbean.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	@Test (priority=4 , enabled=true , groups="API")
	public void deleteUserTest() {
		log.info("********************Deleting User*************************");
		response=UserServiceModule.getUser(userbean.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
