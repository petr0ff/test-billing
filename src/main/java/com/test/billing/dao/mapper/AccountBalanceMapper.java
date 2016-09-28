package com.test.billing.dao.mapper;

import com.test.billing.dao.model.AccountBalance;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
public class AccountBalanceMapper implements ResultSetMapper<AccountBalance> {
    public AccountBalance map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new AccountBalance(r.getLong("link_id"), r.getLong("account_id"), r.getLong("balance_id"),r.getDate("cre_date"));
    }
}
