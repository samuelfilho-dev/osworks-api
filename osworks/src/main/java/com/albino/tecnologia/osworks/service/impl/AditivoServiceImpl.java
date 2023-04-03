package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.AditivoDTO;
import com.albino.tecnologia.osworks.exception.BadResquestException;
import com.albino.tecnologia.osworks.model.Aditivo;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.repository.AditivoRepository;
import com.albino.tecnologia.osworks.repository.ContratoRepository;
import com.albino.tecnologia.osworks.service.AditivoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class AditivoServiceImpl implements AditivoService {

    private final ContratoRepository contratoRepository;
    private final AditivoRepository aditivoRepository;

    Aditivo aditivo = new Aditivo();

    @Override
    @Transactional
    public Aditivo adicionarAditivoDeData(Long id, AditivoDTO aditivoDTO) {

        log.info("Adicionando Nova Data De Vencimento Do Contrato Com ID: '{}' ",id);

        Contrato contrato = encontrarContrato(id);
        aditivo.setDataTermino(aditivoDTO.getDataTermino());
        aditivo.setContrato(contrato);

        aditivoRepository.save(aditivo);

        contrato.getAditivos().add(aditivo);
        contratoRepository.save(contrato);

        return aditivo;
    }

    @Override
    @Transactional
    public Aditivo adicionarAditivoDenovoValorUnitario(Long id, AditivoDTO aditivoDTO) {

        log.info("Adicionando Novo Valor Unitario do Contrato Com ID:'{}'",id);

        Contrato contrato = encontrarContrato(id);

        aditivo.setValorUnitario(aditivoDTO.getValorUnitario());
        aditivo.setContrato(contrato);

        aditivoRepository.save(aditivo);

        contrato.getAditivos().add(aditivo);
        contratoRepository.save(contrato);

        return aditivo;
    }

    @Override
    @Transactional
    public Aditivo adicionarAditivoDeDescricoes(Long id, AditivoDTO aditivoDTO) {

        log.info("Adicionando Novas Descrições do Contrato Com ID:'{}'",id);

        Contrato contrato = encontrarContrato(id);

        aditivo.setDescricoes(aditivoDTO.getDescricoes());
        aditivo.setContrato(contrato);

        aditivoRepository.save(aditivo);

        contrato.getAditivos().add(aditivo);
        contratoRepository.save(contrato);

        return aditivo;
    }

    @Override
    @Transactional
    public Aditivo adicionarAditivoDetiposDeContratos(Long id, AditivoDTO aditivoDTO) {

        log.info("Adicionando Novo Tipo De Contrato no Contrato com ID:'{}'",id);

        Contrato contrato = encontrarContrato(id);

        aditivo.setTipoDeContrato(aditivoDTO.getTipoDeContrato());
        aditivo.setContrato(contrato);

        aditivoRepository.save(aditivo);

        contrato.getAditivos().add(aditivo);
        contratoRepository.save(contrato);

        return aditivo;
    }

    public Contrato encontrarContrato(Long id){

        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new BadResquestException("Contrato Não Encontrado"));

        if (contrato.getStatus().equals("inativo")) throw new BadResquestException("O Contrato Está Inativo");

        return contrato;
    }
}
