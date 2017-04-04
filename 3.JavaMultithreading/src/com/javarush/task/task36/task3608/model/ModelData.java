package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.List;

/**
 * Created by nemchinov on 13.02.2017.
 */
public class ModelData {
    private List<User> users;
    private User activeUser;
    private boolean displayDeletedUserList;
    private boolean displayDeletedUser;

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public boolean isDisplayDeletedUser() {
        return displayDeletedUser;
    }

    public void setDisplayDeletedUser(boolean displayDeletedUser) {
        this.displayDeletedUser = displayDeletedUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isDisplayDeletedUserList() {
        return displayDeletedUserList;
    }

    public void setDisplayDeletedUserList(boolean displayDeletedUserList) {
        this.displayDeletedUserList = displayDeletedUserList;
    }
}
