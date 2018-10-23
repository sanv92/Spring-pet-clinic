package com.example.demo.bootstrap;


import com.example.demo.model.*;
import com.example.demo.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitesService specialitesService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitesService specialitesService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitesService = specialitesService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType dogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType catType = petTypeService.save(cat);

        System.out.println("Loaded PetTypes ....");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialitesService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialitesService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialitesService.save(dentistry);

        System.out.println("Loaded Specialites ....");

        Owner owner1 = new Owner();
        owner1.setFirstName("First Owner - Name 1");
        owner1.setLastName("Last Owner - Name 1");
        owner1.setAddress("111 test - Address");
        owner1.setCity("Tallinn");
        owner1.setPhone("+111");

        Pet pet1 = new Pet();
        pet1.setName("Test Pet name - 1");
        pet1.setPetType(dogType);
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.now());

        owner1.getPets().add(pet1);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("First Owner - Name 2");
        owner2.setLastName("Last Owner - Name 2");
        owner2.setAddress("222 test - Address");
        owner2.setCity("city test 111");
        owner2.setPhone("+222");

        Pet pet2 = new Pet();
        pet2.setName("Test Pet name - 2");
        pet2.setPetType(dogType);
        pet2.setOwner(owner2);
        pet2.setBirthDate(LocalDate.now());

        owner1.getPets().add(pet2);
        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("First Owner - Name 3");
        owner3.setLastName("Last Owner - Name 3");
        owner3.setAddress("333 test - Address");
        owner3.setCity("city test 222");
        owner3.setPhone("+333");

        Pet pet3 = new Pet();
        pet3.setName("Test Pet name - 3");
        pet3.setPetType(catType);
        pet3.setOwner(owner3);
        pet3.setBirthDate(LocalDate.now());

        owner3.getPets().add(pet3);
        ownerService.save(owner3);

        Owner owner4 = new Owner();
        owner4.setFirstName("First Owner - Name 4");
        owner4.setLastName("Last Owner - Name 4");
        owner4.setAddress("444 test - Address");
        owner4.setCity("city test 333");
        owner4.setPhone("+444");

        Pet pet4 = new Pet();
        pet4.setName("Test Pet name - 4");
        pet4.setPetType(catType);
        pet4.setOwner(owner4);
        pet4.setBirthDate(LocalDate.now());

        owner4.getPets().add(pet4);
        ownerService.save(owner4);

        System.out.println("Loaded Owners....");

        Visit catVisit = new Visit();
        catVisit.setPet(pet1);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("animal - 1");
        visitService.save(catVisit);

        System.out.println("Loaded Visits....");

        Vet vet1 = new Vet();
        vet1.setFirstName("First Vet - Name 1");
        vet1.setLastName("Last Vet - Name 1");
        vet1.getSpecialities().add(savedDentistry);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("First Vet - Name 2");
        vet2.setLastName("Last Vet - Name 2");
        vet2.getSpecialities().add(savedRadiology);
        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("First Vet - Name 3");
        vet3.setLastName("Last Vet - Name 3");
        vet3.getSpecialities().add(savedSurgery);
        vetService.save(vet3);

        System.out.println("Loaded Vets....");
    }
}
