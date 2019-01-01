package es.geeksusma.employee;

class Employee {

    private Long id;

    private String name;

    private Boolean disabled;

    private Employee(final Builder builder) {

        id = builder.id;
        name = builder.name;
        disabled = builder.disabled;
    }

    public static Builder builder() {

        return new Builder();
    }

    Long getId() {

        return id;
    }

    String getName() {

        return name;
    }

    Boolean getDisabled() {

        return disabled;
    }


    public static final class Builder {
        private Long id;
        private String name;
        private Boolean disabled;

        private Builder() {}

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

        public Employee build() {

            return new Employee(this);
        }
    }
}
