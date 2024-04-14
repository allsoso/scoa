package com.scoa.web.controller;

import com.scoa.web.dto.AlunoDto;
import com.scoa.web.models.Aluno;
import com.scoa.web.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
       return "alunos-new";

   }
   @GetMapping("/alunos/new")
   public String createAlunoForm(Model model){
       Aluno aluno = new Aluno();
       model.addAttribute("aluno",aluno);
       return "alunos-new";
   }
   @PostMapping("/alunos/new")
   public String saveAluno(@ModelAttribute("aluno") Aluno aluno){
       alunoService.saveAluno(aluno);
       return "redirect:/alunos/new";
   }


}
