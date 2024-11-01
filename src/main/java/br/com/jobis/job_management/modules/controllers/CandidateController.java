package br.com.jobis.job_management.modules.controllers;

import br.com.jobis.job_management.modules.dtos.ProfileCandidateResponseDTO;
import br.com.jobis.job_management.modules.entities.CandidateEntity;
import br.com.jobis.job_management.modules.services.CandidateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/post")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity){
        try {
            var result = this.candidateService.create(candidateEntity);
            return ResponseEntity.ok(result);
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object>get(HttpServletRequest request){

        var idCandidate = request.getAttribute("candidate_id");
        try{
            var profile = this.candidateService.getProfileCandidate(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
