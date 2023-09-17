package com.volbog.ranobe.mapper;

import com.volbog.ranobe.dto.ChapterDto;
import com.volbog.ranobe.entity.Chapter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChapterMapper extends EntityMapper<ChapterDto, Chapter> {

}