package frameworkBase;


import frameworkUtils.Log;
import io.qameta.allure.Allure;
import io.restassured.response.ValidatableResponse;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.LinkedHashMap;

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
        HashMap<String, String> loginForm = new HashMap<String, String>();
        loginForm.put("user", userRow.getCell(1).toString());
        loginForm.put("password", userRow.getCell(2).toString());
        System.out.println("login details " + userRow.getCell(1).toString() + userRow.getCell(2).toString() + userRow.getCell(3).toString());


        try {
            TestBotBase.requestSpecification.basePath("");
        } catch (Exception e) {
        }
        ValidatableResponse login_response;
        login_response = expect()
                .given()
                .when()
                .get(baseuri)
                .then()
                .spec(responseSpecification);

        setFM_Bearer_token(login_response.extract().jsonPath().getString("Bearer ID"));//setting the Bearer ID
        setFM_Token_ID(login_response.extract().jsonPath().getString("TOKEN ID"));//setting the TOKEN_ID

        Log.info("Bearer Token is: "+FM_Bearer_token+"Token ID is: "+FM_Token_ID);
        getAuthData=new LinkedHashMap<>();//This will be passed in the Header of the API
        getAuthData.put("Bearer Token",TestBotBase.FM_Bearer_token);//Storing the Bearer Token
        getAuthData.put("Token ID ",TestBotBase.FM_Token_ID);//Storing the Token ID


    }

    /**
     * Custom assertion method for string with allure reporting for results
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
     * Custom assertion method for boolean with allure reporting for results
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
