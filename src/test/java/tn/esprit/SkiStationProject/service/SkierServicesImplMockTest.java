package tn.esprit.SkiStationProject.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.SkiStationProject.entities.Skier;
import tn.esprit.SkiStationProject.repositories.*;
import tn.esprit.SkiStationProject.services.SkierServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.HashSet;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SkierServicesImplMockTest {

    @Mock
    private SkierRepository skierRepository;

    @Mock
    private PisteRepository pisteRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SkierServicesImpl skierServicesImpl;

    @Test
    public void shouldRetrieveAllSkiers() {
        List<Skier> mockSkiers = new ArrayList<>(); // Initialize mock skiers
        Mockito.lenient().when(skierRepository.findAll()).thenReturn(mockSkiers);

        List<Skier> retrievedSkiers = skierServicesImpl.retrieveAllSkiers();
        assertNotNull(retrievedSkiers);
        // Add assertions based on what's expected after retrieving skiers
    }

   // @Test
    //public void shouldAddSkier() {
      //  Skier mockSkier = new Skier(); // Initialize the mock skier with necessary details
        //when(skierRepository.save(any(Skier.class))).thenReturn(mockSkier);

        //Skier savedSkier = skierServicesImpl.addSkier(mockSkier);
        //assertNotNull(savedSkier);
        // Ensure that the appropriate assertions are made for the attributes of the added skier
        // For example: assertNotNull(savedSkier.getAttributeName());
   // }

   }
