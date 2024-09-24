package com.kdt.wolf.global.config;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {
	
	@Bean
	DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("scott");
		ds.setPassword("tiger");
		ds.setInitialSize(5);
		ds.setMinIdle(5);
		ds.setMaxIdle(10);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(1000 * 10);
		
		return ds;
	}
	
}
