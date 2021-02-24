package com.example.CatDog.controller;

import com.example.CatDog.entity.Cat;
import com.example.CatDog.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/cats")
public class CatController {
    @Autowired
    private CatService catService;

    @PostMapping()
    public @ResponseBody // Good to go
    ResponseEntity<Cat> addNewCat(@RequestParam String name, @RequestParam int age, @RequestParam String color,
                                  @RequestParam String gender, @RequestParam String breed, @RequestParam int weight){
        return catService.addCat(name, age, color, gender, breed, weight);
    }

    @GetMapping() // Good to go, postman OK
    public @ResponseBody ResponseEntity<List<Cat>> getAllCats(){
        return catService.getAllCats();
    }

    @GetMapping(path="/{id}") // Good to go
    public @ResponseBody ResponseEntity<Cat> getCatById(@PathVariable(value = "id") int catId){
        return catService.getCatById(catId);
    }

    @GetMapping(path="/search") //Good to go
    @Transactional(readOnly = true)
    public @ResponseBody
    ResponseEntity<List<Cat>> searchForCats(@RequestParam(value = "age", defaultValue = "0") String age, @RequestParam(value = "name", defaultValue = "") String name,
                                            @RequestParam(value = "color", defaultValue = "") String color, @RequestParam(value = "gender", defaultValue = "") String gender,
                                            @RequestParam(value = "breed", defaultValue = "") String breed, @RequestParam(value = "weight", defaultValue = "0") String weight){
        return catService.searchForCats(age, name, color, gender, breed, weight);
    }

    @PutMapping("/{id}")
    public @ResponseBody // Postman "Unsupported Media Type"
    ResponseEntity<Cat> updateCat(@PathVariable(value = "id") int catId, @RequestBody Cat inputCat){
        return catService.updateCat(catId, inputCat);
    }

    @DeleteMapping("/{id}") // Good to go, postman OK
    public @ResponseBody ResponseEntity<String> deleteCatById(@PathVariable(value = "id") int catId){
        return catService.deleteCatById(catId);
    }
}
