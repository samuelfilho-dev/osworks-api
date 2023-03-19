package com.albino.tecnologia.osworks.repository;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato,Long> {

    List<Contrato> findByGerenteDeProjeto(Usuario gerenteDeProjeto);
}
