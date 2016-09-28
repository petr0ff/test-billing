package com.test.billing.resource;

import com.test.billing.dao.BalanceDAO;
import com.test.billing.dao.model.Account;
import com.test.billing.dao.AccountDAO;
import com.test.billing.dao.model.Balance;
import org.skife.jdbi.v2.DBI;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/billing")
@Produces(MediaType.APPLICATION_JSON)
public class BillingApplicationResource {

    private DBI dbi;

    public BillingApplicationResource(DBI dbi) {
        this.dbi = dbi;
    }

    /**/

    private Account doCreateAccount(Account account) {
        if (account.getAccountId() == null) {
            account.setAccountId(1l);
        }
        AccountDAO accountDAO = dbi.onDemand(AccountDAO.class);
        accountDAO.insert(account);
        return accountDAO.getAccountById(1l);
    }

    private Account doGetAccount(Long accountId) {
        AccountDAO accountDAO = dbi.onDemand(AccountDAO.class);
        return accountDAO.getAccountById(accountId);
    }

    /**/

    private Balance doCreateBalance(Balance balance) {
        if (balance.getBalanceId() == null) {
            balance.setBalanceId(1l);
        }
        BalanceDAO balanceDAO = dbi.onDemand(BalanceDAO.class);
        balanceDAO.insert(balance);
        return balanceDAO.getBalanceById(1l);
    }

    private Balance doGetBalance(Long balanceId) {
        BalanceDAO balanceDAO = dbi.onDemand(BalanceDAO.class);
        return balanceDAO.getBalanceById(balanceId);
    }

    /**/

    @GET
    @Path("/account/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("accountId") @NotNull Long accountId) {
        return doGetAccount(accountId);
    }

    @POST
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    public Account createAccount(Account account) {
        return doCreateAccount(account);
    }

    /**/

    @GET
    @Path("/balance/{balanceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Balance getBalance(@PathParam("balanceId") @NotNull Long balanceId) {
        return doGetBalance(balanceId);
    }

    @POST
    @Path("/balance")
    @Consumes(MediaType.APPLICATION_JSON)
    public Balance createBalance(Balance balance) {
        return doCreateBalance(balance);
    }
}