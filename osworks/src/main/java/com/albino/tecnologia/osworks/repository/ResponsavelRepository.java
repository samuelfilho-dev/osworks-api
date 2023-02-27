package com.albino.tecnologia.osworks.repository;

import com.albino.tecnologia.osworks.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {

}
