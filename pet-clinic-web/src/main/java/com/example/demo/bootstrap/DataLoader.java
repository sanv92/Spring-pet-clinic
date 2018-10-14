package com.example.demo.bootstrap;

import com.example.demo.model.Owner;
import com.example.demo.model.Vet;
import com.example.demo.services.OwnerService;
import com.example.demo.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("First Owner - Name 1");
        owner1.setLastName("Last Owner - Name 1");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("First Owner - Name 2");
        owner2.setLastName("Last Owner - Name 2");
        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("First Owner - Name 3");
        owner3.setLastName("Last Owner - Name 3");
        ownerService.save(owner3);

        Owner owner4 = new Owner();
        owner4.setFirstName("First Owner - Name 4");
        owner4.setLastName("Last Owner - Name 4");
        ownerService.save(owner4);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("First Vet - Name 1");
        vet1.setLastName("Last Vet - Name 1");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("First Vet - Name 2");
        vet2.setLastName("Last Vet - Name 2");
        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("First Vet - Name 3");
        vet3.setLastName("Last Vet - Name 3");
        vetService.save(vet3);

        System.out.println("Loaded Vets....");
    }
}
