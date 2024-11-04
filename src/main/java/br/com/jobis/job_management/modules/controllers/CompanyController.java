package br.com.jobis.job_management.modules.controllers;

import br.com.jobis.job_management.modules.dtos.ProfileCandidateResponseDTO;
import br.com.jobis.job_management.modules.entities.CompanyEntity;
import br.com.jobis.job_management.modules.services.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@Tag(name = "Empresa")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/")
    @Operation(summary = "Cadastro de empresa")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CompanyEntity.class))
    })
    @ApiResponse(responseCode = "400", description = "Company already registered")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity){
        try {
            var result = this.companyService.createCompany(companyEntity);
            return  ResponseEntity.ok().body(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
