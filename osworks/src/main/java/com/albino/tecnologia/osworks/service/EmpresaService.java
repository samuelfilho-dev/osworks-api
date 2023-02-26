package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.EmpresaDTO;
import com.albino.tecnologia.osworks.model.Empresa;

import java.util.List;

public interface EmpresaService {
    Empresa encontrarPeloId(Long id);
    List<Empresa> listarTodasEmpresas();
    Empresa criarEmpresa(EmpresaDTO empresaDTO);
    Empresa atualizarEmpresa(Long id,EmpresaDTO empresaDTO);
    void deletarEmpresa(Long id);
}
