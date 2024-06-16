package com.scoa.web.controller;

import com.scoa.web.dto.SalaDto;
import com.scoa.web.dto.TurmaDto;
import com.scoa.web.models.Sala;
import com.scoa.web.service.SalaService;
import com.scoa.web.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SalaController {
    private SalaService salaService;
    private TurmaService turmaService;

    @Autowired
    private SalaController(SalaService salaService, TurmaService turmaService){
        this.salaService = salaService;
        this.turmaService = turmaService;
    }

    @GetMapping("/salas")
    public String listSalas(Model model){
        List<SalaDto> salas = salaService.findAllSalas();
        model.addAttribute("salas",salas);
        return "salas-list";
    }
    @PostMapping("/salas")
    public String filterSalas(
            @RequestParam(value = "filterNome", required = false) String nome,
            @RequestParam(value = "filterCodigo", required = false) String codigo,
            Model model){
        List<SalaDto> salas;
        if (nome != null || codigo != null) {
            salas = salaService.findSalasByParams(nome, codigo);
        } else {
            salas = salaService.findAllSalas();
        }
        model.addAttribute("salas",salas);
        return "salas-list";
    }
    @GetMapping("/salas/new")
    public String createSalaForm(Model model){
        Sala sala = new Sala();
        model.addAttribute("sala",sala);
        return "salas-new";
    }
    @PostMapping("/salas/new")
    public String saveSala(@Valid @ModelAttribute("sala") SalaDto salaDto,
                                 BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("sala", salaDto);
            return "salas-new";
        }
        salaService.saveSala(salaDto);
        return "redirect:/salas/new";
    }

    @GetMapping("/salas/{salaId}/edit")
    public String editSalaForm(@PathVariable("salaId") long salaId, Model model){
        List<TurmaDto> turmas = turmaService.findAllTurmas();
        SalaDto sala = salaService.findSalaById(salaId);
        model.addAttribute("sala",sala);
        model.addAttribute("turmas",turmas);
        return "salas-edit";
    }

    @PostMapping("/salas/{salaId}/edit")
    public String updateSala(@PathVariable("salaId") Long salaId, @Valid @ModelAttribute("sala") SalaDto salaDto,
                                   BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("sala", salaDto);
            return "salas-edit";
        }
        salaDto.setId(salaId);
        salaService.updateSala(salaDto);
        return "redirect:/salas";
    }
    @GetMapping("/salas/{salaId}/delete")
    public String deleteSala(@PathVariable("salaId") Long salaId){
        salaService.delete(salaId);
        return "redirect:/salas";
    }
}
