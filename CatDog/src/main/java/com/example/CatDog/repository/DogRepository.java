package com.example.CatDog.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.CatDog.entity.Dog;

import java.util.List;
import java.util.stream.Stream;

public interface DogRepository extends CrudRepository<Dog, Integer>{

    // when this function is run, the sql query below is run against
    // sql database
    @Query(value = "SELECT * FROM dog where age = ?1 or name = ?2 or color = ?3 or gender = ?4 or breed = ?5 or weight = ?6", nativeQuery = true)
    Stream<Dog> findDogsMatchingCriteria(String age, String name, String color, String gender, String breed, String weight);
}
