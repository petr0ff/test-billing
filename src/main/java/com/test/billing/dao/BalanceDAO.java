package com.test.billing.dao;

import com.test.billing.dao.mapper.BalanceMapper;
import com.test.billing.dao.model.Balance;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
@RegisterMapper(BalanceMapper.class)
public interface BalanceDAO {
    @SqlUpdate("create table balance (balance_id bigint primary key, amount float, cre_date timestamp without time zone)")
    void createBalanceTable();

    @SqlUpdate("drop table balance")
    void dropBalanceTable();

    @SqlQuery("select * from balance where balance_id = :balance_id")
    public Balance getBalanceById(@Bind("balance_id") Long balance_id);

    @SqlQuery("select count(*) from pg_catalog.pg_tables where tablename='balance'")
    public int checkTable();

    @SqlUpdate("insert into balance (balance_id, amount, cre_date) values (:balanceId, :amount, :creDate)")
    void insert(@BindBean Balance balance);

    @SqlUpdate("update balance set balance_id=:balanceId, amount=:amount, cre_date=:creDate where balance_id=:balanceId")
    void update(@BindBean Balance balance);
}
