package com.prueba.tecnica.proteccion.mappers;

import com.prueba.tecnica.proteccion.dto.SerieFibonacciDTO;
import com.prueba.tecnica.proteccion.entity.SerieFibonacciEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SerieFibonacciMapper {

    SerieFibonacciMapper INSTANCE = Mappers.getMapper(SerieFibonacciMapper.class);

    SerieFibonacciDTO getDTOFromEntity(SerieFibonacciEntity entity);

    SerieFibonacciEntity getEntityFromDTO(SerieFibonacciDTO dto);

    List<SerieFibonacciDTO> toDtoList(List<SerieFibonacciEntity> usuarios);

}
