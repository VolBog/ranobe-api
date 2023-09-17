package com.volbog.ranobe.mapper;

import com.volbog.ranobe.dto.NovelDto;
import com.volbog.ranobe.entity.Novel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NovelMapper extends EntityMapper<NovelDto, Novel> {

}