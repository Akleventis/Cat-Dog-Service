package com.example.CatDog.service;

import com.example.CatDog.repository.CatRepository;
import com.example.CatDog.entity.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CatService {
    @Autowired
    private CatRepository catRepository;

    public ResponseEntity<Cat> addCat(String name, int age, String color, String gender,  String breed,  int weight) {
        Cat cat = new Cat();
        cat.setName(name);
        cat.setAge(age);
        cat.setColor(color);
        cat.setGender(gender);
        cat.setBreed(breed);
        cat.setWeight(weight);
        try {
            catRepository.save(cat);
            return new ResponseEntity<>(cat, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(cat, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Cat>> getAllCats(){
        return new ResponseEntity<>((List<Cat>) catRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Cat> getCatById(int catId){
        boolean catExists = catRepository.findById(catId).isPresent();
        if (catExists){
            return new ResponseEntity<>(catRepository.findById(catId).get(), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Cat>> searchForCats(String age, String name, String color, String gender, String breed, String weight){
        try {
            Stream<Cat> catStream = catRepository.findCatsMatchingCriteria(age, name, color, gender, breed, weight);
            List<Cat> cats = new ArrayList<>();
            catStream.forEach((d) -> {
                cats.add(d);
            });
            return new ResponseEntity<>(cats, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<Cat>) null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Cat> updateCat(int catId, Cat inputCat) {
        boolean catExists = catRepository.findById(catId).isPresent();
        if (catExists) {
            Cat catToSave = this.setPropertiesOnNewCat(inputCat, catRepository.findById(catId).get());
            try {
                catRepository.save(catToSave);
                return new ResponseEntity<>(catToSave, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    private Cat setPropertiesOnNewCat(Cat inputCat, Cat currentCat) {
        if (inputCat.getAge() != 0) {
            currentCat.setAge(inputCat.getAge());
        }
        if (inputCat.getName() != null){
            currentCat.setName(inputCat.getName());
        }
        if (inputCat.getColor() != null){
            currentCat.setColor(inputCat.getColor());
        }
        if (inputCat.getGender() != null){
            currentCat.setGender(inputCat.getGender());
        }
        if (inputCat.getBreed() != null){
            currentCat.setBreed(inputCat.getBreed());
        }
        if (inputCat.getWeight() != 0){
            currentCat.setWeight(inputCat.getWeight());
        }
        return currentCat;
    }

    public ResponseEntity<String> deleteCatById(int catId){
        if (catRepository.findById(catId).isPresent()){
            catRepository.deleteById(catId);
            return new ResponseEntity<>("Cat has been deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Cat does not exist", HttpStatus.NOT_FOUND);
    }
}
