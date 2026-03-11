package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.MusicianVO;
import com.example.service.MusicianService;

@Controller
@RequestMapping("/musician")
public class MusicianController {

    @Autowired
    private MusicianService musicianService;

    @GetMapping("/list")
    public String list(Model model) {
        // 서비스 호출 -> 매퍼 호출 -> XML SQL 실행 -> DB 결과 반환
        List<MusicianVO> musician = musicianService.getMusicianList();

        model.addAttribute("musicianList", musician);
        return "musician/list";
    }
}
