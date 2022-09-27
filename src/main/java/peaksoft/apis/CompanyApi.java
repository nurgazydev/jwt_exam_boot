package peaksoft.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.CompanyRequest;
import peaksoft.dto.responses.CompanyResponse;
import peaksoft.dto.responses.simpl.SimpleResponse;
import peaksoft.services.CompanyService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyApi {

    private final CompanyService companyService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CompanyResponse save(@RequestBody CompanyRequest companyRequest) {
        return companyService.save(companyRequest);
    }

    @GetMapping("/findById/{companyId}")
    @PermitAll
    public CompanyResponse findById(@PathVariable Long companyId) {
        return companyService.findById(companyId);
    }

    @GetMapping("/findAll")
    @PermitAll
    public List<CompanyResponse> findAll() {
        return companyService.findAll();
    }

    @PutMapping("/update/{companyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CompanyResponse update(@PathVariable Long companyId,
                                  @RequestBody CompanyRequest companyRequest) {
        return companyService.update(companyId, companyRequest);
    }

    @DeleteMapping("/delete/{companyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse delete(@PathVariable Long companyId) {
        return companyService.delete(companyId);
    }

}
