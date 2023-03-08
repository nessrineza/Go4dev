package esprit.tn.repository;

import esprit.tn.Entites.Role;
import esprit.tn.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findUserByRoles(Role role);
  Optional<User> findByUsername(String username);
  User findByCodePromo(String codePromo);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
  User findByVerificationCode(String code);
  @Query("SELECT u FROM User u WHERE u.email = ?1")
  User findByEmail(String email);

  User findByResetPasswordToken(String token);

}
