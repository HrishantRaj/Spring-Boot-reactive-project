package com.reactive.springproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoTest {

    private FluxAndMono fluxAndMono;

    @BeforeEach
    void init() {
        fluxAndMono = new FluxAndMono();
    }

    @Test
    void fluxTest() {
//        fluxAndMono = new FluxAndMono();

        Flux<String> res = fluxAndMono.getFlux();

        StepVerifier.create(res)
                .expectNext("abc")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void monoTest() {
//        fluxAndMono = new FluxAndMono();

        Mono<String> res = fluxAndMono.getMono();
        StepVerifier.create(res)
                .expectNext("monobla")
                .verifyComplete();
    }

    @Test
    void upper() {
//        fluxAndMono = new FluxAndMono();

        Flux<String> res = fluxAndMono.upper();
        StepVerifier.create(res)
                .expectNext("ABC")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void filterResTest() {
        Flux<String> res = fluxAndMono.filterRes();
        StepVerifier.create(res)
                .expectNext("3-ABC","3-DEF","3-EFE")
                .verifyComplete();
    }

    @Test
    void flatmapFLuxTest() {
        Flux<String> res = fluxAndMono.flatmapFLux();
        StepVerifier.create(res)
                .expectNext("A")
                .expectNextCount(8)
                .verifyComplete();
    }

    @Test
    void flatmapFLuxAsyncTest() {
        Flux<String> res = fluxAndMono.flatmapFLuxAsync();
        StepVerifier.create(res)
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void concatmapFLuxAsyncTest() {
        Flux<String> res = fluxAndMono.concatmapFLuxAsync();
        StepVerifier.create(res)
                .expectNext("A","B","C","D","E","F","E","F","E")
                .verifyComplete();
    }

    @Test
    void flatmapMono() {
        Mono<List<String>> res = fluxAndMono.flatmapMono();
        List<String> list = List.of("M","O","N","O","B","L","A");
        StepVerifier.create(res)
                .expectNext(list)
                .verifyComplete();
    }

    @Test
    void flatmapManyMono() {
        Flux<String> res = fluxAndMono.flatmapManyMono();
        StepVerifier.create(res)
                .expectNext("M","O","N","O","B","L","A")
                .verifyComplete();
    }

    @Test
    void transformFLux() {
        Flux<String> res = fluxAndMono.transformFLux();
        StepVerifier.create(res)
                .expectNext("A")
                .expectNextCount(8)
                .verifyComplete();
    }

    @Test
    void transformFLuxDefaultIfEmpty() {
        Flux<String> res = fluxAndMono.transformFLuxDefaultIfEmpty();
        StepVerifier.create(res)
                .expectNext("d","e","f","a","u","l","t")
                .verifyComplete();
    }

    @Test
    void transformFLuxSwitchIfEmpty() {
        Flux<String> res = fluxAndMono.transformFLuxSwitchIfEmpty();
        StepVerifier.create(res)
                .expectNext("D")
                .expectNextCount(6)
                .verifyComplete();
    }

    @Test
    void concatFun() {
        Flux<String> res = fluxAndMono.concatFun();
        StepVerifier.create(res)
                .expectNext("a","b","c","d")
                .verifyComplete();
    }

    @Test
    void concatWithFun() {
        Flux<String> res = fluxAndMono.concatWithFun();
        StepVerifier.create(res)
                .expectNext("a","b")
                .verifyComplete();
    }

    @Test
    void mergeFun() {
        Flux<String> res = fluxAndMono.mergeFun();
        StepVerifier.create(res)
                .expectNext("a","c","b","d")
                .verifyComplete();
    }

    @Test
    void mergeWithFun() {
        Flux<String> res = fluxAndMono.mergeWithFun();
        StepVerifier.create(res)
                .expectNext("a","b")
                .verifyComplete();
    }

    @Test
    void mergeSequentialFun() {
        Flux<String> res = fluxAndMono.mergeSequentialFun();
        StepVerifier.create(res)
                .expectNext("a","b","c","d")
                .verifyComplete();
    }

    @Test
    void zipFun() {
        Flux<String> res = fluxAndMono.zipFun();
        StepVerifier.create(res)
                .expectNext("ac","bd")
                .verifyComplete();
    }

    @Test
    void zipFun1() {
        Flux<String> res = fluxAndMono.zipFun1();
        StepVerifier.create(res)
                .expectNext("ac1","bd2")
                .verifyComplete();
    }

    @Test
    void zipWithFun() {
        Mono<String> res = fluxAndMono.zipWithFun();
        StepVerifier.create(res)
                .expectNext("ab")
                .verifyComplete();
    }
}