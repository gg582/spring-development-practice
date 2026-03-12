package com.example.service;

import com.example.model.MusicianVO;
import com.example.mapper.MusicianMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MusicianServiceImpl implements MusicianService {
	@Autowired
	private MusicianMapper musicianMapper;

	@Override
	public List<MusicianVO> getMusicianList() {
		return musicianMapper.selectAllMusicians();
	}

	@Override
	public void register(MusicianVO vo) {
		musicianMapper.insert(vo); // 매퍼의 insert를 호출
	}

	@Override
	public void update(MusicianVO vo) {
		musicianMapper.update(vo);
	}

	@Override
	public void deleteById(Long id) {
		musicianMapper.deleteById(id); //매퍼의 deleteById를 호출
	}

	@Override
	public void deleteByName(String name) {
		musicianMapper.deleteByName(name);
	}

}
