package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.OSDTO;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.OS;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OSService {
    OS encontrarPeloId(Long id);
    Page<OS> listarTodasOS(Pageable pageable);
    List<OS> listarTodasOSPorStatus(String status);
    Contrato mostrarContratoDaOS(Long id);
    OS criarOS(Long id,OSDTO osdto);
    OS atualizarOS(Long id,OSDTO osdto);
    OS execultarOS(Long id);
    OS finalizarOS(Long id);
    void inativarOS(Long id);
}
