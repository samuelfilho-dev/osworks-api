package com.albino.tecnologia.osworks.repository;

import com.albino.tecnologia.osworks.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {
}
