package br.com.jobis.job_management.modules.services;

import br.com.jobis.job_management.modules.entities.JobEntity;
import br.com.jobis.job_management.modules.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity createJob(JobEntity jobEntity){
        return this.jobRepository.save(jobEntity);
    }
}
