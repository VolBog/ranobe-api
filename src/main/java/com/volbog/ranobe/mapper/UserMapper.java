package com.volbog.ranobe.mapper;

import com.volbog.ranobe.dto.UserDto;
import com.volbog.ranobe.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

}