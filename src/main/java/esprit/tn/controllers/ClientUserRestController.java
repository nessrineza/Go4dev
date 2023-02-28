package esprit.tn.controllers;

import esprit.tn.Entites.User;
import esprit.tn.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/user")
public class ClientUserRestController {
@Autowired
    IUserService iUserService;
    @GetMapping("/getuser/{iduser}")
    public ResponseEntity<?> findById(@PathVariable Long idUser){
        User user =  iUserService.findUser(idUser);
        return ResponseEntity.ok().body(user);
    }
}
