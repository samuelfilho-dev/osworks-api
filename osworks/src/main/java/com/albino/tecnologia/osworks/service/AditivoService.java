package com.albino.tecnologia.osworks.service;

import com.albino.tecnologia.osworks.controller.dto.AditivoDTO;
import com.albino.tecnologia.osworks.model.Aditivo;

public interface AditivoService {

    Aditivo adicionarAditivoDeData(Long id, AditivoDTO aditivoDTO);
    Aditivo adicionarAditivoDenovoValorUnitario(Long id, AditivoDTO aditivoDTO);
    Aditivo adicionarAditivoDeDescricoes(Long id, AditivoDTO aditivoDTO);
    Aditivo adicionarAditivoDetiposDeContratos(Long id, AditivoDTO aditivoDTO);

}
