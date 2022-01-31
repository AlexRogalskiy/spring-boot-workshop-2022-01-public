package de.workshops.bookdemo.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
    
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRestController bookRestController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllBooksByMethodCall() {
        assertEquals(3, bookRestController.getAllBooks().size());
    }

    @Test
    void testGetAllBooksByMockMvc() throws Exception {
        mockMvc.perform(get((BookRestController.REQUEST_URL)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.hasSize(3)))
            .andExpect(jsonPath("$[1].title", CoreMatchers.is("Clean Code")));
    }
    
    @Test
    void testGetAllBooksByMockMvcWithResult() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get((BookRestController.REQUEST_URL)))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();       
        String jsonPayload = mvcResult.getResponse().getContentAsString();

        // Book[] books = objectMapper.readValue(jsonPayload, Book[].class);
        List<Book> books = objectMapper.readValue(jsonPayload, new TypeReference<>() {});
        assertEquals(3, books.size());
        assertEquals("Clean Code", books.get(1).getTitle());
    }

    @Test
    void testCreateBook() throws Exception {
        
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        Book book = Book.builder()
            .author("Autor")
            .description("Desc")
            .isbn("1234567890")
            .title("Titel")
            .build();
        String jsonPayload = objectMapper.writeValueAsString(book);

        mockMvc.perform(
            post(BookRestController.REQUEST_URL)
                .content(jsonPayload)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());            
            
        MvcResult result =  mockMvc.perform(get(BookRestController.REQUEST_URL))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
        
        String jsonResult = result.getResponse().getContentAsString();
        List<Book> books = objectMapper.readValue(jsonResult, new TypeReference<>() {});
        assertEquals(4, books.size());
        assertEquals("Titel", books.get(3).getTitle());

    }

    
}
