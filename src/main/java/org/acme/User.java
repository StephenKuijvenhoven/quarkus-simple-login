package org.acme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

/**
 * User entity class for persisting user data
 * If you plan on using this in production, please add some requirements
 * to the password field and restrictions to the username. 
 */

@Entity
@Table(name = "user")
@UserDefinition 
public class User extends PanacheEntity {

    @Column(unique = true)
    @Username 
    public String username;

    @Password 
    public String password;

    // Comma-separated list of roles
    @Roles
    public String roles;
}
