package com.example.hello_spring;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
// 이것은 서비스로 동작합니다.
// 서비스에 정의된 것들은 MusicianProfile에 인젝션됩니다.
// TODO: 맞게 이해한지 검토 후 주석 수정 필요
public class MusicianService {
    private final MusicianRepository musicianRepository;

    // 뮤지션 레포지토리를 인젝션합니다. 이것은 뮤지션 프로필을 저장하는 역할을 합니다.
    public MusicianService(MusicianRepository musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    public MusicianProfile register(String name, String gender, String genre) {
        MusicianProfile existing = musicianRepository.findByName(name);
        if (existing != null) {
            throw new IllegalArgumentException("Musician with name " + name + " already exists.");
        }

        MusicianProfile profile = new MusicianProfile();
        profile.setName(name);
        profile.setGender(gender);
        profile.setGenre(genre);

        return musicianRepository.save(profile);
    }

    public List<MusicianProfile> findAll() {
        return musicianRepository.findAll();
    }

    public MusicianProfile findByName(String name) {
        MusicianProfile profile = musicianRepository.findByName(name);
        if (profile == null) {
            throw new IllegalArgumentException("Musician with name " + name + " not found.");
        }
        return profile;
    }

    public MusicianProfile findById(Long id) {
        MusicianProfile profile = musicianRepository.findById(id);
        if (profile == null) {
            throw new IllegalArgumentException("Musician with id " + id + " not found.");
        }
        return profile;
    }

    public MusicianProfile findByGenre(String genre) {
        MusicianProfile profile = musicianRepository.findByGenre(genre);
        if (profile == null) {
            throw new IllegalArgumentException("Musician with genre " + genre + " not found.");
        }
        return profile;
    }

    public MusicianProfile changeGenre(Long id, String newGenre) {
        MusicianProfile profile = musicianRepository.findById(id);
        if (profile == null) {
            throw new IllegalArgumentException("Musician with id " + id + " not found.");
        }
        profile.setGenre(newGenre);
        return musicianRepository.save(profile);
    }
}
