package testSuite;


import ServiceAPIs.EmployeeDetails.EmployeeDetails_ConfigDP;
import ServiceAPIs.EmployeeDetails.EmployeeDetails_ConfigEP;
import ServiceAPIs.EmployeeDetails.EmployeeDetails_Pojo;
import com.sun.org.glassfish.gmbal.Description;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APISampleTest {

    //TestNG annotation to control the flow of the test
    @Test(description = "View all the employee details")
    @Description("Get the employee data from the API")
    public void Test01_get_Employee()
    {
        //Specify the base URL
        RestAssured.baseURI= EmployeeDetails_ConfigEP.VIEW_EMPLOYEE_GET;
        //Request Object to the server
        RequestSpecification getResponse=  RestAssured.given();
        //Response Object
        Response viewEmployee=getResponse.request(Method.GET);
        //printing the response of the API
        String responseBody=viewEmployee.getBody().asString();
        System.out.println("Response body is "+responseBody );

        //Assertion for status code
        Assert.assertEquals(viewEmployee.statusCode(),200,"Status Code Verification");
        //Assertion for Status Line Verification
        Assert.assertEquals(viewEmployee.getStatusLine(),"HTTP/1.1 200 OK","Status Code Verification");

    }

    @Test(description = "Create new employee record in database",dataProvider = "createEmployee",dataProviderClass = EmployeeDetails_ConfigDP.class)
    @Description("Create Employee Record in the database")
    public void Test02_post_EmployeeRecord(String name,String salary,String age)
    {
        //Specify the base URL
        RestAssured.baseURI= EmployeeDetails_ConfigEP.CREATE_EMPLOYEE_POST;
        //Request Object
        RequestSpecification getResponse=  RestAssured.given();
        //Request payload for post request data
        JSONObject requestPayload=new JSONObject();
        requestPayload.put("name",name);
        requestPayload.put("salary",salary);
        requestPayload.put("age",age);

        //getResponse.header("Content-Type","application/json");
        getResponse.body(requestPayload.toJSONString());

        //Response Object
        Response createEmployee=getResponse.request(Method.POST);
        //printing the response of the API
        String responseBody=createEmployee.getBody().asString();
        System.out.println("Response body is "+responseBody );

        EmployeeDetails_Pojo.setId(createEmployee.jsonPath().getString("data.id"));

        //Assertion for status code
        Assert.assertEquals(createEmployee.statusCode(),200,"Status Code Verification");
        //Assertion for Status Line Verification
        Assert.assertEquals(createEmployee.getStatusLine(),"HTTP/1.1 200 OK","Status Code Verification");
        //Assertion for Success Status
        Assert.assertEquals(createEmployee.jsonPath().get("status"),"success","Validating success message for new employee record");
        //Assertion for the success message
        Assert.assertEquals(createEmployee.jsonPath().get("message"),"Successfully! Record has been added.","Success Message Validation");
    }


    @Test(description = "Update the existing employee record in database",dependsOnMethods = {"Test02_post_EmployeeRecord"})
    @Description("Update Employee Record in the database")
    public void Test03_update_EmployeeRecord()
    {

        //Specify the base URL for PUT Request
        RestAssured.baseURI= EmployeeDetails_ConfigEP.UPDATE_EMPLOYEE_PUT;
        //Request Object
        RequestSpecification putResponse=  RestAssured.given();
        //Response Object
        Response updateEmployee=putResponse.request(Method.PUT,EmployeeDetails_Pojo.getId());
        //printing the response of the API
        String responseBody=updateEmployee.getBody().asString();
        System.out.println("Response body is "+responseBody );

        //Assertion for status code
        Assert.assertEquals(updateEmployee.statusCode(),200,"Status Code Verification");
        //Assertion for Status Line Verification
        Assert.assertEquals(updateEmployee.getStatusLine(),"HTTP/1.1 200 OK","Status Code Verification");

        //Assertion for Success Status
        Assert.assertEquals(updateEmployee.jsonPath().get("status"),"success","Validating success message for new employee record");
        //Assertion for the success message
        Assert.assertEquals(updateEmployee.jsonPath().get("message"),"Successfully! Record has been updated.","Update Message Validation");
    }
    @Test(description = "Delete the existing employee record in database",dependsOnMethods = {"Test02_post_EmployeeRecord"})
    @Description("Delete Employee Record in the database")
    public void Test04_delete_EmployeeRecord()
    {

        //Specify the base URL for PUT Request
        RestAssured.baseURI= EmployeeDetails_ConfigEP.EMPLOYEE_DELETE;
        //Request Object
        RequestSpecification putResponse=  RestAssured.given();
        //Response Object
        Response updateEmployee=putResponse.request(Method.DELETE,EmployeeDetails_Pojo.getId());
        //printing the response of the API
        String responseBody=updateEmployee.getBody().asString();
        System.out.println("Response body is "+responseBody );

        //Assertion for status code
        Assert.assertEquals(updateEmployee.statusCode(),200,"Status Code Verification");
        //Assertion for Status Line Verification
        Assert.assertEquals(updateEmployee.getStatusLine(),"HTTP/1.1 200 OK","Status Code Verification");

        //Assertion for Success Status
        Assert.assertEquals(updateEmployee.jsonPath().get("status"),"success","Validating success message for delete employee record");
        //Assertion for the success message
        Assert.assertEquals(updateEmployee.jsonPath().get("message"),"Successfully! Record has been deleted","Delete Message Validation");
    }

}
