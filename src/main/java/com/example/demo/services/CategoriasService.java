package com.example.demo.services;

import com.example.demo.dto.CategoriasRequestDTO;
import com.example.demo.dto.CategoriasResponseDTO;
import com.example.demo.entities.Categorias;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.mappers.CategoriasMapper;
import com.example.demo.repositories.CategoriasRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriasService {
    @Autowired
    private CategoriasRepository categoriasRepository;
    @Autowired
    private CategoriasMapper categoriasMapper;

    //Criar novas categorias(save)
    @Transactional
    public CategoriasResponseDTO criarCategoria(CategoriasRequestDTO dto){
        Categorias categoriaEntidade = categoriasMapper.toEntity(dto);
        Categorias categoriaSalva =  categoriasRepository.save(categoriaEntidade);
        return categoriasMapper.toResponseDTO(categoriaSalva);
    }
    //Listar todas as categorias(findAll)
    @Transactional(readOnly = true)
    public List<CategoriasResponseDTO> listarCategorias(){
        List<Categorias> categorias = categoriasRepository.findAll();
        List<CategoriasResponseDTO> categoriasResponseDTO = new ArrayList<>();
        for(Categorias c: categorias){
            categoriasResponseDTO.add(categoriasMapper.toResponseDTO(c));
        }
        return categoriasResponseDTO;
    }
    //Atualizar categorias existentes(save)
    @Transactional
    public CategoriasResponseDTO atualizarCategoria(Long id, CategoriasRequestDTO dto){
        Categorias categoriaExistente = categoriasRepository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException("Categoria com ID " + id + " não encontrado."));
        categoriasMapper.updateEntityFromDTO(dto, categoriaExistente);
        Categorias categoriaAtualizada = categoriasRepository.save(categoriaExistente);
        return categoriasMapper.toResponseDTO(categoriaAtualizada);
    }
    //Remover categorias(deleteById)
    @Transactional
    public boolean removerCategoria(Long id){
        Categorias categoriaExistente = categoriasRepository.findById(id).
                orElseThrow(() ->new ResourceNotFoundException("Categoria com ID " + id + " não encontrado."));
        categoriasRepository.delete(categoriaExistente);
        return true;
    }
}
