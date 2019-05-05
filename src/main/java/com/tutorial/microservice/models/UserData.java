package com.tutorial.microservice.models;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.ManagedBean;
import java.util.Date;

@ManagedBean
@ApplicationScope
@Data
public class UserData {

    @Nullable
    private Integer id;

    private String name;

    private Date birthDate;
}
