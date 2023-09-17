package com.volbog.ranobe.mapper;

import com.volbog.ranobe.dto.TagDto;
import com.volbog.ranobe.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper extends EntityMapper<TagDto, Tag> {

}