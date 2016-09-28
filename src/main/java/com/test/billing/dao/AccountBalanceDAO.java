package com.test.billing.dao;

import com.test.billing.dao.mapper.AccountBalanceMapper;
import com.test.billing.dao.model.AccountBalance;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
@RegisterMapper(AccountBalanceMapper.class)
public interface AccountBalanceDAO {
    @SqlUpdate("create table account_balance (link_id bigint primary key, account_id bigint, balance_id bigint, cre_date timestamp without time zone)")
    void createAccountBalanceTable();

    @SqlUpdate("drop table account_balance")
    void dropAccountBalanceTable();

    @SqlQuery("select * from account_balance where account_id = :account_id")
    public AccountBalance getAccountBalanceByAccountId(@Bind("account_id") Long account_id);

    @SqlQuery("select * from account_balance where balance_id = :balance_id")
    public AccountBalance getAccountBalanceByBalanceId(@Bind("balance_id") Long balance_id);

    @SqlUpdate("insert into account_balance (link_id, account_id, balance_id, cre_date) values (:linkId, :accountId, :balanceId, :creDate)")
    void insert(@BindBean AccountBalance accountBalance);

    @SqlUpdate("update account_balance set account_id=:accountId, balance_id=:balanceId, cre_date=:creDate where link_id=:linkId")
    void update(@BindBean AccountBalance accountBalance);
}
