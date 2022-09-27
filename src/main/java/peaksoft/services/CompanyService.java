package peaksoft.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.CompanyRequest;
import peaksoft.dto.responses.CompanyResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.dto.mappers.CompanyMapper;
import peaksoft.models.Company;
import peaksoft.repositories.CompanyRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService  {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;


    public List<CompanyResponse> findAll() {
        List<Company> allCompanies = companyRepository.findAll();
        return companyMapper.getAllCompaniesToView(allCompanies);
    }


    public CompanyResponse findById(Long id) {
        Company company = getCompanyById(id);
        return  companyMapper.convertToView(company);
    }

    private Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("company with id:" + id + " not found!"));
    }


    public CompanyResponse save(CompanyRequest companyRequest) {
        Company company = companyMapper.convertToEntity(companyRequest);
        Company company2 = companyRepository.save(company);
        return companyMapper.convertToView(company2);
    }

    @Transactional
    public CompanyResponse update(Long id, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException("course with id: " + id + " does not exists"));
        companyMapper.convertToUpdate(company, companyRequest);
        Company company2 = companyRepository.save(company);
        return companyMapper.convertToView(company2);
    }

    public SimpleResponse delete(Long id) {
        boolean exists = companyRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException(
                    "Company with id: " + id +"does not exists"
            );
        }
        companyRepository.deleteById(id);
        return new SimpleResponse("DELETED", "Company with id: " +id+ " deleted successfully");
    }
}
