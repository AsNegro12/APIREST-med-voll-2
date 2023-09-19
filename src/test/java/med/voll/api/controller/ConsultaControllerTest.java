package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.domain.medico.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosAgendarConsulta> JSONAgendar;

    @Autowired
    private JacksonTester<DatosDetalleConsulta> JSONDetalleConsulta;

    @MockBean
    private AgendaDeConsultaService am;

    @Test
    @DisplayName("Deberia retornar estado http 400 cuando los datos ingresados sean invalidos")
    @WithMockUser
    void agendarEscenario1() throws Exception
    {
        //given //when
        var respone =  mvc.perform(post("/consultas")).andReturn().getResponse();


        //then
        assertThat(respone.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deberia retornar estado http 404 cuando los datos ingresados no sean encontrado")
    @WithMockUser
    void agendarEscenario3() throws Exception
    {
        //given
        Long id = 90l;

        //when //then
        var respone =  mvc.perform(MockMvcRequestBuilders.get("/consulta"+id))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value())).andReturn();

        assertThat(respone.getResponse().getContentAsString()).isEmpty();

    }

    @Test
    @DisplayName("Deberia retornar estado http 200 cuando los datos ingresados son validos")
    @WithMockUser
    void agendarEscenario2() throws Exception
    {
        //given
        var fecha = LocalDateTime.now().plusHours(1);
        var especialidad = Especialidad.GINECOLOGIA;
        var detalles = new DatosDetalleConsulta(null,2l,5l, fecha);

        // when

        when(am.agendar(any())).thenReturn(detalles);

        var respone =  mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONAgendar.write(new DatosAgendarConsulta(null, 2l, 5l, fecha, especialidad))
                                .getJson()))
                .andReturn().getResponse();


        //then
        assertThat(respone.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = JSONDetalleConsulta.write(detalles).getJson();

        assertThat(respone.getContentAsString()).isEqualTo(jsonEsperado);
    }
}