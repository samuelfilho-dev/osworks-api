package com.albino.tecnologia.osworks.service;



import com.albino.tecnologia.osworks.controller.dto.ProjetoDTO;
import com.albino.tecnologia.osworks.model.Projeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjetoService {
    Projeto encontrarPeloId(Long id);
    Page<Projeto> listarTodosProjetos(Pageable pageable);
    Projeto criarProjeto(ProjetoDTO projetoDTO);
    Projeto atualizarProjeto(Long id,ProjetoDTO projetoDTO);
    void distribuirProjeto(Projeto projeto, Long id);

    void inativarProjeto(Long id);
}
