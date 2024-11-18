package com.example.Trabalho.service;


import com.example.Trabalho.model.Situacao;
import com.example.Trabalho.model.Tarefa;
import com.example.Trabalho.repository.TarefaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TarefaService {
    private final TarefaRepository TarefaRepository;

    public TarefaService(TarefaRepository TarefaRepository) {
        this.TarefaRepository = TarefaRepository;
    }

    public List<Tarefa> listarTodos() {
        return TarefaRepository.findAll(
                Sort.by(
                        Sort.Order.asc("situacao"),
                        Sort.Order.desc("prioridade")
                )
        );
    }

    public Tarefa salvar(Tarefa tarefa) {
        tarefa.setDatadeCriacao(new Date());
        tarefa.setSituacao(Situacao.Afazer);
        return TarefaRepository.save(tarefa);
    }

    public Tarefa atualizar(Long id, Tarefa tarefa) {
        Tarefa trf = TarefaRepository.findById(id).orElse(null);

        if(trf != null){
            trf.setTitulo(tarefa.getTitulo());
            trf.setDescricao(tarefa.getDescricao());
            trf.setPrioridade(tarefa.getPrioridade());

            TarefaRepository.save(trf);
        }

        return trf;
    }

    public void delete(Long id) {
        Tarefa trf = TarefaRepository.findById(id).orElse(null);

        if(trf != null){
            TarefaRepository.delete(trf);
        }
    }

    public Tarefa move(Long id) {
        Tarefa trf = TarefaRepository.findById(id).orElse(null);

        if (trf.getSituacao() == Situacao.Afazer){
            trf.setSituacao(Situacao.Emprogresso);
            TarefaRepository.save(trf);
        } else if (trf.getSituacao() == Situacao.Emprogresso) {
            trf.setSituacao(Situacao.Concluido);
            TarefaRepository.save(trf);
        }

        return trf;
    }
}