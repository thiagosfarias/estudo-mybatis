package com.example.produtomybatis.service;

import com.example.produtomybatis.mapper.ProdutoMapper;
import com.example.produtomybatis.model.Produto;
import com.example.produtomybatis.model.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService{
    private final ProdutoMapper mapper;

    @Override
    public Produto add(ProdutoDTO produtoDTO) {

        Produto produto = Produto.builder()
                .nome(produtoDTO.nome)
                .codigoBarra(produtoDTO.codigoBarra).build();

        mapper.insert(produto);

        return produto;
    }

    @Override
    public List<Produto> findAll() {
        return mapper.findAll();
    }

    @Override
    public Produto update(Long id, ProdutoDTO produtoDTO) throws NotFoundException {
        Produto produto = mapper.findBy(id);

        if(produto != null) {
            produto.setNome(produtoDTO.nome);
            produto.setCodigoBarra(produtoDTO.codigoBarra);

            mapper.update(id, produto);

            return produto;
        }

        throw new NotFoundException("Produto nao encontrado");
    }

    @Override
    public Produto findBy(Long id) throws NotFoundException {
        Produto produto = mapper.findBy(id);

        if(produto == null)
            throw new NotFoundException("Produto nao encontrado");

        return produto;
    }

    @Override
    public Produto delete(Long id) {
        Produto produto = mapper.findBy(id);
        mapper.delete(id);
        return produto;
    }
}
