package com.ge.exercise1;
import java.util.Collection;
import java.util.ArrayList;

public class MyParser implements Parser {
    public Application parseApplicationData(String data) {
        Application application;
        String simpleTestData = data;
        String applicationData = simpleTestData.substring(12);
        int firstCommaIndex = applicationData.indexOf(',');
        String applicationId = applicationData.substring(0,firstCommaIndex);
        String afterIdRemoval = applicationData.substring(firstCommaIndex+1);
        int secondCommaIndex = afterIdRemoval.indexOf(',');
        String applicationName = afterIdRemoval.substring(0,secondCommaIndex);
        application = new ApplicationImpl(applicationId, applicationName);
        String afterNameRemoval = afterIdRemoval.substring(secondCommaIndex+1);
        int applicationUsersIndex = afterNameRemoval.indexOf("groups");
        String applicationUsers = afterNameRemoval.substring(0, applicationUsersIndex-1);
        String applicationGroups = afterNameRemoval.substring(applicationUsersIndex);
        int userIndex = applicationUsers.indexOf("User(");
        Collection<User> users = new ArrayList<User>();
        while(userIndex > 0 && userIndex < applicationUsers.length()) {
            String userObject = applicationUsers.substring(userIndex);
            String userId = applicationUsers.substring(applicationUsers.indexOf("id")+4, applicationUsers.indexOf(","));
            String userName = applicationUsers.substring(applicationUsers.indexOf("name")+6, applicationUsers.indexOf(")"));
            users.add(new UserImpl(userId,userName));
            userIndex = applicationUsers.indexOf("User",userIndex+1);
        }
        application.setUsers(users);
        Collection<Group> groups = new ArrayList<Group>();
        int groupIndex = applicationGroups.indexOf("Group(");
        int groupUsersIndex;
        String groupUsers = "";
        while(groupIndex > 0 && groupIndex < applicationGroups.length()) {
            String groupObject = applicationGroups.substring(groupIndex);
            String groupId = groupObject.substring(groupObject.indexOf("id")+4, groupObject.indexOf(","));
            String groupName = groupObject.substring(groupObject.indexOf("name")+6, groupObject.indexOf(",users"));
            Group group = new GroupImpl(groupId, groupName);
            group.setSize(1);
            groups.add(group);
            groupUsersIndex = groupObject.indexOf("users:");
            groupUsers = groupObject.substring(groupUsersIndex+7);
            groupIndex = applicationGroups.indexOf("Group(", groupIndex+1);
        }
        application.setGroups(groups);
        return application;
    }
}
