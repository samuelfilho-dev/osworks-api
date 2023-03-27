package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.exception.BadResquestException;
import com.albino.tecnologia.osworks.model.Usuario;
import com.albino.tecnologia.osworks.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario userModel = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado com esse " + username));


        if (userModel.getStatus().equals("inativo")) {
            throw new BadResquestException("Usuario " + userModel.getUsername() + " está inativo");
        }

        return new User(userModel.getUsername(), userModel.getPassword(), true,true,
                true,true, userModel.getAuthorities());
    }

}
