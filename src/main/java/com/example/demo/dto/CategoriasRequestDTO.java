package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class CategoriasRequestDTO {
    @NotBlank(message = "Nome da categoria eh obrigatorio")
    @Schema(description = "Nome da categoria", example = "Classico")
    private String nome;

    @Schema(description = "Descricao da categoria", example = "Livros classicos...")
    private String descricao;

    public CategoriasRequestDTO() {
    }

    public CategoriasRequestDTO(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
