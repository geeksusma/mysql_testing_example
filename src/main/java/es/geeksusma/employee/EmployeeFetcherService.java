package es.geeksusma.employee;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
class EmployeeFetcherService {

    private final EmployeeRepository employeeRepository;

    EmployeeFetcherService(final EmployeeRepository employeeRepository) {

        this.employeeRepository = employeeRepository;
    }

    List<Employee> findOnlyEnabled() {

        return employeeRepository.findByDisabledIsFalse().stream()
                .map(e -> Employee.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .disabled(e.getDisabled())
                        .build()).collect(Collectors.toList());
    }
}
