/*
 * package com.secretesc.demo;
 * 
 * import static org.assertj.core.api.Assertions.assertThat; import static
 * org.hamcrest.CoreMatchers.is; import static
 * org.springframework.test.web.client.match.MockRestRequestMatchers.content;
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 * import org.springframework.boot.test.context.SpringBootTest; import
 * org.springframework.boot.test.context.SpringBootTest.WebEnvironment; import
 * org.springframework.boot.test.web.client.TestRestTemplate; import
 * org.springframework.boot.web.server.LocalServerPort; import
 * org.springframework.http.MediaType; import
 * org.springframework.test.web.servlet.MockMvc; import
 * org.springframework.test.web.servlet.ResultMatcher;
 * 
 * @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
 * 
 * @AutoConfigureMockMvc public class HttpRequestPortTest {
 * 
 * @LocalServerPort private int port;
 * 
 * @Autowired private TestRestTemplate restTemplate;
 * 
 * @Autowired private MockMvc mockMvc;
 * 
 * @Test public void greetingShouldReturnDefaultMessage() throws Exception {
 * assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
 * String.class)).contains("Hello, World"); }
 * 
 * 
 * @Test public void shouldReturnDefaultMessage() throws Exception {
 * mockMvc.perform(get("/api/accounts")) .andExpect(status().isOk())
 * .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
 * .andExpect((ResultMatcher) jsonPath("$[0]",is())); } }
 */