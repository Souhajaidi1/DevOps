package tn.esprit.SkiStationProject.service;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import tn.esprit.SkiStationProject.entities.*;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;
import tn.esprit.SkiStationProject.repositories.*;
import tn.esprit.SkiStationProject.services.ISkierServices;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class SkierServicesImplTest {

    @Autowired
    private ISkierServices skierServices;

    @Autowired
    private SkierRepository skierRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PisteRepository pisteRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Test
    public void shouldAddSkier() {
        Skier skier = new Skier();
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        // Set the start date for the subscription
        subscription.setStartDate(LocalDate.now()); // Example: set the start date as the current date

        // Set other details for the skier
        skier.setSubscription(subscription);

        Skier savedSkier = skierServices.addSkier(skier);

        assertNotNull(savedSkier);
        assertEquals(subscription.getTypeSub(), savedSkier.getSubscription().getTypeSub());
        // You can add more assertions to test other properties
    }


  /*  @Test
    public void shouldUpdateSkierSubscription() {
        Skier skier = new Skier();
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        // Ensure the startDate is properly set
        subscription.setStartDate(LocalDate.now()); // Set the start date as the current date

        skier.setSubscription(subscription);

        Skier savedSkier = skierServices.addSkier(skier);
        assertNotNull(savedSkier);

        Subscription newSubscription = new Subscription();
        newSubscription.setTypeSub(TypeSubscription.MONTHLY);
        newSubscription.setStartDate(LocalDate.now()); // Ensure the startDate for the new subscription is set

        skierServices.assignSkierToSubscription(savedSkier.getId(), newSubscription.getId());
        log.info("Skier subscription updated");

        Skier updatedSkier = skierServices.retrieveSkier(savedSkier.getId());
        assertEquals(newSubscription.getTypeSub(), updatedSkier.getSubscription().getTypeSub());
    }*/



}

