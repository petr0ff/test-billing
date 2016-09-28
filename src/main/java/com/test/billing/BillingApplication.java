package com.test.billing;

import com.test.billing.configuration.BillingApplicationConfiguration;
import com.test.billing.dao.AccountDAO;
import com.test.billing.dao.BalanceDAO;
import com.test.billing.dao.model.Account;
import com.test.billing.health.BillingApplicationHelthCheck;
import com.test.billing.dao.model.Balance;
import com.test.billing.resource.BillingApplicationResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.Date;

public class BillingApplication extends Application<BillingApplicationConfiguration> {

    @Override
    public String getName() {
        return "test-billing";
    }

    @Override
    public void initialize(Bootstrap<BillingApplicationConfiguration> bootstrap) {
    }

    @Override
    public void run(BillingApplicationConfiguration configuration, Environment environment) {
        //1
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDatabase(), "postgresql");
        //2
        AccountDAO accountDao = jdbi.onDemand(AccountDAO.class);
        if (accountDao.checkAccountTable() == 0) {
            accountDao.createAccountTable();
            accountDao.insert(new Account(1l,"name1","name2","name3",new Date()));
        }
        BalanceDAO balanceDAO = jdbi.onDemand(BalanceDAO.class);
        if (balanceDAO.checkTable() == 0) {
            balanceDAO.createBalanceTable();
            balanceDAO.insert(new Balance(1l,15f,new Date()));
        }
        //3
        final BillingApplicationHelthCheck healthCheck = new BillingApplicationHelthCheck();
        environment.healthChecks().register("health", healthCheck);
        //4
        final BillingApplicationResource resource = new BillingApplicationResource(jdbi);
        environment.jersey().register(resource);
    }

    public static void main(String[] args) throws Exception {
        new BillingApplication().run(args);
    }
}
