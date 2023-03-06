package esprit.tn.controllers;

import esprit.tn.Entites.Role;
import esprit.tn.Entites.User;
import esprit.tn.payload.response.MessageResponse;
import esprit.tn.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserRestController {
    @Autowired
    IUserService userService;


    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUser( @PathVariable Long idUser) {
        try {
            if(userService.findUserbyId(idUser)==null){
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("ERROR: user does not exist!"));
            }
            userService.removeUser(idUser);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse(" Deleted successfully!"));

        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("ERROR: Failed to delete Client!"));
        }
    }
    @GetMapping("/getAll")
    public  ResponseEntity<?> findAll(){
        try {
            List<User> users =  userService.findAllUser();
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("ERROR: Failed to load the users list!"));
        }
    }
    @PostMapping("/banclient/{userId}")
    public ResponseEntity< ? > banClient(@PathVariable Long userId) {
        try {
            if(userService.findUserbyId(userId)==null){
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("ERROR: user does not exist!"));
            }
            userService.blockUser(userId);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("This account is blocked successfully!"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(" Failed to block this account!"));
        }
    }
    @PostMapping("/unbanclient/{userId}")
    public ResponseEntity< ? > unbanClient(@PathVariable Long userId) {
        try {
            if(userService.findUserbyId(userId)==null){
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("ERROR: user does not exist!"));
            }
            userService.unBlockUser(userId);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse(" un blocked successfully!"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(" Failed to unblock employee!"));
        }
    }
    @GetMapping("/getuser/{userId}")
    public ResponseEntity<?> findById(@PathVariable Long userId){
        if(userService.findUserbyId(userId)==null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("ERROR: user does not exist!"));
        }
        User user =  userService.findUserbyId(userId);
        return ResponseEntity.ok().body(user);
    }
    @GetMapping("/getbyrole")
    public  ResponseEntity<?> findByRole(@RequestBody Role role){
        List<User> users =  userService.findUserByRole(role.getName());
        return ResponseEntity.ok().body(users);
    }

}
