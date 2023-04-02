package com.albino.tecnologia.osworks.service;


import com.albino.tecnologia.osworks.controller.dto.ProjetoDTO;
import com.albino.tecnologia.osworks.controller.dto.UsuarioPorIdDTO;
import com.albino.tecnologia.osworks.controller.dto.UsuarioPorUsernameDTO;
import com.albino.tecnologia.osworks.model.Projeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjetoService {
    Projeto encontrarPeloId(Long id);
    Page<Projeto> listarTodosProjetos(Pageable pageable);
    Projeto criarProjeto(ProjetoDTO projetoDTO);
    Projeto atualizarProjeto(Long id,ProjetoDTO projetoDTO);
    Projeto distribuirProjeto(Long id, UsuarioPorIdDTO usuarioDTO);
    Projeto distribuirProjetoPorUsername(Long id, UsuarioPorUsernameDTO usernameDTO);
    Projeto encerrarProjeto(Long id);
    void inativarProjeto(Long id);
}
