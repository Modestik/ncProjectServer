package nc.test.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Driver {
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String carNumber;
    private String realPoint;

    public Users toUser() {
        Users users = new Users();
        users.setUsername(username)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone)
                .setCarNumber(carNumber)
                .setRealPoint(realPoint)
                .setRole("DRIVER");
        return users;
    }
}
