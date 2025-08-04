package com.org.cafe_delivery.service;

import com.org.cafe_delivery.dto.DishDto;

import java.util.List;

public interface DishService {

    List<DishDto> getAllDishes();

    DishDto getDishById(Long id);

    DishDto createDish(DishDto dishDto);

    DishDto updateDish(Long id, DishDto dishDto);

    void deleteDish(Long id);
}
