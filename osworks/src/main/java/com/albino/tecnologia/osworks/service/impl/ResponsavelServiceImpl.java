package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.ResponsavelDTO;
import com.albino.tecnologia.osworks.model.Responsavel;
import com.albino.tecnologia.osworks.repository.ResponsavelRepository;
import com.albino.tecnologia.osworks.service.ResponsavelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ResponsavelServiceImpl implements ResponsavelService {
    private final ResponsavelRepository responsavelRepository;
    @Override
    public Responsavel encontrarPeloIdResponsavel(Long id) {

        log.info("Responsavel com ID:'{}' Encontrado", id);
        return responsavelRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id Não Encontrado"));
    }

    @Override
    public Page<Responsavel> listarTodosResponsaveis(Pageable pageable) {

        log.info("Listando Todos os Responsaveis");
        return responsavelRepository.findAll(pageable);
    }

    @Override
    public Responsavel criarResponsavel(ResponsavelDTO responsavelDTO) {

        log.info("Novo Responsavel Criado '{}'", responsavelDTO);
        Responsavel novoResponsavel = Responsavel.builder()
                .cpf(responsavelDTO.getCpf())
                .nome(responsavelDTO.getNome())
                .rg(responsavelDTO.getRg())
                .numTelefone(responsavelDTO.getNumDeTelefone())
                .email(responsavelDTO.getEmail())
                .departamento(responsavelDTO.getDepartamento())
                .cargo(responsavelDTO.getCargo())
                .build();

        return responsavelRepository.save(novoResponsavel);
    }

    @Override
    public Responsavel atualizarResponsavel(Long id,ResponsavelDTO responsavelDTO) {

        Responsavel responsavelAtualizado = encontrarPeloIdResponsavel(id);

        log.info("Responsavel de ID:'{}' sendo Atualizado '{}'", id, responsavelDTO);
        responsavelAtualizado.setCpf(responsavelDTO.getCpf());
        responsavelAtualizado.setNome(responsavelDTO.getNome());
        responsavelAtualizado.setRg(responsavelDTO.getRg());
        responsavelAtualizado.setNumTelefone(responsavelDTO.getNumDeTelefone());
        responsavelAtualizado.setEmail(responsavelDTO.getEmail());
        responsavelAtualizado.setDepartamento(responsavelDTO.getDepartamento());
        responsavelAtualizado.setCargo(responsavelDTO.getCargo());

        log.info("Responsavel de ID:'{}' Atualizado '{}'", id, responsavelDTO);
        return responsavelRepository.save(responsavelAtualizado);
    }

    @Override
    public void deletarResponsavel(Long id) {

        log.info("Responsavel com ID:'{}' sendo Inativado", id);
        Responsavel responsavelDeletado = encontrarPeloIdResponsavel(id);

        log.info("Responsavel com ID:'{}' foi Inativado", id);
        responsavelRepository.delete(responsavelDeletado);
    }
}
