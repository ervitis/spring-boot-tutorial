package com.tutorial.microservice.models;

import lombok.Data;
import java.util.Date;

@Data
public class ApiError {
    private Date date;

    private String message;

    public ApiError(String message) {
        this.date = new Date();
        this.message = message;
    }
}
