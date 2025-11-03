package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ProdutosRequestDTO {
    @NotBlank(message = "Nome do produto eh obrigatorio")
    @Schema(description = "Nome do produto", example = "Livro Dracula - Bram Stoker" )
    private String nome;

    @Schema(description = "Descricao do produto")
    private String descricao;

    @NotNull(message = "Preco para o produto eh obrigatorio")
    @Positive(message = "O preco deve ser maior do que 0")
    @Schema(description = "Preco de venda do produto", example = "40.00")
    private BigDecimal preco;

    @NotNull(message = "ID da categoria eh obrigatorio")
    @Schema(description = "ID da categoria à qual o produto pertence")
    private Long categoriaID;

    public ProdutosRequestDTO() {}

    public ProdutosRequestDTO(String nome, String descricao, BigDecimal preco, Long categoriaID) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriaID = categoriaID;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Long getCategoriaID() {
        return categoriaID;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setCategoriaID(Long categoriaID) {
        this.categoriaID = categoriaID;
    }
}
