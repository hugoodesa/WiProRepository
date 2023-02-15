package br.com.wipro.consultaCep.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CEP {

    @Min(value = 8,message = "cep deve ter no mínimo 8 digitos")
    @Max(value = 9,message = "cep deve ter no máximo 9 digitos")
    private String cep;

}
