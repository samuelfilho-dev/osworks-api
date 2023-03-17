package com.albino.tecnologia.osworks.repository;

import com.albino.tecnologia.osworks.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmpresaRespository extends JpaRepository<Empresa,Long> {

    List<Empresa> findByStatus(String status);
}
