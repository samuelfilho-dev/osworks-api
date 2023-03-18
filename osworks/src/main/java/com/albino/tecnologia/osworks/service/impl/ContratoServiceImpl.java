package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.ContratoDTO;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.Empresa;
import com.albino.tecnologia.osworks.model.Responsavel;
import com.albino.tecnologia.osworks.model.Usuario;
import com.albino.tecnologia.osworks.repository.ContratoRepository;
import com.albino.tecnologia.osworks.repository.EmpresaRespository;
import com.albino.tecnologia.osworks.repository.ResponsavelRepository;
import com.albino.tecnologia.osworks.repository.UsuarioRepository;
import com.albino.tecnologia.osworks.service.ContratoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
@Log4j2
public class ContratoServiceImpl implements ContratoService {
    private static int id = 1;
    private final ContratoRepository contratoRepository;
    private final EmpresaRespository empresaRespository;
    private final ResponsavelRepository responsavelRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Contrato encontrarPeloIdContrato(Long id) {

        log.info("Encontrado o Contrato com ID:'{}'",id);

        return contratoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id Não Encontrado"));
    }

    @Override
    public List<Contrato> listarTodosContratos() {

        log.info("Listando Todos os Contratos");

        return contratoRepository.findAll();
    }

    @Override
    public Contrato criarContrato(ContratoDTO contratoDTO) {

        log.info("Novo Contrato Criado '{}'", contratoDTO);

        Integer contador = geradorDeCodigoDoContrato();
        String codigoDoContrato = String.format("%03d/%d", contador, LocalDate.now().getYear());

        Empresa empresa = empresaRespository.findById(contratoDTO.getIdDaEmpresa()).get();
        Responsavel responsavel = responsavelRepository.findById(contratoDTO.getIdDoResponsavel()).get();


        Contrato novoContrato = Contrato.builder()
                .codigoDoContrato(codigoDoContrato)
                .dataInicio(contratoDTO.getDataInicio())
                .dataTermino(contratoDTO.getDataTermino())
                .qtdDePontosFuncao(contratoDTO.getQtdDePontosFuncao())
                .valor(contratoDTO.getValor())
                .descricao(contratoDTO.getDescricao())
                .empresa(empresa)
                .responsavel(responsavel)
                .tipoDeContrato(contratoDTO.getTipoDeContrato())
                .build();

        return contratoRepository.save(novoContrato);
    }

    @Override
    public Contrato atualizarContrato(Long id,ContratoDTO contratoDTO) {

        Contrato contratoAtualizado = encontrarPeloIdContrato(id);

        log.info("Contrato com ID:'{}' Sendo Atualizado '{}'",id,contratoDTO);

        contratoAtualizado.setDataInicio(contratoDTO.getDataInicio());
        contratoAtualizado.setDataTermino(contratoDTO.getDataTermino());
        contratoAtualizado.setValor(contratoDTO.getValor());
        contratoAtualizado.setDescricao(contratoDTO.getDescricao());
        contratoAtualizado.setTipoDeContrato(contratoDTO.getTipoDeContrato());

        log.info("Contrato com ID: '{}' Foi Atualizado '{}'",id,contratoDTO);

        return contratoRepository.save(contratoAtualizado);
    }

    @Override
    public Contrato distribuirContrato(Long id, ContratoDTO contratoDTO) {

        Contrato contratoAtualizado = encontrarPeloIdContrato(id);
        Usuario usuario = usuarioRepository.findById(contratoDTO.getIdDoUsuario()).get();

        log.info("Contrato com ID:'{}' Foi Repasado Para '{}'",id,usuario);

        contratoAtualizado.setUsuario(usuario);

        return contratoRepository.save(contratoAtualizado);
    }

    @Override
    public void deletarContrato(Long id) {

        log.info("Contrato com ID:'{}' Sendo Inativado",id);

        Contrato contratoDeletado = encontrarPeloIdContrato(id);

        log.info("Contrato com ID:'{}' Foi Inativado",id);

        contratoRepository.delete(contratoDeletado);
    }

    public static Integer geradorDeCodigoDoContrato(){

        Integer ano = LocalDate.now().getYear();

        if (ano != LocalDate.now().getYear()){
            id = 0;
        }

        return id++;
    }
}
