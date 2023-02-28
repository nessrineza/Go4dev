package esprit.tn.services;

import esprit.tn.Entites.User;

import java.util.List;

public interface IUserService {
    List<User> findAllUser();
    List<User> findUserByRole(String role);
    User blockUser(Long idUser);
    User unBlockUser(Long idUser);
    User updateUser (User user);
    User findUser(Long idUser);
    void removeUser(Long idUser);
}
