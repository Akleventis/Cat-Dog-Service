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
    public @ResponseBody
    ResponseEntity<Cat> addNewCat(@RequestBody Cat inputCat){
        return catService.addCat(inputCat);
    }

    @GetMapping()
    public @ResponseBody ResponseEntity<List<Cat>> getAllCats(){
        return catService.getAllCats();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Cat> getCatById(@PathVariable(value = "id") int catId){
        return catService.getCatById(catId);
    }

    @GetMapping(path="/search")
    @Transactional(readOnly = true)
    public @ResponseBody
    ResponseEntity<List<Cat>> searchForCats(@RequestParam(value = "age", defaultValue = "0") String age, @RequestParam(value = "name", defaultValue = "") String name,
                                            @RequestParam(value = "color", defaultValue = "") String color, @RequestParam(value = "gender", defaultValue = "") String gender,
                                            @RequestParam(value = "breed", defaultValue = "") String breed, @RequestParam(value = "weight", defaultValue = "0") String weight){
        return catService.searchForCats(age, name, color, gender, breed, weight);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Cat> updateCat(@PathVariable(value = "id") int catId, @RequestBody Cat inputCat){
        return catService.updateCat(catId, inputCat);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> deleteCatById(@PathVariable(value = "id") int catId){
        return catService.deleteCatById(catId);
    }
}
