package com.piotgrochowiecki.eriderent.repository;

import com.piotgrochowiecki.eriderent.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

@DisplayName("User repository specification")
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository; //classUnderTest

    @Test
    @DisplayName("Should find user with given email address")
    public void findByEmail_success() {
        User testUser = User.builder()
                .firstName("Jan")
                .lastName("Nowak")
                .email("jannowak123@gmail.com")
                .drivingLicenseIssueDate(LocalDate.ofEpochDay(2012-03-12))
                .build();

        testEntityManager.persist()
        userRepository.findByEmail()

    }

}v