package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.EmpresaDTO;
import com.albino.tecnologia.osworks.model.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmpresaService {
    Empresa encontrarPeloId(Long id);
    Page<Empresa> listarTodasEmpresas(String status, Pageable pageable);
    Empresa criarEmpresa(EmpresaDTO empresaDTO);
    Empresa atualizarEmpresa(Long id,EmpresaDTO empresaDTO);
    void deletarEmpresa(Long id);
}
