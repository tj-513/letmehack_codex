package org.codex.uom.letmehack.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by TJR on 2/24/2018.
 */
public class ErrorMessage {
    private int status;
    @JsonProperty("message")
    private String errorMessage;
    @JsonProperty("developerMessage")
    private String developerMessage;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
}
