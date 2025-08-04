package com.org.cafe_delivery.mapper;

import com.org.cafe_delivery.dto.DishDto;
import com.org.cafe_delivery.entity.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DishMapper {
//    DishMapper INSTANCE = Mappers.getMapper(DishMapper.class);

    DishDto toDto(Dish dish);

    Dish toEntity(DishDto dishDto);
}
