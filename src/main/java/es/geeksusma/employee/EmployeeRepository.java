package es.geeksusma.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {

    List<EmployeeModel> findByDisabledIsFalse();
}
