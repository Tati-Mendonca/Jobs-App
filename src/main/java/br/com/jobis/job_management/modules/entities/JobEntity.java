package br.com.jobis.job_management.modules.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

//    @NotBlank(message = "O campo [category] é obrigatório")
    private String category;

    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;

    @Column(name ="company_id", nullable = false)
    private UUID companyID;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
