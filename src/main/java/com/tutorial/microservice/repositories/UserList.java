package com.tutorial.microservice.repositories;

import com.tutorial.microservice.models.UserData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserList implements Service<UserData> {
    private static List<UserData> users = new ArrayList<>();

    private int userCount = 0;

    @Override
    public List<UserData> findAll() {
        return users;
    }

    @Override
    public UserData save(UserData data) {
        if (users.stream().anyMatch(u -> u.getName().equalsIgnoreCase(data.getName()) &&
                u.getBirthDate().equals(data.getBirthDate()))) {
            return null;
        }

        this.userCount ++;
        data.setId(this.userCount);
        users.add(data);

        return data;
    }

    @Override
    public UserData findOne(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    @Override
    public boolean delete(int id) {
        if (users.stream().anyMatch(u -> u.getId() == id)) {
            users.remove(id-1);
            return true;
        }
        return false;
    }
}
