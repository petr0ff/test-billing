package com.test.billing.dao.mapper;

import com.test.billing.dao.model.BalanceHistory;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
public class BalanceHistoryMapper implements ResultSetMapper<BalanceHistory> {
    public BalanceHistory map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new BalanceHistory(r.getLong("history_id"), r.getLong("balance_id"), r.getFloat("old_value"), r.getFloat("new_value"), r.getDate("cre_date"));
    }
}