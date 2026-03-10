package com.example.hello_spring;
import org.springframework.beans.factory.annotation.Repository;

@Repository
public interface MusicianRepository {
    MusicianProfile save(MusicianProfile profile);
    List<MusicianProfile> findAll();
    MusicianProfile findById(Long id);
    MusicianProfile findByName(String name);
}
