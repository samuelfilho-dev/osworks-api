package com.albino.tecnologia.osworks.service.impl;

import com.albino.tecnologia.osworks.controller.dto.ProjetoDTO;
import com.albino.tecnologia.osworks.controller.dto.UsuarioPorIdDTO;
import com.albino.tecnologia.osworks.controller.dto.UsuarioPorUsernameDTO;
import com.albino.tecnologia.osworks.exception.BadResquestException;
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

import java.util.List;

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
    public List<Projeto> listarProjetoPorGerenteDeProjeto(Long id) {

        Usuario usuario = encontrarGppPorId(new UsuarioPorIdDTO(id));

        log.info("Listando Todos os Projetos Do Gerente de Projeto '{}'",usuario.getNome());

        return projetoRepository.findByGerenteDeProjeto(usuario);
    }

    @Override
    public Projeto criarProjeto(ProjetoDTO projetoDTO) {

        log.info("Novo Projeto Criado '{}'", projetoDTO);

        OS os = osRepository.findById(projetoDTO.getIdDaOs()).get();

        if (os.getStatus().equals("inativo") || os.getStatus().equals("finalizada"))
            throw new BadResquestException("A OS está Inativa ou Finalizada");

        Projeto novoProjeto = Projeto.builder()
                .nome(projetoDTO.getNome())
                .descricao(projetoDTO.getDescricao())
                .dataDeInicio(projetoDTO.getDataDeInicio())
                .os(os)
                .dataDeTermino(projetoDTO.getDataDeTermino())
                .status("ativo")
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

        log.info("Projeto de ID:'{}' Foi Atualizado '{}'", id, projetoDTO);

        return projetoRepository.save(projetoAtualizado);
    }

    @Override
    public Projeto distribuirProjeto(Long id, UsuarioPorIdDTO usuarioDTO) {

        Projeto projetoDistribuido = encontrarPeloId(id);
        Usuario usuario = encontrarGppPorId(new UsuarioPorIdDTO(usuarioDTO.getIdDoUsuario()));

        log.info("Projeto com Id '{}' foi distribuido Para '{}'",id,usuario.getNome());

        projetoDistribuido.setGerenteDeProjeto(usuario);
        projetoDistribuido.setStatus("em andamento");

        return projetoRepository.save(projetoDistribuido);
    }

    @Override
    public Projeto distribuirProjetoPorUsername(Long id, UsuarioPorUsernameDTO usernameDTO) {

        Projeto projetoDistribuido = encontrarPeloId(id);
        Usuario usuario = encontrarGppPorUsername(new UsuarioPorUsernameDTO(usernameDTO.getUsernameDoUsuario()));

        log.info("Projeto com Id '{}' foi distribuido Para '{}'",id,usuario.getNome());

        projetoDistribuido.setGerenteDeProjeto(usuario);
        projetoDistribuido.setStatus("em andamento");

        return projetoRepository.save(projetoDistribuido);
    }

    @Override
    public Projeto encerrarProjeto(Long id) {

        Projeto projetoEncerrado = encontrarPeloId(id);

        log.info("Projeto com Id '{}' foi Encerrado",id);

        projetoEncerrado.setStatus("concluido");

        return projetoRepository.save(projetoEncerrado);
    }

    @Override
    public void inativarProjeto(Long id) {

        log.info("Projeto de ID:'{}' Sendo Inativado", id);

        Projeto projetoInativado = encontrarPeloId(id);
        projetoInativado.setStatus("inativo");

        projetoRepository.save(projetoInativado);

        log.info("Projeto de ID:'{}' Foi Inativado", id);
    }

    private Usuario encontrarGppPorId(UsuarioPorIdDTO usuarioPorIdDTO){

        Usuario usuario = usuarioRepository.findById(usuarioPorIdDTO.getIdDoUsuario())
                .orElseThrow(() -> new BadResquestException("Usuario Não Encontrado"));

        boolean roleGp = usuario.getRoles().stream().anyMatch(role -> role.getRoleName().name().equals("ROLE_GP"));
        if (!roleGp) throw new BadResquestException("Usuario não é Gerente de Projeto, por favor Verifique");

        if (usuario.getStatus().equals("inativo"))
            throw new BadResquestException("O Usuario está inativado");

        return usuario;
    }

    private Usuario encontrarGppPorUsername(UsuarioPorUsernameDTO usernameDTO){

        Usuario usuario = usuarioRepository.findByUsername(usernameDTO.getUsernameDoUsuario())
                .orElseThrow(() -> new BadResquestException("Usuario Não Encontrado"));

        boolean roleGp = usuario.getRoles().stream().anyMatch(role -> role.getRoleName().name().equals("ROLE_GP"));
        if (!roleGp) throw new BadResquestException("Usuario não é Gerente de Projeto, por favor Verifique");

        if (usuario.getStatus().equals("inativo"))
            throw new BadResquestException("O Usuario está inativado");

        return usuario;
    }
}
