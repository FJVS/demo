package com.example.demo.mapper;

import com.example.demo.data.entity.Employee;
import com.example.demo.dto.GetEmployeeDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeMapper {

    @Bean(name = "EmployeeMapper")
    public MapperFacade getMapperFacade() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Employee.class, GetEmployeeDTO.class)
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade();
    }
}
