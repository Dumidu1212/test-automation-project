package com.sliit.testing.profile;

import com.sliit.code.profile.ProfileUser;
import com.sliit.code.profile.UserProfileRepository;
import com.sliit.code.RegistrationMain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = RegistrationMain.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Import(TestSecurityConfig.class)
public class ProfileUserApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserProfileRepository userProfileRepository;

    private String baseUrl() {
        return "http://localhost:" + port + "/users";
    }

    @Test
    public void testGetUserProfile_ValidId() {
        // given
        ProfileUser saved = userProfileRepository.save(
                new ProfileUser("Test User", "testuser@example.com", "pass123", "Colombo", "user")
        );

        // when
        ResponseEntity<ProfileUser> resp =
                restTemplate.getForEntity(baseUrl() + "/" + saved.getId(), ProfileUser.class);

        // then
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertNotNull(resp.getBody());
        assertEquals("Test User", resp.getBody().getName());
        assertEquals("testuser@example.com", resp.getBody().getEmail());
        assertEquals("pass123", resp.getBody().getPassword());
        assertEquals("Colombo", resp.getBody().getLocation());
        assertEquals("user", resp.getBody().getRole());
    }

    @Test
    public void testGetUserProfile_InvalidId() {
        ResponseEntity<ProfileUser> resp =
                restTemplate.getForEntity(baseUrl() + "/99999", ProfileUser.class);

        assertEquals(HttpStatus.NOT_FOUND, resp.getStatusCode());
    }
}
