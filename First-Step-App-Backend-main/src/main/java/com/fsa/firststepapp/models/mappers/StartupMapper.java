package com.fsa.firststepapp.models.mappers;

import com.fsa.firststepapp.models.Startup;
import com.fsa.firststepapp.models.dto.StartupDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care se ocupă de conversia dintre Startup și StartupDto.
 */
@Component
public class StartupMapper {
    public StartupMapper() {
    }

    /**
     * Converteste un obiect Investor într-un obiect UserDto.
     * @param startup Obiectul Startup de conversie.
     * @return Obiectul StartupDto rezultat.
     */
    public StartupDto convertModelToDto(Startup startup) {
        StartupDto startupDto = new StartupDto();

        startupDto.setStartupId(startup.getStartupId());
        startupDto.setName(startup.getName());
        startupDto.setEmail(startup.getEmail());
        startupDto.setPassword(startup.getPassword());
        startupDto.setRole(String.valueOf(startup.getRole()));

        return startupDto;
    }

    /**
     * Converteste o listă de obiecte User într-o listă de obiecte UserDto.
     * @param startups Lista de obiecte User de conversie.
     * @return Lista de obiecte StartupDto rezultate.
     */
    public List<StartupDto> convertModelListToDtoList(List<Startup> startups) {
        List<StartupDto> startupDtos = new ArrayList<>();

        for(Startup startup: startups) {
            startupDtos.add(convertModelToDto(startup));
        }

        return startupDtos;
    }
}
