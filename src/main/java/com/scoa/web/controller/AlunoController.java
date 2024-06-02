package com.scoa.web.controller;

import com.scoa.web.dto.AlunoDto;
import com.scoa.web.dto.TurmaDto;
import com.scoa.web.models.Aluno;
import com.scoa.web.models.Turma;
import com.scoa.web.service.AlunoService;
import com.scoa.web.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.scoa.web.mapper.TurmaMapper.mapToTurma;

@Controller
public class AlunoController {
   private AlunoService alunoService;
   private TurmaService turmaService;

   @Autowired
   private AlunoController(AlunoService alunoService, TurmaService turmaService){
       this.alunoService = alunoService;
       this.turmaService = turmaService;
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
       List<TurmaDto> turmas = turmaService.findAllTurmas();
       AlunoDto aluno = alunoService.findAlunoById(alunoId);
       model.addAttribute("aluno",aluno);
       model.addAttribute("turmas",turmas);
       return "alunos-edit";
   }

   @PostMapping("/alunos/{alunoId}/edit")
   public String updateAluno(@PathVariable("alunoId") Long alunoId,
                             @RequestParam(value="turma", required = false) String turmaId,
                             @Valid @ModelAttribute("aluno") AlunoDto alunoDto,
                             BindingResult result, Model model){
       if(result.hasErrors()) {
           model.addAttribute("aluno", alunoDto);
           return "alunos-edit";
       }
       TurmaDto turmaDto = turmaService.findTurmaById(Integer.parseInt(turmaId));
       Turma turma = mapToTurma(turmaDto);
       alunoDto.setId(alunoId);
       alunoDto.setTurma(turma);
       alunoService.updateAluno(alunoDto);
       return "redirect:/alunos";
   }
   @GetMapping("/alunos/{alunoId}/delete")
   public String deleteAluno(@PathVariable("alunoId") Long alunoId){
       alunoService.delete(alunoId);
       return "redirect:/alunos";
   }
}
