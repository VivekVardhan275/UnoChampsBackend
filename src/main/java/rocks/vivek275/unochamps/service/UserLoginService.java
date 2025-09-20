package rocks.vivek275.unochamps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rocks.vivek275.unochamps.model.User;
import rocks.vivek275.unochamps.model.UserLoginWrapper;
import rocks.vivek275.unochamps.model.UserWrapperWithCheck;
import rocks.vivek275.unochamps.repo.UserRepo;
import rocks.vivek275.unochamps.security.JwtUtil;

import java.util.Objects;

@Service
public class UserLoginService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    JwtUtil jwtUtil;
    public ResponseEntity<UserWrapperWithCheck> loginPlayer(UserLoginWrapper userLoginWrapper) {
        try{
            User user = userRepo.getUserByEmail(userLoginWrapper.getEmail());
            if(user != null && user.getPassword().equals(userLoginWrapper.getPassword())) {
                UserWrapperWithCheck userWrapperWithCheck = new UserWrapperWithCheck();
                userWrapperWithCheck.setTypeOfUser(user.getTypeOfUser());
                userWrapperWithCheck.setEmail(user.getEmail());
                userWrapperWithCheck.setUsername(user.getUsername());
                userWrapperWithCheck.setSuccess(Boolean.TRUE);
                String token = jwtUtil.generateToken(user.getUsername(), user.getTypeOfUser());
                userWrapperWithCheck.setToken(token);
                return new ResponseEntity<>(userWrapperWithCheck, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    public ResponseEntity<UserWrapperWithCheck> loginAdmin(UserLoginWrapper userLoginWrapper) {
        try {
            User user = userRepo.getUserByEmail(userLoginWrapper.getEmail());
            if(user != null && user.getPassword().equals(userLoginWrapper.getPassword()) && Objects.equals(user.getTypeOfUser(), "ADMIN")) {
                UserWrapperWithCheck userWrapperWithCheck = new UserWrapperWithCheck();
                userWrapperWithCheck.setTypeOfUser(user.getTypeOfUser());
                userWrapperWithCheck.setEmail(user.getEmail());
                userWrapperWithCheck.setUsername(user.getUsername());
                userWrapperWithCheck.setSuccess(Boolean.TRUE);
                String token = jwtUtil.generateToken(user.getUsername(), user.getTypeOfUser());
                userWrapperWithCheck.setToken(token);
                return new ResponseEntity<>(userWrapperWithCheck, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
