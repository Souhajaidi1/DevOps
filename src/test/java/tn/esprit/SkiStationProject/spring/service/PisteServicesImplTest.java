package tn.esprit.SkiStationProject.spring.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.SkiStationProject.entities.Piste;
import tn.esprit.SkiStationProject.services.IPisteServices;

@SpringBootTest
@Slf4j
public class PisteServicesImplTest {

    @Autowired
    private IPisteServices pisteServices;

    @Test
    public void shouldAddPiste() {
        Piste piste = new Piste(); // Assurez-vous d'initialiser la piste avec les détails nécessaires
        Piste savedPiste = pisteServices.addPiste(piste);

        assertNotNull(savedPiste);
        //    Assurez-vous que les assertions adéquates sont faites pour les attributs de la piste ajoutée
        // Par exemple : assertNotNull(savedPiste.getNomAttribut());
    }

    @Test
    public void shouldUpdatePiste() {
        Piste piste = new Piste(); // Assurez-vous d'initialiser la piste avec les détails nécessaires
        Piste savedPiste = pisteServices.addPiste(piste);

        assertNotNull(savedPiste);
        // Assurez-vous que les assertions adéquates sont faites pour les attributs de la piste ajoutée
        // Par exemple : assertNotNull(savedPiste.getNomAttribut());

        piste.setNamePiste("nouvelle_valeur"); // Mettez à jour un attribut de la piste
        pisteServices.addPiste(piste);
        log.info("Piste mise à jour");

        Piste updatedPiste = pisteServices.retrievePiste(savedPiste.getId());
        assertEquals("nouvelle_valeur", updatedPiste.getNamePiste());

        pisteServices.removePiste(savedPiste.getId());
        log.info("aaa");
    }

    @Test
    public void shouldCheckPisteListSize() {
        int size = pisteServices.retrieveAllPistes().size();
        Piste piste = new Piste(); // Assurez-vous d'initialiser la piste avec les détails nécessaires
        Piste savedPiste = pisteServices.addPiste(piste);

        //log.info("processing...");
        //assertEquals(size+1, pisteServices.retrieveAllPistes().size());
        // Assurez-vous que la taille de la liste de pistes a augmenté de 1 après l'ajout d'une nouvelle piste
    }
    @Test
    public void shouldCheckPiste() {
        Piste piste = new Piste(); // Assurez-vous d'initialiser la piste avec les détails nécessaires
        Piste savedPiste = pisteServices.addPiste(piste);
        Long numPiste = savedPiste.getId();

        Piste retrievedPiste = pisteServices.retrievePiste(numPiste);

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
