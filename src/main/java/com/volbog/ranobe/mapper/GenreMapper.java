package com.volbog.ranobe.mapper;

import com.volbog.ranobe.dto.GenreDto;
import com.volbog.ranobe.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper extends EntityMapper<GenreDto, Genre> {

}