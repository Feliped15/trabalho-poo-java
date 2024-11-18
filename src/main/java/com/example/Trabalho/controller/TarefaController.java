package com.example.Trabalho.controller;

import com.example.Trabalho.model.Tarefa;
import com.example.Trabalho.service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public List<Tarefa> listarTodos() {
        return tarefaService.listarTodos();
    }

    @PostMapping
    public Tarefa salvar(@RequestBody Tarefa tarefa) {
        // Validação manual
        if (tarefa.getTitulo() == null || tarefa.getTitulo().trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O título da tarefa é obrigatório"
            );
        }

        return tarefaService.salvar(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return tarefaService.atualizar(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tarefaService.delete(id);
    }

    @PutMapping("/{id}/move")
    public Tarefa move(@PathVariable Long id) {
        return tarefaService.move(id);

    }

}