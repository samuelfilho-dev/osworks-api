package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.ResponsavelDTO;
import com.albino.tecnologia.osworks.model.Responsavel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResponsavelService {
    Responsavel encontrarPeloIdResponsavel(Long id);
    Page<Responsavel> listarTodosResponsaveis(Pageable pageable);
    Responsavel criarResponsavel(ResponsavelDTO responsavelDTO);
    Responsavel atualizarResponsavel(Long id,ResponsavelDTO responsavelDTO);
    void inativarResponsavel(Long id);
}
