package frontend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    
    private int userid;
    private String user;
    private String password;
    private String fullname;
    private byte[] photo;
    private String role;
    private int state;
}
