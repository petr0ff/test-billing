package com.test.billing.dao.mapper;

import com.test.billing.dao.model.Account;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
public class AccountMapper implements ResultSetMapper<Account> {
    public Account map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Account(r.getLong("account_id"), r.getString("first_name"), r.getString("second_name"), r.getString("last_name"), r.getDate("cre_date"));
    }
}