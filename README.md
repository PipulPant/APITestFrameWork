# APITestFramework
Sample Rest-assured API TestFramework
===============================================
Rest Assured : is an API designed for automating REST services/Rest API’s. Rest assured itself is an API to test the other API. 
Rest assured is a API which is used to test the other APIs so we can define the rest assured as: it is an API which is designed for automating the the rest services or rest APIs . 
Rest assured is available in the form of jar files so basically the rest assured is implemented using Java language and they have lot of classes and methods and by which we can automate out APIs.
We can send in multiple type of request and we can get the response from the API and we can do multiple type of validation on APIs . 
Official Website: https://rest-assured.io/

Pre-Requisites:
===============
1. Java Installation
2. Eclipse/ IntelliJ IDEA -> IDE for writing the code
3. TestNG -> Framework implemented on top of Java used for organising test cases, test suites. Unit testing framework. Parallel execution and report generation
4. Maven: -> Build tool used for creating build, automating infrastructure, execute test using CMD or Jenkins.

Setup
=====
1) Creating Maven project in Eclipse/IntelliJ IDEA
2) We need to update pom.xml with required dependencies

 - RestAssured  https://mvnrepository.com/artifact/io.rest-assured/rest-assured
 - TestNG       https://mvnrepository.com/artifact/org.testng/testng
 - Json-simple  https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple
 - apache poi   https://mvnrepository.com/artifact/org.apache.poi/poi
                https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml 

RequestSpecification is an interface that allows you to specify how the request will look like.
This interface has readymade methods to define base URL, base path, headers, etc. 
We need to use given() method of RestAssured class to get a reference for RequestSpecification

How to write test
==================
1. We are writing our test cases in src/test/java.
2. Inside Java folder we are creating package.Inside package we can add our testcases.
3. Inside the package Click New -> Create Java Class with suitable name.
4. Inside the Java class we can have multiple test scripts.

Following format to be followed for writing the test cases:
===========================================================
@Test(description = "Name of the test cases")

@Description("What is the testcases for")

public void Test01_get_Employee(). -> TestCases name
{
//TestSteps
}

How to run
==========
1. To run the test scripts, we include all the test in testng.xml file.
2. To create the xml file, we store the file in test/resources/testSuites
3. In the xml file, we will specify the class name and all the test cases in the particular test
4. Right Click -> Hit Run
5. This will execute all the test that are include in the xml file.

How to check the  error
=======================
1. During the execution of the test cases if any error are found, our IDE gives us complete information about the error. 
2. Simply click the error line in the console window and it will redirect you to the particular test.

How to Report
=============
1. The TestNG will generate the default report.
When you execute testng.xml file, all the passed and failed test cases are displayed in the console.
