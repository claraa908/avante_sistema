package com.example.demo.controllers;

import com.example.demo.dto.ProdutosRequestDTO;
import com.example.demo.dto.ProdutosResponseDTO;
import com.example.demo.services.ProdutosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
@Tag(name = "Produtos", description = "Endpoints de CRUD para produtos")
public class ProdutosController {
    @Autowired
    private ProdutosService produtosService;

    @PostMapping
    @Operation(summary = "Criar um novo produto via método POST")
    public ResponseEntity<ProdutosResponseDTO> criarNovoProduto(@Valid @RequestBody ProdutosRequestDTO dto){
        ProdutosResponseDTO produto = produtosService.criarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping
    @Operation(summary = "Lista todos os produtos e se houver um id no parâmetro filtra produtos por categoria")
    public ResponseEntity<List<ProdutosResponseDTO>> listarProdutos(@RequestParam(required = false) Long categoriaID){
        List<ProdutosResponseDTO> produtos;
        if(categoriaID == null){
            produtos = produtosService.listarProdutos();
        }else{
            produtos = produtosService.listarProdutosPorCategoria(categoriaID);
        }
        return ResponseEntity.ok(produtos);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar um produto existente via método PUT")
    public ResponseEntity<ProdutosResponseDTO> atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutosRequestDTO dto){
        ProdutosResponseDTO produto = produtosService.atualizarProduto(id, dto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um produto existente via método DELETE")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        produtosService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}
