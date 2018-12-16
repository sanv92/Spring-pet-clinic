package com.example.demo.services;

import com.example.demo.model.Owner;
import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike (String lastName);
}
