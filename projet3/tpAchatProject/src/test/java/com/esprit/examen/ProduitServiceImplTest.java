// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import java.util.Arrays;
// import java.util.Date;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// @SpringBootTest
// public class ProduitServiceImplTest {

//     @Mock
//     private ProduitRepository produitRepository;

//     @Mock
//     private StockRepository stockRepository;

//     @InjectMocks
//     private ProduitServiceImpl produitService;

//     private Produit produit;
//     private Stock stock;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         produit = new Produit(1L, "P001", "Produit 1", 100.0f, new Date(), new Date(), null, null, null);
//         stock = new Stock();
//         stock.setIdStock(1L);
//     }

//     @Test
//     public void testRetrieveAllProduits() {
//         when(produitRepository.findAll()).thenReturn(Arrays.asList(produit));

//         List<Produit> produits = produitService.retrieveAllProduits();
//         assertFalse(produits.isEmpty());
//         assertEquals(1, produits.size());
//         assertEquals(produit.getCodeProduit(), produits.get(0).getCodeProduit());
//     }

//     @Test
//     public void testAddProduit() {
//         when(produitRepository.save(produit)).thenReturn(produit);

//         Produit addedProduit = produitService.addProduit(produit);
//         assertNotNull(addedProduit);
//         assertEquals(produit.getCodeProduit(), addedProduit.getCodeProduit());
//     }

//     @Test
//     public void testDeleteProduit() {
//         Long produitId = 1L;
//         doNothing().when(produitRepository).deleteById(produitId);

//         produitService.deleteProduit(produitId);
//         verify(produitRepository, times(1)).deleteById(produitId);
//     }

//     @Test
//     public void testUpdateProduit() {
//         produit.setLibelleProduit("Updated Produit");
//         when(produitRepository.save(produit)).thenReturn(produit);

//         Produit updatedProduit = produitService.updateProduit(produit);
//         assertEquals("Updated Produit", updatedProduit.getLibelleProduit());
//     }

//     @Test
//     public void testRetrieveProduit() {
//         when(produitRepository.findById(produit.getIdProduit())).thenReturn(Optional.of(produit));

//         Produit foundProduit = produitService.retrieveProduit(produit.getIdProduit());
//         assertNotNull(foundProduit);
//         assertEquals(produit.getCodeProduit(), foundProduit.getCodeProduit());
//     }

//     @Test
//     public void testAssignProduitToStock() {
//         when(produitRepository.findById(produit.getIdProduit())).thenReturn(Optional.of(produit));
//         when(stockRepository.findById(stock.getIdStock())).thenReturn(Optional.of(stock));

//         produitService.assignProduitToStock(produit.getIdProduit(), stock.getIdStock());

//         assertEquals(stock, produit.getStock());
//         verify(produitRepository, times(1)).save(produit);
//     }
// }
