package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.model.service.UserService;

/**
 * Created by nemchinov on 13.02.2017.
 */
public class MainModel implements Model {
    private UserService userService;

    @Override
    public ModelData getModelData() {
        return null;
    }

    @Override
    public void loadUsers() {

    }
}
