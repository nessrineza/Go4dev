package esprit.tn.controllers;

import esprit.tn.Entites.User;
import esprit.tn.payload.request.SignupRequest;
import esprit.tn.payload.response.MessageResponse;
import esprit.tn.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserRestController {
    @Autowired
    IUserService userService;

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUser( @PathVariable Long idUser) {
        try {
            userService.removeUser(idUser);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse(" Deleted successfully!"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(" Failed to delete Client!"));
        }
    }
    @GetMapping("/getAll")
    public  ResponseEntity<?> findAll(){
        List<User> users =  userService.findAllUser();
        return ResponseEntity.ok().body(users);
    }
    @PostMapping("/banclient/{id}")
    public ResponseEntity< ? > banClient(@PathVariable Long id) {
        try {
            userService.blockUser(id);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse(" blocked successfully!"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(" Failed to block employee!"));
        }
    }
    @PostMapping("/unbanclient/{id}")
    public ResponseEntity< ? > unbanClient(@PathVariable Long id) {
        try {
            userService.unBlockUser(id);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse(" un blocked successfully!"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(" Failed to unblock employee!"));
        }
    }
}
