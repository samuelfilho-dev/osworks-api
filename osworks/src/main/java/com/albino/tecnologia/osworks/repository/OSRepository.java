package com.albino.tecnologia.osworks.repository;

import com.albino.tecnologia.osworks.model.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OSRepository extends JpaRepository<OS, Long> {
}
