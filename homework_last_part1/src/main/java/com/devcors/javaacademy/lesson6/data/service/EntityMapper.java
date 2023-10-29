package com.devcors.javaacademy.lesson6.data.service;

public interface EntityMapper<ENTITY, DTO> {
    ENTITY DtoToEntity(DTO dto, ENTITY entity);
    DTO EntityToDto(ENTITY entity);
}
