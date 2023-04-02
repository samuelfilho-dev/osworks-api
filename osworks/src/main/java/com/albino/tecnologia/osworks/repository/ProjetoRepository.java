package com.albino.tecnologia.osworks.repository;

import com.albino.tecnologia.osworks.model.Projeto;
import com.albino.tecnologia.osworks.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

    List<Projeto> findByGerenteDeProjeto(Usuario gerenteDeProjeto);
}
