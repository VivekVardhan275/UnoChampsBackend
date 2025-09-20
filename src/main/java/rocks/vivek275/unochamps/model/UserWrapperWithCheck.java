package rocks.vivek275.unochamps.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWrapperWithCheck {
    private Boolean success;
    private String email;
    private String username;
    private String typeOfUser;
    private String token;
}
