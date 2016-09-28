package com.test.billing.dao.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by denis.medvedev on 09.08.2016.
 */
@XmlRootElement
public class Account {
    private Long accountId;
    @XmlElement
    private String firstName;
    @XmlElement
    private String secondName;
    @XmlElement
    private String lastName;
    private Date creDate;

    public Account() {
    }

    public Account(Long accountId, String firstName, String secondName, String lastName, Date creDate) {
        this.accountId = accountId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.creDate = creDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCreDate(Date creDate) {
        this.creDate = creDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getCreDate() {
        return creDate;
    }
}
