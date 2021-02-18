package com.example.CatDog.controller;

import com.example.CatDog.repository.CatRepository;
import com.example.CatDog.entity.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
@RequestMapping(path = "/cats")
public class CatController {
    @Autowired
    private CatRepository catRepository;

    @PostMapping()
    public @ResponseBody
    String addNewCat(@RequestParam String name, @RequestParam int age, @RequestParam String color,
                     @RequestParam String gender, @RequestParam String breed, @RequestParam int weight) {
        Cat cat = new Cat();
        cat.setName(name);
        cat.setAge(age);
        cat.setColor(color);
        cat.setGender(gender);
        cat.setBreed(breed);
        cat.setWeight(weight);
        catRepository.save(cat);
        return "Saved cat";

    }
    @GetMapping()
    public @ResponseBody Iterable<Cat> getAllCats(){
        return catRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Optional<Cat> getCatById(@PathVariable(value= "id") int catId){
        return catRepository.findById(catId);
    }

    @GetMapping(path="/search")
    @Transactional(readOnly = true)
    public @ResponseBody
    List<Cat> searchForCats(@RequestParam(value = "age", defaultValue = "0") String age, @RequestParam(value = "name", defaultValue = "") String name,
                            @RequestParam(value = "color", defaultValue = "") String color, @RequestParam(value = "gender", defaultValue = "") String gender,
                            @RequestParam(value = "breed", defaultValue = "") String breed, @RequestParam(value = "weight", defaultValue = "0") String weight){
        try {
            Stream<Cat> catStream = catRepository.findCatsMatchingCriteria(age, name, color, gender, breed, weight);
            List<Cat> cats = new ArrayList<Cat>();
            catStream.forEach((c) -> {
                cats.add(c);
            });
            return cats;
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/{id}")
    public @ResponseBody
    String updateCat(@PathVariable(value="id") int catId, @RequestParam(value = "age", defaultValue = "0") String age, @RequestParam(value = "name", defaultValue = "") String name,
                     @RequestParam(value = "color", defaultValue = "") String color, @RequestParam(value = "gender", defaultValue = "") String gender,
                     @RequestParam(value = "breed", defaultValue = "") String breed, @RequestParam(value = "weight", defaultValue = "0") String weight){
        if (catRepository.existsById(catId)) {
            Cat cat = catRepository.findById(catId).get();
            if (!age.equals("0")){
                cat.setAge(Integer.parseInt(age));
            }
            if (!name.equals("")){
                cat.setName(name);
            }
            if (!color.equals("")){
                cat.setColor(color);
            }
            if (!gender.equals("")){
                cat.setGender(gender);
            }
            if (!breed.equals("")){
                cat.setBreed(breed);
            }
            if (!weight.equals("")){
                cat.setWeight(Integer.parseInt(weight));
            }
            catRepository.save(cat);
            return "Cat has been updated";
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteCatById(@PathVariable(value = "id") int catId){
        catRepository.deleteById(catId);
        return "Deleted Cat";
    }

}

