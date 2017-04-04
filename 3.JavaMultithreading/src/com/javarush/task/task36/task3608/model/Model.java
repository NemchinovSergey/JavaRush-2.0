package com.javarush.task.task36.task3608.model;

/**
 * Created by nemchinov on 13.02.2017.
 */
public interface Model {
    ModelData getModelData();
    void loadUsers();
    void loadDeletedUsers();
    void loadUserById(long userId);
    void deleteUserById(long id);
}
