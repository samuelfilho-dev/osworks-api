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

        log.info("Projeto Encontrado '{}'",id);
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

        log.info("Criando Projeto '{}'", projetoDTO);
        Projeto novoProjeto = Projeto.builder()
                .nome(projetoDTO.getNome())
                .descricao(projetoDTO.getDescricao())
                .dataDeInicio(projetoDTO.getDataDeInicio())
                .dataDeTermino(projetoDTO.getDataDeTermino())
                .status(projetoDTO.getStatus())
                .build();

        log.info("Projeto Criado '{}'", projetoDTO);
        return projetoRepository.save(novoProjeto);
    }

    @Override
    public Projeto atualizarProjeto(Long id, ProjetoDTO projetoDTO) {
        Projeto projetoAtualizado = encontrarPeloId(id);

        log.info("Atualizando Projeto '{}' '{}", id, projetoDTO);
        projetoAtualizado.setNome(projetoDTO.getNome());
        projetoAtualizado.setDescricao(projetoDTO.getDescricao());
        projetoAtualizado.setDataDeInicio(projetoDTO.getDataDeInicio());
        projetoAtualizado.setDataDeTermino(projetoDTO.getDataDeTermino());
        projetoAtualizado.setStatus(projetoDTO.getStatus());

        log.info("Projeto Atualizado para '{}' '{}", id, projetoDTO);
        return projetoRepository.save(projetoAtualizado);
    }

    @Override
    public void deletarProjeto(Long id) {
        log.info("Inativando Projeto '{}'", id);
        Projeto projetoDeletado = encontrarPeloId(id);

        log.info("Projeto Inativado '{}'", id);
        projetoRepository.delete(projetoDeletado);
    }
}
