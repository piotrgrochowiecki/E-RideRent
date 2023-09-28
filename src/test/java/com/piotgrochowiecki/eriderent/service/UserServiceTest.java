package com.piotgrochowiecki.eriderent.service;

import com.piotgrochowiecki.eriderent.dto.request.UserRegisterRequestDto;
import com.piotgrochowiecki.eriderent.dto.response.UserResponseDto;
import com.piotgrochowiecki.eriderent.exception.EmailAlreadyExistsException;
import com.piotgrochowiecki.eriderent.exception.NoUserFoundException;
import com.piotgrochowiecki.eriderent.model.User;
import com.piotgrochowiecki.eriderent.model.enumerator.Role;
import com.piotgrochowiecki.eriderent.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Given UserRegisterRequestDto object with new email address, " +
                 "when <registerNewAccount> method is invoked, " +
                 "then it should invoke repository <save> method with user entity.")
    void shouldInvokeRepositorySaveMethod() throws EmailAlreadyExistsException {
        //given
        UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .password("password123")
                .matchingPassword("password123")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2005, 4, 12))
                .build();

        User user = User.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .password("password123")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2005, 4, 12))
                .build();

        //when
        userService.registerNewAccount(userRegisterRequestDto);

        //then
        Mockito.verify(userRepository).save(user);
    }

    @Test
    @DisplayName("Given UserRegisterRequestDto object with already existing email address, " +
                 "when <registerNewAccount> method is invoked, " +
                 "then it should throw EmailAlreadyExists exception")
    void shouldThrowEmailExistsException() {
        //given
        UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .password("password123")
                .matchingPassword("password123")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2005, 4, 12))
                .build();

        User user = User.builder()
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .password("password123")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2005, 4, 12))
                .build();

        //when
        Mockito.when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));

        //then
        assertThrows(EmailAlreadyExistsException.class, () -> userService.registerNewAccount(userRegisterRequestDto));
    }

    @Test
    @DisplayName("Given user with some id, " +
                 "when <getById> service method is invoked," +
                 "then it should return UserResponseDto object mapped from the user")
    void shouldReturnUserWithGivenId() throws NoUserFoundException {
        //given
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2005, 4, 12))
                .build();

        User user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .password("password123")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2005, 4, 12))
                .build();

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(user));

        //when
        UserResponseDto result = userService.getById(1L);

        //then
        assertEquals(userResponseDto, result);
    }

    @Test
    @DisplayName("Given no user with provided id, " +
                 "when <getById> service method is invoked, " +
                 "then it should throw NoUserFoundException")
    void shouldThrowNoUserFoundException() {
        //when
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.empty());

        //then
        assertThrows(NoUserFoundException.class, () -> userService.getById(1L));
    }

    @Test
    @DisplayName("Given user with some email, " +
                 "when <getById> service method is invoked," +
                 "then it should return UserResponseDto object mapped from the user")
    void shouldReturnUserWithGivenEmail() throws NoUserFoundException {
        //given
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2005, 4, 12))
                .build();

        User user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .password("password123")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2005, 4, 12))
                .build();

        Mockito.when(userRepository.findByEmail("john.smith@gmail.com")).thenReturn(Optional.of(user));

        //when
        UserResponseDto result = userService.getByEmail("john.smith@gmail.com");

        //then
        assertEquals(userResponseDto, result);
    }

    @Test
    @DisplayName("Given no user with provided email, " +
                 "when <getById> service method is invoked, " +
                 "then it should throw NoUserFoundException")
    void shouldThrowNoUserFoundExceptionWhenEmailIsProvided() {
        //when
        Mockito.when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        //then
        assertThrows(NoUserFoundException.class, () -> userService.getByEmail("john.smith@gmail.com"));
    }

    @Test
    @DisplayName("Given two users, " +
                 "when <getAll> service method is invoked, " +
                 "then it should return list of two UserResponseDto objects mapped from these users")
    void shouldReturnListOfUserResponseDtos() {
        //given
        UserResponseDto userResponseDto1 = UserResponseDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2005, 4, 12))
                .build();

        User user1 = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Smith")
                .email("john.smith@gmail.com")
                .password("password123")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2005, 4, 12))
                .build();

        UserResponseDto userResponseDto2 = UserResponseDto.builder()
                .id(2L)
                .firstName("Anna")
                .lastName("Smith")
                .email("anna.smith@gmail.com")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2009, 7, 5))
                .build();

        User user2 = User.builder()
                .id(2L)
                .firstName("Anna")
                .lastName("Smith")
                .email("anna.smith@gmail.com")
                .password("password123")
                .role(Role.USER)
                .drivingLicenseIssueDate(LocalDate.of(2009, 7, 5))
                .build();

        List<User> userList = List.of(user1, user2);
        List<UserResponseDto> userResponseDtoList = List.of(userResponseDto1, userResponseDto2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        //when
        List<UserResponseDto> result = userService.getAll();

        //then
        assertEquals(userResponseDtoList, result);
    }
}