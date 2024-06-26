package main.helper;

import main.entity.User;
import main.helper.database.repository.UserRepository;

public class Session {
    private static User user;
    private static UserRepository userRepository;
    public static User getUser() {
        return user;
    }
    public static void login(String username) {
        User searchUser = userRepository.selectUser(username);
        if (searchUser != null) {
            Session.user = searchUser;
        } else {
            Session.user = new User(username, 0, 0, 0, 0, 0);
            userRepository.insertUser(username, 0, 0, 0, 0);
            Session.user.setId(userRepository.selectUser(username).getId());
        }
        System.out.println("Welcome, " + username + "");
    }
    public static void clearSession() {
        Session.user = null;
    }

    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static void setUserRepository(UserRepository userRepository) {
        Session.userRepository = userRepository;
    }

}
