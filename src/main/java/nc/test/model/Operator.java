package nc.test.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Operator {
    private String username;
    private String firstName;
    private String lastName;
    private String phone;

    public Users toUser() {
        Users users = new Users();
        users.setUsername(username)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone)
                .setRole("OPERATOR");
        return users;
    }
}
