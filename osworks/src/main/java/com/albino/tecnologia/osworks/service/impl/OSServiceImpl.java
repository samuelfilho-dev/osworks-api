package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.OSDTO;
import com.albino.tecnologia.osworks.exception.BadResquestException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
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
                .orElseThrow(() -> new BadResquestException("Os não encontrada"));

    }

    @Override
    public Page<OS> listarTodasOS(Pageable pageable) {

        log.info("Listando Todas as OS");

        return osRepository.findAll(pageable);
    }

    @Override
    public List<OS> listarTodasOSPorStatus(String status) {

        log.info("Listando Todas as OS Com o Status de '{}'",status);

        return osRepository.findAllByStatus(status);
    }

    @Override
    public Contrato mostrarContratoDaOS(Long id) {

        log.info("Contrato da OS com ID: ",id);

        OS os = encontrarPeloId(id);

        return os.getContrato();
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

        osEValida(osdto,id);

        OS novaOS = OS.builder()
                .codigoDaOS(codigoDaOS)
                .descricao(osdto.getDescricao())
                .qtdDeHoras(osdto.getQtdDeHoras())
                .qtdPontosDeFuncao(osdto.getQtdPontosDeFuncao())
                .contrato(contrato)
                .responsavel(responsavel)
                .empresa(empresa)
                .status("ativo")
                .dataDeAbertura(LocalDate.now())
                .build();

        return osRepository.save(novaOS);
    }

    @Override
    public OS atualizarOS(Long id, OSDTO osdto) {

        OS osAtualizada = encontrarPeloId(id);

        log.info("OS com ID:'{}' Sendo Atualizada Para '{}'", id, osdto);

        osAtualizada.setDescricao(osdto.getDescricao());
        osAtualizada.setQtdDeHoras(osdto.getQtdDeHoras());
        osAtualizada.setQtdPontosDeFuncao(osdto.getQtdPontosDeFuncao());

        log.info("OS com ID:'{}' Foi Atualizada",id);

        return osRepository.save(osAtualizada);
    }

    @Override
    public OS execultarOS(Long id) {

        OS os = encontrarPeloId(id);

        os.setStatus("em execucao");

        return osRepository.save(os);
    }

    @Override
    public OS finalizarOS(Long id) {

        OS os = encontrarPeloId(id);

        log.info("OS com o Codigo:'{}' Foi Finalizada",os.getCodigoDaOS());

        os.setStatus("finalizada");

        return osRepository.save(os);
    }

    @Override
    public void inativarOS(Long id) {

        log.info("OS de ID:'{}' sendo Inativada ", id);

        OS osInativada = encontrarPeloId(id);
        osInativada.setStatus("inativo");

        osRepository.save(osInativada);

        log.info("OS de ID:'{}' foi Inativada", id);
    }

    public static Integer geradorDeCodigoDaOS(){
        return id++;
    }

    public Boolean osEValida(OSDTO osdto, Long id){

        Contrato contrato = contratoRepository.findById(id).get();
        Empresa empresa = empresaRespository.findById(osdto.getIdDaEmpresa()).get();
        Responsavel responsavel = responsavelRepository.findById(osdto.getIdDoResponsavel()).get();

        if (contrato.getStatus().equals("inativo")) throw new BadResquestException("Contrato está inativo");
        if (empresa.getStatus().equals("inativo")) throw new BadResquestException("Empresa está inativa");
        if (responsavel.getStatus().equals("inativo")) throw new BadResquestException("Responsavel está inativo");

        return true;
    }

}
