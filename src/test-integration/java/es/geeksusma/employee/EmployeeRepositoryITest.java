package es.geeksusma.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

import javax.inject.Inject;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SqlGroup({@Sql(scripts = "classpath:sql/clean-database.sql", executionPhase = AFTER_TEST_METHOD)})
public class EmployeeRepositoryITest {

    @Inject
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {

        final EmployeeModel existingEmployee = EmployeeModel.builder()
                .disabled(false)
                .name("no-disabled")
                .build();
        employeeRepository.save(existingEmployee);
    }

    @Test
    public void should_fetchAll_when_fetchAll() {
        //when
        final List<EmployeeModel> all = employeeRepository.findAll();

        //then
        assertThat(all).hasSize(1);
    }

    @Test
    public void should_fetchOnlyEnabled_when_findByDisabledIsFalse() {
        //given
        employeeRepository.save(EmployeeModel.builder()
                .name("disabled")
                .disabled(true)
                .build());
        //when
        final List<EmployeeModel> enabledEmployees = employeeRepository.findByDisabledIsFalse();

        //then
        assertThat(enabledEmployees).hasSize(1);
        assertThat(enabledEmployees).extracting("disabled").containsOnly(false);
    }
}