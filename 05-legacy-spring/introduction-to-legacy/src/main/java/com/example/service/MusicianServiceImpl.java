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

}
