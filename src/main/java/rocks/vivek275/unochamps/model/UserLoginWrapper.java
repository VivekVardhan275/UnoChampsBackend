package rocks.vivek275.unochamps.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginWrapper {
    private String email;
    private String password;
}
