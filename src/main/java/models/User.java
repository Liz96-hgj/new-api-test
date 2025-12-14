package models;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;

}
