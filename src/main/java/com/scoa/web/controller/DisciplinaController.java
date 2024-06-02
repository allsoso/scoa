package com.scoa.web.controller;

import com.scoa.web.dto.DisciplinaDto;
import com.scoa.web.models.Disciplina;
import com.scoa.web.service.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DisciplinaController {
    private DisciplinaService disciplinaService;

    @Autowired
    private DisciplinaController(DisciplinaService disciplinaService){
        this.disciplinaService = disciplinaService;
    }

    @GetMapping("/disciplinas")
    public String listDisciplinas(Model model){
        List<DisciplinaDto> disciplinas = disciplinaService.findAllDisciplinas();
        model.addAttribute("disciplinas",disciplinas);
        return "disciplinas-list";
    }
    @PostMapping("/disciplinas")
    public String filterDisciplinas(
            @RequestParam(value = "filterNome", required = false) String nome,
            @RequestParam(value = "filterCodigo", required = false) String codigo,
            Model model){
        List<DisciplinaDto> disciplinas;
        if (nome != null || codigo != null) {
            disciplinas = disciplinaService.findDisciplinasByParams(nome, codigo);
        } else {
            disciplinas = disciplinaService.findAllDisciplinas();
        }
        model.addAttribute("disciplinas",disciplinas);
        return "disciplinas-list";
    }
    @GetMapping("/disciplinas/new")
    public String createDisciplinaForm(Model model){
        Disciplina disciplina = new Disciplina();
        model.addAttribute("disciplina",disciplina);
        return "disciplinas-new";
    }
    @PostMapping("/disciplinas/new")
    public String saveDisciplina(@Valid @ModelAttribute("disciplina") DisciplinaDto disciplinaDto,
                            BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("disciplina", disciplinaDto);
            return "disciplinas-new";
        }
        disciplinaService.saveDisciplina(disciplinaDto);
        return "redirect:/disciplinas/new";
    }

    @GetMapping("/disciplinas/{disciplinaId}/edit")
    public String editDisciplinaForm(@PathVariable("disciplinaId") long disciplinaId, Model model){
        DisciplinaDto disciplina = disciplinaService.findDisciplinaById(disciplinaId);
        model.addAttribute("disciplina",disciplina);
        return "disciplinas-edit";
    }

    @PostMapping("/disciplinas/{disciplinaId}/edit")
    public String updateDisciplina(@PathVariable("disciplinaId") Long disciplinaId, @Valid @ModelAttribute("disciplina") DisciplinaDto disciplinaDto,
                              BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("disciplina", disciplinaDto);
            return "disciplinas-edit";
        }
        disciplinaDto.setId(disciplinaId);
        disciplinaService.updateDisciplina(disciplinaDto);
        return "redirect:/disciplinas";
    }
    @GetMapping("/disciplinas/{disciplinaId}/delete")
    public String deleteDisciplina(@PathVariable("disciplinaId") Long disciplinaId){
        disciplinaService.delete(disciplinaId);
        return "redirect:/disciplinas";
    }
}
