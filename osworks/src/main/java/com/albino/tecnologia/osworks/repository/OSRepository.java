package com.albino.tecnologia.osworks.repository;

import com.albino.tecnologia.osworks.model.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OSRepository extends JpaRepository<OS, Long> {

    List<OS> findAllByStatus(String status);
}
