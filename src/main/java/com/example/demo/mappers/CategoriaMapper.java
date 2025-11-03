package com.example.demo.mappers;

import com.example.demo.dto.CategoriasRequestDTO;
import com.example.demo.dto.CategoriasResponseDTO;
import com.example.demo.entities.Categorias;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categorias toEntity(CategoriasRequestDTO dto);
    CategoriasResponseDTO toResponseDTO(Categorias entity);
}