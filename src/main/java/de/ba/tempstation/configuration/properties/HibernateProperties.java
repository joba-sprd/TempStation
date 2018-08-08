package de.ba.tempstation.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "hibernate")
@Validated
public class HibernateProperties {
    @NotEmpty
    private String dialect;

    @NotEmpty
    private String hbm2ddlAuto;

    @NotEmpty
    private String connectionReleaseMode;

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        properties.setProperty("hibernate.connection.release_mode", connectionReleaseMode);
        return properties;
    }

    //region Getter/Setter
    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getHbm2ddlAuto() {
        return hbm2ddlAuto;
    }

    public void setHbm2ddlAuto(String hbm2ddlAuto) {
        this.hbm2ddlAuto = hbm2ddlAuto;
    }

    public String getConnectionReleaseMode() {
        return connectionReleaseMode;
    }

    public void setConnectionReleaseMode(String connectionReleaseMode) {
        this.connectionReleaseMode = connectionReleaseMode;
    }

    //endregion
}
