package nc.test.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MutantOperCust {
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String table;
}
