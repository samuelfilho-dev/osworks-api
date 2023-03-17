package com.albino.tecnologia.osworks.service.impl;


import com.albino.tecnologia.osworks.controller.dto.EnderecoDTO;
import com.albino.tecnologia.osworks.model.Endereco;
import com.albino.tecnologia.osworks.repository.EnderecoRepository;
import com.albino.tecnologia.osworks.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EnderecoServiceImpl implements  EnderecoService {
    private final EnderecoRepository enderecoRepository;

    @Override
    public Endereco encontrarPeloEndereco(Long id) {
        log.info("Endereco de ID:'{}' Encontrado", id);
        return enderecoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id Não Encontrado"));
    }

    @Override
    public List<Endereco> listarTodosEnderecos() {

        log.info("Listando Todos os Enderecos");
        return enderecoRepository.findAll();
    }

    @Override
    public Endereco criarEndereco(EnderecoDTO enderecoDTO) {

        log.info("Novo Endereco Criado '{}'", enderecoDTO);
        Endereco novoEndereco = Endereco.builder()
                .CEP(enderecoDTO.getCep())
                .logradouro(enderecoDTO.getLogradouro())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .bairro(enderecoDTO.getBairro())
                .cidade(enderecoDTO.getCidade())
                .UF(enderecoDTO.getUf())
                .build();

        return enderecoRepository.save(novoEndereco);
    }

    @Override
    public Endereco atualizarEndereco(Long id,EnderecoDTO enderecoDTO) {
        Endereco enderecoAtualizado = encontrarPeloEndereco(id);

        log.info("Endereco de ID:'{}' Sendo Atualizada '{}", id, enderecoDTO);
        enderecoAtualizado.setCEP(enderecoDTO.getCep());
        enderecoAtualizado.setLogradouro(enderecoDTO.getLogradouro());
        enderecoAtualizado.setNumero(enderecoDTO.getNumero());
        enderecoAtualizado.setComplemento(enderecoDTO.getComplemento());
        enderecoAtualizado.setBairro(enderecoDTO.getBairro());
        enderecoAtualizado.setCidade(enderecoDTO.getCidade());
        enderecoAtualizado.setUF(enderecoDTO.getUf());

        log.info("Endereco de ID:'{}' Foi Atualizado '{}'",id, enderecoDTO);
        return enderecoRepository.save(enderecoAtualizado);
    }

    @Override
    public void deletarEndereco(Long id) {
        log.info("Endereco de ID:'{}' Sendo Inativado", id);
        Endereco enderecoDeletado = encontrarPeloEndereco(id);

        log.info("Endereco de ID:'{}' Foi Inativado", id);
        enderecoRepository.delete(enderecoDeletado);
    }
}
