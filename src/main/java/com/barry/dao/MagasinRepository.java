package com.barry.dao;

import com.barry.entities.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagasinRepository extends JpaRepository<Magasin, String> {
}
