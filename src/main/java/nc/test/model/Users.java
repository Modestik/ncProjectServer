package nc.test.model;

import lombok.Data;

@Data
public class Users {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int role;
}
