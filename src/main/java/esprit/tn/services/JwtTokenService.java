package esprit.tn.services;

import esprit.tn.Entites.JwtToken;
import esprit.tn.repository.JwtTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class JwtTokenService implements IJwtTokenService{
    @Autowired
    JwtTokenRepository jwtTokenRepository;
    @Override
    public List<JwtToken> getAllActiveTokens() {
        return jwtTokenRepository.findAllByExpirationDateAfter(new Date());
    }
}
