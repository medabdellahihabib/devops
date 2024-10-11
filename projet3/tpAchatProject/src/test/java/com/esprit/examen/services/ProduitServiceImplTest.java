package com.esprit.examen.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitServiceImplTest {

    @InjectMocks
    ProduitServiceImpl produitService;

    @Mock
    ProduitRepository produitRepository;

    @Mock
    StockRepository stockRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Test adding a product with complex validation
    @Test
    public void testAddProduit_ValidProduit_ShouldReturnSavedProduit() {
        Produit produit = new Produit();
        produit.setCodeProduit("P003");
        produit.setLibelleProduit("Complex Produit");

        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        Produit savedProduit = produitService.addProduit(produit);

        assertNotNull(savedProduit);
        assertEquals("Complex Produit", savedProduit.getLibelleProduit());
        verify(produitRepository, times(1)).save(produit);
    }

    // Test retrieving a product that doesn't exist (handling null case)
    @Test
    public void testRetrieveProduit_NonExistingProduit_ShouldReturnNull() {
        Long nonExistingId = 999L;

        when(produitRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Produit produit = produitService.retrieveProduit(nonExistingId);

        assertNull(produit);
        verify(produitRepository, times(1)).findById(nonExistingId);
    }

    // Test assigning a product to a stock with validation
    @Test
    public void testAssignProduitToStock_ValidIds_ShouldAssignStockToProduit() {
        Long produitId = 1L;
        Long stockId = 1L;

        Produit produit = new Produit();
        produit.setIdProduit(produitId);
        Stock stock = new Stock();
        stock.setIdStock(stockId);

        when(produitRepository.findById(produitId)).thenReturn(Optional.of(produit));
        when(stockRepository.findById(stockId)).thenReturn(Optional.of(stock));
        when(produitRepository.save(produit)).thenReturn(produit);

        produitService.assignProduitToStock(produitId, stockId);

        assertEquals(stock, produit.getStock());
        verify(produitRepository, times(1)).findById(produitId);
        verify(stockRepository, times(1)).findById(stockId);
        verify(produitRepository, times(1)).save(produit);
    }

    // Test assigning a product to a non-existing stock (handling errors)
    @Test(expected = RuntimeException.class)
    public void testAssignProduitToStock_NonExistingStock_ShouldThrowException() {
        Long produitId = 1L;
        Long nonExistingStockId = 999L;

        Produit produit = new Produit();
        produit.setIdProduit(produitId);

        when(produitRepository.findById(produitId)).thenReturn(Optional.of(produit));
        when(stockRepository.findById(nonExistingStockId)).thenReturn(Optional.empty());

        produitService.assignProduitToStock(produitId, nonExistingStockId);
    }

    // Test updating a product with valid data
    @Test
    public void testUpdateProduit_ValidProduit_ShouldReturnUpdatedProduit() {
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setCodeProduit("P004");
        produit.setLibelleProduit("Updated Produit");

        when(produitRepository.save(any(Produit.class))).thenReturn(produit);

        Produit updatedProduit = produitService.updateProduit(produit);

        assertNotNull(updatedProduit);
        assertEquals("Updated Produit", updatedProduit.getLibelleProduit());
        verify(produitRepository, times(1)).save(produit);
    }

    // Test delete product by ID and validate repository interaction
    @Test
    public void testDeleteProduit_ValidId_ShouldDeleteProduit() {
        Long produitId = 1L;

        doNothing().when(produitRepository).deleteById(produitId);

        produitService.deleteProduit(produitId);

        verify(produitRepository, times(1)).deleteById(produitId);
    }
}
