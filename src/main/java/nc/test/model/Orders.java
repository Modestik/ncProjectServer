package nc.test.model;

import lombok.Data;
import java.sql.Date;

@Data
public class Orders {
    private int idOrder;
    private String pointFrom;
    private String pointTo;
    private double cost;
    private String weight;
    private String description;
    private Date startTime;
    private Date endTime;
    private String status;
    private String driver;
    private String customer;
}
