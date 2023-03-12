package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.EmpresaDTO;
import com.albino.tecnologia.osworks.model.Empresa;
import com.albino.tecnologia.osworks.repository.EmpresaRespository;
import com.albino.tecnologia.osworks.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRespository empresaRespository;

    @Override
    public Empresa encontrarPeloId(Long id) {

        log.info("Empresa Encontrada '{}'", id);
       return empresaRespository.findById(id)
               .orElseThrow(()-> new RuntimeException("Id Não Encontrado"));
    }

    @Override
    public List<Empresa> listarTodasEmpresas() {

        log.info("Listando Todas as Empresas");
        return empresaRespository.findAll();
    }

    @Override
    public Empresa criarEmpresa(EmpresaDTO empresaDTO) {

        log.info("Criando Empresa '{}'", empresaDTO);
        Empresa novaEmpresa = Empresa.builder()
                .CNPJ(empresaDTO.getCNPJ())
                .razaoSocial(empresaDTO.getRazaoSocial())
                .inscricaoEstadual(empresaDTO.getInscricaoEstadual())
                .numeroDeTelefone(empresaDTO.getNumeroDeTelefone())
                .tipoDeEmpresa(empresaDTO.getTipoDeEmpresa())
                .email(empresaDTO.getEmail())
                .dataDeNascimento(empresaDTO.getDataDeNascimento())
                .build();

        log.info("Empresa Criada '{}'", empresaDTO);
        return empresaRespository.save(novaEmpresa);
    }

    @Override
    public Empresa atualizarEmpresa(Long id,EmpresaDTO empresaDTO) {
        Empresa empresaAtualizada = encontrarPeloId(id);

        log.info("Atualizando Empresa '{}''{}'", id, empresaDTO);
        empresaAtualizada.setRazaoSocial(empresaDTO.getRazaoSocial());
        empresaAtualizada.setInscricaoEstadual(empresaDTO.getInscricaoEstadual());
        empresaAtualizada.setNumeroDeTelefone(empresaDTO.getNumeroDeTelefone());
        empresaAtualizada.setEmail(empresaDTO.getEmail());
        empresaAtualizada.setDataDeNascimento(empresaDTO.getDataDeNascimento());

        log.info("Empresa Atualizada para '{}''{}'", id, empresaDTO);
        return empresaRespository.save(empresaAtualizada);
    }

    @Override
    public void deletarEmpresa(Long id) {
        log.info("Inativando Empresa '{}'", id);
        Empresa empresaDeletada = encontrarPeloId(id);

        log.info("Empresa Inativada '{}'", id);
        empresaRespository.delete(empresaDeletada);
    }
}
