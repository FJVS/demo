package com.example.demo.mapper;

import com.example.demo.data.entity.Rating;
import com.example.demo.dto.RatingDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RatingMapper {

    @Bean(name = "RatingMapper")
    public MapperFacade getMapperFacade() {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Rating.class, RatingDTO.class)
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade();
    }
}
