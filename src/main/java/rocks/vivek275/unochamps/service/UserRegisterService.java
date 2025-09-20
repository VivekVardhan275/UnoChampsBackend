package rocks.vivek275.unochamps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rocks.vivek275.unochamps.model.User;
import rocks.vivek275.unochamps.model.UserWrapper;
import rocks.vivek275.unochamps.model.UserWrapperWithCheck;
import rocks.vivek275.unochamps.repo.UserRepo;
import rocks.vivek275.unochamps.security.JwtUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRegisterService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    JwtUtil jwtUtil;
    public ResponseEntity<UserWrapperWithCheck> registerPlayer(UserWrapper userWrapper) {
        try{
            UserWrapperWithCheck userWrapperWithCheck = new UserWrapperWithCheck();
            User user = new User();
            List<User> users = userRepo.findAll();
            List<String> usernames = users.stream().map(User::getUsername).collect(Collectors.toList());
            if (userWrapper.getUsername() != null && !usernames.contains(userWrapper.getUsername())) {
                user.setEmail(userWrapper.getEmail());
                user.setUsername(userWrapper.getUsername());
                user.setPassword(userWrapper.getPassword());
                user.setTypeOfUser("PLAYER");
                userRepo.save(user);
                userWrapperWithCheck.setSuccess(Boolean.TRUE);
                userWrapperWithCheck.setUsername(user.getUsername());
                userWrapperWithCheck.setEmail(user.getEmail());
                userWrapperWithCheck.setTypeOfUser("PLAYER");
                String token = jwtUtil.generateToken(userWrapper.getUsername(), "PLAYER");
                userWrapperWithCheck.setToken(token);
                return new ResponseEntity<>(userWrapperWithCheck, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }

        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
