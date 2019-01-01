package es.geeksusma.employee;

import javax.persistence.*;


@Entity
@Table(name = "employee")
class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "disabled", nullable = false)
    private Boolean disabled;

    public EmployeeModel() {

    }

    private EmployeeModel(final Builder builder) {

        setId(builder.id);
        setName(builder.name);
        setDisabled(builder.disabled);
    }

    public static Builder builder() {

        return new Builder();
    }

    public Long getId() {

        return id;
    }

    public void setId(final Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public Boolean getDisabled() {

        return disabled;
    }

    public void setDisabled(final Boolean disabled) {

        this.disabled = disabled;
    }


    public static final class Builder {
        private Long id;
        private String name;
        private Boolean disabled;

        private Builder() {
        }

        public Builder id(final Long id) {

            this.id = id;
            return this;
        }

        public Builder name(final String name) {

            this.name = name;
            return this;
        }

        public Builder disabled(final Boolean disabled) {

            this.disabled = disabled;
            return this;
        }

        public EmployeeModel build() {

            return new EmployeeModel(this);
        }
    }
}
