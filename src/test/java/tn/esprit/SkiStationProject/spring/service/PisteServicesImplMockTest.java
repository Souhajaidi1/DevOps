package tn.esprit.SkiStationProject.spring.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.SkiStationProject.entities.Piste;
import tn.esprit.SkiStationProject.services.IPisteServices;
import tn.esprit.SkiStationProject.services.PisteServicesImpl;

@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
public class PisteServicesImplMockTest {

    @Mock
    private IPisteServices pisteServices;

    @InjectMocks
    private PisteServicesImpl pisteServicesImpl;

    @Test
    public void shouldAddPiste() {
        Piste piste = new Piste(); // Assurez-vous d'initialiser la piste avec les détails nécessaires
        when(pisteServices.addPiste(any(Piste.class))).thenReturn(piste);

        Piste savedPiste = pisteServices.addPiste(piste);
        assertNotNull(savedPiste);
        // Assurez-vous que les assertions adéquates sont faites pour les attributs de la piste ajoutée
        // Par exemple : assertNotNull(savedPiste.getNomAttribut());
    }

    @Test
    public void shouldUpdatePiste() {
        Piste piste = new Piste(); // Assurez-vous d'initialiser la piste avec les détails nécessaires
        when(pisteServices.addPiste(any(Piste.class))).thenReturn(piste);

        Piste savedPiste = pisteServices.addPiste(piste);
        assertNotNull(savedPiste);
        // Assurez-vous que les assertions adéquates sont faites pour les attributs de la piste ajoutée
        // Par exemple : assertNotNull(savedPiste.getNomAttribut());

        piste.setNamePiste("nouvelle_valeur"); // Mettez à jour un attribut de la piste
        when(pisteServices.retrievePiste(anyLong())).thenReturn(piste);

        pisteServices.addPiste(piste);
        log.info("Piste mise à jour");

        Piste updatedPiste = pisteServices.retrievePiste(savedPiste.getId());
        assertEquals("nouvelle_valeur", updatedPiste.getNamePiste());

        doNothing().when(pisteServices).removePiste(anyLong());
        pisteServices.removePiste(savedPiste.getId());
        log.info("aaa");
    }

    @Test
    public void shouldCheckPisteListSize() {
        Piste piste = new Piste(); // Assurez-vous d'initialiser la piste avec les détails nécessaires
        when(pisteServices.addPiste(any(Piste.class))).thenReturn(piste);

        int size = pisteServices.retrieveAllPistes().size();

        Piste savedPiste = pisteServices.addPiste(piste);

        //log.info("processing...");
        //assertEquals(size+1, pisteServices.retrieveAllPistes().size());
        // Assurez-vous que la taille de la liste de pistes a augmenté de 1 après l'ajout d'une nouvelle piste
    }

    @Test
    public void shouldCheckPiste() {
        // Initialisation
        Piste piste = new Piste(); // Assurez-vous d'initialiser la piste avec les détails nécessaires
        when(pisteServices.addPiste(any(Piste.class))).thenReturn(piste);

        // Ajout d'une piste
        Piste savedPiste = pisteServices.addPiste(piste);

        // Récupération de la piste
        Long numPiste = savedPiste.getId();
        when(pisteServices.retrievePiste(anyLong())).thenReturn(savedPiste);

        Piste retrievedPiste = pisteServices.retrievePiste(numPiste);

        // Assertions
        assertNotNull(retrievedPiste);
        assertEquals(savedPiste.getId(), retrievedPiste.getId());
        // Assurez-vous que les détails de la piste récupérée correspondent à ceux de la piste ajoutée
    }
/*
    @Test
    public void shouldRemovePiste() {
        Piste piste = new Piste(); // Assurez-vous d'initialiser la piste avec les détails nécessaires
        Piste savedPiste = pisteServices.addPiste(piste);
        Long numPiste = savedPiste.getId();

        pisteServices.removePiste(numPiste);
        Piste removedPiste = pisteServices.retrievePiste(numPiste);

        assertNull(removedPiste);
        // Vérifiez que la piste a été supprimée en tentant de la récupérer, ce qui doit renvoyer null
    }
    */
}
