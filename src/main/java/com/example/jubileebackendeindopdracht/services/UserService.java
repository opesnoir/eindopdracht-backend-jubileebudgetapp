package com.example.jubileebackendeindopdracht.services;

import com.example.jubileebackendeindopdracht.dtos.UserDto;
import com.example.jubileebackendeindopdracht.exceptions.UsernameNotFoundException;
import com.example.jubileebackendeindopdracht.models.*;
import com.example.jubileebackendeindopdracht.repository.UserRepository;
import com.example.jubileebackendeindopdracht.utils.RandomStringGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //get all
/*    public List<UserDto> getAllUsers(){
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = userRepository.findAll();

        for (User user : users){
            UserDto userDto = transferUserToUserDto(user);

            Account account = user.getAccount();
            if (account != null){
                userDto.setAccountId(account.getId());
            }

            userDtoList.add(userDto);
        }
        return userDtoList;
    }*/


    //get user
    public UserDto getUser(String username) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return transferUserToUserDto(user);
    }

    //create
    public String createUser(UserDto userDto){
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        userDto.setEnabled(true);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User newUser = userRepository.save(transferUserDtoToUser(userDto));

        return newUser.getUsername();
    }


    //update
/*    public UserDto updateUser(Long id, UserDto updatedUserDto){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));

        updateUserFromUserDto(existingUser, updatedUserDto);
        User updatedUser = userRepository.save(existingUser);

        return transferUserToUserDto(updatedUser);

    }*/


    //delete
/*    public ResponseEntity<UserDto> deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserIdNotFoundException(id));

        Account account = user.getAccount();
        if (account != null) {
            account.setUser(null);
            accountRepository.save(account);
        }

        userRepository.delete(user);

        return ResponseEntity.noContent().build();
    }*/


    //helper methods
    public UserDto transferUserToUserDto(User user){
        var userDto = new UserDto();

        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.enabled = user.isEnabled();
        userDto.apikey = user.getApikey();
        userDto.email = user.getEmail();
        userDto.authorities = user.getAuthorities();

        return userDto;

    }

    public User transferUserDtoToUser(UserDto userDto){
        var user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());

        return user;
    }


    public void updateUserFromUserDto(String username, UserDto updatedUserDto) {

        if (!userRepository.existsById(username)) {
            throw new UsernameNotFoundException(username);
        }

        User user = userRepository.findById(username).get();

        if (updatedUserDto.getUsername() != null) {
            user.setUsername(updatedUserDto.getUsername());
        }
        if (updatedUserDto.getPassword() != null) {
            user.setPassword(updatedUserDto.getPassword());
        }
        if (updatedUserDto.getEnabled() != null) {
            user.setEnabled(updatedUserDto.getEnabled());
        }
        if (updatedUserDto.getEmail() != null) {
            user.setEmail(updatedUserDto.getEmail());
        }
    }




}
