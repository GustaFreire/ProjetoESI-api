package br.usp.esi.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import br.usp.esi.api.domain.dto.RetornoDTO;
import br.usp.esi.api.domain.dto.SigInDTO;
import br.usp.esi.api.domain.dto.SignUpDTO;
import br.usp.esi.api.domain.dto.TokenDTO;
import br.usp.esi.api.domain.model.User;
import br.usp.esi.api.domain.service.TokenService;
import br.usp.esi.api.domain.util.TestDtoFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AuthenticationControllerTest {

    @Autowired //simula o disparo de requests
    private MockMvc mvc; 

    @Autowired //simula JSON de entrada
    private JacksonTester<SignUpDTO> registerRequestJson;

    @Autowired //simula JSON de saída
    private JacksonTester<RetornoDTO> registerResponseJson;

    @Autowired //simula JSON de entrada
    private JacksonTester<SigInDTO> loginRequestJson;

    @Autowired //simula JSON de saída
    private JacksonTester<TokenDTO> loginResponseJson;

    //mocks para o teste de login
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private TokenService tokenService;

    @Test
    @DisplayName("Dado que o usuáro (discente) informou os dados na tela de cadastro, ao clicar em cadastrar é devolvido HTTP 200 com o JSON")
    @WithMockUser
    void registerScenarioOk() throws Exception {
        //disparou requisicao e obteve o response, em seguida valida o status code
        var response = mvc.perform(
                post("/auth/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(registerRequestJson.write(TestDtoFactory.criaDtoCadastroDoscente())
                    .getJson())
        ).andReturn().getResponse();

        //valida se o http status veio 200 e se o JSON de response veio conforme esperado (msg de sucesso)
        var jsonResponse = registerResponseJson.write(TestDtoFactory.criaDtoResponseCadastroAluno()).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse);
    }

    @Test
    @DisplayName("Dado que o usuario (desconhecido) não informou nenhum dado na tela de cadastro, ao clicar em cadastrar é devolvido HTTP 400")
    @WithMockUser
    void registerScenarioNok() throws Exception {
        //disparou requisicao e obteve o response, em seguida valida o status code
        var response = mvc.perform(post("/auth/register")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Dado que o usuario nao informou nenhum dado no JSON de login, ao clicar em logar é devolvido HTTP 400")
    @WithMockUser
    void loginScenarioNok() throws Exception {
        //disparou requisicao e obteve o response, em seguida valida o status code
        var response = mvc.perform(post("/auth/login")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Dado que o usuario informou dado no JSON de login, ao clicar em logar é devolvido HTTP 200")
    @WithMockUser
    void loginScenarioOk() throws Exception {

        // Simular a autenticação
        Authentication auth = Mockito.mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
            .thenReturn(auth);
        
        // Simular a geração do token JWT
        String token = "mocked-jwt-token";
        when(tokenService.generateToken(any(User.class))).thenReturn(token);

        // Executar a requisição de login e validar o resultado
        var response = mvc.perform(
            post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestJson.write(TestDtoFactory.criaDtoRequestLogin())
                .getJson())
        ).andReturn().getResponse();

        //valida se o http status veio 200 e se o JSON de response veio conforme esperado (msg de sucesso)
        var jsonResponse = loginResponseJson.write(TestDtoFactory.criaDtoResponseLogin()).getJson();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse);
    }
}