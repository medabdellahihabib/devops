import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OperateurServiceImplTest {

    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    private Operateur operateur1;
    private Operateur operateur2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        operateur1 = new Operateur(1L, "John", "Doe", "password123", null);
        operateur2 = new Operateur(2L, "Jane", "Smith", "password456", null);
    }

    @Test
    public void testRetrieveAllOperateurs() {
        when(operateurRepository.findAll()).thenReturn(Arrays.asList(operateur1, operateur2));

        List<Operateur> operateurs = operateurService.retrieveAllOperateurs();

        assertEquals(2, operateurs.size());
        verify(operateurRepository, times(1)).findAll();
    }

    @Test
    public void testAddOperateur() {
        when(operateurRepository.save(operateur1)).thenReturn(operateur1);

        Operateur savedOperateur = operateurService.addOperateur(operateur1);

        assertNotNull(savedOperateur);
        assertEquals("John", savedOperateur.getNom());
        verify(operateurRepository, times(1)).save(operateur1);
    }

    @Test
    public void testDeleteOperateur() {
        Long id = 1L;
        doNothing().when(operateurRepository).deleteById(id);

        operateurService.deleteOperateur(id);

        verify(operateurRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateOperateur() {
        when(operateurRepository.save(operateur1)).thenReturn(operateur1);

        Operateur updatedOperateur = operateurService.updateOperateur(operateur1);

        assertNotNull(updatedOperateur);
        assertEquals("John", updatedOperateur.getNom());
        verify(operateurRepository, times(1)).save(operateur1);
    }

    @Test
    public void testRetrieveOperateur() {
        Long id = 1L;
        when(operateurRepository.findById(id)).thenReturn(Optional.of(operateur1));

        Operateur foundOperateur = operateurService.retrieveOperateur(id);

        assertNotNull(foundOperateur);
        assertEquals("John", foundOperateur.getNom());
        verify(operateurRepository, times(1)).findById(id);
    }
}
