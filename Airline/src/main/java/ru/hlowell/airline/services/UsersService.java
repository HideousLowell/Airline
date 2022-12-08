package ru.hlowell.airline.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hlowell.airline.dto.UserDto;
import ru.hlowell.airline.persistence.entity.User;
import ru.hlowell.airline.persistence.repository.UsersRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepo usersRepo;

    public Optional<User> save(UserDto userDto) {
        if (usersRepo.findByUsername(userDto.getUsername()).isPresent())
            return Optional.empty();

        return Optional.of(usersRepo.save(
                        new User(userDto.getUsername(), userDto.getPassword(), "ROLE_USER")));
    }

}
