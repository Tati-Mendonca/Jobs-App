package br.com.jobis.job_management.modules.services;

import br.com.jobis.job_management.modules.candidate.CandidateEntity;
import br.com.jobis.job_management.modules.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity create(CandidateEntity candidateEntity){
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(),candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new RuntimeException("Já existe um usuário cadastro!");
                });

        return this.candidateRepository.save(candidateEntity);
    }
}
