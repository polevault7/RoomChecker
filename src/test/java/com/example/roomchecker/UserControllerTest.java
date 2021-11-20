package com.example.roomchecker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
  @Autowired
  MockMvc mockMvc;

  @Test
  void crudTest() throws Exception {
    mockMvc.perform(
            get("/users"))
        .andExpect(
            content()
                .json("[\n" +
                    "  {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Sergey\",\n" +
                    "    \"room\": {\n" +
                    "      \"id\": 1,\n" +
                    "      \"name\": \"Mercury\"\n" +
                    "    },\n" +
                    "    \"inside\": false\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 2,\n" +
                    "    \"name\": \"Nikolay\",\n" +
                    "    \"room\": {\n" +
                    "      \"id\": 5,\n" +
                    "      \"name\": \"Jupiter\"\n" +
                    "    },\n" +
                    "    \"inside\": false\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 3,\n" +
                    "    \"name\": \"Mariya\",\n" +
                    "    \"room\": {\n" +
                    "      \"id\": 5,\n" +
                    "      \"name\": \"Jupiter\"\n" +
                    "    },\n" +
                    "    \"inside\": false\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 4,\n" +
                    "    \"name\": \"Mark\",\n" +
                    "    \"room\": {\n" +
                    "      \"id\": 3,\n" +
                    "      \"name\": \"Earth\"\n" +
                    "    },\n" +
                    "    \"inside\": false\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 5,\n" +
                    "    \"name\": \"Elena\",\n" +
                    "    \"room\": {\n" +
                    "      \"id\": 2,\n" +
                    "      \"name\": \"Venus\"\n" +
                    "    },\n" +
                    "    \"inside\": false\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 6,\n" +
                    "    \"name\": \"Kate\",\n" +
                    "    \"room\": {\n" +
                    "      \"id\": 1,\n" +
                    "      \"name\": \"Mercury\"\n" +
                    "    },\n" +
                    "    \"inside\": true\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 7,\n" +
                    "    \"name\": \"Diana\",\n" +
                    "    \"room\": {\n" +
                    "      \"id\": 4,\n" +
                    "      \"name\": \"Mars\"\n" +
                    "    },\n" +
                    "    \"inside\": false\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 8,\n" +
                    "    \"name\": \"Viktor\",\n" +
                    "    \"room\": {\n" +
                    "      \"id\": 5,\n" +
                    "      \"name\": \"Jupiter\"\n" +
                    "    },\n" +
                    "    \"inside\": false\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 9,\n" +
                    "    \"name\": \"Bars\",\n" +
                    "    \"room\": {\n" +
                    "      \"id\": 3,\n" +
                    "      \"name\": \"Earth\"\n" +
                    "    },\n" +
                    "    \"inside\": false\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": 10,\n" +
                    "    \"name\": \"Darren\",\n" +
                    "    \"room\": {\n" +
                    "      \"id\": 2,\n" +
                    "      \"name\": \"Venus\"\n" +
                    "    },\n" +
                    "    \"inside\": false\n" +
                    "  }\n" +
                    "]")
        )
        .andExpect(
            status()
                .isOk());

    mockMvc.perform(
            post("/users")
                .contentType("application/json")
                .content("{\n" +
                    "  \"id\": 0,\n" +
                    "  \"name\": \"Bars\",\n" +
                    "  \"room\": {\n" +
                    "    \"id\": 2\n" +
                    "  }\n" +
                    "}"))
        .andExpect(
            content()
                .json("{\n" +
                    "  \"id\": 11,\n" +
                    "  \"name\": \"Bars\",\n" +
                    "  \"room\": {\n" +
                    "    \"id\": 2,\n" +
                    "    \"name\": \"Venus\"\n" +
                    "  },\n" +
                    "  \"inside\": false\n" +
                    "}")
        );

    mockMvc.perform(
            get("/users/2"))
        .andExpect(
            content()
                .json("{\n" +
                    "  \"id\": 2,\n" +
                    "  \"name\": \"Nikolay\",\n" +
                    "  \"room\": {\n" +
                    "    \"id\": 5,\n" +
                    "    \"name\": \"Jupiter\"\n" +
                    "  },\n" +
                    "  \"inside\": false\n" +
                    "}\n")
        );

    mockMvc.perform(
            get("/users/check?userId=6&roomId=3"))
        .andExpect(
            content()
                .string("false"))
        .andExpect(
            status()
                .isForbidden()
        );
    mockMvc.perform(
            get("/users/check?userId=3&roomId=3"))
        .andExpect(
            content()
                .string("true")
        )
        .andExpect(
            status()
                .isOk()
        );
  }
}
