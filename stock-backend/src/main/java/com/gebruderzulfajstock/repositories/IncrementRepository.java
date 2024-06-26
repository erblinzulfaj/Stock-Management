package com.gebruderzulfajstock.repositories;

import com.gebruderzulfajstock.models.Increment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncrementRepository extends JpaRepository<Increment, Long> {
}
