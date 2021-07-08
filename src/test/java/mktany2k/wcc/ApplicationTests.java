package mktany2k.wcc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void valid() throws Exception {
        distanceBetween("AB10 1XG", "AB12 5GL", status().isOk())
                .andExpect(jsonPath("from.postalCode", is("AB10 1XG")))
                .andExpect(jsonPath("to.postalCode", is("AB12 5GL")))
        ;
    }

    @Test
    public void invalid_from() throws Exception {
        distanceBetween("invalid from", "AB12 5GL", status().is(404))
                .andExpect(jsonPath("errors.[0]", is("Post code 'invalid from' not found")))
        ;
    }

    @Test
    public void invalid_to() throws Exception {
        distanceBetween("AB10 1XG", "invalid to", status().is(404))
                .andExpect(jsonPath("errors.[0]", is("Post code 'invalid to' not found")))
        ;
    }

    private ResultActions distanceBetween(String from, String to, ResultMatcher resultMatcher) throws Exception {
        return mvc.perform(get(distance(from, to)))
                .andExpect(resultMatcher)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private static String distance(String from, String to) {
        return String.format("/distance/%s/%s", from, to);
    }
}
