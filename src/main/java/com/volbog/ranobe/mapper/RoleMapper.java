package com.volbog.ranobe.mapper;

import com.volbog.ranobe.dto.RoleDto;
import com.volbog.ranobe.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<RoleDto, Role> {

}