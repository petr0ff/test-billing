package com.test.billing.dao.mapper;

import com.test.billing.dao.model.Balance;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
public class BalanceMapper implements ResultSetMapper<Balance> {
    public Balance map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Balance(r.getLong("balance_id"), r.getFloat("amount"), r.getDate("cre_date"));
    }
}
