package com.scoa.web.controller;

import com.scoa.web.dto.AlunoDisciplinaInfoDto;
import com.scoa.web.dto.AlunoDto;
import com.scoa.web.dto.DisciplinaDto;
import com.scoa.web.dto.TurmaDto;
import com.scoa.web.models.Aluno;
import com.scoa.web.models.Disciplina;
import com.scoa.web.models.InfoAluno;
import com.scoa.web.models.Turma;
import com.scoa.web.service.AlunoService;
import com.scoa.web.service.DisciplinaService;
import com.scoa.web.service.InfoAlunoService;
import com.scoa.web.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.scoa.web.mapper.AlunoMapper.mapToAluno;
import static com.scoa.web.mapper.AlunoMapper.mapToAlunoDto;
import static com.scoa.web.mapper.DisciplinaMapper.mapToDisciplina;
import static com.scoa.web.mapper.TurmaMapper.mapToTurma;

@Controller
public class AlunoController {
   private AlunoService alunoService;
   private TurmaService turmaService;
   private DisciplinaService disciplinaService;
   private InfoAlunoService infoAlunoService;

   @Autowired
   private AlunoController(AlunoService alunoService,
                           TurmaService turmaService,
                           DisciplinaService disciplinaService,
                           InfoAlunoService infoAlunoService){
       this.alunoService = alunoService;
       this.turmaService = turmaService;
       this.disciplinaService = disciplinaService;
       this.infoAlunoService = infoAlunoService;
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

       AlunoDto alunoDto = alunoService.findAlunoById(alunoId);
       Aluno aluno = mapToAluno(alunoDto);

       Set<InfoAluno> infoAlunos = new HashSet<>();
       List<DisciplinaDto> disciplinasSelecionadas;

       if (aluno.getTurma() != null) {
           disciplinasSelecionadas = disciplinaService.findAllDisciplinasByTurma(aluno.getTurma().getId());
       } else {
           disciplinasSelecionadas = Collections.emptyList();
       }

       for (DisciplinaDto disciplinaDto : disciplinasSelecionadas) {
           Disciplina disciplina = mapToDisciplina(disciplinaDto);
           Optional<InfoAluno> notaAlunoOpt = infoAlunoService.findByAlunoAndDisciplina(aluno, disciplina);
           InfoAluno infoAluno = notaAlunoOpt.orElseGet(() -> {
               InfoAluno defaultInfoAluno = new InfoAluno();
               defaultInfoAluno.setAluno(aluno);
               defaultInfoAluno.setDisciplina(disciplina);
               defaultInfoAluno.setNota(0L);
               defaultInfoAluno.setFrequencia(0L);
               return defaultInfoAluno;
           });

           infoAlunos.add(infoAluno);
       }
       model.addAttribute("aluno",alunoDto);
       model.addAttribute("turmas",turmas);
       model.addAttribute("disciplinas",disciplinasSelecionadas);
       model.addAttribute("infoAluno", infoAlunos);
       return "alunos-edit";
   }

   @PostMapping("/alunos/{alunoId}/edit")
   public String updateAluno(@PathVariable("alunoId") Long alunoId,
                             @RequestParam(value="turma", required = false) String turmaId,
                             @RequestParam(value = "notasList", required = false) List<String> notasList,
                             @RequestParam(value = "frequenciasList", required = false) List<String> frequenciasList,
                             @RequestParam(value = "disciplinasList", required = false) List<String> disciplinasList,
                             @Valid @ModelAttribute("aluno") AlunoDto alunoDto,
                             BindingResult result, Model model){
       if(result.hasErrors()) {
           model.addAttribute("aluno", alunoDto);
           return "alunos-edit";
       }
       TurmaDto turmaDto = turmaService.findTurmaById(Integer.parseInt(turmaId));
       Turma turma = mapToTurma(turmaDto);
       alunoDto.setTurma(turma);
       alunoService.updateAluno(alunoDto);
       Aluno aluno = mapToAluno(alunoDto);
       alunoService.deleteAllNotaByAlunoId(alunoId);
       System.out.println(aluno.getId());
       if(notasList != null){
           for (int i = 0; i < notasList.size(); i++) {
               DisciplinaDto disciplinaDto = disciplinaService.findDisciplinaById(Long.parseLong(disciplinasList.get(i)));
               Disciplina disciplina = mapToDisciplina(disciplinaDto);
               InfoAluno infoAluno = new InfoAluno();
               infoAluno.setAluno(aluno);
               infoAluno.setDisciplina(disciplina);
               infoAluno.setNota(Long.valueOf(notasList.get(i)));
               infoAluno.setFrequencia(Long.valueOf(frequenciasList.get(i)));
               infoAlunoService.insertNotaForAluno(infoAluno);
           }
       }
       return "redirect:/alunos";
   }
   @GetMapping("/alunos/{alunoId}/delete")
   public String deleteAluno(@PathVariable("alunoId") Long alunoId){
       alunoService.delete(alunoId);
       return "redirect:/alunos";
   }


    @GetMapping("/relatorio/turma")
    public String listRelatorioTurma(Model model){
        return "relatorios-list";
    }
    @PostMapping("/relatorio/turma")
    public String filterRelatorioTurma(
            @RequestParam(value = "filterTurma", required = false) String nome,
            Model model){
       TurmaDto turmaDto = turmaService.findTurmaByNome(nome);
       if(turmaDto != null){
           List<AlunoDisciplinaInfoDto> alunoDisciplinaInfo =  alunoService.findAllAlunosWithDisciplinasAndNotas(turmaDto.getId());
           model.addAttribute("alunosWithDisciplinasAndNotas", alunoDisciplinaInfo);
       }
        return "relatorios-list";
    }

}
