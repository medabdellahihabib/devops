import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.repositories.DetailFactureRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.services.FactureServiceImpl;
import com.esprit.examen.services.ReglementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FactureServiceImplTest {

    private FactureServiceImpl factureServiceImpl;
    private FactureRepository factureRepository;
    private OperateurRepository operateurRepository;
    private DetailFactureRepository detailFactureRepository;
    private FournisseurRepository fournisseurRepository;
    private ProduitRepository produitRepository;
    private ReglementServiceImpl reglementService;

    @BeforeEach
    public void setUp() {
        factureRepository = mock(FactureRepository.class);
        operateurRepository = mock(OperateurRepository.class);
        detailFactureRepository = mock(DetailFactureRepository.class);
        fournisseurRepository = mock(FournisseurRepository.class);
        produitRepository = mock(ProduitRepository.class);
        reglementService = mock(ReglementServiceImpl.class);
        
        // Inject all mocks into the service
        factureServiceImpl = new FactureServiceImpl(factureRepository, operateurRepository,
                detailFactureRepository, fournisseurRepository, produitRepository, reglementService);
    }

    @Test
    public void testCreateFacture() {
        // Prepare the data
        Facture facture = new Facture();
        // Configure your facture object with necessary data

        // Act: call the method to test
        when(factureRepository.save(any(Facture.class))).thenReturn(facture); // Simulate the repository behavior
        Facture createdFacture = factureServiceImpl.addFacture(facture); // Change to addFacture

        // Verify: assertions
        assertNotNull(createdFacture); // Verify that the created facture is not null
        assertEquals(facture, createdFacture); // Verify that the created facture is the same as the provided one
        verify(factureRepository, times(1)).save(facture); // Verify that the save method was called once
    }
}
