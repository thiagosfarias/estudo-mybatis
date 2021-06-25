package com.example.produtomybatis.service;


import com.example.produtomybatis.mapper.ProdutoMapper;
import com.example.produtomybatis.model.Produto;
import com.example.produtomybatis.model.ProdutoDTO;
import org.apache.ibatis.javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ProdutoServiceTest {
    private ProdutoMapper produtoMapper;
    private ProdutoServiceImpl produtoService;
    private ProdutoDTO produtoDTO;

    @Before
    public void before(){
        produtoMapper = mock(ProdutoMapper.class);
        produtoService = new ProdutoServiceImpl(produtoMapper);
        produtoDTO = new ProdutoDTO();
        produtoDTO.nome = "Notebook";
        produtoDTO.codigoBarra = "123468";
    }

    @Test
    public void deveBuscarPorTodosOsProdutos(){
        when(produtoMapper.findAll()).thenReturn(Arrays.asList(new Produto()));

        List<Produto> all = produtoService.findAll();

        verify(produtoMapper, times(1)).findAll();

        assertNotNull(all);
    }

    @Test
    public void deveBuscarPorUmProduto() throws NotFoundException {
        //cenario
        when(produtoMapper.findBy(anyLong())).thenReturn(new Produto());
        //acao
        Produto produto = produtoService.findBy(anyLong());

        verify(produtoMapper, times(1)).findBy(anyLong());

        //verificacao
        assertNotNull(produto);
    }

    @Test
    public void deveCriarUmNovoProduto(){
       doNothing().when(produtoMapper).insert(any());

       Produto produto = produtoService.add(produtoDTO);

       verify(produtoMapper, times(1)).insert(any(Produto.class));

       assertNotNull(produto);
    }

    @Test
    public void deveAtualizarOProduto() throws NotFoundException {
        //cenario
        doNothing().when(produtoMapper).update(anyLong(), any());

        when(produtoMapper.findBy(anyLong())).thenReturn(new Produto());

        //acao
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.nome = "iphone";
        produtoDTO.codigoBarra = "21as35d1";
        Produto produto = produtoService.update(anyLong(), produtoDTO);

        verify(produtoMapper, times(1)).update(anyLong(), any());

        assertNotNull(produto);

        assertDoesNotThrow(() -> produtoMapper.update(anyLong(), any()), "Did not thrown");
    }

    @Test
    public void deveDeletarProduto(){
        doNothing().when(produtoMapper).delete(anyLong());

        when(produtoMapper.findBy(anyLong())).thenReturn(new Produto());

        Produto produto = produtoService.delete(anyLong());

        verify(produtoMapper, times(1)).delete(anyLong());

    }



}
