package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.ResponsavelDTO;
import com.albino.tecnologia.osworks.model.Responsavel;
import com.albino.tecnologia.osworks.repository.ResponsavelRepository;
import com.albino.tecnologia.osworks.service.ResponsavelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ResponsavelServiceImpl implements ResponsavelService {
    private final ResponsavelRepository responsavelRepository;
    @Override
    public Responsavel encontrarPeloIdResponsavel(Long id) {

        log.info("Responsavel Encontrado '{}'", id);
        return responsavelRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id Não Encontrado"));
    }

    @Override
    public List<Responsavel> listarTodosResponsaveis() {

        log.info("Listando Todos os Responsaveis");
        return responsavelRepository.findAll();
    }

    @Override
    public Responsavel criarResponsavel(ResponsavelDTO responsavelDTO) {

        log.info("Criando Responsavel '{}'", responsavelDTO);
        Responsavel novoResponsavel = Responsavel.builder()
                .CPF(responsavelDTO.getCPF())
                .nome(responsavelDTO.getNome())
                .Rg(responsavelDTO.getRg())
                .numTelefone(responsavelDTO.getNumTelefone())
                .email(responsavelDTO.getEmail())
                .endereco(responsavelDTO.getEndereco())
                .departamento(responsavelDTO.getDepartamento())
                .cargo(responsavelDTO.getCargo())
                .build();
        log.info("Responsavel Criado '{}'", responsavelDTO);
        return responsavelRepository.save(novoResponsavel);
    }

    @Override
    public Responsavel atualizarResponsavel(Long id,ResponsavelDTO responsavelDTO) {
        Responsavel responsavelAtualizado = encontrarPeloIdResponsavel(id);

        log.info("Atualizando Responsavel '{}''{}", id, responsavelDTO);
        responsavelAtualizado.setCPF(responsavelDTO.getCPF());
        responsavelAtualizado.setNome(responsavelDTO.getNome());
        responsavelAtualizado.setRg(responsavelDTO.getRg());
        responsavelAtualizado.setNumTelefone(responsavelDTO.getNumTelefone());
        responsavelAtualizado.setEmail(responsavelDTO.getEmail());
        responsavelAtualizado.setEndereco(responsavelDTO.getEndereco());
        responsavelAtualizado.setDepartamento(responsavelDTO.getDepartamento());
        responsavelAtualizado.setCargo(responsavelDTO.getCargo());

        log.info("Responsavel Atualizado para '{}''{}'", id, responsavelDTO);
        return responsavelRepository.save(responsavelAtualizado);
    }

    @Override
    public void deletarResponsavel(Long id) {

        log.info("Inativando Responsavel '{}'", id);
        Responsavel responsavelDeletado = encontrarPeloIdResponsavel(id);

        log.info("Responsavel Inativo '{}'", id);
        responsavelRepository.delete(responsavelDeletado);
    }
}
