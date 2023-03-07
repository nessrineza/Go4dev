package esprit.tn.services;

import esprit.tn.Entites.JwtToken;

import java.util.List;

public interface IJwtTokenService {
    List<JwtToken> getAllActiveTokens();
}