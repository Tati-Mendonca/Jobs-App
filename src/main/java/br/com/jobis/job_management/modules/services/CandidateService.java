package br.com.jobis.job_management.modules.services;

import br.com.jobis.job_management.modules.dtos.AuthCandidateRequestDTO;
import br.com.jobis.job_management.modules.dtos.AuthCandidateResponseDTO;
import br.com.jobis.job_management.modules.dtos.ProfileCandidateResponseDTO;
import br.com.jobis.job_management.modules.entities.CandidateEntity;
import br.com.jobis.job_management.modules.repositories.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

@Service
public class CandidateService {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity create(CandidateEntity candidateEntity){
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(),candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new RuntimeException("Já existe um usuário cadastro!");
                });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }

    public AuthCandidateResponseDTO AuthCandidate(AuthCandidateRequestDTO authCandidateRequestDTO){
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(()-> {
                   throw new RuntimeException("Username/password incorrect");
                });
        var passwordMatches = this.passwordEncoder
                .matches(authCandidateRequestDTO.password(), candidate.getPassword());
        if (!passwordMatches){
            throw new RuntimeException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expireIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("jobis")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withExpiresAt(expireIn)
                .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
                .access_token(token)
                .expires_in(expireIn.toEpochMilli())
                .build();

        return authCandidateResponse;
    }

    public ProfileCandidateResponseDTO getProfileCandidate(UUID idCandidate){
        var candidate = this.candidateRepository.findById(idCandidate)
                .orElseThrow(()->{
                    throw new RuntimeException("User not found");
                });
        var candidateDTO = ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .avatar(candidate.getAvatar())
                .name(candidate.getName())
                .curriculum(candidate.getCurriculum())
                .build();
        return candidateDTO;
    }
}