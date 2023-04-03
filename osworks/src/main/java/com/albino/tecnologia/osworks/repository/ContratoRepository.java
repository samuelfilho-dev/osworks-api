package com.albino.tecnologia.osworks.repository;
import com.albino.tecnologia.osworks.model.Contrato;
import com.albino.tecnologia.osworks.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato,Long> {

    List<Contrato> findByDataTermino(LocalDate dataDeTermino);
    List<Contrato> findByDataTerminoBetween(LocalDate dataInicial, LocalDate dataFinal);
    @Query(value = "SELECT CASE WHEN data_termino < CURRENT_DATE THEN 0 ELSE " +
            "DATE_PART('day', AGE(data_termino, CURRENT_DATE)) END AS dias_para_vencer FROM contrato " +
            "WHERE id = :idContrato", nativeQuery = true)
    int findDiasParaVencer(@Param("idContrato") Long idContrato);
}
