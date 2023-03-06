package esprit.tn.security.jwt;

import esprit.tn.Entites.JwtToken;
import esprit.tn.repository.JwtTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JwtTokenCleanupService {

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @Scheduled(fixedDelay = 30 * 60 * 1000) // Runs every 30 minutes
    public void deleteExpiredTokens() {

        List<JwtToken> expiredTokens = jwtTokenRepository.findByExpirationDateBefore(new Date());
        jwtTokenRepository.deleteAll(expiredTokens);
        System.out.println("cleanup done");
    }
}
