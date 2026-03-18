package com.example.service;

import com.example.model.MusicianVO;
import java.util.List;

public interface MusicianService {
	List<MusicianVO> getMusicianList();
	void register(MusicianVO vo);
	void update(MusicianVO vo);
	void deleteById(Long id);
	void deleteByName(String name);
}
