package com.example.hello_spring;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

// 이것은 뮤지션 프로필을 저장하는 역할을 하는 레포지토리에 대한 구현체 클래스입니다. 이 클래스는 MusicianRepository 인터페이스를 구현합니다.
@Repository
public class InMemoryMusicianRepository implements MusicianRepository {
    private final Map<Long, MusicianProfile> musicianStore = new HashMap<>();
    private long currentId = 1L;

    // 오버라이드는 인터페이스에 대한 구현체로 기존 함수 위에 덮어써서 동작합니다. 인터페이스에 정의된 함수들을 구현체에서 구현하는 것입니다.
    @Override
    public MusicianProfile save(MusicianProfile profile) {
        if (profile.getId() == null) {
            profile.setId(currentId++);
        }
        musicianStore.put(profile.getId(), profile);
        return profile;
    }

    @Override
    public List<MusicianProfile> findAll() {
        return new ArrayList<>(musicianStore.values());
    }

    @Override
    public MusicianProfile findById(Long id) {
        return musicianStore.get(id);
    }

    @Override
    public MusicianProfile findByGenre(String genre) {
        return musicianStore.values().stream()
                .filter(profile -> profile.getGenre().equals(genre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public MusicianProfile findByName(String name) {
        return musicianStore.values().stream()
                .filter(profile -> profile.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
