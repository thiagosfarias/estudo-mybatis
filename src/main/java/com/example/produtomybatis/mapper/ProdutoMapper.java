package com.example.produtomybatis.mapper;

import com.example.produtomybatis.model.Produto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProdutoMapper {
    void insert(@Param("produto") Produto produto);

    void update(@Param("id") Long id, @Param("produto") Produto product);

    void delete(@Param("id") Long id);

    List<Produto> findAll();

    Produto findBy(@Param("id") Long id);
}