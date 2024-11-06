// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import java.util.Arrays;
// import java.util.Date;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @SpringBootTest
// @AutoConfigureMockMvc
// public class ProduitRestControllerTest {

//     private MockMvc mockMvc;

//     @Mock
//     private IProduitService produitService;

//     @InjectMocks
//     private ProduitRestController produitRestController;

//     private Produit produit;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         this.mockMvc = MockMvcBuilders.standaloneSetup(produitRestController).build();
//         produit = new Produit(1L, "P001", "Produit 1", 100.0f, new Date(), new Date(), null, null, null);
//     }

//     @Test
//     public void testGetProduits() throws Exception {
//         when(produitService.retrieveAllProduits()).thenReturn(Arrays.asList(produit));

//         mockMvc.perform(get("/produit/retrieve-all-produits"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$[0].codeProduit").value("P001"));
//     }

//     @Test
//     public void testRetrieveProduit() throws Exception {
//         when(produitService.retrieveProduit(produit.getIdProduit())).thenReturn(produit);

//         mockMvc.perform(get("/produit/retrieve-produit/{produit-id}", produit.getIdProduit()))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.codeProduit").value("P001"));
//     }

//     @Test
//     public void testAddProduit() throws Exception {
//         when(produitService.addProduit(any(Produit.class))).thenReturn(produit);

//         mockMvc.perform(post("/produit/add-produit")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(asJsonString(produit)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.codeProduit").value("P001"));
//     }

//     @Test
//     public void testRemoveProduit() throws Exception {
//         doNothing().when(produitService).deleteProduit(produit.getIdProduit());

//         mockMvc.perform(delete("/produit/remove-produit/{produit-id}", produit.getIdProduit()))
//                 .andExpect(status().isOk());

//         verify(produitService, times(1)).deleteProduit(produit.getIdProduit());
//     }

//     @Test
//     public void testModifyProduit() throws Exception {
//         produit.setLibelleProduit("Updated Produit");
//         when(produitService.updateProduit(any(Produit.class))).thenReturn(produit);

//         mockMvc.perform(put("/produit/modify-produit")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(asJsonString(produit)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.libelleProduit").value("Updated Produit"));
//     }

//     @Test
//     public void testAssignProduitToStock() throws Exception {
//         doNothing().when(produitService).assignProduitToStock(produit.getIdProduit(), 1L);

//         mockMvc.perform(put("/produit/assignProduitToStock/{idProduit}/{idStock}", produit.getIdProduit(), 1L))
//                 .andExpect(status().isOk());

//         verify(produitService, times(1)).assignProduitToStock(produit.getIdProduit(), 1L);
//     }

//     private static String asJsonString(final Object obj) {
//         try {
//             return new ObjectMapper().writeValueAsString(obj);
//         } catch (Exception e) {
//             throw new RuntimeException(e);
//         }
//     }
// }
