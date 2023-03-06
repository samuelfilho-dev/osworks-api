package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.OSDTO;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.repository.OSRepository;
import com.albino.tecnologia.osworks.service.OSService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OSServiceImpl implements OSService {
    private static int id = 1;
    private final OSRepository osRepository;

    @Override
    public OS encontrarPeloId(Long id) {
        return osRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não Encontramos Id"));
    }

    @Override
    public List<OS> listarTodasOS() {
        return osRepository.findAll();
    }

    @Override
    public OS criarOS(OSDTO osdto) {

        Integer contador = geradorDeCodigoDaOS();
        String codigoDaOS = String.format("OS-Nº%05d", contador);

        OS novaOS = OS.builder()
                .codigoDaOS(codigoDaOS)
                .descricao(osdto.getDescricao())
                .qtdDeHoras(osdto.getQtdDeHoras())
                .qtdPontosDeFuncao(osdto.getQtdPontosDeFuncao())
                .dataDeAbertura(osdto.getDataDeAbertura())
                .build();

        return osRepository.save(novaOS);
    }

    @Override
    public OS atualizarOS(Long id, OSDTO osdto) {

        OS osAtualizada = encontrarPeloId(id);

        osAtualizada.setDescricao(osdto.getDescricao());
        osAtualizada.setQtdDeHoras(osdto.getQtdDeHoras());
        osAtualizada.setQtdPontosDeFuncao(osdto.getQtdPontosDeFuncao());
        osAtualizada.setDataDeAbertura(osdto.getDataDeAbertura());

        return osRepository.save(osAtualizada);
    }

    @Override
    public void deletarOS(Long id) {

        OS osDeletada = encontrarPeloId(id);

        osRepository.delete(osDeletada);
    }

    public static Integer geradorDeCodigoDaOS(){
        return id++;
    }
}
