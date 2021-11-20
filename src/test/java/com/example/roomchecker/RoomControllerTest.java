package com.example.roomchecker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomControllerTest {
  @Autowired
  MockMvc mockMvc;

  @Test
  void crudTest() throws Exception {
    mockMvc.perform(get("/rooms"))
        .andExpect(
            content()
                .json("[\n" +
                    "  {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Mercury\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 2,\n" +
                    "    \"name\": \"Venus\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 3,\n" +
                    "    \"name\": \"Earth\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 4,\n" +
                    "    \"name\": \"Mars\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 5,\n" +
                    "    \"name\": \"Jupiter\"\n" +
                    "  }\n" +
                    "]"
                )
        );

    mockMvc.perform(get("/rooms/3"))
        .andExpect(content()
            .json("{\n" +
                "  \"id\": 3,\n" +
                "  \"name\": \"Earth\"\n" +
                "}"
            )
        );
  }

}
