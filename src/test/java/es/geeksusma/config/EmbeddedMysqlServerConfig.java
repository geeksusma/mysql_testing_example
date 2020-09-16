package es.geeksusma.config;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_6_23;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.ServerSocket;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

@Configuration
@ConditionalOnProperty(value = "container", havingValue = "false")
class EmbeddedMysqlServerConfig {

    private static final String DATABASE_USERNAME = "root_test";
    private static final String DATABASE_PASSWORD = "root_test";
    private static final String DATABASE_NAME = "test_db";

    @Bean(name = "embeddedMysql", destroyMethod = "stop")
    @Scope(value = SCOPE_SINGLETON)
    public EmbeddedMysql embeddedMysql(@Qualifier("mysqldConfig") final MysqldConfig config) {

        return anEmbeddedMysql(config).addSchema(DATABASE_NAME, classPathScript("sql/base-schema-creation.sql"))
                .start();
    }

    @Bean(name = "mysqldConfig")
    @Scope(value = SCOPE_SINGLETON)
    public MysqldConfig config() throws IOException {

        return aMysqldConfig(v5_6_23).withCharset(UTF8).withPort(getRandomFreePort())
                .withUser(DATABASE_USERNAME, DATABASE_PASSWORD).build();
    }

    @Bean
    @DependsOn("embeddedMysql") // Because mysqlserver must be present before jdbc/hibernate
    public DataSource dataSource(@Qualifier("mysqldConfig") final MysqldConfig mysqldConfig) {

        return DataSourceBuilder.create().username(mysqldConfig.getUsername()).password(mysqldConfig.getPassword())
                .url("jdbc:mysql://localhost:" + mysqldConfig.getPort() + "/" + DATABASE_NAME)
                .driverClassName("com.mysql.jdbc.Driver").build();
    }

    private int getRandomFreePort() throws IOException {
        // obtain random port for Mysql
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            return serverSocket.getLocalPort();
        }
    }
}
