package rocks.vivek275.unochamps.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.vivek275.unochamps.model.UserLoginWrapper;
import rocks.vivek275.unochamps.model.UserWrapper;
import rocks.vivek275.unochamps.model.UserWrapperWithCheck;
import rocks.vivek275.unochamps.service.UserLoginService;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    UserLoginService userLoginService;
    @PostMapping("/player")
    public ResponseEntity<UserWrapperWithCheck> loginPlayer(@RequestBody UserLoginWrapper userLoginWrapper) {
        try {
            return userLoginService.loginPlayer(userLoginWrapper);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/admin")
    public ResponseEntity<UserWrapperWithCheck> loginAdmin(@RequestBody UserLoginWrapper userLoginWrapper) {
        try {
            return userLoginService.loginAdmin(userLoginWrapper);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
