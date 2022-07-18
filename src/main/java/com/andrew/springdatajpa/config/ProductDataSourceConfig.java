package com.andrew.springdatajpa.config;

import com.andrew.springdatajpa.product.entities.Product;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class ProductDataSourceConfig {

    @Primary
    @Bean(name = "productDataSourceProperties")
    @ConfigurationProperties("spring.datasource-product")
    public DataSourceProperties productDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name = "productDataSource")
    public DataSource productDataSource() {
        return productDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "productEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean productEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder.dataSource(productDataSource()).packages(Product.class).build();
    }

    @Primary
    @Bean(name = "productTransactionManager")
    public PlatformTransactionManager productTransactionManager(@Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {
        return new JpaTransactionManager(productEntityManagerFactory);
    }

}
