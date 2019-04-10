package nc.test.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Sessions {

    public static final String BASIC = "Basic ";
    public static final String SESSION = "Session ";

    private String id;
    private String username;
    private LocalDateTime timeOfBegin;
    private LocalDateTime timeRecentActivity;
}
