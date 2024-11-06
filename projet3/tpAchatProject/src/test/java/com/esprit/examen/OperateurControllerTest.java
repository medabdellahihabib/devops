// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import java.util.Arrays;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import com.fasterxml.jackson.databind.ObjectMapper;

// @WebMvcTest(OperateurController.class)
// public class OperateurControllerTest {

//     private MockMvc mockMvc;

//     @Mock
//     private IOperateurService operateurService;

//     @InjectMocks
//     private OperateurController operateurController;

//     private Operateur operateur1;
//     private Operateur operateur2;
    
//     private ObjectMapper objectMapper;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.initMocks(this);
//         this.mockMvc = MockMvcBuilders.standaloneSetup(operateurController).build();
//         operateur1 = new Operateur(1L, "John", "Doe", "password123", null);
//         operateur2 = new Operateur(2L, "Jane", "Smith", "password456", null);
//         objectMapper = new ObjectMapper();
//     }

//     @Test
//     public void testGetOperateurs() throws Exception {
//         when(operateurService.retrieveAllOperateurs()).thenReturn(Arrays.asList(operateur1, operateur2));

//         mockMvc.perform(get("/operateur/retrieve-all-operateurs"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].nom").value("John"))
//                .andExpect(jsonPath("$[1].nom").value("Jane"));
//     }

//     @Test
//     public void testRetrieveOperateur() throws Exception {
//         when(operateurService.retrieveOperateur(1L)).thenReturn(operateur1);

//         mockMvc.perform(get("/operateur/retrieve-operateur/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.nom").value("John"));
//     }

//     @Test
//     public void testAddOperateur() throws Exception {
//         when(operateurService.addOperateur(any(Operateur.class))).thenReturn(operateur1);

//         mockMvc.perform(post("/operateur/add-operateur")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(operateur1)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.nom").value("John"));
//     }

//     @Test
//     public void testRemoveOperateur() throws Exception {
//         doNothing().when(operateurService).deleteOperateur(1L);

//         mockMvc.perform(delete("/operateur/remove-operateur/1"))
//                .andExpect(status().isOk());

//         verify(operateurService, times(1)).deleteOperateur(1L);
//     }

//     @Test
//     public void testModifyOperateur() throws Exception {
//         when(operateurService.updateOperateur(any(Operateur.class))).thenReturn(operateur1);

//         mockMvc.perform(put("/operateur/modify-operateur")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(objectMapper.writeValueAsString(operateur1)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.nom").value("John"));
//     }
// }
