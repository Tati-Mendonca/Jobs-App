package br.com.jobis.job_management.modules.controllers;

import br.com.jobis.job_management.modules.dtos.AuthCompanyDTO;
import br.com.jobis.job_management.modules.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/auth")
    public ResponseEntity<Object> authorize(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            var result = this.companyService.authCompany(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
