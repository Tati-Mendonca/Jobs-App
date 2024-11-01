package br.com.jobis.job_management.modules.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileCandidateResponseDTO {

    private String name;
    private String username;
    private String email;
    private String avatar;
    private String description;
    private String curriculum;
}
