package com.volbog.ranobe.mapper;

import com.volbog.ranobe.dto.GlossaryTermDto;
import com.volbog.ranobe.entity.GlossaryTerm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GlossaryTermMapper extends EntityMapper<GlossaryTermDto, GlossaryTerm> {

}