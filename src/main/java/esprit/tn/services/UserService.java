package esprit.tn.services;

import esprit.tn.Entites.ERole;
import esprit.tn.Entites.Role;
import esprit.tn.Entites.User;
import esprit.tn.repository.RoleRepository;
import esprit.tn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public List<User> findAllUser() {
        List<User> userList = new ArrayList<>();
        userRepository.findAll().forEach(userList::add);
        return userList;
    }

    @Override
    public List<User> findUserByRole(String role) {
        Role role1 = roleRepository.findByName(ERole.valueOf(role)).get();
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
        User user1 = userRepository.findById(user.getId()).orElse(null);
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPhoneNumber(user1.getPhoneNumber());
        return userRepository.save(user1);
    }

    @Override
    public User findUser(Long idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    @Override
    public void removeUser(Long idUser) {
        userRepository.deleteById(idUser);
    }
}
