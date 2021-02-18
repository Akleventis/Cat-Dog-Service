package com.example.CatDog.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.CatDog.entity.Dog;

import java.util.List;
import java.util.stream.Stream;

public interface DogRepository extends CrudRepository<Dog, Integer>{
    // with hibernate if you're explicit about the names of your methods
    // it is smart enough to connect the name to the table and generate
    // a sql query for you - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
    List<Dog> findByAge(int age);

    // when this function is run, the sql query below is run against
    // your sql database
    @Query(value = "SELECT * FROM dog where age = ?1 or name = ?2 or color = ?3 or gender = ?4 or breed = ?5 or weight = ?6", nativeQuery = true)
    Stream<Dog> findDogsMatchingCriteria(String age, String name, String color, String gender, String breed, String weight);



    // for PUT you need to update right? is there a method that you get
    // from the CrudRepository class to help you with that?
}
