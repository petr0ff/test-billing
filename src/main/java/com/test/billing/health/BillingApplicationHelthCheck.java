package com.test.billing.health;

import com.codahale.metrics.health.HealthCheck;

public class BillingApplicationHelthCheck extends HealthCheck {

    public BillingApplicationHelthCheck() {
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}