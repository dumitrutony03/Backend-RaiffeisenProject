package com.fsa.firststepapp.models.mappers;
import com.fsa.firststepapp.models.Role;
import com.fsa.firststepapp.models.User;
import com.fsa.firststepapp.models.dto.InvestorDto;
import com.fsa.firststepapp.models.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care se ocupă de conversia dintre Investor și InvestorDto.
 */
@Component
public class InvestorMapper {
    public InvestorMapper() {
    }

    /**
     * Converteste un obiect Investor într-un obiect InvestorDto.
     * @param user Obiectul User de conversie, cu atributul 'INVESTOR'.
     * @return Obiectul InvestorDto rezultat.
     */
    public InvestorDto convertModelToDto(UserDto user) {
        InvestorDto investorDto = new InvestorDto();

        investorDto.setInvestorId(user.getUserId());
        investorDto.setName(user.getName());
        investorDto.setEmail(user.getEmail());
        investorDto.setPassword(user.getPassword());
        investorDto.setRole("INVESTOR");

        return investorDto;
    }

    /**
     * Converteste o listă de obiecte User într-o listă de obiecte UserDto.
     * @param users Lista de obiecte User de conversie.
     * @return Lista de obiecte UserDto rezultate.
     */
//    public List<InvestorDto> convertModelListToDtoList(List<User> users) {
//        List<InvestorDto> investorDtos = new ArrayList<>();
//
//        for(User user: users) {
//            user.setRole(Role.valueOf("INVESTOR"));
//            investorDtos.add(convertModelToDto(user));
//        }
//
//        return investorDtos;
//    }
}
