package com.sliit.testing.database;

import com.sliit.code.RegistrationMain;
import com.sliit.code.database.UserRepository;
import com.sliit.code.registration.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;       // ★
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.*;

/**
 * Pure-JPA slice test. Each method runs in its own
 * rollback-transaction against an in-memory H2 database.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
@ContextConfiguration(classes = RegistrationMain.class)          // ★ tell Spring where the @SpringBootApplication lives
class DatabasePersistenceTest {

    @Autowired
    UserRepository repo;

    /* ─────── banners ─────── */
    @BeforeAll void bannerStart() { System.out.println("\n==========  DATABASE tests START  ==========\n"); }
    @AfterAll  void bannerEnd()   { System.out.println("\n==========  DATABASE tests  END   ==========\n"); }

    /* ---------- happy path ---------- */
    @Test @DisplayName("Save user → can find by e-mail")
    void saveAndFind() {
        repo.save(new User("Dave", "dave@example.com", "pw", "Colombo", "student"));

        assertThat(repo.findByEmail("dave@example.com"))
                .get()
                .extracting(User::getName, User::getLocation)
                .containsExactly("Dave", "Colombo");
    }

    /* ---------- duplicate e-mail ---------- */
    @Test @DisplayName("Unique constraint: duplicate e-mail throws")
    void duplicateEmail() {
        repo.save(new User("Alice", "alice@example.com", "pw", "Kandy", "teacher"));

        assertThatThrownBy(() ->
                repo.saveAndFlush(new User("Alice2", "alice@example.com", "pw2", "Galle", "student")))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    /* ---------- null e-mail ---------- */
    @Test @DisplayName("NOT-NULL constraint: null e-mail throws")
    void nullEmail() {
        User noMail = new User();
        noMail.setName("NoMail");
        noMail.setPassword("pw");
        noMail.setLocation("Matara");
        noMail.setRole("student");

        assertThatThrownBy(() -> {
            repo.save(noMail);
            repo.flush();                     // force DB hit
        }).isInstanceOf(DataIntegrityViolationException.class);
    }
}
