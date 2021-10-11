package org.acme;

import javax.transaction.Transactional;

import io.quarkus.elytron.security.common.BcryptUtil;

/**
 * The UserManager class contains all User related transactional methods
 */
public class UserManager {

    /** Default roles and SEPARATOR character */
    final static String USERROLE = "user";
    final static String ADMINROLE = "admin";
    final static String SEPARATOR = ",";

    @Transactional
    public static void addUser(User user){
        if(!userExists(user.username)){
            user.roles = USERROLE;
            user.password = BcryptUtil.bcryptHash(user.password);
            user.persist();
        } else {
        }
    }

    @Transactional
    public static void addAdminRole(Long userId){
        User user = User.findById(userId);
        if(userExists(user.username)){
            addRole(user, ADMINROLE);
            user.persist();
        }
    }

    @Transactional
    public static void addCustomRole(Long userId, String role){
        User user = User.findById(userId);
        if(userExists(user.username)){
            addRole(user, role);
            user.persist();
        }
    }

    public static String generateJWT(String username){
        User foundUser = User.find("username", username).firstResult();
        return TokenGenerator.generate(username, foundUser.roles);
    }

    private static boolean userExists(String username){
        return (User.count("username", username) > 0);
    }

    private static void addRole(User user, String role){
        user.roles = user.roles + SEPARATOR + role;
    }
}
