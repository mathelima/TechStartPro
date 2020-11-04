package com.olist.techstartpro;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TechStartProApplicationTests.class)
@SpringBootTest(classes = TechStartProApplicationTests.class)
public class IntegrationTestBase {
    protected MockMvc mockMvc;
}
