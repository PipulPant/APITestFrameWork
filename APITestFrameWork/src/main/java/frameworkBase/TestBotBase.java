package frameworkBase;

import frameworkUtils.Log;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.util.Map;
import java.util.ResourceBundle;


// TODO: Auto-generated Javadoc

/**
 * The Class TestBotBase.
 *
 * @author PIPULPANT
 */
public class TestBotBase {

    //declaring the global variable
    private static final String CONFIG_FILENAME;
    public static SoftAssert softAssert;
    public static String environmentBasePath;
    public static RequestSpecification requestSpecification = null;
    public static ResponseSpecification responseSpecification;
    public static RequestSpecBuilder requestSpecBuilder;
    public static ResponseSpecBuilder responseSpecBuilder;
    public static String statusLine_200, statusLine_400;
    private static ResourceBundle resourceBundle;
    public static Map<String, String> getAuthData = null;
    public static String FM_Bearer_token, FM_Token_ID ;

    /**
     * @param bearer_token the bearer_token to set
     */
    public static void setFM_Bearer_token(String bearer_token) {
        FM_Bearer_token = bearer_token;
    }

    /**
     * @param Token_id the Token_id to set
     */
    public static void setFM_Token_ID(String Token_id) {
        FM_Token_ID = Token_id;
    }



    /**
     * We are loading the value from the config properties using status
     * so that it can used across the test for assertion
     */
    static {

        CONFIG_FILENAME = "config"; //properties name
        resourceBundle = ResourceBundle.getBundle(CONFIG_FILENAME);//using resource bundle to read the file
        softAssert = new SoftAssert();//creating object for SA
        statusLine_200 = getProperty("HTTP_StatusLine_200");//loading statusLine_400 from properties
        statusLine_400 = getProperty("HTTP_StatusLine_400");// loading statusLine_400 from properties
    }

    /**
     * This method is used for building the response for the API with same parameter
     * ResponseSpecBuilder helps us for the assertion of various response of the api like status code
     *
     * @param baseurl basepath of the API
     */
    /*public static void setRequestResponseSpec() {

        //need to check this function once
      }*/

    /**
     * Method to read the property file value.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(final String key) {

        String propertyValue = null;//setting the initial value to null

        if (resourceBundle != null) {//checking if resource bundle is present
            propertyValue = resourceBundle.getString(key);//searching for the resource file


            Log.info("Property Value Found: " + propertyValue + " For Key: " + key);//logging the properties value
        } else {
            Log.info("Property File not loaded successfully! ");//if file is not found
        }
        return propertyValue;//returning th property value
    }

    /**
     * This method is used to set the environment for testing. Here the env is passed as a parameter.
     * Need some change
     */
    //@Parameters({"env"})//this is used to set environment
    @BeforeTest
    public void globalTestSetup(String env) {
        //String env= EmployeeDetails_ConfigEP.CREATE_EMPLOYEE_POST; remove it after completion
        TestBotBase.environmentBasePath = env;//setting environment variable
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */

    @AfterTest
    public void tearDown() throws Exception {

        //logout after the test execution
    }

}