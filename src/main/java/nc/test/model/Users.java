package nc.test.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Users {

    private String username;
    private String password;
    private String role;

    //для оператора и водителя
    private String firstName;
    private String lastName;
    private String phone;
    //для водителя
    private String carNumber;
    private String realPoint;


    public Driver toDriver() {
        Driver driver = new Driver();
        driver.setUsername(username)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone)
                .setCarNumber(carNumber)
                .setRealPoint(realPoint);
        return driver;
    }

    public Operator toOperator() {
        Operator operator = new Operator();
        operator.setUsername(username)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone);
        return operator;
    }

    public Customer toCustomer() {
        Customer cust = new Customer();
        cust.setUsername(username)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone);
        return cust;
    }
}
