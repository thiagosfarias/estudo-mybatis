package com.example.produtomybatis.service;

import com.example.produtomybatis.model.Produto;
import com.example.produtomybatis.model.ProdutoDTO;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface ProdutoService {
    Produto add(ProdutoDTO produto);

    List<Produto> findAll();

    Produto update(Long id, ProdutoDTO produto) throws NotFoundException;

    Produto findBy(Long id) throws NotFoundException;

    Produto delete(Long id);
}
