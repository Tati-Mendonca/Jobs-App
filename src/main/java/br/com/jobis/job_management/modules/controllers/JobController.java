package br.com.jobis.job_management.modules.controllers;

import br.com.jobis.job_management.modules.dtos.CreateJobDTO;
import br.com.jobis.job_management.modules.entities.JobEntity;
import br.com.jobis.job_management.modules.services.JobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/post")
    public JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request){
        var companyId = request.getAttribute("company_id");

        // createJobDTO.setCompanyID(UUID.fromString(companyId.toString()));
        var jobEntity = JobEntity.builder()
                .benefits(createJobDTO.getDescription())
                .companyID(UUID.fromString(companyId.toString()))
                .level(createJobDTO.getLevel())
                .build();

        return this.jobService.createJob(jobEntity);
    }
}
