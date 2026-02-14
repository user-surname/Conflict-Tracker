package com.example.Conflict.Tracker.controller.web;

import com.example.Conflict.Tracker.dto.ConflictDTO;
import com.example.Conflict.Tracker.service.ConflictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(ConflictControlWeb.CONFLICT_WEB)
public class ConflictControlWeb {
    public static final String CONFLICT_WEB = "web/conflicts";

    @Autowired
    private ConflictService service;

    @GetMapping
    public String llistaConflicts(Model model) {
        List<ConflictDTO> conflicts = service.getAllConflicts();

        model.addAttribute("llistaConflicts", conflicts);

        return "llista-conflicts";
    }
}