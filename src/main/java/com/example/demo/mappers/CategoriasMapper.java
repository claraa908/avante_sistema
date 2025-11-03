package com.example.demo.mappers;

import com.example.demo.dto.CategoriasRequestDTO;
import com.example.demo.dto.CategoriasResponseDTO;
import com.example.demo.entities.Categorias;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoriasMapper {
    Categorias toEntity(CategoriasRequestDTO dto);
    CategoriasResponseDTO toResponseDTO(Categorias entity);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(CategoriasRequestDTO dto, @MappingTarget Categorias entity);
}