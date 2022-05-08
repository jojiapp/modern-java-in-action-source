package com.jojiapp.modrenjavainactiontest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class Chap4 {

    @Test
    void 기존의_최댓값_구하기() throws Exception {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5);

        // when
        int max = Integer.MIN_VALUE;
        for (Integer integer : integers) {
            if (max < integer) {
                max = integer;
            }
        }

        // then
        assertThat(max).isEqualTo(5);
    }

    @Test
    void 스트림으로_최댓값_구하기() throws Exception {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5);

        // when
        int max = integers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);

        // then
        assertThat(max).isEqualTo(5);
    }

    @Test
    void 기존의_필터링_방법() throws Exception {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5);

        List<Integer> result = new ArrayList<>();
        // when
        for (Integer integer : integers) {
            if (integer > 2) {
                result.add(integer);
            }
        }

        // then
        assertThat(result).contains(3, 4, 5);
        assertThat(result).doesNotContain(1, 2);
    }

    @Test
    void 스트림으로_필터링_방법() throws Exception {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5);

        // when
        int[] result = integers.stream()
                .mapToInt(Integer::intValue)
                .filter(i -> i > 2)
                .toArray();

        // then
        assertThat(result).contains(3, 4, 5);
        assertThat(result).doesNotContain(1, 2);
    }


    @Test
    void _2보다_큰_값중_2개만_제곱하기() throws Exception {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5);

        // when
        int[] result = integers.stream()
                .mapToInt(Integer::intValue)
                .filter(i -> i > 2)
                .limit(2)
                .map(i -> i * i)
                .toArray();

        // then
        assertThat(result).contains(9, 16);
    }

    @Test
    void 상상한_로직() throws Exception {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5);

        List<Integer> filtering = new ArrayList<>();
        // when
        for (Integer integer : integers) {
            if (integer > 2) {
                filtering.add(integer);
            }
        }

        List<Integer> limit = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            limit.add(filtering.get(i));
        }

        List<Integer> result = new ArrayList<>();
        for (Integer integer : limit) {
            result.add(integer * integer);
        }

        // then
        assertThat(result).contains(9, 16);
    }

    @Test
    void 실제_비슷한_로직() throws Exception {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5);

        List<Integer> result = new ArrayList<>();

        // when
        for (Integer integer : integers) {
            if (integer > 2) {
                result.add(integer * integer);
                if(result.size() == 2) {
                    break;
                }
            }
        }

        // then
        assertThat(result).contains(9, 16);
    }

    @Test
    void 외부반복() throws Exception {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5);

        List<Integer> result = new ArrayList<>();
        // when
        for (Integer integer : integers) {
            result.add(integer * integer);
        }

        // then
    }


}
