package com.springboot.restapi;

import com.springboot.restapi.data.dto.ChangeProductNameDto;
import com.springboot.restapi.data.dto.ProductResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTests {

  @Autowired
  private MockMvc mockMvc;

  Long testId = 20L;

  //@Test
  //public void getProductAPI() throws Exception {
  //
  //  this.mockMvc.perform(get("/product")).andDo(print()).andExpect(status().isOk())
  //      .andExpect(jsonPath("$.number").value(1));
  //}

  @Test
  public void createProductAPI() throws Exception {
  
    try{
      this.mockMvc.perform( post("/product")
          .contentType(MediaType.APPLICATION_JSON)
          .content(asJsonString(new ProductResponseDto(null, "Model 19", testId, testId)))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.number").exists());
    }catch(Exception ex){
      System.out.print(ex.getMessage());
    }
  }

  @Test
  public void getProductAPI() throws Exception {
    try{
      this.mockMvc.perform(get("/product/{number}",testId))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.number").value(testId));
    }catch(Exception ex){
      System.out.print(ex.getMessage());
    }
  }

  @Test
  public void changeProductNameAPI() throws Exception {
  
    try{
      this.mockMvc.perform( put("/product")
          .contentType(MediaType.APPLICATION_JSON)
          .content(asJsonString(new ChangeProductNameDto(testId, "Model YY")))
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.number").value(testId))
        .andExpect(jsonPath("$.name").value("Model YY"));
    }catch(Exception ex){
      System.out.print(ex.getMessage());
    }
  }

  @Test
  public void deleteProductAPI() throws Exception {
  
    try{
      this.mockMvc.perform( delete("/product/{number}", (testId+1L)) )
        .andExpect(status().isOk());
    }catch(Exception ex){
      System.out.print(ex.getMessage());
    }
  }
  
  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
