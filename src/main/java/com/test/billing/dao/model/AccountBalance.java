package com.test.billing.dao.model;

import java.util.Date;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
public class AccountBalance {
    private Long linkId;
    private Long accountId;
    private Long balanceId;
    private Date creDate;

    public AccountBalance(Long linkId, Long accountId, Long balanceId, Date creDate) {
        this.linkId = linkId;
        this.accountId = accountId;
        this.balanceId = balanceId;
        this.creDate = creDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long getBalanceId() {
        return balanceId;
    }

    public Date getCreDate() {
        return creDate;
    }

    public Long getLinkId() {
        return linkId;
    }
}
