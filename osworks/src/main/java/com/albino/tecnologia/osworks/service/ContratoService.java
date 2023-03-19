package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.ContratoDTO;
import com.albino.tecnologia.osworks.model.Contrato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContratoService {
    Contrato encontrarPeloIdContrato(Long id);
    Page<Contrato> listarTodosContratos(Pageable pageable);
    Contrato criarContrato(ContratoDTO contratoDTO);
    Contrato atualizarContrato(Long id,ContratoDTO contratoDTO);
    Contrato distribuirContrato(Long id,ContratoDTO contratoDTO);
    void deletarContrato(Long id);
}
