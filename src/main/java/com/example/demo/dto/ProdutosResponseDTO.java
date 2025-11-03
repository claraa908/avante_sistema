package com.example.demo.dto;

import java.math.BigDecimal;

public class ProdutosResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriasResponseDTO categoria;

    public ProdutosResponseDTO() {}

    public Long getId() {
        return id;
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

    public CategoriasResponseDTO getCategoria() {
        return categoria;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setCategoria(CategoriasResponseDTO categoria) {
        this.categoria = categoria;
    }
}
