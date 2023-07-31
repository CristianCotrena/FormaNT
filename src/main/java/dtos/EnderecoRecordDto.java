package dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoRecordDto (@NotBlank String logradouro, @NotNull int numero,
                                @NotBlank String complemento, @NotBlank String cidade, @NotBlank String estado,
                                @NotBlank String pais, @NotBlank String cep, @NotNull int status) {
}
