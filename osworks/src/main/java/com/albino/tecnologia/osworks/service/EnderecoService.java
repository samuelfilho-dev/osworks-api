package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.EnderecoDTO;
import com.albino.tecnologia.osworks.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EnderecoService {
    Endereco encontrarPeloEndereco(Long id);
    Page<Endereco> listarTodosEnderecos(Pageable pageable);
    Endereco criarEndereco(EnderecoDTO enderecoDTO);
    Endereco atualizarEndereco(Long id, EnderecoDTO enderecoDTO);
    void deletarEndereco(Long id);
}
