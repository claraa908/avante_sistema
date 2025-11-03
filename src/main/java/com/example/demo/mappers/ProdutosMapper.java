package com.example.demo.mappers;

import com.example.demo.dto.ProdutosRequestDTO;
import com.example.demo.dto.ProdutosResponseDTO;
import com.example.demo.entities.Produtos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CategoriasMapper.class)
public interface ProdutosMapper {
    @Mapping(target = "categoria", ignore = true)
    Produtos toEntity(ProdutosRequestDTO dto);
    ProdutosResponseDTO toResponseDTO(Produtos entity);

    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "id",  ignore = true)
    void updateEntityfromDTO(ProdutosRequestDTO dto, @MappingTarget Produtos entity);
}