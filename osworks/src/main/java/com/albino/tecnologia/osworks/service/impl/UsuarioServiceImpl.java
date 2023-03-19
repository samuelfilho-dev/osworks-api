package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.UsuarioDTO;
import com.albino.tecnologia.osworks.model.Role;
import com.albino.tecnologia.osworks.model.Usuario;
import com.albino.tecnologia.osworks.repository.RoleRepository;
import com.albino.tecnologia.osworks.repository.UsuarioRepository;
import com.albino.tecnologia.osworks.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public Usuario encontrarPeloId(Long id) {

        log.info("Pesquisando o usuario Pelo Id '{}'", id);

        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id Não Encontrado"));
    }

    @Override
    public Page<Usuario> listarTodosUsuarios(Pageable pageable) {

        log.info("Listando Todos Usuarios");

        return usuarioRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Usuario criarUsuario(UsuarioDTO usuarioDTO) {

        log.info("Criando Um Novo Usuario '{}'", usuarioDTO.toString());

        Usuario novoUsuario = Usuario.builder()
                .nome(usuarioDTO.getNome())
                .username(usuarioDTO.getUsername())
                .password(passwordEncoder.encode(usuarioDTO.getPassword()))
                .email(usuarioDTO.getEmail())
                .dataDeCriacao(LocalDateTime.now())
                .build();

        List<Role> roles = new ArrayList<>();

        log.info("Criando Roles Para O Usuario");
        for (Long roleId : usuarioDTO.getRoleIds()) {
            Role role = roleRepository.findById(roleId).get();
            roles.add(role);
        }

        log.info("As Roles do Usuario '{}' Serão '{}'",usuarioDTO.getUsername(),roles);

        novoUsuario.setRoles(roles);

        log.info("Salvando O Usuario '{}' No Banco De Dados", usuarioDTO);

        return usuarioRepository.save(novoUsuario);
    }

    @Override
    public Usuario atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {

        Usuario usuarioAtualizado = encontrarPeloId(id);

        log.info("Atualizando O Usuario '{}' Com Id '{}' Para '{}'",
                usuarioAtualizado.toString(),id,usuarioDTO.toString());


        usuarioAtualizado.setUsername(usuarioDTO.getUsername());
        usuarioAtualizado.setEmail(usuarioDTO.getEmail());

        return usuarioRepository.save(usuarioAtualizado);
    }

    @Override
    public void inativarUsuario(Long id) {

        log.info("Inativando O Usuario com Id'{}'",id);


        Usuario usuarioInativado = encontrarPeloId(id);

        usuarioInativado.setStatus("Inativo");

        log.info("O Usuario '{}' Foi Inativado", usuarioInativado);

    }

    @Override
    public Usuario trocarSenha(Long id, UsuarioDTO usuarioDTO) {


        Usuario usuarioAtualizado = encontrarPeloId(id);

        log.info("A Senha Do Usuario '{}' Foi Trocada", usuarioAtualizado.getUsername());

        usuarioAtualizado.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));


        return usuarioRepository.save(usuarioAtualizado);
    }

    @Override
    public Usuario trocarSenhaPorUsername(String username, UsuarioDTO usuarioDTO) {

        Usuario usuarioAtualizado = usuarioRepository.findByUsername(username).get();

        log.info("A Senha Do Usuario '{}' Foi Trocada Por Username", usuarioAtualizado.getUsername());

        usuarioAtualizado.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        return usuarioRepository.save(usuarioAtualizado);
    }

    @Override
    public Usuario trocarSenhaPorEmail(String email, UsuarioDTO usuarioDTO) {

        Usuario usuarioAtualizado = usuarioRepository.findByEmail(email).get();

        log.info("A Senha Do Usuario '{}' Foi Trocada Por E-mail", usuarioAtualizado.getEmail());

        usuarioAtualizado.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        return usuarioRepository.save(usuarioAtualizado);
    }
}
