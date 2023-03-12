package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.ContratoDTO;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.repository.ContratoRepository;
import com.albino.tecnologia.osworks.service.ContratoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ContratoServiceImpl implements ContratoService {
    private final ContratoRepository contratoRepository;

    @Override
    public Contrato encontrarPeloIdContrato(Long id) {

        log.info("Encontrado O Contrato com '{}'", id);

        return contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id Não Encontrado"));
    }

    @Override
    public List<Contrato> listarTodosContratos() {
        return contratoRepository.findAll();
    }

    @Override
    public Contrato criarContrato(ContratoDTO contratoDTO) {

        log.info("Novo Contrado Criado '{}'", contratoDTO.toString());

        Contrato novoContrato = Contrato.builder()
                .numero(contratoDTO.getNumero())
                .dataInicio(contratoDTO.getDataInicio())
                .dataTermino(contratoDTO.getDataTermino())
                .valor(contratoDTO.getValor())
                .descricao(contratoDTO.getDescricao())
                .produtoOuServico(contratoDTO.getProdutoOuServico())
                .build();

        return contratoRepository.save(novoContrato);
    }

    @Override
    public Contrato atualizarContrato(Long id, ContratoDTO contratoDTO) {

        Contrato contratoAtualizado = encontrarPeloIdContrato(id);

        log.info(" Contrado Com Id: '{}' Sendo Atualizado",id);

        contratoAtualizado.setDataInicio(contratoDTO.getDataInicio());
        contratoAtualizado.setDataTermino(contratoDTO.getDataTermino());
        contratoAtualizado.setValor(contratoDTO.getValor());
        contratoAtualizado.setDescricao(contratoDTO.getDescricao());
        contratoAtualizado.setProdutoOuServico(contratoDTO.getProdutoOuServico());

        log.info(" Contrado Com Id: '{}' Foi Atualizado Para '{}' ",id,contratoAtualizado.toString());

        return contratoRepository.save(contratoAtualizado);
    }

    @Override
    public void deletarContrato(Long id) {

        log.info(" Contrado Com Id: '{}' Sendo Inativado",id);

        Contrato contratoDeletado = encontrarPeloIdContrato(id);

        contratoRepository.delete(contratoDeletado);

        log.info(" Contrado Com Id: '{}' Foi Inativado",id);
    }
}
