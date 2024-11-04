package br.com.jobis.job_management.modules.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name ="candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Luiz Carlos")
    @Pattern(regexp = "^((\\b[A-zÀ-ú']{2,40}\\b)\\s*){2,}$", message = "O campo [name] não foi preenchido corretamente")
    private String name;

    @Schema(example = "Luca")
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço em branco")
    private String username;

    @Schema(example = "luca@gmail.com")
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @Schema(example = "test@1234")
    @Length(min = 8, max = 100, message = "A senha deve conter entre 8 e 100 caracteres")
    private String password;

    @Schema(example = "https://avatars.githubusercontent.com/u/7829205?v=4")
    @Pattern(regexp = "^(http|https)://[a-zA-Z0-9\\.-]+(\\.[a-zA-Z]{2,4})(:[0-9]+)?(/.*)?$", message = "Url da imagem não é válida")
    private String avatar;
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
