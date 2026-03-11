package com.example.mapper;

import com.example.model.MusicianVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MusicianMapper {
	List<MusicianVO> selectAllMusicians();
}
