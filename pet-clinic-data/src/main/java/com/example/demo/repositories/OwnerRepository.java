package com.example.demo.repositories;

import com.example.demo.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByLastName(String lastName);
}
