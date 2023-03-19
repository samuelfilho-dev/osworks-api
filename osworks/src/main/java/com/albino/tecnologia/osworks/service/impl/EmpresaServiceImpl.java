package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.EmpresaDTO;
import com.albino.tecnologia.osworks.exception.BadResquestException;
import com.albino.tecnologia.osworks.model.Empresa;
import com.albino.tecnologia.osworks.model.Endereco;
import com.albino.tecnologia.osworks.model.Responsavel;
import com.albino.tecnologia.osworks.repository.EmpresaRespository;
import com.albino.tecnologia.osworks.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRespository empresaRespository;

    @Override
    public Empresa encontrarPeloId(Long id) {

        log.info("Empresa com ID:'{}' Encontrada", id);

       return empresaRespository.findById(id)
               .orElseThrow(()-> new BadResquestException("Empresa Não Encontrada"));
    }

    @Override
    public Page<Empresa> listarTodasEmpresas(String status, Pageable pageable) {

        if (status == null){
            log.info("Listando Todas as Empresas");
            return empresaRespository.findAll(pageable);
        }

        log.info("Listando Todas as Empresas");
        return empresaRespository.findByStatus(status, pageable);
    }

    @Override
    public Empresa criarEmpresa(EmpresaDTO empresaDTO) {

        log.info("Novo Endereço Criado '{}' ", empresaDTO.getEndereco());

        Endereco enderecoDaEmpresa = Endereco.builder()
                .CEP(empresaDTO.getEndereco().getCEP())
                .logradouro(empresaDTO.getEndereco().getLogradouro())
                .numero(empresaDTO.getEndereco().getNumero())
                .complemento(empresaDTO.getEndereco().getComplemento())
                .bairro(empresaDTO.getEndereco().getBairro())
                .cidade(empresaDTO.getEndereco().getCidade())
                .UF(empresaDTO.getEndereco().getUF())
                .build();

        log.info("Novo Responsavel Criado '{}' ", empresaDTO.getEndereco());

        Responsavel responsavelDaEmpresa = Responsavel.builder()
                .cpf(empresaDTO.getResponsavel().getCpf())
                .nome(empresaDTO.getResponsavel().getNome())
                .rg(empresaDTO.getResponsavel().getRg())
                .numTelefone(empresaDTO.getResponsavel().getNumTelefone())
                .email(empresaDTO.getResponsavel().getEmail())
                .departamento(empresaDTO.getResponsavel().getDepartamento())
                .cargo(empresaDTO.getResponsavel().getCargo())
                .endereco(empresaDTO.getResponsavel().getEndereco())
                .build();

        log.info("Nova Empresa Criada '{}'", empresaDTO);

        Empresa novaEmpresa = Empresa.builder()
                .cnpj(empresaDTO.getCnpj())
                .razaoSocial(empresaDTO.getRazaoSocial())
                .inscricaoEstadual(empresaDTO.getInscricaoEstadual())
                .numeroDeTelefone(empresaDTO.getNumeroDeTelefone())
                .tipoDeEmpresa(empresaDTO.getTipoDeEmpresa())
                .email(empresaDTO.getEmail())
                .endereco(enderecoDaEmpresa)
                .responsavel(responsavelDaEmpresa)
                .dataDeNascimento(empresaDTO.getDataDeNascimento())
                .build();

        return empresaRespository.save(novaEmpresa);
    }

    @Override
    public Empresa atualizarEmpresa(Long id,EmpresaDTO empresaDTO) {
        Empresa empresaAtualizada = encontrarPeloId(id);

        log.info("Empresa de ID:'{}' Sendo Atualizada '{}'", id, empresaDTO);
        empresaAtualizada.setRazaoSocial(empresaDTO.getRazaoSocial());
        empresaAtualizada.setInscricaoEstadual(empresaDTO.getInscricaoEstadual());
        empresaAtualizada.setNumeroDeTelefone(empresaDTO.getNumeroDeTelefone());
        empresaAtualizada.setEmail(empresaDTO.getEmail());
        empresaAtualizada.setDataDeNascimento(empresaDTO.getDataDeNascimento());

        log.info("Empresa de ID:'{}' Foi Atualizada '{}'", id, empresaDTO);
        return empresaRespository.save(empresaAtualizada);
    }

    @Override
    public void deletarEmpresa(Long id) {

        log.info("Empresa de ID:'{}' Sendo Inativada", id);

        Empresa empresaDeletada = encontrarPeloId(id);

        empresaDeletada.setStatus("inativa");
        log.info(empresaDeletada);

        log.info("Empresa de ID:'{}' Foi Inativada", id);
    }
}
