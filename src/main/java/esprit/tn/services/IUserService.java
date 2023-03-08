package esprit.tn.services;

import esprit.tn.Entites.ERole;
import esprit.tn.Entites.User;

import java.util.List;

public interface IUserService {
    List<User> findAllUser();
    List<User> findUserByRole(ERole role);
    User blockUser(Long idUser);
    User unBlockUser(Long idUser);
    User updateUser (User user);
    User findUserbyId(Long idUser);
    void removeUser(Long idUser);
    boolean changePassword(String verificationCode, String newPassword);
    boolean changePasswordByUser(Long id , String password , String newPassword);

}
