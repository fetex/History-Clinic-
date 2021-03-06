package com.qoajad.backend.model.internal.log;

import java.util.Date;
import java.util.Objects;

public class LogCreate {

    // If there is no active user id, then the following value must be set to -1.
    private final String activeUsername;
    private final String state;
    private final Date requestDate;
    private final String ip;
    private final Object data;
    private final String requestType;
    private final String eventType;

    public LogCreate(String activeUsername, String state, Date requestDate, String ip, Object data, String requestType) {
        this.activeUsername = activeUsername;
        this.state = state;
        this.requestDate = requestDate;
        this.ip = ip;
        this.data = data;
        this.requestType = requestType;
        this.eventType = data.getClass().getSimpleName();
        Objects.requireNonNull(this.state, "The state cannot be null.");
        Objects.requireNonNull(this.requestDate, "The date cannot be null.");
        Objects.requireNonNull(this.ip, "The ip cannot be null.");
        Objects.requireNonNull(this.data, "The data cannot be null.");
        Objects.requireNonNull(this.requestType, "The requestType cannot be null.");

    }

    public String getActiveUsername() {
        return activeUsername;
    }

    public String getState() {
        return state;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public String getIp() {
        return ip;
    }

    public Object getData() {
        return data;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getEventType() {
        return eventType;
    }
}
