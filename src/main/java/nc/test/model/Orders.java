package nc.test.model;

import lombok.Data;
import java.sql.Date;

@Data
public class Orders {
    private int id_order;
    private String point_from;
    private String point_to;
    private double cost;
    private String description;
    private Date start_time;
    private Date end_time;
    private String status;
    private String driver;
    private String customer;
}
