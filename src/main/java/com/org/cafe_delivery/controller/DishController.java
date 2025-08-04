package com.org.cafe_delivery.controller;

import com.org.cafe_delivery.dto.DishDto;
import com.org.cafe_delivery.service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping
    public ResponseEntity<List<DishDto>> getAll() {
        return ResponseEntity.ok(dishService.getAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dishService.getDishById(id));
    }

    @PostMapping
    public ResponseEntity<DishDto> create(@Valid @RequestBody DishDto dishDto) {
        return ResponseEntity.status(201).body(dishService.createDish(dishDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDto> update(@PathVariable Long id, @Valid @RequestBody DishDto dishDto) {
        return ResponseEntity.ok(dishService.updateDish(id, dishDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}