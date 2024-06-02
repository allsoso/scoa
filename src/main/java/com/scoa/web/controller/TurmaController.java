package com.scoa.web.controller;

import com.scoa.web.dto.TurmaDto;
import com.scoa.web.models.Turma;
import com.scoa.web.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TurmaController {
    private TurmaService turmaService;

    @Autowired
    private TurmaController(TurmaService turmaService){
        this.turmaService = turmaService;
    }

    @GetMapping("/turmas")
    public String listTurmas(Model model){
        List<TurmaDto> turmas = turmaService.findAllTurmas();
        model.addAttribute("turmas",turmas);
        return "turmas-list";
    }
    @PostMapping("/turmas")
    public String filterTurmas(
            @RequestParam(value = "filterNome", required = false) String nome,
            @RequestParam(value = "filterCodigo", required = false) String codigo,
            Model model){
        List<TurmaDto> turmas;
        if (nome != null || codigo != null) {
            turmas = turmaService.findTurmasByParams(nome, codigo);
        } else {
            turmas = turmaService.findAllTurmas();
        }
        model.addAttribute("turmas",turmas);
        return "turmas-list";
    }
    @GetMapping("/turmas/new")
    public String createTurmaForm(Model model){
        Turma turma = new Turma();
        model.addAttribute("turma",turma);
        return "turmas-new";
    }
    @PostMapping("/turmas/new")
    public String saveTurma(@Valid @ModelAttribute("turma") TurmaDto turmaDto,
                            BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("turma", turmaDto);
            return "turmas-new";
        }
        turmaService.saveTurma(turmaDto);
        return "redirect:/turmas/new";
    }

    @GetMapping("/turmas/{turmaId}/edit")
    public String editTurmaForm(@PathVariable("turmaId") long turmaId, Model model){
        TurmaDto turma = turmaService.findTurmaById(turmaId);
        model.addAttribute("turma",turma);
        return "turmas-edit";
    }

    @PostMapping("/turmas/{turmaId}/edit")
    public String updateTurma(@PathVariable("turmaId") Long turmaId, @Valid @ModelAttribute("turma") TurmaDto turmaDto,
                              BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("turma", turmaDto);
            return "turmas-edit";
        }
        turmaDto.setId(turmaId);
        turmaService.updateTurma(turmaDto);
        return "redirect:/turmas";
    }
    @GetMapping("/turmas/{turmaId}/delete")
    public String deleteTurma(@PathVariable("turmaId") Long turmaId){
        turmaService.delete(turmaId);
        return "redirect:/turmas";
    }
}
