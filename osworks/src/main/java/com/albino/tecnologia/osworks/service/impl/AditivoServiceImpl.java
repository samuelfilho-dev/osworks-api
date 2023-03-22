package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.AditivoDTO;
import com.albino.tecnologia.osworks.model.Aditivo;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.repository.AditivoRepository;
import com.albino.tecnologia.osworks.repository.ContratoRepository;
import com.albino.tecnologia.osworks.service.AditivoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AditivoServiceImpl implements AditivoService {

    private final ContratoRepository contratoRepository;
    private final AditivoRepository aditivoRepository;

    @Override
    public Aditivo adicionarAditivoDeData(Long id, AditivoDTO aditivoDTO) {

        log.info("Adicionando Nova Data De Vencimento Do Contrato Com ID: '{}' ",id);

        Aditivo aditivo = new Aditivo();
        Contrato contrato = contratoRepository.findById(id).get();
        aditivo.setDataTermino(aditivoDTO.getDataTermino());
        contrato.getAditivos().add(aditivo);

        return aditivoRepository.save(aditivo);
    }

    @Override
    public Aditivo adicionarAditivoDenovoValorUnitario(Long id, AditivoDTO aditivoDTO) {

        log.info("Adicionando Novo Valor Unitario do Contrato Com ID:'{}'",id);

        Aditivo aditivo = new Aditivo();
        Contrato contrato = contratoRepository.findById(id).get();

        aditivo.setValorUnitario(aditivoDTO.getValorUnitario());
        contrato.getAditivos().add(aditivo);





        return aditivoRepository.save(aditivo);
    }

    @Override
    public Aditivo adicionarAditivoDeDescricoes(Long id, AditivoDTO aditivoDTO) {

        log.info("Adicionando Novas Descrições do Contrato Com ID:'{}'",id);

        Aditivo aditivo = new Aditivo();
        Contrato contrato = contratoRepository.findById(id).get();

        aditivo.setDescricoes(aditivoDTO.getDescricoes());
        contrato.getAditivos().add(aditivo);

        return aditivoRepository.save(aditivo);
    }

    @Override
    public Aditivo adicionarAditivoDetiposDeContratos(Long id, AditivoDTO aditivoDTO) {

        log.info("Adicionando Novo Tipo De Contrato no Contrato com ID:'{}'",id);

        Aditivo aditivo = new Aditivo();
        Contrato contrato = contratoRepository.findById(id).get();

        aditivo.setTipoDeContrato(aditivoDTO.getTipoDeContrato());
        contrato.getAditivos().add(aditivo);

        return aditivoRepository.save(aditivo);
    }
}
