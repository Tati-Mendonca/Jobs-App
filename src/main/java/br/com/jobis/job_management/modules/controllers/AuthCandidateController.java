package br.com.jobis.job_management.modules.controllers;

import br.com.jobis.job_management.modules.dtos.AuthCandidateRequestDTO;
import br.com.jobis.job_management.modules.services.CandidateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/auth")
    @Tag(name = "Candidato")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO){

        try{
            var token = this.candidateService.AuthCandidate(authCandidateRequestDTO);
            return  ResponseEntity.ok().body(token);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
}
