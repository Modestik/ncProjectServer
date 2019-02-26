package nc.test.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Order {

    private int id;
    private String name;
    private float startPointLongitude;
    private float startPointWidth;
    private float endPointLongitude;
    private float endPointWidth;
    private String status;
}
