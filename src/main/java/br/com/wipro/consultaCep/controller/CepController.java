package br.com.wipro.consultaCep.controller;

import br.com.wipro.consultaCep.DTO.CepDTO;
import br.com.wipro.consultaCep.DTO.CepDTOResponse;
import br.com.wipro.consultaCep.service.CepService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1/consulta-endereco")
public class CepController {

    @Autowired
    CepService service;

    @PostMapping
    public ResponseEntity buscarCEP(@RequestBody CepDTO cepDTO){
        try{
            return ResponseEntity.ok(service.consultaViaCEP(cepDTO.getCep()));
        }catch (Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
