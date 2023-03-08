package esprit.tn.controllers;

import esprit.tn.Entites.ERole;
import esprit.tn.Entites.JwtToken;
import esprit.tn.Entites.Role;
import esprit.tn.Entites.User;
import esprit.tn.payload.request.LoginRequest;
import esprit.tn.payload.request.NewPasswordRequest;
import esprit.tn.payload.request.SignupRequest;
import esprit.tn.payload.response.JwtResponse;
import esprit.tn.payload.response.MessageResponse;
import esprit.tn.repository.JwtTokenRepository;
import esprit.tn.repository.RoleRepository;
import esprit.tn.repository.UserRepository;
import esprit.tn.security.jwt.JwtUtils;
import esprit.tn.security.services.UserDetailsImpl;
import esprit.tn.services.IJwtTokenService;
import esprit.tn.services.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  IJwtTokenService jwtTokenService;
  @Autowired
  UserRepository userRepository;
  @Autowired
  RoleRepository roleRepository;
  @Autowired
  JwtTokenRepository jwtTokenRepository;
  @Autowired
  UserService userService;
  @Autowired
  PasswordEncoder encoder;
  @Autowired
  JwtUtils jwtTokenUtil;


  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws BadCredentialsException {
    if (userRepository.findByUsername(loginRequest.getUsername()).orElse(null)==null) {
      if (userRepository.existsByUsername(loginRequest.getUsername())) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: This account dose not exist!"));
      }
    }
    User user =userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
    if (user!=null){
      if (user.isBlocked()) {
        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: This account is blocked! please contact the admin at Go4Dev@outlook.com"));
      }}
    try {
      Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

      // Generate JWT token
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles = userDetails.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.toList());
      String jwt = jwtTokenUtil.generateToken(userDetails);

      // Save JWT token to database
      JwtToken jwtToken = new JwtToken();
      jwtToken.setToken(jwt);
      jwtToken.setCreatedDate(jwtTokenUtil.getIssuedDateFromToken(jwt));
      jwtToken.setExpirationDate(jwtTokenUtil.getExpirationDateFromToken(jwt));
      jwtTokenRepository.save(jwtToken);

      return ResponseEntity.ok(new JwtResponse(jwt,
              userDetails.getId(),
              userDetails.getUsername(),
              userDetails.getEmail(),
              roles));
    }
    catch (BadCredentialsException e ){
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Wrong password !"));
    }

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }


    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role clientRole = roleRepository.findByName(ERole.ROLE_CLIENT)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(clientRole);
    } else {
      strRoles.forEach(role -> {
        if ("admin".equals(role)) {
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);
        } else {
          Role clientRole = roleRepository.findByName(ERole.ROLE_CLIENT)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(clientRole);
        }
      });
    }
    /////////////////// save Employee with given input
    user.setRoles(roles);
    String randomCode = RandomString.make(64);
    user.setVerificationCode(randomCode);
    user.setBlocked(false);
    userRepository.save(user);

    /////////////////// send verification mail
    userService.sendVerificationEmail(user, userService.getSiteURL(request));

    return ResponseEntity.ok(new MessageResponse("User registered successfully, check your email to verify your account!"));
  }
  @GetMapping("/verify")
  public String verifyUser(@Param("code") String code) {
    if (userService.verify(code)) {
      return "verify_success";
    } else {
      return "verify_fail";
    }
  }

  @PostMapping("/forgotpassword/{email}")
  public ResponseEntity<?> forgotPassword( @PathVariable String email, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
    User user= userRepository.findByEmail(email);
    String randomCode = RandomString.make(64);
    user.setResetPasswordToken(randomCode);
    userRepository.save(user);
    userService.sendForgotPassword(user, userService.getSiteURL(request));
    return ResponseEntity.ok(new MessageResponse("check your email to change your password!"));
  }
  @PostMapping("/resetPasswordToken")
  public ResponseEntity<?> changePassword(@Param("code") String code,@RequestBody NewPasswordRequest newPasswordRequest) {
    if (userService.changePassword(code,encoder.encode(newPasswordRequest.getNewPassword()))) {

      return ResponseEntity
              .ok()
              .body(new MessageResponse(" Password is changed successfully!"));
    } else {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse(" Failed to change password!"));
    }
  }
  @PostMapping("/signout")
  public ResponseEntity<?> logout(HttpServletRequest request) {
    String token = jwtTokenUtil.getTokenFromRequest(request);
    JwtToken jwtToken=jwtTokenRepository.findByToken(token);
    if(jwtToken==null ){
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse(" you already are signed out!"));
    }
    jwtTokenRepository.deleteById(jwtToken.getId());
    return ResponseEntity
            .ok()
            .body(new MessageResponse( "Logout successful"));
  }

  @GetMapping("/current-users")
  public ResponseEntity<List<String>> getCurrentUsers() {
    List<String> usernames = new ArrayList<>();

    List<JwtToken> activeTokens = jwtTokenService.getAllActiveTokens();

    for (JwtToken token : activeTokens) {
      usernames.add(jwtTokenUtil.getUsernameFromToken(token.getToken()));
    }

    return ResponseEntity.ok(usernames);
  }

}

