package org.acme;

import javax.enterprise.context.control.ActivateRequestContext;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;


/**
 * Main method for intercepting command line arguments
 * @param userdata The initial admin user in username:password format
 */
@QuarkusMain
public class Main {
    public static void main(String... args) {
        Quarkus.run(SimpleLogin.class, args);
    }

    public static class SimpleLogin implements QuarkusApplication {

      @Override
      @ActivateRequestContext
      public int run(String... args) throws Exception {
        
        if (args.length>0) {
            String[] userData = args[0].split(":");
            addAdmin(userData[0], userData[1]);
            }
          Quarkus.waitForExit();
          return 0;
      }

      private void addAdmin(String username, String password){
        User admin = new User();
        admin.username = username;
        admin.password = password;
        UserManager.addUser(admin);
        UserManager.addAdminRole(admin.id);
      }
    }
  }
 