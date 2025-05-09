package org.wallet.biz.entities;

import java.util.UUID;

public class Result {
    private UUID userId;
    private boolean success;

    public Result() {

    }

    public Result(boolean success) {
        this.success = success;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
