// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import java.util.Arrays;
// import java.util.Date;
// import java.util.List;
// import java.util.Optional;
// import java.util.Set;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// @ExtendWith(MockitoExtension.class)
// class FactureServiceImplTest {

//     @Mock
//     private FactureRepository factureRepository;
//     @Mock
//     private OperateurRepository operateurRepository;
//     @Mock
//     private DetailFactureRepository detailFactureRepository;
//     @Mock
//     private FournisseurRepository fournisseurRepository;
//     @Mock
//     private ProduitRepository produitRepository;
//     @Mock
//     private ReglementServiceImpl reglementService;

//     @InjectMocks
//     private FactureServiceImpl factureService;

//     @Test
//     void testRetrieveAllFactures() {
//         Facture facture1 = new Facture();
//         Facture facture2 = new Facture();
//         when(factureRepository.findAll()).thenReturn(Arrays.asList(facture1, facture2));

//         List<Facture> result = factureService.retrieveAllFactures();

//         assertEquals(2, result.size());
//         verify(factureRepository, times(1)).findAll();
//     }

//     @Test
//     void testAddFacture() {
//         Facture facture = new Facture();
//         when(factureRepository.save(facture)).thenReturn(facture);

//         Facture result = factureService.addFacture(facture);

//         assertNotNull(result);
//         verify(factureRepository, times(1)).save(facture);
//     }

//     @Test
//     void testCancelFacture() {
//         Long factureId = 1L;
//         Facture facture = new Facture();
//         when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));

//         factureService.cancelFacture(factureId);

//         assertTrue(facture.getArchivee());
//         verify(factureRepository, times(1)).save(facture);
//     }

//     @Test
//     void testRetrieveFacture() {
//         Long factureId = 1L;
//         Facture facture = new Facture();
//         when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));

//         Facture result = factureService.retrieveFacture(factureId);

//         assertNotNull(result);
//         verify(factureRepository, times(1)).findById(factureId);
//     }

//     // @Test
//     // void testGetFacturesByFournisseur() {
//     //     Long fournisseurId = 1L;
//     //     Fournisseur fournisseur = new Fournisseur();
//     //     Facture facture1 = new Facture();
//     //     Facture facture2 = new Facture();
//     //     fournisseur.setFactures(Set.of(facture1, facture2));
//     //     when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(fournisseur));

//     //     List<Facture> result = factureService.getFacturesByFournisseur(fournisseurId);

//     //     assertEquals(2, result.size());
//     //     verify(fournisseurRepository, times(1)).findById(fournisseurId);
//     // }

//     @Test
//     void testPourcentageRecouvrement() {
//         Date startDate = new Date();
//         Date endDate = new Date();
//         when(factureRepository.getTotalFacturesEntreDeuxDates(startDate, endDate)).thenReturn(1000f);
//         when(reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(500f);

//         float result = factureService.pourcentageRecouvrement(startDate, endDate);

//         assertEquals(50, result);
//     }
// }
