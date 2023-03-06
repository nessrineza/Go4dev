package esprit.tn.controllers;

import esprit.tn.Entites.User;
import esprit.tn.payload.request.ChangePasswordRequest;
import esprit.tn.payload.response.MessageResponse;
import esprit.tn.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/client/user")
public class ClientUserRestController {
    @Autowired
    IUserService iUserService;
    @GetMapping("/getuser/{userId}")
    public ResponseEntity<?> findById(@PathVariable Long userId){
        if(iUserService.findUserbyId(userId)==null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("ERROR: user does not exist!"));
        }
        User user =  iUserService.findUserbyId(userId);
        return ResponseEntity.ok().body(user);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User user1= iUserService.findUserbyId(user.getId());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setLastName(user.getLastName());
        user1.setFirstName(user.getFirstName());
        iUserService.updateUser(user1);
        return ResponseEntity.ok().body(iUserService.findUserbyId(user.getId()));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        if (!iUserService.changePasswordByUser(changePasswordRequest.getId(), changePasswordRequest.getPassword(), changePasswordRequest.getNewPassword())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: you entered a wrong password!"));
        }
        return ResponseEntity
                .ok()
                .body(new MessageResponse("Password changed successfully !"));
    }
    @GetMapping("/getAll")
    public  ResponseEntity<?> findAll(){
        try {
            List<User> users =  iUserService.findAllUser();
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("ERROR: Failed to load the users list!"));
        }
    }

}
