package com.org.cafe_delivery.service.impl;

import com.org.cafe_delivery.dto.DishDto;
import com.org.cafe_delivery.entity.Dish;
import com.org.cafe_delivery.exception.ResourceNotFoundException;
import com.org.cafe_delivery.mapper.DishMapper;
import com.org.cafe_delivery.repository.DishRepository;
import com.org.cafe_delivery.service.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @Transactional(readOnly = true)
    public List<DishDto> getAllDishes() {
        return dishRepository.findAll()
                .stream()
                .map(dishMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DishDto getDishById(Long id) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found with id: " + id));
        return dishMapper.toDto(dish);
    }

    @Transactional
    public DishDto createDish(DishDto dishDto) {
        dishRepository.findByName(dishDto.getName()).ifPresent(d -> {
            throw new IllegalArgumentException("Dish with name '" + dishDto.getName() + "' already exists");
        });

        Dish dish = dishMapper.toEntity(dishDto);
        Dish saved = dishRepository.save(dish);
        return dishMapper.toDto(saved);
    }

    @Transactional
    public DishDto updateDish(Long id, DishDto dishDto) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dish not found with id: " + id));

        dish.setName(dishDto.getName());
        dish.setPrice(dishDto.getPrice());
        dish.setDescription(dishDto.getDescription());

        Dish updated = dishRepository.save(dish);
        return dishMapper.toDto(updated);
    }

    @Transactional
    public void deleteDish(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dish not found with id: " + id);
        }
        dishRepository.deleteById(id);
    }
}
