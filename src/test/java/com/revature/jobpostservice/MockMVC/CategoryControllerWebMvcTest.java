package com.revature.jobpostservice.MockMVC;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.jobpostservice.controller.CategoryController;
import com.revature.jobpostservice.model.Category;
import com.revature.jobpostservice.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(CategoryController.class)
public class CategoryControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCategory() throws Exception {
        Category mockCategory = new Category();
        Mockito.when(categoryService.createCategory(Mockito.any(Category.class))).thenReturn(mockCategory);

        mockMvc.perform(post("/api/categories/create")
                        .content(objectMapper.writeValueAsString(mockCategory))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllCategories() throws Exception {
        Mockito.when(categoryService.getAllCategories())
                .thenReturn(Arrays.asList(new Category(), new Category()));

        mockMvc.perform(get("/api/categories/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
