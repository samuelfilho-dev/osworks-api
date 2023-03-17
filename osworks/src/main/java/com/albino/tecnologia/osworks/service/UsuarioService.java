package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.UsuarioDTO;
import com.albino.tecnologia.osworks.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario encontrarPeloId(Long id);
    List<Usuario> listarTodosUsuarios();
    Usuario criarUsuario(UsuarioDTO usuarioDTO);
    Usuario atualizarUsuario(Long id,UsuarioDTO usuarioDTO);
    void inativarUsuario(Long id);
    Usuario trocarSenha(Long id, UsuarioDTO usuarioDTO);
    Usuario trocarSenhaPorUsername(String username, UsuarioDTO usuarioDTO);
    Usuario trocarSenhaPorEmail(String email, UsuarioDTO usuarioDTO);
}
