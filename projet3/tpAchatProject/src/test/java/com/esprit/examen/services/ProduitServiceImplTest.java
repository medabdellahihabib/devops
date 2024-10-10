package com.esprit.examen.services;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Produit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitServiceImplTest {

    @Autowired
    IProduitService produitService;

    @Test
    public void testAddProduit() {
        Produit produit = new Produit();
        produit.setCodeProduit("P001");
        produit.setLibelleProduit("Test Produit");
        Produit savedProduit = produitService.addProduit(produit);
        assertNotNull(savedProduit.getIdProduit());
        assertEquals("Test Produit", savedProduit.getLibelleProduit());
        produitService.deleteProduit(savedProduit.getIdProduit());
    }

    @Test
    public void testRetrieveAllProduits() {
        List<Produit> produits = produitService.retrieveAllProduits();
        assertNotNull(produits);
        assertTrue(produits.size() > 0);
    }

    @Test
    public void testDeleteProduit() {
        Produit produit = new Produit();
        produit.setCodeProduit("P002");
        produit.setLibelleProduit("Produit to Delete");
        Produit savedProduit = produitService.addProduit(produit);
        Long id = savedProduit.getIdProduit();
        produitService.deleteProduit(id);
        Produit deletedProduit = produitService.retrieveProduit(id);
        assertNull(deletedProduit);
    }
}
