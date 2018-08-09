package cn.edu.upc.eduroamcontrolsystembackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by jay on 2018/08/09
 */


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryRadius",
        transactionManagerRef = "transactionManagerRadius",
        basePackages = {"cn.edu.upc.eduroamcontrolsystembackend.dao.radius"})
public class RadiusDBConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("radiusDataSource")
    private DataSource radiusDataSource;

    @Bean(name = "entityManagerRadius")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryRadius(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryRadius")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryRadius(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(radiusDataSource)
                .properties(getVendorProperties(radiusDataSource))
                .packages("cn.edu.upc.eduroamcontrolsystembackend.model.radius")
                .persistenceUnit("radiusPersistenceUnit")
                .build();
    }

    private Map<String, ?> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManagerRadius")
    PlatformTransactionManager transactionManagerRadius(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryRadius(builder).getObject());
    }

}
