package com.ge.exercise1;

import java.util.Collection;
import java.util.stream.Collectors;

public class ApplicationImpl extends Application {

    ApplicationImpl(String id, String name) {
        super(id, name);
    }

    public  Collection<User> getUsers(){
        return this.users;
    }

    public  User getUser(String userId) {
        Collection<User> usersFiltered = this.users.stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        return usersFiltered.iterator().next();
    }

    public  Collection<Group> getGroups() {
        return this.groups;
    }

    public  Group getGroup(String groupId) {
        Collection<Group> groupsFiltered = this.groups.stream().filter(group -> group.getId().equals(groupId)).collect(Collectors.toList());
        return groupsFiltered.iterator().next();
    }
}