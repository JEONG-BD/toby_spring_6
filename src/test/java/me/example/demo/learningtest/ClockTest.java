package me.example.demo.learningtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClockTest {

    //
    @Test
    @Order(1)
    @DisplayName("Clock을 이용해서 LocalDateTime Now를 가져온다")
    public void clock() throws Exception{
        //given
        Clock clock = Clock.systemDefaultZone();

        //when
        LocalDateTime dt = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);
        
        //then
        assertThat(dt2).isAfter(dt);
    }

    @Test
    @Order(2)
    @DisplayName("원하는 시간을 지정해서 가져 올 수 있다")
    public void fixClock () throws Exception{
        //given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        //when
        LocalDateTime dt = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        //then
        assertThat(dt2).isEqualTo(dt);
    }
}
