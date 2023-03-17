package com.albino.tecnologia.osworks.service;



import com.albino.tecnologia.osworks.controller.dto.ProjetoDTO;
import com.albino.tecnologia.osworks.model.Projeto;

import java.util.List;

public interface ProjetoService {
    Projeto encontrarPeloId(Long id);
    List<Projeto> listarTodosProjetos();
    Projeto criarProjeto(ProjetoDTO projetoDTO);
    Projeto atualizarProjeto(Long id,ProjetoDTO projetoDTO);
    void distribuirProjeto(Projeto projeto, Long id);
    void deletarProjeto(Long id);
}
