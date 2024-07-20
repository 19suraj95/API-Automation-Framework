package com.automation.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
	public static ExtentSparkReporter sparkreporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	private static ThreadLocal<ExtentTest> ThreadExtentTest = new ThreadLocal<ExtentTest>();  // to make the report thread safe in case parallel execution of Test
	String timeStamp=new SimpleDateFormat("yyyy.MM.DD.HH.mm.ss").format(new Date());
	String reportName="test-report-"+ timeStamp+ ".html";
	File file = new File(System.getProperty("user.dir")+ "\\Reports\\ExtentsReports\\"+reportName);
	public void onStart(ITestContext testContext) {
		
		sparkreporter = new ExtentSparkReporter(file);
		sparkreporter.config().setDocumentTitle("RestAssured API Automation");
		sparkreporter.config().setReportName("PetStore Automation Report");
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "Pet Store Users API");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Environment", "Staging");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		
	    }
	
    public void onTestStart(ITestResult result) {
		
		test=extent.createTest(result.getName());
		ThreadExtentTest.set(test);
		ThreadExtentTest.get().assignCategory(result.getMethod().getGroups());
		ThreadExtentTest.get().createNode(result.getName());
	
	    }
	
	public void onSuccess(ITestResult result) {
		
		ThreadExtentTest.get().log(Status.PASS, "Passed");
		
	    }
	
    public void onTestFailure(ITestResult result) 
        {
		
    	ThreadExtentTest.get().log(Status.FAIL, "Failed");
    	ThreadExtentTest.get().log(Status.FAIL, result.getThrowable().getMessage());
		
	    }
    
    public void onTestSkip(ITestResult result) 
    {
	
    	ThreadExtentTest.get().log(Status.SKIP, "Skipped");
    	ThreadExtentTest.get().log(Status.SKIP, result.getThrowable().getMessage());
	
    }
    
    public void onFinish(ITestContext testContext) {
    	
    	extent.flush();
    	
    }

}
