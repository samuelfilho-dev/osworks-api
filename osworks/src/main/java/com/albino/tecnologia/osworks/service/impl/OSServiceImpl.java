package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.OSDTO;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.Empresa;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.model.Responsavel;
import com.albino.tecnologia.osworks.repository.ContratoRepository;
import com.albino.tecnologia.osworks.repository.EmpresaRespository;
import com.albino.tecnologia.osworks.repository.OSRepository;
import com.albino.tecnologia.osworks.repository.ResponsavelRepository;
import com.albino.tecnologia.osworks.service.OSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OSServiceImpl implements OSService {
    private static int id = 1;
    private final OSRepository osRepository;
    private final ContratoRepository contratoRepository;
    private final EmpresaRespository empresaRespository;
    private final ResponsavelRepository responsavelRepository;


    @Override
    public OS encontrarPeloId(Long id) {
        log.info("OS com ID:'{}' Encontrada", id);
        return osRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não Encontramos Id"));
    }

    @Override
    public List<OS> listarTodasOS() {
        log.info("Listando Todas as OS");
        return osRepository.findAll();
    }

    @Override
    public OS criarOS(Long id,OSDTO osdto) {

        Contrato contrato = contratoRepository.findById(id).get();
        Empresa empresa = empresaRespository.findById(osdto.getIdDaEmpresa()).get();
        Responsavel responsavel = responsavelRepository.findById(osdto.getIdDoResponsavel()).get();

        Long qtdDePontosFuncao = contrato.getQtdDePontosFuncao();
        Long atualizarPontosDeFuncao = qtdDePontosFuncao - osdto.getQtdPontosDeFuncao();
        contrato.setQtdDePontosFuncao(atualizarPontosDeFuncao);

        Integer contador = geradorDeCodigoDaOS();
        String codigoDaOS = String.format("OS-Nº%05d", contador);


        log.info("Nova OS Criada '{}'", osdto);

        OS novaOS = OS.builder()
                .codigoDaOS(codigoDaOS)
                .descricao(osdto.getDescricao())
                .qtdDeHoras(osdto.getQtdDeHoras())
                .qtdPontosDeFuncao(osdto.getQtdPontosDeFuncao())
                .contrato(contrato)
                .responsavel(responsavel)
                .empresa(empresa)
                .dataDeAbertura(LocalDate.now())
                .build();

        return osRepository.save(novaOS);
    }

    @Override
    public OS atualizarOS(Long id, OSDTO osdto) {

        OS osAtualizada = encontrarPeloId(id);

        log.info("OS com ID:'{}' Sendo Atualizada '{}'", id, osdto);
        osAtualizada.setDescricao(osdto.getDescricao());
        osAtualizada.setQtdDeHoras(osdto.getQtdDeHoras());
        osAtualizada.setQtdPontosDeFuncao(osdto.getQtdPontosDeFuncao());


        log.info("OS com ID:'{}' Atualizada '{}'", id, osdto);
        return osRepository.save(osAtualizada);
    }

    @Override
    public void deletarOS(Long id) {

        log.info("OS de ID:'{}' sendo Inativada ", id);
        OS osDeletada = encontrarPeloId(id);

        log.info("OS de ID:'{}' foi Inativada", id);
        osRepository.delete(osDeletada);
    }

    public static Integer geradorDeCodigoDaOS(){
        return id++;
    }
}
