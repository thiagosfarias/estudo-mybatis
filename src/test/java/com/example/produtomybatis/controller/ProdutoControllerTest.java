package com.example.produtomybatis.controller;

import com.example.produtomybatis.mapper.ProdutoMapper;
import com.example.produtomybatis.model.Produto;
import com.example.produtomybatis.model.ProdutoDTO;
import com.example.produtomybatis.service.ProdutoServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class ProdutoControllerTest {
    private MockMvc mockMvc;

    private ProdutoServiceImpl produtoService;

    @Mock
    private ProdutoMapper produtoMapper;

    private ProdutoDTO produtoDTO;

    private String jsonBodyTest;

    @Before
    public void setUp(){
        produtoMapper = mock(ProdutoMapper.class);
        produtoService = new ProdutoServiceImpl(produtoMapper);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ProdutoController(produtoService))
                .build();

        produtoDTO = new ProdutoDTO();
        produtoDTO.nome = "tablet";
        produtoDTO.codigoBarra = "13156789554444500";

        jsonBodyTest = "{"+
                "\"" + "nome" + "\"" + ":" + "\"" + produtoDTO.nome + "\"" + ","
                + "\"" + "codigoBarra" + "\"" + ":" + "\"" + produtoDTO.codigoBarra + "\"" +
                "}";
    }

    @Test
    public void deveRetornarTodosOsProdutos() throws Exception {
        when(produtoMapper.findAll()).thenReturn(Arrays.asList(new Produto()));

        MvcResult result = mockMvc.perform(get("/produtos")).andExpect(status().isOk()).andReturn();

        verify(produtoMapper, times(1)).findAll();

        assertNotNull(result);
    }

    @Test
    public void deveCriarUmNovoProduto() throws Exception {
       MvcResult result = mockMvc.perform(post("/produtos")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonBodyTest))
               .andExpect(status().isCreated()).andReturn();

       assertNotNull(result);
    }

    @Test
    public void deveRetornarUmProdutoPeloSeuId() throws Exception {
        when(produtoMapper.findBy(anyLong())).thenReturn(new Produto());

        mockMvc.perform(get("/produtos/by")
                .param("id", "1"))
                .andExpect(status().isOk());

        verify(produtoMapper, times(1)).findBy(anyLong());
    }

    @Test
    public void deveDeletarUmProdutoPeloSeuId() throws Exception {
        mockMvc.perform(delete("/produtos")
                .header("id", "1"))
                .andExpect(status().isOk());

        verify(produtoMapper, times(1)).delete(anyLong());
    }

    @Test
    public void deveAtualizarUmProdutoPeloSeuId() throws Exception {
        doNothing().when(produtoMapper).update(anyLong(), any());

        when(produtoMapper.findBy(anyLong())).thenReturn(new Produto());

        produtoDTO.nome = "iPhone";
        produtoDTO.codigoBarra = "13468759800012";

        MvcResult result = mockMvc.perform(put("/produtos")
                .header("id", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBodyTest))
                .andExpect(status().isOk()).andReturn();

        verify(produtoMapper, times(1)).update(anyLong(), any());

        assertNotNull(result);
    }

}
