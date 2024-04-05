package com.fsa.firststepapp.models.mappers;

import com.fsa.firststepapp.models.Admin;
import com.fsa.firststepapp.models.Investor;
import com.fsa.firststepapp.models.dto.InvestorDto;
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
     * @param investor Obiectul Investor de conversie.
     * @return Obiectul InvestorDto rezultat.
     */
    public InvestorDto convertModelToDto(Investor investor) {
        InvestorDto investorDto = new InvestorDto();

        investorDto.setInvestorId(investor.getInvestorId());
        investorDto.setName(investor.getName());
        investorDto.setEmail(investor.getEmail());
        investorDto.setPassword(investor.getPassword());
        investorDto.setRole(String.valueOf(investor.getRole()));

        return investorDto;
    }

    /**
     * Converteste o listă de obiecte User într-o listă de obiecte UserDto.
     * @param investors Lista de obiecte User de conversie.
     * @return Lista de obiecte UserDto rezultate.
     */
    public List<InvestorDto> convertModelListToDtoList(List<Investor> investors) {
        List<InvestorDto> investorDtos = new ArrayList<>();

        for(Investor investor: investors) {
            investorDtos.add(convertModelToDto(investor));
        }

        return investorDtos;
    }
}
