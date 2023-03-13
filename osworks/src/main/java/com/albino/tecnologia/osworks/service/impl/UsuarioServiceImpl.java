package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.UsuarioDTO;
import com.albino.tecnologia.osworks.model.Role;
import com.albino.tecnologia.osworks.model.Usuario;
import com.albino.tecnologia.osworks.repository.RoleRepository;
import com.albino.tecnologia.osworks.repository.UsuarioRepository;
import com.albino.tecnologia.osworks.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public Usuario encontrarPeloId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id Não Encontrado"));
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario criarUsuario(UsuarioDTO usuarioDTO) {

        Usuario novoUsuario = Usuario.builder()
                .username(usuarioDTO.getUsername())
                .password(passwordEncoder.encode(usuarioDTO.getPassword()))
                .email(usuarioDTO.getEmail())

                .build();

        List<Role> roles = new ArrayList<>();

        for (Long roleId : usuarioDTO.getRoleIds()) {
            Role role = roleRepository.findById(roleId).get();
            roles.add(role);
        }

        novoUsuario.setRoles(roles);

        return usuarioRepository.save(novoUsuario);
    }

    @Override
    public Usuario atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {

        Usuario usuarioAtualizado = encontrarPeloId(id);

        usuarioAtualizado.setUsername(usuarioDTO.getUsername());
        usuarioAtualizado.setEmail(usuarioDTO.getEmail());


        return usuarioRepository.save(usuarioAtualizado);
    }

    @Override
    public void inativarUsuario(Long id) {

        Usuario usuarioInativado = encontrarPeloId(id);

        usuarioInativado.setStatus("Inativo");
    }
}
