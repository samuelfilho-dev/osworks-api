package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.ContratoDTO;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.OS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContratoService {
    Contrato encontrarPeloIdContrato(Long id);
    Page<Contrato> listarTodosContratos(Pageable pageable);
    List<OS> listarOSDoContrato(Long id);
    Contrato criarContrato(ContratoDTO contratoDTO);
    Contrato atualizarContrato(Long id,ContratoDTO contratoDTO);
    Contrato distribuirContrato(Long id,ContratoDTO contratoDTO);
    void deletarContrato(Long id);
}
