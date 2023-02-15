package br.com.wipro.consultaCep;

import br.com.wipro.consultaCep.DTO.CepDTO;
import br.com.wipro.consultaCep.DTO.CepDTOResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class ConsultaCepApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	@Description("Deve retonar sucesso ao consultar o cep")
	void deveRetornarUmaRespostaDoViaCep() throws Exception {

		var cepDTO = new CepDTO("88745000");
		URI uri = new URI("/v1/consulta-endereco");

		var cep = mapper.writeValueAsString(cepDTO);

		var result = this.mockMvc.perform(
						MockMvcRequestBuilders.post(uri)
						.contentType(MediaType.APPLICATION_JSON)
						.content(cep)
				).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		var cepDTOResponse = this.mapper.readValue(result, CepDTOResponse.class);

		assertEquals("88745-000", cepDTOResponse.getCep());
		assertEquals("Capivari de Baixo", cepDTOResponse.getLocalidade());
	}

	@Test
	@Description("Deve retornar um badRequest")
	void deveRetornarUmaErroAoConsultarOCep() throws Exception {

		var cepDTO = new CepDTO("88884444");
		String cep = this.mapper.writeValueAsString(cepDTO);
		URI uri = new URI("/v1/consulta-endereco");

		this.mockMvc
			.perform(MockMvcRequestBuilders
					.post(uri)
					.contentType(MediaType.APPLICATION_JSON)
					.content(cep)
			)
			.andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));

	}

}
