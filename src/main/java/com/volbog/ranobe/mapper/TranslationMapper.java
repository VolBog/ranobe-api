package com.volbog.ranobe.mapper;

import com.volbog.ranobe.dto.TranslationDto;
import com.volbog.ranobe.entity.Translation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TranslationMapper extends EntityMapper<TranslationDto, Translation> {

}