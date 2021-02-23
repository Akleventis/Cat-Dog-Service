package com.example.CatDog.service;

import com.example.CatDog.repository.DogRepository;
import com.example.CatDog.entity.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DogService {
    @Autowired
    private DogRepository dogRepository;
    
    public ResponseEntity<Dog> addDog(String name, int age, String color, String gender,  String breed,  int weight) {
        Dog dog = new Dog();
        dog.setName(name);
        dog.setAge(age);
        dog.setColor(color);
        dog.setGender(gender);
        dog.setBreed(breed);
        dog.setWeight(weight);
        try {
            dogRepository.save(dog);
            return new ResponseEntity<>(dog, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(dog, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Dog>> getAllDogs(){
        return new ResponseEntity<>((List<Dog>) dogRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Dog> getDogById(int dogId){
        boolean dogExists = dogRepository.findById(dogId).isPresent();
        if (dogExists){
            return new ResponseEntity<>(dogRepository.findById(dogId).get(), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Dog>> searchForDogs(String age, String name, String color, String gender, String breed, String weight){
        try {
            Stream<Dog> dogStream = dogRepository.findDogsMatchingCriteria(age, name, color, gender, breed, weight);
            List<Dog> dogs = new ArrayList<Dog>();
            dogStream.forEach((d) -> {
                dogs.add(d);
            });
            return new ResponseEntity<>(dogs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((List<Dog>) null, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Dog> updateDog(int dogId, Dog inputDog) {
        boolean dogExists = dogRepository.findById(dogId).isPresent();
        if (dogExists) {
            Dog dogToSave = this.setPropertiesOnNewDog(inputDog, dogRepository.findById(dogId).get());
            try {
                dogRepository.save(dogToSave);
                return new ResponseEntity<>(dogToSave, HttpStatus.OK);
            } catch (Exception e) {
                // if the dog exists but cannot be saved -> internal server error?
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        // bad request because they inputted a dog that does not exist?
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    private Dog setPropertiesOnNewDog(Dog inputDog, Dog currentDog) {
        if (inputDog.getAge() != 0) {
            currentDog.setAge(inputDog.getAge());
        }
        if (inputDog.getName() != null){
            currentDog.setName(inputDog.getName());
        }
        if (inputDog.getColor() != null){
            currentDog.setColor(inputDog.getColor());
        }
        if (inputDog.getGender() != null){
            currentDog.setGender(inputDog.getGender());
        }
        if (inputDog.getBreed() != null){
            currentDog.setBreed(inputDog.getBreed());
        }
        if (inputDog.getWeight() != 0){
            currentDog.setWeight(inputDog.getWeight());
        }
        return currentDog;
    }

    public ResponseEntity<String> deleteDogById(int dogId){
        if (dogRepository.findById(dogId).isPresent()){
            dogRepository.deleteById(dogId);
            return new ResponseEntity<>("Dog has been deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Dog does not exist", HttpStatus.BAD_REQUEST);
    }
}
