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
public class TestGetAccount {
    private Account account;

    @BeforeClass
    public void setUp() {
        basePath = AutoConfig.BASE_HOST;
        baseURI = AutoConfig.ACCOUNT;
    }

    /**
     * Test-case: Retrieve valid account.
     * Step: Call GET /account method with valid account Id
     * Result: Verify that account is found, response code is 200, verify it has correct values
     */
    @Test(description = "Retrieve valid account")
    public void getAccount() {
        Response response = given().when().get(baseURI + "/1");
        Assert.assertEquals(200, response.getStatusCode());
        account = response.as(Account.class);
        Assert.assertEquals(account.getFirstName(), "name1");
        Assert.assertEquals(account.getSecondName(), "name2");
        Assert.assertEquals(account.getLastName(), "name3");
        Assert.assertEquals(account.getAccountId(), Long.valueOf("1"));
    }

    /**
     * Test-case: Retrieve invalid account.
     * Step: Call GET /account method with non existing account Id
     * Result: Verify that account is not found, response code is 404
     */
    @Test(description = "Retrieve invalid account")
    public void getInvalidAccount() {
        Response response = given().when().get(baseURI + "/2");
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
