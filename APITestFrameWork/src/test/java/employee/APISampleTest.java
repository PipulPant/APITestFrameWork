package employee;


import frameworkBase.TestBotBase;
import io.qameta.allure.*;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import serviceAPIs.EmployeeDetails.EmployeeDetails_ConfigEP;

import static frameworkBase.TestBotServiceWrapper.assertionEquals;
import static io.restassured.RestAssured.expect;

public class APISampleTest extends TestBotBase {

    @Step("Step for login to the Fuse Classroom")
    @Test(description = "Verifying the employee in the organization")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get Employee details")
    @Story("Checking the employee details")
    public void Test1_viewEmployeeDetails()
    {
        //Assigning user to vehicle
        ValidatableResponse attach_user_response = expect()
                .given()
                .spec(requestSpecification)
                .when()
                .get(EmployeeDetails_ConfigEP.VIEW_EMPLOYEE_GET)
                .then()
                .spec(responseSpecification);

        String id = attach_user_response.extract().jsonPath().getString("data.id");
        System.out.println("Id is "+id);
        assertionEquals(id,"3","ID Verification");
        assertionEquals(attach_user_response.extract().jsonPath().getString("data.name"),"true red","Name verification");
        System.out.println("Name of the data is :"+attach_user_response.extract().jsonPath().getString("data.name"));


    }
}
