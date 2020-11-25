package frameworkBase;

import java.util.Map;
import java.util.ResourceBundle;

import General.GeneralConfigEP;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import frameworkUtils.Log;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import serviceAPIs.EmployeeDetails.EmployeeDetails_ConfigEP;


// TODO: Auto-generated Javadoc

/**
 * The Class TestBotBase.
 *
 * @author PIPULPANT
 */
public class TestBotBase {

    private static final String CONFIG_FILENAME;
    private static ResourceBundle resourceBundle;
    public static SoftAssert softAssert;
    public static String environmentBasePath;
    public static RequestSpecification requestSpecification = null;
    public static ResponseSpecification responseSpecification;
    public static RequestSpecBuilder requestSpecBuilder;
    public static ResponseSpecBuilder responseSpecBuilder;
    public static String statusLine_200, statusLine_400;



    static {

        CONFIG_FILENAME = "config"; //properties name
        resourceBundle = ResourceBundle.getBundle(CONFIG_FILENAME);//using resource bundle to read the file

        softAssert = new SoftAssert();//creating object for SA
        statusLine_200 = getProperty("HTTP_StatusLine_200");//loading statusLine_400 from properties
        statusLine_400 = getProperty("HTTP_StatusLine_400");// loading statusLine_400 from properties


    }

    /**
     * This method is used to set the environment for testing. Here the env is passed as a parameter.
     */
    //@Parameters({"env"})
    @BeforeTest
    public void globalTestSetup() {
        String env= EmployeeDetails_ConfigEP.CREATE_EMPLOYEE_POST;
        TestBotBase.environmentBasePath = env;
        setRequestResponseSpec(env);
    }


    /**
     * This method is used to build requestSpecification and responseSpecification which is used in the entire test as header and response respectively
     * @param baseurl
     */
    public static void setRequestResponseSpec(String baseurl) {

        requestSpecBuilder = new RequestSpecBuilder()//creating object of RequestSpecBuilder Class
                .setBaseUri(baseurl)//setting baseURI
                .setContentType(ContentType.URLENC)//setting content type
                .log(LogDetail.ALL);//logging all the request header

        requestSpecification = requestSpecBuilder.build().filter(new AllureRestAssured());//creating allure integration for the

        responseSpecBuilder = new ResponseSpecBuilder()//creating object of ResponseSpecBuilder Class
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);//logging all the response of the API

        responseSpecification = responseSpecBuilder.build();//building a response specification for further assertions
    }



    /**
     * Method to read the property value.
     *
     * @param key the key
     * @return the property
     */
    public static String getProperty(final String key) {

        String propertyValue = null;//setting the initial value to null

        if (resourceBundle != null) {//checking for the resource file
            propertyValue = resourceBundle.getString(key);//getting all the valued for the keys


            Log.info("Property Value Found: " + propertyValue + " For Key: " + key);//logging the info for property value found
        } else {
            Log.info("Property File not loaded successfully! ");//property value not found
        }
        return propertyValue;//returning the value of the property
    }




    /**
     * Tear down.
     *
     * @throws Exception the exception
     */

    @AfterTest
    public void tearDown() throws Exception {

        //logout
    }

}