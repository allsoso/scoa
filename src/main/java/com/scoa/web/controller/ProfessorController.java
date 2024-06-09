package com.scoa.web.controller;

import com.scoa.web.dto.DisciplinaDto;
import com.scoa.web.dto.ProfessorDto;
import com.scoa.web.dto.TurmaDto;
import com.scoa.web.models.Disciplina;
import com.scoa.web.models.Professor;
import com.scoa.web.service.DisciplinaService;
import com.scoa.web.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.scoa.web.mapper.ProfessorMapper.mapToProfessor;

@Controller
public class ProfessorController {
    private ProfessorService professorService;
    private DisciplinaService disciplinaService;

    @Autowired
    private ProfessorController(ProfessorService professorService, DisciplinaService disciplinaService){
        this.professorService = professorService;
        this.disciplinaService = disciplinaService;
    }

    @GetMapping("/professores")
    public String listProfessores(Model model){
        List<ProfessorDto> professores = professorService.findAllProfessores();
        model.addAttribute("professores",professores);
        return "professores-list";
    }
    @PostMapping("/professores")
    public String filterProfessores(
            @RequestParam(value = "filterNome", required = false) String nome,
            @RequestParam(value = "filterCpf", required = false) String cpf,
            @RequestParam(value = "filterMatricula", required = false) String matricula,
            Model model){
        List<ProfessorDto> professores;
        if (nome != null || cpf != null) {
            professores = professorService.findProfessoresByParams(nome, cpf);
        } else {
            professores = professorService.findAllProfessores();
        }
        model.addAttribute("professores",professores);
        return "professores-list";
    }
    @GetMapping("/professores/new")
    public String createProfessorForm(Model model){
        Professor professor = new Professor();
        model.addAttribute("professor",professor);
        return "professores-new";
    }
    @PostMapping("/professores/new")
    public String saveProfessor(@Valid @ModelAttribute("professor") ProfessorDto professorDto,
                                BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("professor", professorDto);
            return "professores-new";
        }
        professorService.saveProfessor(professorDto);
        return "redirect:/professores/new";
    }

    @GetMapping("/professores/{professorId}/edit")
    public String editProfessorForm(@PathVariable("professorId") long professorId, Model model){
        ProfessorDto professor = professorService.findProfessorById(professorId);
        List<DisciplinaDto> disciplinas = disciplinaService.findAllDisciplinas();
        model.addAttribute("professor",professor);
        model.addAttribute("disciplinas",disciplinas);
        return "professores-edit";
    }

    @PostMapping("/professores/{professorId}/edit")
    public String updateProfessor(@PathVariable("professorId") Long professorId,
                                  @Valid @ModelAttribute("professor") ProfessorDto professorDto,
                                  @RequestParam(value = "selectedItems", required = false) List<String> selectedItems,
                                  BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("professor", professorDto);
            return "professores-edit";
        }
        disciplinaService.clearProfessorFromDisciplinas(professorId);
        Professor professor = mapToProfessor(professorDto);
        if (selectedItems != null) {
            for (String disciplinaId : selectedItems) {
                System.out.println(disciplinaId);
                DisciplinaDto disciplinaDto = disciplinaService.findDisciplinaById(Integer.parseInt(disciplinaId));
                disciplinaDto.setProfessor(professor);
                System.out.println(disciplinaDto.getNome());
                System.out.println(disciplinaDto.getProfessor().getId());
                disciplinaService.updateDisciplina(disciplinaDto);
            }
        }
        professorDto.setId(professorId);
        professorService.updateProfessor(professorDto);
        return "redirect:/professores";
    }
    @GetMapping("/professores/{professorId}/delete")
    public String deleteProfessor(@PathVariable("professorId") Long professorId){
        professorService.delete(professorId);
        return "redirect:/professores";
    }
}
