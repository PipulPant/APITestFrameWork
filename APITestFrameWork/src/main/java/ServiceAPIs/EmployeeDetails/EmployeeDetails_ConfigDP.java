package ServiceAPIs.EmployeeDetails;

import org.testng.annotations.DataProvider;

/**
 * This is the DataProvider Class used for the test
 */
public class EmployeeDetails_ConfigDP {


    @DataProvider(name = "createEmployee")
    public Object[][] createEmployee() {
        //String name,salary,age
        return new Object[][]
                {
                        {"Akash", "10000", "23"},

                };
    }


}