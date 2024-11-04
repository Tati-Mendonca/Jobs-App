package br.com.jobis.job_management.modules.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @Schema(example = "My Company Ltda.")
//    @Pattern(regexp = "^((\\b[A-zÀ-ú']{2,40}\\b)\\s*){2,}$", message = "O campo [name] não foi preenchido corretamente")
    @NotBlank(message = "O preenchimento do campo [name] é obrigatório")
    private String name;

    @Schema(example = "MyCompany")
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço em branco")
    private String username;

    @Schema(example = "mycompany@gmail.com")
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @Schema(example = "admin@123")
    @Length(min = 8, max = 100, message = "A senha deve conter entre 8 e 100 caracteres")
    private String password;

    private String website;

    private String cnpj;

    private String description;

    private  String avatar;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
