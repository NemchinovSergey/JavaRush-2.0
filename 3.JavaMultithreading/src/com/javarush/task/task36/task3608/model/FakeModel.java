package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by nemchinov on 13.02.2017.
 */
public class FakeModel implements Model {
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List<User> users = new LinkedList<>();
        users.add(new User("UserA", 1, 1));
        users.add(new User("UserB", 2, 2));
        users.add(new User("UserC", 3, 3));
        modelData.setUsers(users);
    }
}
