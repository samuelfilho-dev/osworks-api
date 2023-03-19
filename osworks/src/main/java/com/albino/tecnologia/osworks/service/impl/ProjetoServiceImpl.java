package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.ProjetoDTO;
import com.albino.tecnologia.osworks.model.OS;
import com.albino.tecnologia.osworks.model.Projeto;
import com.albino.tecnologia.osworks.model.Usuario;
import com.albino.tecnologia.osworks.repository.OSRepository;
import com.albino.tecnologia.osworks.repository.ProjetoRepository;
import com.albino.tecnologia.osworks.repository.UsuarioRepository;
import com.albino.tecnologia.osworks.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository projetoRepository;
    private final OSRepository osRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public Projeto encontrarPeloId(Long id) {

        log.info("Projeto de ID:'{}' Encontrado",id);
        return projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado"));
    }


    @Override
    public Page<Projeto> listarTodosProjetos(Pageable pageable) {

        log.info("Listando Todos os Projetos");
        return projetoRepository.findAll(pageable);
    }

    @Override
    public Projeto criarProjeto(ProjetoDTO projetoDTO) {

        log.info("Novo Projeto Criado '{}'", projetoDTO);

        OS os = osRepository.findById(projetoDTO.getIdDaOs()).get();
        Usuario usuario = usuarioRepository.findById(projetoDTO.getIdDoUsuario()).get();

        Projeto novoProjeto = Projeto.builder()
                .nome(projetoDTO.getNome())
                .descricao(projetoDTO.getDescricao())
                .dataDeInicio(projetoDTO.getDataDeInicio())
                .os(os)
                .usuario(usuario)
                .dataDeTermino(projetoDTO.getDataDeTermino())
                .status(projetoDTO.getStatus())
                .build();

        return projetoRepository.save(novoProjeto);
    }

    @Override
    public Projeto atualizarProjeto(Long id, ProjetoDTO projetoDTO) {
        Projeto projetoAtualizado = encontrarPeloId(id);

        log.info("Projeto de ID:'{}' Sendo Atualizado '{}'", id, projetoDTO);
        projetoAtualizado.setNome(projetoDTO.getNome());
        projetoAtualizado.setDescricao(projetoDTO.getDescricao());
        projetoAtualizado.setDataDeInicio(projetoDTO.getDataDeInicio());
        projetoAtualizado.setDataDeTermino(projetoDTO.getDataDeTermino());
        projetoAtualizado.setStatus(projetoDTO.getStatus());

        log.info("Projeto de ID:'{}' Foi Atualizado '{}'", id, projetoDTO);
        return projetoRepository.save(projetoAtualizado);
    }

    @Override
    public void distribuirProjeto(Projeto projeto, Long id) {

        Projeto projetoDistribuido = encontrarPeloId(projeto.getId());
        Usuario usuario = usuarioRepository.findById(id).get();

        projetoDistribuido.setUsuario(usuario);
    }

    @Override
    public void inativarProjeto(Long id) {
        log.info("Projeto de ID:'{}' Sendo Inativado", id);

        Projeto projetoInativado = encontrarPeloId(id);
        projetoInativado.setStatus("Inativo");

        log.info("Projeto de ID:'{}' Foi Inativado", id);
    }
}
