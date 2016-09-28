package com.test.billing.dao.model;

import java.util.Date;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
public class BalanceHistory {
    private Long historyId;
    private Long balanceId;
    private Float oldValue;
    private Float newValue;
    private Date creDate;

    public BalanceHistory(Long historyId, Long balanceId, Float oldValue, Float newValue, Date creDate) {
        this.historyId = historyId;
        this.balanceId = balanceId;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.creDate = creDate;
    }

    public Long getHistoryId() {
        return historyId;
    }

    public Long getBalanceId() {
        return balanceId;
    }

    public Float getOldValue() {
        return oldValue;
    }

    public Float getNewValue() {
        return newValue;
    }

    public Date getCreDate() {
        return creDate;
    }
}
