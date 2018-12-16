package com.example.demo.services.jpa;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Owner;
import com.example.demo.repositories.OwnerRepository;
import com.example.demo.repositories.PetRepository;
import com.example.demo.repositories.PetTypeRepository;
import com.example.demo.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("jpa")
public class OwnerJpaService implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerJpaService(
            OwnerRepository ownerRepository,
            PetRepository petRepository,
            PetTypeRepository petTypeRepository
    ) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        return new HashSet<>(ownerRepository.findAll());
    }

    @Override
    public Owner findById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);

        if (!owner.isPresent()) {
            throw new NotFoundException("Not Found. For id: " + id.toString());
        }

        return owner.get();
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
