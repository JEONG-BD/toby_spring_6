package me.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SortTest {

    Sort sort;

    @BeforeEach
    void beforeEach(){
        sort = new Sort();
    }


    @Test
    @Order(1)
    @DisplayName("2개의 아이템을 가진 리스트를 문자열 길이로 정렬한다")
    public void sort2Item() throws Exception{
        //given
        //when
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        //then
        Assertions.assertThat(list).isEqualTo(List.of("b", "aa"));
    }

    @Test
    @Order(2)
    @DisplayName("3개의 아이템을 가진 리스트를 문자열 길이로 정렬한다")
    public void sort3Item() throws Exception{
        //given
        //Sort sort = new Sort();

        //when
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        //then
        Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }

    @Test
    @Order(3)
    @DisplayName("이미 정렬된 문자열을 정렬한다")
    public void sortAlreadySorted() throws Exception{
        //given
        //Sort sort = new Sort();

        //when
        List<String> list = sort.sortByLength(Arrays.asList("b", "dd", "ccc"));

        //then
        Assertions.assertThat(list).isEqualTo(List.of("b", "dd", "ccc"));
    }

}