package Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WSREF {
    int id;
    String url;
    String description;
    int minus;
    int plus;
}
