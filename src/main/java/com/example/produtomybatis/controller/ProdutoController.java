package com.example.produtomybatis.controller;

import com.example.produtomybatis.model.Produto;
import com.example.produtomybatis.model.ProdutoDTO;
import com.example.produtomybatis.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodosProdutos(){
        List<Produto> produtos = produtoService.findAll();

        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping(value = "/by")
    public ResponseEntity<Produto> buscaPorProduto(@RequestParam(value = "id", required = true) Long id) throws NotFoundException {
        Produto produto = produtoService.findBy(id);

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody ProdutoDTO produto){
        Produto novoProduto = produtoService.add(produto);

        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Produto> atualizarProduto(@RequestHeader(value = "id", required = true) Long id, @RequestBody ProdutoDTO produto) throws NotFoundException {
        Produto produtoAtualizado = produtoService.update(id, produto);

        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<Produto> deletarProduto(@RequestHeader(value = "id", required = true) Long id){
        Produto produtoDeletado = produtoService.delete(id);

        return new ResponseEntity<>(produtoDeletado, HttpStatus.OK);
    }


}
