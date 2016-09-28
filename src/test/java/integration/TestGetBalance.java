package integration;

import com.test.billing.dao.model.Account;
import com.test.billing.dao.model.Balance;
import configuration.AutoConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

/**
 * Created by German on 25.09.2016.
 */
public class TestGetBalance {
    private Balance balance;

    @BeforeClass
    public void setUp() {
        basePath = AutoConfig.BASE_HOST;
        baseURI = AutoConfig.BALANCE;
    }

    /**
     * Test-case: Retrieve valid balance.
     * Step: Call GET /balance method with valid balance Id
     * Result: Verify that balance is found, response code is 200, verify it has correct values
     */
    @Test(description = "Retrieve valid balance")
    public void getBalance() {
        Response response = given().when().get(baseURI + "/1");
        Assert.assertEquals(200, response.getStatusCode());
        balance = response.as(Balance.class);
        Assert.assertEquals(balance.getAmount(), 15.0);
        Assert.assertEquals(balance.getBalanceId(), Long.valueOf(1));
    }

    /**
     * Test-case: Retrieve invalid balance.
     * Step: Call GET /balance method with non existing balance Id
     * Result: Verify that balance is not found, response code is 404
     */
    @Test(description = "Retrieve invalid balance")
    public void getInvalidBalance() {
        Response response = given().when().get(baseURI + "/2666");
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
