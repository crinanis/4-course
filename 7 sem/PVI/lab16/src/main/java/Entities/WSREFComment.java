package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class WSREFComment {
    int id;
    int wsref_id;
    String session_id;
    Date stamp;
    String comment;
    String usr;
}
