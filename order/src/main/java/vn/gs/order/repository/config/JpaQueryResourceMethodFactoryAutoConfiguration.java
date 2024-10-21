package vn.gs.order.repository.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;

//@Configuration(proxyBeanMethods = false)
//@ConditionalOnMissingBean({JpaQueryMethodFactory.class})
//@ConditionalOnClass({EntityManager.class, JpaRepositoryFactoryBean.class})
//@ConditionalOnBean({EntityManagerFactory.class})
//@ConditionalOnProperty(
//        prefix = "spring.data.jpa.repositories",
//        name = {"enabled"},
//        havingValue = "true",
//        matchIfMissing = true
//)
//@AutoConfigureAfter({JpaRepositoriesAutoConfiguration.class})
//@Configuration(proxyBeanMethods = false)
//@ConditionalOnClass({EntityManager.class, JpaRepositoryFactoryBean.class})
//@ConditionalOnBean({EntityManagerFactory.class})
//@AutoConfigureAfter({JpaRepositoriesAutoConfiguration.class})
@Configuration
public class JpaQueryResourceMethodFactoryAutoConfiguration {
    @PersistenceContext
    EntityManager entityManager;

    @Bean
    @Primary
    JpaQueryMethodFactory jpaQueryResourceMethodFactory() {
        QueryExtractor extractor = PersistenceProvider.fromEntityManager(entityManager);
        return new JpaQueryResourceMethodFactory(extractor);
    }
}