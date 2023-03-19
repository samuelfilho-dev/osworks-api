package com.albino.tecnologia.osworks.repository;

import com.albino.tecnologia.osworks.model.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpresaRespository extends JpaRepository<Empresa,Long> {

    Page<Empresa> findByStatus(String status, Pageable pageable);
}
