package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.ProjetoDTO;
import com.albino.tecnologia.osworks.model.Projeto;
import com.albino.tecnologia.osworks.repository.ProjetoRepository;
import com.albino.tecnologia.osworks.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;

    @Override
    public Projeto encontrarPeloId(Long id) {

        log.info("Projeto de ID:'{}' Encontrado",id);
        return projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }

    @Override
    public List<Projeto> listarTodosProjetos() {

        log.info("Listando Todos os Projetos");
        return projetoRepository.findAll();
    }

    @Override
    public Projeto criarProjeto(ProjetoDTO projetoDTO) {

        log.info("Novo Projeto Criado '{}'", projetoDTO);
        Projeto novoProjeto = Projeto.builder()
                .nome(projetoDTO.getNome())
                .descricao(projetoDTO.getDescricao())
                .dataDeInicio(projetoDTO.getDataDeInicio())
                .dataDeTermino(projetoDTO.getDataDeTermino())
                .status(projetoDTO.getStatus())
                .build();

        return projetoRepository.save(novoProjeto);
    }

    @Override
    public Projeto atualizarProjeto(Long id, ProjetoDTO projetoDTO) {
        Projeto projetoAtualizado = encontrarPeloId(id);

        log.info("Projeto de ID:'{}' Sendo Atualizado '{}'", id, projetoDTO);
        projetoAtualizado.setNome(projetoDTO.getNome());
        projetoAtualizado.setDescricao(projetoDTO.getDescricao());
        projetoAtualizado.setDataDeInicio(projetoDTO.getDataDeInicio());
        projetoAtualizado.setDataDeTermino(projetoDTO.getDataDeTermino());
        projetoAtualizado.setStatus(projetoDTO.getStatus());

        log.info("Projeto de ID:'{}' Foi Atualizado '{}'", id, projetoDTO);
        return projetoRepository.save(projetoAtualizado);
    }

    @Override
    public void deletarProjeto(Long id) {
        log.info("Projeto de ID:'{}' Sendo Inativado", id);
        Projeto projetoDeletado = encontrarPeloId(id);

        log.info("Projeto de ID:'{}' Foi Inativado", id);
        projetoRepository.delete(projetoDeletado);
    }
}
