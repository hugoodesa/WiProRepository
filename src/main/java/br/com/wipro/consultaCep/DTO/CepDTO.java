package br.com.wipro.consultaCep.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CepDTO {

    @Min(value = 8,message = "cep deve ter no mínimo 8 digitos")
    @Max(value = 9,message = "cep deve ter no máximo 9 digitos")
    private String cep;

}
