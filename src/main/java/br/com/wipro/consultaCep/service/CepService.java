package br.com.wipro.consultaCep.service;

import br.com.wipro.consultaCep.DTO.CepDTO;
import br.com.wipro.consultaCep.DTO.CepDTOResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class CepService {

    @Autowired
    ObjectMapper mapper;

    private final RestTemplate restTemplate;

    public CepService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public CepDTOResponse consultaViaCEP(String cep) throws Exception {

        String url = new StringBuilder()
         .append("https://viacep.com.br/ws/")
         .append(cep)
         .append("/json")
         .toString();

        try {

            CepDTOResponse cepDTOResponse = this.restTemplate.getForObject(url, CepDTOResponse.class);

            if(Objects.isNull(cepDTOResponse.getCep())){
                throw new Exception("Erro ao consultar o cep");
            }

            return cepDTOResponse;

        } catch (Exception e){

            throw new Exception("Erro ao consultar o cep , CEP inválido");

        }

    }

}
