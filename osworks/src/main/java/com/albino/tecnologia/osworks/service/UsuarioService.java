package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.AlterarSenhaDTO;
import com.albino.tecnologia.osworks.controller.dto.UsuarioDTO;
import com.albino.tecnologia.osworks.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {
    Usuario encontrarPeloId(Long id);
    Page<Usuario> listarTodosUsuarios(Pageable pageable);
    Usuario criarUsuario(UsuarioDTO usuarioDTO);
    Usuario atualizarUsuario(Long id,UsuarioDTO usuarioDTO);
    void inativarUsuario(Long id);
    Usuario trocarSenhaPorId(Long id, AlterarSenhaDTO senhaDTO);
    Usuario trocarSenhaPorUsername(String username, AlterarSenhaDTO senhaDTO);
    Usuario trocarSenhaPorEmail(String email, AlterarSenhaDTO senhaDTO);
}
