package com.test.billing.dao;

import com.test.billing.dao.mapper.BalanceHistoryMapper;
import com.test.billing.dao.model.BalanceHistory;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
@RegisterMapper(BalanceHistoryMapper.class)
public interface BalanceHistoryDAO {
    @SqlUpdate("create table balance_history (history_id bigint, balance_id bigint, old_value float, new_value float, cre_date timestamp without time zone)")
    void createBalanceHistoryTable();

    @SqlUpdate("drop table balance_history")
    void dropBalanceHistoryTable();

    @SqlQuery("select * from balance_history where balance_id = :balance_id")
    public BalanceHistory getBalanceHistoryByBalanceId(@Bind("balance_id") Long balance_id);

    @SqlUpdate("insert into balance_history (history_id, balance_id, old_value, new_value, cre_date) values (:historyId, :balanceId, :oldValue, :newValue, :creDate)")
    void insert(@BindBean BalanceHistory balanceHistory);

    @SqlUpdate("update balance_history set old_value=:oldValue, new_value=:newValue, cre_date=:creDate where history_id=:historyId")
    void update(@BindBean BalanceHistory balanceHistory);
}
