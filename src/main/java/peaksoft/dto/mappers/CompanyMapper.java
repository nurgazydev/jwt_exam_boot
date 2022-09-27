package peaksoft.dto.mappers;

import org.springframework.stereotype.Component;
import peaksoft.dto.requests.CompanyRequest;
import peaksoft.dto.responses.CompanyResponse;
import peaksoft.models.Company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyMapper {

    public Company convertToEntity(CompanyRequest companyRequest) {
        if (companyRequest == null) {
            return null;
        }
        Company company = new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
        company.setCreatedAt(LocalDate.now());
        company.setActive(true);
        return company;
    }

    public CompanyResponse convertToView(Company company) {
        if (company == null) {
            return null;
        }

        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setLocatedCountry(company.getLocatedCountry());
        companyResponse.setCreatedAt(company.getCreatedAt());
        companyResponse.setActive(company.isActive());
        return companyResponse;
    }

    public List<CompanyResponse> getAllCompaniesToView(List<Company> companies) {
        List<CompanyResponse> companyResponses = new ArrayList<>();
        for (Company company : companies) {
            companyResponses.add(convertToView(company));
        }
        return companyResponses;
    }

    public void convertToUpdate(Company company, CompanyRequest companyRequest) {
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
        company.setCreatedAt(LocalDate.now());
        company.setActive(company.isActive());
    }
}
