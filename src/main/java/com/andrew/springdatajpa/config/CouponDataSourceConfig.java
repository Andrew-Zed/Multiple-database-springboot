package com.andrew.springdatajpa.config;

import com.andrew.springdatajpa.coupon.entities.Coupon;
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
public class CouponDataSourceConfig {

    @Primary
    @Bean(name = "couponDataSourceProperties")
    @ConfigurationProperties("spring.datasource-coupon")
    public DataSourceProperties couponDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name = "couponDataSource")
    public DataSource couponDataSource() {
        return couponDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Primary
    @Bean(name = "couponEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean couponEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder.dataSource(couponDataSource()).packages(Coupon.class).build();
    }

    @Primary
    @Bean(name = "couponTransactionManager")
    public PlatformTransactionManager couponTransactionManager(@Qualifier("couponEntityManagerFactory") EntityManagerFactory couponEntityManagerFactory) {
        return new JpaTransactionManager(couponEntityManagerFactory);
    }

}
