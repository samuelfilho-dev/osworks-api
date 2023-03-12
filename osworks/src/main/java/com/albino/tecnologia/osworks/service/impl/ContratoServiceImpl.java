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

        log.info("Contrato encontrado '{}'", id);
        return contratoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id Não Encontrado"));
    }

    @Override
    public List<Contrato> listarTodosContratos() {

        log.info("Listando Todos Os Contratos");
        return contratoRepository.findAll();
    }

    @Override
    public Contrato criarContrato(ContratoDTO contratoDTO) {

        log.info("Criando Contrato '{}'", contratoDTO);
        Contrato novoContrato = Contrato.builder()
                .numero(contratoDTO.getNumero())
                .dataInicio(contratoDTO.getDataInicio())
                .dataTermino(contratoDTO.getDataTermino())
                .valor(contratoDTO.getValor())
                .descricao(contratoDTO.getDescricao())
                .produtoOuServico(contratoDTO.getProdutoOuServico())
                .build();

        log.info("Contrato Criado '{}'", contratoDTO);
        return contratoRepository.save(novoContrato);
    }

    @Override
    public Contrato atualizarContrato(Long id,ContratoDTO contratoDTO) {
        Contrato contratoAtualizado = encontrarPeloIdContrato(id);

        log.info("Atualizando Contrato '{}''{}'", id, contratoDTO);

        contratoAtualizado.setDataInicio(contratoDTO.getDataInicio());
        contratoAtualizado.setDataTermino(contratoDTO.getDataTermino());
        contratoAtualizado.setValor(contratoDTO.getValor());
        contratoAtualizado.setDescricao(contratoDTO.getDescricao());
        contratoAtualizado.setProdutoOuServico(contratoDTO.getProdutoOuServico());

        log.info("Contrato Atualizado '{}''{}'", id, contratoDTO);
        return contratoRepository.save(contratoAtualizado);
    }

    @Override
    public void deletarContrato(Long id) {
        log.info("Inativando Contrato '{}'", id);
        Contrato contratoDeletado = encontrarPeloIdContrato(id);

        log.info("Contrato Inativo '{}'", id);
        contratoRepository.delete(contratoDeletado);
    }
}
