package br.com.jobis.job_management.modules.company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

//    @Pattern(regexp = "^((\\b[A-zÀ-ú']{2,40}\\b)\\s*){2,}$", message = "O campo [name] não foi preenchido corretamente")
    @NotBlank(message = "O preenchimento do campo [name] é obrigatório")
    private String name;

    @Length(min = 8, max = 100, message = "O campo [username] deve conter entre 8 e 100 caracteres")
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @Length(min = 8, max = 100, message = "A senha deve conter entre 8 e 100 caracteres")
    private String password;

    private String website;

    private String cnpj;

    private String description;

    private  String avatar;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
