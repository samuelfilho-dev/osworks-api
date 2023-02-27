package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.ResponsavelDTO;
import com.albino.tecnologia.osworks.model.Responsavel;

import java.util.List;

public interface ResponsavelService {
    Responsavel encontrarPeloIdResponsavel(Long id);
    List<Responsavel> listarTodosOsResponsaveis();
    Responsavel criarResponsavel(ResponsavelDTO responsavelDTO);
    Responsavel atualizarResponsavel(Long id,ResponsavelDTO responsavelDTO);
    void deletarResponsavel(Long id);
}
