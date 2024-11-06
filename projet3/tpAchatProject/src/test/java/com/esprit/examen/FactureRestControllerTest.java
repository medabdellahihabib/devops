// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.web.servlet.MockMvc;

// import java.util.Arrays;
// import java.util.Date;
// import java.util.List;

// @WebMvcTest(FactureRestController.class)
// class FactureRestControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private IFactureService factureService;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @Test
//     void testGetFactures() throws Exception {
//         Facture facture1 = new Facture();
//         Facture facture2 = new Facture();
//         when(factureService.retrieveAllFactures()).thenReturn(Arrays.asList(facture1, facture2));

//         mockMvc.perform(get("/facture/retrieve-all-factures"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.length()").value(2));
//     }

//     @Test
//     void testRetrieveFacture() throws Exception {
//         Long factureId = 1L;
//         Facture facture = new Facture();
//         when(factureService.retrieveFacture(factureId)).thenReturn(facture);

//         mockMvc.perform(get("/facture/retrieve-facture/{facture-id}", factureId))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     void testAddFacture() throws Exception {
//         Facture facture = new Facture();
//         when(factureService.addFacture(any(Facture.class))).thenReturn(facture);

//         mockMvc.perform(post("/facture/add-facture")
//                         .contentType("application/json")
//                         .content(objectMapper.writeValueAsString(facture)))
//                 .andExpect(status().isOk());
//     }

//     @Test
//     void testCancelFacture() throws Exception {
//         Long factureId = 1L;

//         mockMvc.perform(put("/facture/cancel-facture/{facture-id}", factureId))
//                 .andExpect(status().isOk());

//         verify(factureService, times(1)).cancelFacture(factureId);
//     }
// }
