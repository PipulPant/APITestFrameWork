package frameworkBase;


import io.qameta.allure.Allure;
import io.restassured.response.ValidatableResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.asserts.SoftAssert;
import serviceAPIs.EmployeeDetails.EmployeeDetails_ConfigEP;

import java.util.HashMap;

import static io.restassured.RestAssured.expect;


// TODO: Auto-generated Javadoc

/**
 * The Class TestBotServiceWrapper.
 *
 * @author PipulPant
 */

public class TestBotServiceWrapper extends TestBotBase {
    /**
     * This method is used to setup new user session
     *
     * @param baseuri is the base uri of the API
     * @param userRow is the column from where we read the user data
     * @throws Exception
     */
    public static void setNewUserSession(String baseuri, XSSFRow userRow) throws Exception {
        HashMap<String, String> loginForm = new HashMap<String, String>();//creating hashmap for the login
        loginForm.put("user", userRow.getCell(1).toString());//accessing the first cell value
        loginForm.put("password", userRow.getCell(2).toString());//accessing the second cell value
        System.out.println("login details " + userRow.getCell(1).toString() + userRow.getCell(2).toString() );//printing the details


        try {
            TestBotBase.requestSpecification.basePath("");
        } catch (Exception e) {
        }
        ValidatableResponse response;
        response = expect()
                .given()
                .when()
                .get(baseuri + EmployeeDetails_ConfigEP.VIEW_EMPLOYEE_GET)//logging to the API
                .then()
                .spec(responseSpecification);



    }

    /**
     * Custom assertion method for string with allure reporting integration for results
     *
     * @param expected    expected string
     * @param actual      actual string from the response
     * @param description used for assertion of string with allure step for reporting
     */
    public static void assertionEquals(String expected, String actual, String description) {
        Allure.step("Assertion :" + description);
        SoftAssert softAssert = new SoftAssert();
        Allure.step("Expected output : " + expected);
        Allure.step(" Actual output :" + actual);
        softAssert.assertEquals(actual, expected, description);
        softAssert.assertAll();
    }

    /**
     * Custom assertion method for boolean with allure reporting integration integration for results
     *
     * @param expected    expected boolean(true/false)
     * @param actual      actual boolean from the response
     * @param description used for assertion of boolean with allure step for reporting
     */
    public static void assertionEquals(Boolean expected, Boolean actual, String description) {
        Allure.step("Assertion :" + description);
        SoftAssert softAssert = new SoftAssert();
        Allure.step("Expected output : " + expected);
        Allure.step(" Actual output :" + actual);
        softAssert.assertEquals(actual, expected, description);
        softAssert.assertAll();
    }


}
