package dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record UsuarioDto(    @NotBlank String cpf,
                             @NotBlank String nome,
                             @NotBlank @Email(message = "O email deve estar no formato valido") String email,
                             @NotBlank String senha,
                             @NotBlank String funcao,
                             @NotBlank int id_funcao,
                             Date data_admissao
) {

}