package com.example.demo.mapper;

import com.example.demo.data.entity.Office;
import com.example.demo.dto.GetOfficeDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfficeMapper {

    @Bean(name = "OfficeMapper")
    public MapperFacade getMapperFacade() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Office.class, GetOfficeDTO.class)
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade();
    }
}
