package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.ContratoDTO;
import com.albino.tecnologia.osworks.model.Contrato;

import java.util.List;

public interface ContratoService {
    Contrato encontrarPeloIdContrato(Long id);
    List<Contrato> listarTodosContratos();
    Contrato criarContrato(ContratoDTO contratoDTO);
    Contrato atualizarContrato(Long id,ContratoDTO contratoDTO);
    void deletarContrato(Long id);
}
