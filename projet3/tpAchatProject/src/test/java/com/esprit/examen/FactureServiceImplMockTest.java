// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import com.esprit.examen.entities.Facture;
// import com.esprit.examen.repositories.FactureRepository;
// import com.esprit.examen.services.FactureServiceImpl;

// import java.util.Optional;

// public class FactureServiceImplMockTest {

//     private FactureServiceImpl factureServiceImpl;
//     private FactureRepository factureRepository;

//     @BeforeEach
//     public void setUp() {
//         // Mock the repository
//         factureRepository = mock(FactureRepository.class);

//         // Initialize the service with the mocked repository
//         factureServiceImpl = new FactureServiceImpl(factureRepository, null, null, null, null, null);
//     }

//     @Test
//     public void testRetrieveFacture() {
//         // Arrange: prepare the mock response
//         Facture facture = new Facture();
//         facture.setIdFacture(1L); // Set necessary fields
//         when(factureRepository.findById(1L)).thenReturn(Optional.of(facture));

//         // Act: call the method
//         Facture retrievedFacture = factureServiceImpl.retrieveFacture(1L);

//         // Assert: check the result
//         assertNotNull(retrievedFacture);
//         assertEquals(1L, retrievedFacture.getIdFacture());
//         verify(factureRepository, times(1)).findById(1L);
//     }

//     @Test
//     public void testRetrieveFactureNotFound() {
//         // Arrange: simulate an empty response from the repository
//         when(factureRepository.findById(1L)).thenReturn(Optional.empty());

//         // Act: call the method
//         Facture retrievedFacture = factureServiceImpl.retrieveFacture(1L);

//         // Assert: check the result
//         assertNull(retrievedFacture);
//         verify(factureRepository, times(1)).findById(1L);
//     }
// }
