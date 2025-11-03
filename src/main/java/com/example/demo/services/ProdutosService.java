package com.example.demo.services;

import com.example.demo.dto.ProdutosRequestDTO;
import com.example.demo.dto.ProdutosResponseDTO;
import com.example.demo.entities.Categorias;
import com.example.demo.entities.Produtos;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.mappers.ProdutosMapper;
import com.example.demo.repositories.CategoriasRepository;
import com.example.demo.repositories.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutosService {
    @Autowired
    private ProdutosRepository produtosRepository;
    @Autowired
    private ProdutosMapper produtosMapper;
    @Autowired
    private CategoriasRepository categoriasRepository;

    //Criar novos produtos
    @Transactional
    public ProdutosResponseDTO criarProduto(ProdutosRequestDTO dto){
        Produtos produtos = produtosMapper.toEntity(dto);
        Categorias categorias = categoriasRepository.findById(dto.getCategoriaID()).
                orElseThrow(() ->new ResourceNotFoundException("Categoria não encontrada."));
        produtos.setCategoria(categorias);
        produtosRepository.save(produtos);
        return produtosMapper.toResponseDTO(produtos);
    }
    //Listar todos os produtos
    @Transactional(readOnly = true)
    public List<ProdutosResponseDTO> listarProdutos(){
        List<Produtos> produtos = produtosRepository.findAll();
        List<ProdutosResponseDTO> listaProdutosResponseDTO = new ArrayList<>();
        for(Produtos p : produtos) {
            listaProdutosResponseDTO.add(produtosMapper.toResponseDTO(p));
        }
        return listaProdutosResponseDTO;
    }

    //Atualizar produtos existentes
    @Transactional
    public ProdutosResponseDTO atualizarProduto(Long id, ProdutosRequestDTO dto){
        Produtos produtoExistente = produtosRepository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException("Produto com ID " + id + " não encontrado."));
        produtosMapper.updateEntityfromDTO(dto, produtoExistente);

        if(!dto.getCategoriaID().equals(produtoExistente.getCategoria().getId())) {
            Categorias categoria = categoriasRepository.findById(dto.getCategoriaID()).
                    orElseThrow(() ->new ResourceNotFoundException("Categoria não encontrada."));
            produtoExistente.setCategoria(categoria);
        }
        Produtos produtoAtualizado = produtosRepository.save(produtoExistente);
        return produtosMapper.toResponseDTO(produtoAtualizado);
    }

    //Remover produtos
    @Transactional
    public boolean excluirProduto(Long id){
        Produtos produto = produtosRepository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException("Produto com ID " + id + " não encontrado."));
        produtosRepository.delete(produto);
        return true;
    }

    //Listar produtos de uma categoria
    @Transactional(readOnly = true)
    public List<ProdutosResponseDTO> listarProdutosPorCategoria(Long categoriaId){
        List<Produtos> listaProdutos = produtosRepository.findByCategoriaId(categoriaId);
        List<ProdutosResponseDTO> listaProdutosResponseDTO = new ArrayList<>();
        for(Produtos p : listaProdutos) {
            listaProdutosResponseDTO.add(produtosMapper.toResponseDTO(p));
        }
        return listaProdutosResponseDTO;
    }
}
