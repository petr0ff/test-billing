package integration;

import com.test.billing.dao.model.Account;
import configuration.AutoConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

/**
 * Created by German on 25.09.2016.
 */
public class TestCreateAccount {
    private Account account;

    @BeforeClass
    public void setUp() {
        basePath = AutoConfig.BASE_HOST;
        baseURI = AutoConfig.ACCOUNT;
    }

    /**
     * Test-case: Create valid account.
     * Step: Call POST /account method with valid account Id
     * Result: Verify that account is created
     */
    @Test(description = "Create valid account")
    public void createAccount() {
        Response response = given()
                .contentType("application/json")
                .request()
                .body("\"accountId\": 3, \"firstName\": \"John\", \"secondName\": \"Who\", \"thirdName\": \"Smith\"")
                .when()
                .post("");
        Assert.assertEquals(response.getStatusCode(), 201);
    }


}
