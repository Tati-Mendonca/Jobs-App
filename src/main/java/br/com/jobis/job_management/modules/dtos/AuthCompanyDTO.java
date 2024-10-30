package br.com.jobis.job_management.modules.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {
    private String password;
    private String username;
}
