package com.fsa.firststepapp.models.mappers;

import com.fsa.firststepapp.models.User;
import com.fsa.firststepapp.models.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    public UserMapper() {
    }

    /**
     * Converteste un obiect User într-un obiect UserDto.
     * @param user Obiectul User de conversie.
     * @return Obiectul UserDto rezultat.
     */
    public UserDto convertModelToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setUserId(user.getUserId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(String.valueOf(user.getRole()));

        return userDto;
    }

    /**
     * Converteste o listă de obiecte User într-o listă de obiecte UserDto.
     * @param users Lista de obiecte User de conversie.
     * @return Lista de obiecte StartupDto rezultate.
     */
    public List<UserDto> convertModelListToDtoList(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        for(User user: users) {
            userDtos.add(convertModelToDto(user));
        }

        return userDtos;
    }
}
