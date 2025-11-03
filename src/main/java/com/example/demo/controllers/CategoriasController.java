package com.example.demo.controllers;

import com.example.demo.dto.CategoriasRequestDTO;
import com.example.demo.dto.CategoriasResponseDTO;
import com.example.demo.services.CategoriasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
@Tag(name = "Categorias", description = "Endpoints de CRUD para Categorias")
public class CategoriasController {
    @Autowired
    private CategoriasService categoriasService;

    @PostMapping
    @Operation(summary = "Criar uma nova categoria via método POST")
    public ResponseEntity<CategoriasResponseDTO> criarNovaCategoria(@Valid @RequestBody CategoriasRequestDTO dto) {
        CategoriasResponseDTO categoria = categoriasService.criarCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping
    @Operation(summary = "Listar todas as categorias via método GET")
    public ResponseEntity<List<CategoriasResponseDTO>> listarCategorias() {
        List<CategoriasResponseDTO> categorias = categoriasService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar uma categoria existente via método PUT")
    public ResponseEntity<CategoriasResponseDTO> atualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriasRequestDTO dto) {
        CategoriasResponseDTO categoria = categoriasService.atualizarCategoria(id, dto);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Remover uma categoria existente via método DELETE por um id")
    public ResponseEntity<Void> removerCategoria(@PathVariable Long id) {
        categoriasService.removerCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
