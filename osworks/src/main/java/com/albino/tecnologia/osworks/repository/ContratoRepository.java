package com.albino.tecnologia.osworks.repository;
import com.albino.tecnologia.osworks.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato,Long> {
}
