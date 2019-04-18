package nc.test.model;

import lombok.Data;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class Orders {
    private int idOrder;
    private String pointFrom;
    private String pointTo;
    private double cost;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String driver;
    private String customer;
}
