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
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void able_to_get_distance_with_both_valid_postal_code() throws Exception {
        distanceBetween("AB10 1XG", "AB12 5GL", status().isOk())
                .andExpect(jsonPath("from.postalCode", is("AB10 1XG")))
                .andExpect(jsonPath("to.postalCode", is("AB12 5GL")))
                .andExpect(jsonPath("distance", is(greaterThan(0.0))))
                .andExpect(jsonPath("unit", is("KM")))
        ;
    }

    @Test
    public void error_when_provide_invalid_from_postal_code() throws Exception {
        distanceBetween("invalid from", "AB12 5GL", status().is(404))
                .andExpect(jsonPath("errors.[0]", is("Post code 'invalid from' not found")))
        ;
    }

    @Test
    public void error_when_provide_invalid_to_postal_code() throws Exception {
        distanceBetween("AB10 1XG", "invalid to", status().is(404))
                .andExpect(jsonPath("errors.[0]", is("Post code 'invalid to' not found")))
        ;
    }

    @Test
    public void able_to_update_postal_code() throws Exception {
        update(location("BB1 8EN", 0.1234, 123.4567))
                .andExpect(status().is(202))
        ;
    }

    @Test
    public void unable_update_postal_code_with_empty_postal_code() throws Exception {
        update(location("", 0.1234, 123.4567))
                .andExpect(status().is(400))
                .andExpect(jsonPath("errors[0]", is("postalCode cannot be blank")))
        ;
    }

    @Test
    public void unable_update_postal_code_with_invalid_postal_code() throws Exception {
        update(location("invalid", 0.1234, 123.4567))
                .andExpect(status().is(404))
                .andExpect(jsonPath("errors[0]", is("Post code 'invalid' not found")))
        ;
    }

    @Test
    public void unable_update_postal_code_with_out_of_range_latitude() throws Exception {
        update(location("BB1 8EN", -90.0123, 123.4567))
                .andExpect(status().is(400))
                .andExpect(jsonPath("errors[0]", is("latitude must between -90.0 and 90.0 degree")))
        ;
    }

    @Test
    public void unable_update_postal_code_with_out_of_range_longitude() throws Exception {
        update(location("BB1 8EN", 0.1234, 180.4321))
                .andExpect(status().is(400))
                .andExpect(jsonPath("errors[0]", is("longitude must between -180.0 and 180.0 degree")))
        ;
    }

    private ResultActions update(String content) throws Exception {
        return mvc.perform(put("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));
    }

    private ResultActions distanceBetween(String from, String to, ResultMatcher resultMatcher) throws Exception {
        return mvc.perform(get(distance(from, to)))
                .andExpect(resultMatcher)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    private static String distance(String from, String to) {
        return String.format("/distance/%s/%s", from, to);
    }

    private static String location(String postcode, double latitude, double longitude) {
        return "{\n" +
                "  \"postalCode\": \"" + postcode + "\",\n" +
                "  \"latitude\": " + latitude + ",\n" +
                "  \"longitude\": " + longitude + "\n" +
                "}";
    }
}
