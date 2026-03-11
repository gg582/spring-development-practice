package com.example.hello_spring;

import java.util.List;

public interface MusicianRepository {
    MusicianProfile save(MusicianProfile profile);
    List<MusicianProfile> findAll();
    MusicianProfile findById(Long id);
    MusicianProfile findByName(String name);
    MusicianProfile findByGenre(String genre);
}
