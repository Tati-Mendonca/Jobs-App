package br.com.jobis.job_management.modules.controllers;

import br.com.jobis.job_management.modules.dtos.ProfileCandidateResponseDTO;
import br.com.jobis.job_management.modules.entities.CandidateEntity;
import br.com.jobis.job_management.modules.entities.JobEntity;
import br.com.jobis.job_management.modules.services.CandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping("/")
    @Operation(summary = "Cadastro de candidato")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CandidateEntity.class))
    })
    @ApiResponse(responseCode = "400", description = "User already registered")
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
    @Operation(summary = "Perfil do candidato")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
    })
    @ApiResponse(responseCode = "400", description = "User Not Found")


    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object>get(HttpServletRequest request){

        var idCandidate = request.getAttribute("candidate_id");
        try{
            var profile = this.candidateService.getProfileCandidate(UUID.fromString(idCandidate.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Lista de vagas disponível para o candidato")
    @SecurityRequirement(name = "jwt_auth")
    public List <JobEntity> findJobByfilter(@RequestParam String filter){
        return this.candidateService.listAllJobsByFilter(filter);
    }

}
