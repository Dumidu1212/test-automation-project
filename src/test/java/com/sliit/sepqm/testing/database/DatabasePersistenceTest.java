package com.sliit.sepqm.testing.database;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.*;

// Tell JUnit to keep one instance of the test class, so @BeforeAll/@AfterAll
// can be non-static and we can still use @Autowired fields inside them.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
class DatabasePersistenceTest {

    @Autowired
    UserRepository repo;

    /* ────────────────────────── LIFECYCLE HOOKS ────────────────────────── */

    @BeforeAll
    void announceStart() {
        System.out.println("\n==========  DATABASE tests START  ==========\n");
    }

    @AfterAll
    void announceEnd() {
        System.out.println("\n==========  DATABASE tests  END   ==========\n");
    }

    /* ────────────────────────────── TESTS ──────────────────────────────── */

    @Test
    void whenSaveUser_thenFindByEmail() {
        User u = new User("Dave", "dave@example.com", "pw", "loc", "role");
        repo.save(u);

        var found = repo.findByEmail("dave@example.com");
        assertThat(found)
                .isPresent()
                .get()
                .satisfies(user -> {
                    assertThat(user.getName()).isEqualTo("Dave");
                    assertThat(user.getLocation()).isEqualTo("loc");
                    assertThat(user.getCreatedAt()).isNotNull();
                });
    }

    @Test
    void whenEmailIsNull_thenConstraintViolation() {
        User bad = new User();
        bad.setName("NoEmail");

        assertThatThrownBy(() -> {
            repo.save(bad);   // queued insert
            repo.flush();     // force DB constraint check
        }).isInstanceOf(DataIntegrityViolationException.class);
    }
}
