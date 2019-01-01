package es.geeksusma.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

import javax.inject.Inject;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@SqlGroup({@Sql(scripts = "classpath:sql/sample-data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:sql/clean-database.sql", executionPhase = AFTER_TEST_METHOD)})

public class EmployeeFetcherServiceITest {

    @Inject
    private EmployeeFetcherService employeeFetcherService;


    @Test
    public void should_fetchOnlyEnableds_when_Name() {

        //when
        final List<Employee> employees = employeeFetcherService.findOnlyEnabled();

        //then
        assertThat(employees).extracting("disabled").containsOnly(false);
        assertThat(employees).extracting("name").containsOnly("first employee", "third employee");
    }
}