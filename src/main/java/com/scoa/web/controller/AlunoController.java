package com.scoa.web.controller;

import com.scoa.web.dto.AlunoDto;
import com.scoa.web.models.Aluno;
import com.scoa.web.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AlunoController {
   private AlunoService alunoService;

   @Autowired
   private AlunoController(AlunoService alunoService){
       this.alunoService = alunoService;
   }

   @GetMapping("/alunos")
   public String listAlunos(Model model){
       List<AlunoDto> alunos = alunoService.findAllAlunos();
       model.addAttribute("alunos",alunos);
       return "alunos-list";
   }
    @PostMapping("/alunos")
    public String filterAlunos(
    @RequestParam(value = "filterNome", required = false) String nome,
    @RequestParam(value = "filterCpf", required = false) String cpf,
    @RequestParam(value = "filterMatricula", required = false) String matricula,
    Model model){
        List<AlunoDto> alunos;
        if (nome != null || cpf != null) {
            alunos = alunoService.findAlunosByParams(nome, cpf);
        } else {
            alunos = alunoService.findAllAlunos();
        }
        model.addAttribute("alunos",alunos);
        return "alunos-list";
    }
   @GetMapping("/alunos/new")
   public String createAlunoForm(Model model){
       Aluno aluno = new Aluno();
       model.addAttribute("aluno",aluno);
       return "alunos-new";
   }
   @PostMapping("/alunos/new")
   public String saveAluno(@Valid @ModelAttribute("aluno") AlunoDto alunoDto,
                           BindingResult result, Model model){
       if(result.hasErrors()) {
           model.addAttribute("aluno", alunoDto);
           return "alunos-new";
       }
       alunoService.saveAluno(alunoDto);
       return "redirect:/alunos/new";
   }

   @GetMapping("/alunos/{alunoId}/edit")
   public String editAlunoForm(@PathVariable("alunoId") long alunoId, Model model){
       AlunoDto aluno = alunoService.findAlunoById(alunoId);
       model.addAttribute("aluno",aluno);
       return "alunos-edit";
   }

   @PostMapping("/alunos/{alunoId}/edit")
   public String updateAluno(@PathVariable("alunoId") Long alunoId, @Valid @ModelAttribute("aluno") AlunoDto alunoDto,
                             BindingResult result, Model model){
       if(result.hasErrors()) {
           model.addAttribute("aluno", alunoDto);
           return "alunos-edit";
       }
       alunoDto.setId(alunoId);
       alunoService.updateAluno(alunoDto);
       return "redirect:/alunos";
   }
   @GetMapping("/alunos/{alunoId}/delete")
   public String deleteAluno(@PathVariable("alunoId") Long alunoId){
       alunoService.delete(alunoId);
       return "redirect:/alunos";
   }
}
