package com.example.demo.mapper;

import com.example.demo.data.entity.Booking;
import com.example.demo.dto.BookingDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingMapper {

    @Bean(name = "BookingMapper")
    public MapperFacade getMapperFacade() {

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Booking.class, BookingDTO.class)
                .byDefault()
                .register();

        return mapperFactory.getMapperFacade();
    }
}
