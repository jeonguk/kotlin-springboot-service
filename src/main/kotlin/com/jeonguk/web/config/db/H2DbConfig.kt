package com.jeonguk.web.config.db

import com.google.common.collect.ImmutableMap
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
        basePackages = ["com.jeonguk.web.repository"],
        entityManagerFactoryRef = "entityManager",
        transactionManagerRef = "transactionManager"
)
@EnableTransactionManagement
class H2DbConfig {
    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.h2.Driver")
        dataSource.url = "jdbc:h2:mem:spring-app;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"
        dataSource.username = "sa"
        dataSource.password = ""
        return dataSource
    }

    @Bean
    fun entityManager(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("com.jeonguk.web.entity.h2")
        em.jpaVendorAdapter = HibernateJpaVendorAdapter()
        em.setJpaPropertyMap(ImmutableMap.of(
                "hibernate.hbm2ddl.auto", "create-drop",
                "hibernate.dialect", "org.hibernate.dialect.H2Dialect",
                "hibernate.ejb.entitymanager_factory_name", "entityManager",
                "hibernate.show_sql", "true",
                "hibernate.format_sql", "true"
        ))
        return em
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManager().getObject()
        return transactionManager
    }
}