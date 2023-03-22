package com.albino.tecnologia.osworks.repository;

import com.albino.tecnologia.osworks.model.Aditivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.LinkOption;

@Repository
public interface AditivoRepository extends JpaRepository<Aditivo, LinkOption> {
}
