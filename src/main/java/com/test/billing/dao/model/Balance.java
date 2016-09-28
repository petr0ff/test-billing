package com.test.billing.dao.model;

import java.util.Date;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
public class Balance {
    private Long balanceId;
    private Float amount;
    private Date creDate;

    public Balance(Long balanceId, Float amount, Date creDate) {
        this.balanceId = balanceId;
        this.amount = amount;
        this.creDate = creDate;
    }

    public Long getBalanceId() {
        return balanceId;
    }

    public Float getAmount() {
        return amount;
    }

    public Date getCreDate() {
        return creDate;
    }

    public void setBalanceId(Long balanceId) {
        this.balanceId = balanceId;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }
}
