package esprit.tn.Controller;

import esprit.tn.Entites.Partner;
import esprit.tn.Repository.PartnerRepository;
import esprit.tn.Service.IPartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerController {



 private final IPartnerService partnerService;

















}
