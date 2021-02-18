package com.example.CatDog.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.CatDog.entity.Cat;

import java.util.stream.Stream;

public interface CatRepository extends CrudRepository<Cat, Integer>{


    @Query(value = "SELECT * FROM cat WHERE age = ?1 or name = ?2 or color = ?3 or gender = ?4 or breed = ?5 or weight = ?6", nativeQuery = true)
    Stream<Cat> findCatsMatchingCriteria(String age, String name, String color, String gender, String breed, String weight);

}
