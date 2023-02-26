package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.EmpresaDTO;
import com.albino.tecnologia.osworks.model.Empresa;
import com.albino.tecnologia.osworks.repository.EmpresaRespository;
import com.albino.tecnologia.osworks.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRespository empresaRespository;

    @Override
    public Empresa encontrarPeloId(Long id) {
       return empresaRespository.findById(id)
               .orElseThrow(()-> new RuntimeException("Id Não Encontrado"));
    }

    @Override
    public List<Empresa> listarTodasEmpresas() {
        return empresaRespository.findAll();
    }

    @Override
    public Empresa criarEmpresa(EmpresaDTO empresaDTO) {

        Empresa novaEmpresa = Empresa.builder()
                .CNPJ(empresaDTO.getCNPJ())
                .razaoSocial(empresaDTO.getRazaoSocial())
                .inscricaoEstadual(empresaDTO.getInscricaoEstadual())
                .numeroDeTelefone(empresaDTO.getNumeroDeTelefone())
                .email(empresaDTO.getEmail())
                .dataDeNascimento(empresaDTO.getDataDeNascimento())
                .build();

        return empresaRespository.save(novaEmpresa);
    }

    @Override
    public Empresa atualizarEmpresa(Long id,EmpresaDTO empresaDTO) {
        Empresa empresaAtualizada = encontrarPeloId(id);

        empresaAtualizada.setRazaoSocial(empresaDTO.getRazaoSocial());
        empresaAtualizada.setInscricaoEstadual(empresaDTO.getInscricaoEstadual());
        empresaAtualizada.setNumeroDeTelefone(empresaDTO.getNumeroDeTelefone());
        empresaAtualizada.setEmail(empresaDTO.getEmail());
        empresaAtualizada.setDataDeNascimento(empresaDTO.getDataDeNascimento());

        return empresaRespository.save(empresaAtualizada);
    }

    @Override
    public void deletarEmpresa(Long id) {
        Empresa empresaDeletada = encontrarPeloId(id);

        empresaRespository.delete(empresaDeletada);
    }
}
