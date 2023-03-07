package esprit.tn.services;

import esprit.tn.Entites.ERole;
import esprit.tn.Entites.Role;
import esprit.tn.Entites.User;
import esprit.tn.repository.RoleRepository;
import esprit.tn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Value("${spring.mail.username}")
    private String email;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public List<User> findAllUser() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    @Override
    public List<User> findUserByRole(ERole role) {
        Role role1 = roleRepository.findByName(role).orElse(null);
        List<User> users= new ArrayList<>();
        userRepository.findUserByRoles(role1).forEach(users::add);
        return users;
    }

    @Override
    public User blockUser(Long idUser) {
        User user =userRepository.findById(idUser).orElse(null);
        user.setBlocked(true);

        return userRepository.save(user);
    }

    @Override
    public User unBlockUser(Long idUser) {
        User user =userRepository.findById(idUser).orElse(null);
        user.setBlocked(false);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public User findUserbyId(Long idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    @Override
    public void removeUser(Long idUser) {
        userRepository.deleteById(idUser);
    }

    public String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "/auth");
    }



    public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
        String toAddress = user.getEmail();
        String fromAddress = email;
        String senderName = "Go4Dev";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Go4Dev.";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", user.getUsername());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        mailSender.send(message);
    }
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null) {
            return false;
        } else {
            user.setVerificationCode(null);
            userRepository.save(user);
            return true;
        }
    }


    public void sendForgotPassword(User user, String siteURL) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
        String toAddress = user.getEmail();
        String fromAddress = email;
        String senderName = "Go4Dev";
        String subject = "Change password";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to your password:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">CHANGE</a></h3>"
                + "Thank you,<br>"
                + "Go4Dev.";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[name]]", user.getUsername());
        String changePasswordURL = siteURL + "/resetPasswordToken?code=" + user.getResetPasswordToken();
        content = content.replace("[[URL]]", changePasswordURL);
        helper.setText(content, true);
        mailSender.send(message);
    }
    @Override
    public boolean changePassword(String verificationCode, String newPassword) {
        User user = userRepository.findByResetPasswordToken(verificationCode);

        if (user == null) {
            return false;
        } else {
            user.setResetPasswordToken(null);
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public boolean changePasswordByUser(Long id ,String password, String newPassword) {
        User user = userRepository.findById(id).get();

        if (!encoder.matches(password, user.getPassword())) {
            return false;
        }

        String encodedPassword = encoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return true;
    }

}
