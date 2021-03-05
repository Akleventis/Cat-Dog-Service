package com.example.CatDog.controller;

import com.example.CatDog.entity.Dog;
import com.example.CatDog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping(path = "/dogs")
public class DogController {
    @Autowired
    private DogService dogService;

    @PostMapping()
    public @ResponseBody
    ResponseEntity<Dog> addNewDog(@RequestParam String name, @RequestParam int age, @RequestParam String color,
                                 @RequestParam String gender, @RequestParam String breed, @RequestParam int weight){
        return dogService.addDog(name, age, color, gender, breed, weight);
    }

    @GetMapping()
    public @ResponseBody ResponseEntity<List<Dog>> getAllDogs(){
        return dogService.getAllDogs();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Dog> getDogById(@PathVariable(value = "id") int dogId){
        return dogService.getDogById(dogId);
    }

    @GetMapping(path="/search")
    @Transactional(readOnly = true)
    public @ResponseBody
    ResponseEntity<List<Dog>> searchForDogs(@RequestParam(value = "age", defaultValue = "0") String age, @RequestParam(value = "name", defaultValue = "") String name,
                            @RequestParam(value = "color", defaultValue = "") String color, @RequestParam(value = "gender", defaultValue = "") String gender,
                            @RequestParam(value = "breed", defaultValue = "") String breed, @RequestParam(value = "weight", defaultValue = "0") String weight){
        return dogService.searchForDogs(age, name, color, gender, breed, weight);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Dog> updateDog(@PathVariable(value = "id") int dogId, @RequestBody Dog inputDog){
        return dogService.updateDog(dogId, inputDog);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteDogById(@PathVariable(value = "id") int dogId){
        return dogService.deleteDogById(dogId);
    }
}
