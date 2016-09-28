package com.test.billing.dao;

import com.test.billing.dao.mapper.AccountMapper;
import com.test.billing.dao.model.Account;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
@RegisterMapper(AccountMapper.class)
public interface AccountDAO {
    @SqlUpdate("create table account (account_id bigint primary key, first_name character varying(128), second_name character varying(128), last_name character varying(128), cre_date timestamp without time zone)")
    void createAccountTable();

    @SqlUpdate("drop table account")
    void dropAccountTable();

    @SqlQuery("select count(*) from pg_catalog.pg_tables where tablename='account'")
    public int checkAccountTable();

    @SqlQuery("select * from account where account_id = :account_id")
    public Account getAccountById(@Bind("account_id") Long account_id);

    @SqlUpdate("insert into account (account_id, first_name, second_name, last_name, cre_date) values (:accountId, :firstName, :secondName, :lastName, :creDate)")
    void insert(@BindBean Account account);

    @SqlUpdate("update account set account_id=:accountId, first_name=:firstName, second_name=:secondName, last_name=:lastName, cre_date=:creDate where account_id=:accountId")
    void update(@BindBean Account account);
}
