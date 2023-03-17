package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.OSDTO;
import com.albino.tecnologia.osworks.model.OS;

import java.util.List;

public interface OSService {
    OS encontrarPeloId(Long id);
    List<OS> listarTodasOS();
    OS criarOS(Long id,OSDTO osdto);
    OS atualizarOS(Long id,OSDTO osdto);
    void deletarOS(Long id);
}
