package rocks.vivek275.unochamps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rocks.vivek275.unochamps.model.UserWrapper;
import rocks.vivek275.unochamps.model.UserWrapperWithCheck;
import rocks.vivek275.unochamps.service.UserRegisterService;

@RestController
@CrossOrigin
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    UserRegisterService userRegisterService;
    @PostMapping("/player")
    public ResponseEntity<UserWrapperWithCheck> registerPlayer(@RequestBody UserWrapper userWrapper) {
        try{
            return userRegisterService.registerPlayer(userWrapper);
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserWrapperWithCheck());
        }
    }
}
