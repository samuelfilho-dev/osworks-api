package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.OSDTO;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.repository.OSRepository;
import com.albino.tecnologia.osworks.service.OSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OSServiceImpl implements OSService {
    private static int id = 1;
    private final OSRepository osRepository;

    @Override
    public OS encontrarPeloId(Long id) {
        log.info("Os Encontrada '{}'", id);
        return osRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não Encontramos Id"));
    }

    @Override
    public List<OS> listarTodasOS() {
        log.info("Listando Todas as OS");
        return osRepository.findAll();
    }

    @Override
    public OS criarOS(OSDTO osdto) {

        Integer contador = geradorDeCodigoDaOS();
        String codigoDaOS = String.format("OS-Nº%05d", contador);

        log.info("Criando OS '{}'", osdto);
        OS novaOS = OS.builder()
                .codigoDaOS(codigoDaOS)
                .descricao(osdto.getDescricao())
                .qtdDeHoras(osdto.getQtdDeHoras())
                .qtdPontosDeFuncao(osdto.getQtdPontosDeFuncao())
                .dataDeAbertura(osdto.getDataDeAbertura())
                .build();

        log.info("OS Criada '{}'", osdto);
        return osRepository.save(novaOS);
    }

    @Override
    public OS atualizarOS(Long id, OSDTO osdto) {

        OS osAtualizada = encontrarPeloId(id);

        log.info("Atualizando OS '{}''{}'", id, osdto);
        osAtualizada.setDescricao(osdto.getDescricao());
        osAtualizada.setQtdDeHoras(osdto.getQtdDeHoras());
        osAtualizada.setQtdPontosDeFuncao(osdto.getQtdPontosDeFuncao());
        osAtualizada.setDataDeAbertura(osdto.getDataDeAbertura());

        log.info("OS Atualizada para '{}''{}'", id, osdto);
        return osRepository.save(osAtualizada);
    }

    @Override
    public void deletarOS(Long id) {

        log.info("Inativando OS '{}'", id);
        OS osDeletada = encontrarPeloId(id);

        log.info("OS Inativada '{}'", id);
        osRepository.delete(osDeletada);
    }

    public static Integer geradorDeCodigoDaOS(){
        return id++;
    }
}
