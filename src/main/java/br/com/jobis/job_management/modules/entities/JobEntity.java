package br.com.jobis.job_management.modules.job;

import br.com.jobis.job_management.modules.company.CompanyEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name="job")
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @NotBlank(message = "O preenchimento do campo [name] é obrigatório")
    private String name;
    private String description;
    private String benefits;

    @NotBlank(message = "O preenchimento do campo [level] é obrigatório")
    private String level;
    private Double wage;

    @NotBlank(message = "O campo [category] é obrigatório")
    private String category;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;

    @Column(name ="company_id", nullable = false)
    private UUID companyID;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
