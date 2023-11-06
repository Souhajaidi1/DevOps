package tn.esprit.SkiStationProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.SkiStationProject.entities.*;
import tn.esprit.SkiStationProject.entities.enums.TypeSubscription;
import tn.esprit.SkiStationProject.repositories.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SkierServicesImpl implements ISkierServices {

    private final SkierRepository skierRepository;
    private final PisteRepository pisteRepository;
    private final CourseRepository courseRepository;
    private final RegistrationRepository registrationRepository;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public List<Skier> retrieveAllSkiers() {
        return skierRepository.findAll();
    }

    @Override
    public Skier addSkier(Skier skier) {
        Subscription subscription = skier.getSubscription();
        if (subscription != null) {
            LocalDate startDate = subscription.getStartDate();
            if (startDate != null && subscription.getTypeSub() != null) {
                switch (subscription.getTypeSub()) {
                    case ANNUAL:
                        subscription.setEndDate(startDate.plusYears(1));
                        break;
                    case SEMESTRIEL:
                        subscription.setEndDate(startDate.plusMonths(6));
                        break;
                    case MONTHLY:
                        subscription.setEndDate(startDate.plusMonths(1));
                        break;
                    // Add a default case or handle null typeSub if needed
                }
            } else {
                throw new IllegalArgumentException("Subscription is missing essential data (startDate or typeSub)");
            }
        } else {
            // Handle missing subscription (e.g., throw an exception or create a new one)
            Subscription newSubscription = new Subscription();
            newSubscription.setStartDate(LocalDate.now());
            newSubscription.setTypeSub(TypeSubscription.MONTHLY);
            skier.setSubscription(newSubscription);
        }

        return skierRepository.save(skier);
    }

    @Override
    public Skier assignSkierToSubscription(Long numSkier, Long numSubscription) {
        Skier skier = skierRepository.findById(numSkier).orElse(null);
        Subscription subscription = subscriptionRepository.findById(numSubscription).orElse(null);

        if (skier != null && subscription != null) {
            skier.setSubscription(subscription);
            return skierRepository.save(skier);
        } else {
            if (skier == null && subscription == null) {
                throw new IllegalArgumentException("Both skier and subscription are missing");
            } else if (skier == null) {
                throw new IllegalArgumentException("Skier is missing");
            } else {
                throw new IllegalArgumentException("Subscription is missing");
            }
        }
    }

    @Override
    public Skier addSkierAndAssignToCourse(Skier skier, Long numCourse) {
        Skier savedSkier = skierRepository.save(skier);
        Course course = courseRepository.findById(numCourse)
                .orElseThrow(() -> new IllegalArgumentException("No Course Found with this id " + numCourse));

        Set<Registration> registrations = savedSkier.getRegistrations();
        for (Registration r : registrations) {
            r.setSkier(savedSkier);
            r.setCourse(course);
            registrationRepository.save(r);
        }
        return savedSkier;
    }

    @Override
    public void removeSkier(Long numSkier) {
        skierRepository.deleteById(numSkier);
    }

    @Override
    public Skier retrieveSkier(Long numSkier) {
        return skierRepository.findById(numSkier).orElse(null);
    }

    @Override
    public Skier assignSkierToPiste(Long numSkieur, Long numPiste) {
        Skier skier = skierRepository.findById(numSkieur).orElse(null);
        Piste piste = pisteRepository.findById(numPiste).orElse(null);

        if (skier != null && piste != null) {
            try {
                skier.getPistes().add(piste);
            } catch (NullPointerException exception) {
                Set<Piste> pisteList = new HashSet<>();
                pisteList.add(piste);
                skier.setPistes(pisteList);
            }

            return skierRepository.save(skier);
        } else {
            // Handle missing skier or piste (e.g., throw an exception or create new objects)
            return null; // Or something based on your requirement
        }
    }

    @Override
    public List<Skier> retrieveSkiersBySubscriptionType(TypeSubscription typeSubscription) {
        return skierRepository.findBySubscription_TypeSub(typeSubscription);
    }
}

