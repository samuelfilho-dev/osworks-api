package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.EnderecoDTO;
import com.albino.tecnologia.osworks.model.Endereco;

import java.util.List;

public interface EnderecoService {
    Endereco encontrarPeloEndereco(Long id);
    List<Endereco> listarTodosEnderecos();
    Endereco criarEndereco(EnderecoDTO enderecoDTO);
    Endereco atualizarEndereco(Long id, EnderecoDTO enderecoDTO);
    void deletarEndereco(Long id);
}
