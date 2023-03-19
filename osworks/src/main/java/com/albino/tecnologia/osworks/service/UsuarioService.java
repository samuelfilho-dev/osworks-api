package com.albino.tecnologia.osworks.service;

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
    Usuario trocarSenha(Long id, UsuarioDTO usuarioDTO);
    Usuario trocarSenhaPorUsername(String username, UsuarioDTO usuarioDTO);
    Usuario trocarSenhaPorEmail(String email, UsuarioDTO usuarioDTO);
}
