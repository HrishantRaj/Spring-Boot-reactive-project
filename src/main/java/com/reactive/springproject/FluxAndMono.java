package com.reactive.springproject;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMono {

    Flux<String> getFlux() {
        return Flux.fromIterable(List.of("abc","def","efe"));
    }

    Mono<String> getMono() {
        return Mono.just("monobla");
    }

    Flux<String> upper() {
        return getFlux().map(String::toUpperCase);
    }

    Flux<String> filterRes() {
        return upper()
                .filter(s -> s.length()>2)
                .map(s-> s.length()+"-"+s);
    }

    Flux<String> flatmapFLux() {
        return upper()
                .flatMap(s -> splitString(s));
    }

    Flux<String> transformFLux() {
        Function<Flux<String>,Flux<String>> upperCaseFunctionality = p -> p.map(String::toUpperCase);
        return getFlux()
                .transform(upperCaseFunctionality)
                .flatMap(s -> splitString(s));
    }

    Flux<String> transformFLuxDefaultIfEmpty() {
        Function<Flux<String>,Flux<String>> filterFunctionality = p -> p.map(String::toUpperCase)
                .filter(s->s.length()>3);
        return getFlux()
                .transform(filterFunctionality)
                .defaultIfEmpty("default")
                .flatMap(s -> splitString(s));
    }

    Flux<String> transformFLuxSwitchIfEmpty() {
        Function<Flux<String>,Flux<String>> filterFunctionality = p -> p.map(String::toUpperCase)
                .filter(s->s.length()>3);
        var defaultFlux = Flux.just("default").transform(filterFunctionality);
        return getFlux()
                .transform(filterFunctionality)
                .switchIfEmpty(defaultFlux)
                .flatMap(s -> splitString(s));
    }
    Flux<String> flatmapFLuxAsync() {
        return upper()
                .flatMap(s -> splitStringAsync(s))
                .log();
    }

    Flux<String> concatmapFLuxAsync() {
        return upper()
                .concatMap(s -> splitStringAsync(s))
                .log();
    }

    Flux<String> concatFun() {
        var abFlux = Flux.just("a","b");
        var cdFlux = Flux.just("c","d");
        return Flux.concat(abFlux,cdFlux).log();
    }


    Flux<String> concatWithFun() {
        var aMono = Mono.just("a");
        var bMono = Mono.just("b");
        return aMono.concatWith(bMono);
    }

    Flux<String> mergeFun() {
        var abFlux = Flux.just("a","b")
                .delayElements(Duration.ofMillis(100));
        var cdFlux = Flux.just("c","d")
                .delayElements(Duration.ofMillis(125));
        return Flux.merge(abFlux,cdFlux).log();
    }

    Flux<String> mergeSequentialFun() {
        var abFlux = Flux.just("a","b")
                .delayElements(Duration.ofMillis(100));
        var cdFlux = Flux.just("c","d")
                .delayElements(Duration.ofMillis(125));
        return Flux.mergeSequential(abFlux,cdFlux).log();
    }

    Flux<String> zipFun() {
        var abFlux = Flux.just("a","b");
        var cdFlux = Flux.just("c","d");
        return Flux.zip(abFlux,cdFlux,(f,s) -> f+s)
                .log();
    }

    Flux<String> zipFun1() {
        var abFlux = Flux.just("a","b");
        var cdFlux = Flux.just("c","d");
        var _123Flux = Flux.just("1","2");
        return Flux.zip(abFlux,cdFlux,_123Flux)
                .map(t-> t.getT1()+t.getT2()+t.getT3())
                .log();
    }

    Mono<String> zipWithFun() {
        var aMono = Mono.just("a");
        var bMono = Mono.just("b");
        return aMono.zipWith(bMono)
                .map(t-> t.getT1()+t.getT2());
    }

    Flux<String> mergeWithFun() {
        var aMono = Mono.just("a");
        var bMono = Mono.just("b");
        return aMono.mergeWith(bMono);
    }

    Mono<List<String>> flatmapMono() {
        return getMono()
                .map(String::toUpperCase)
                .flatMap(s->splitStringMono(s));
    }

    Flux<String> flatmapManyMono() {
        return getMono()
                .map(String::toUpperCase)
                .flatMapMany(s->splitString(s));
    }

    private Mono<? extends List<String>> splitStringMono(String s) {
        String[] charArray = s.split("");
        List<String> list = List.of(charArray);
        return Mono.just(list);
    }

    Flux<String> splitString(String name) {
        String[] charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    Flux<String> splitStringAsync(String name) {
        String[] charArray = name.split("");
        var delay = new Random().nextInt(1000);
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(delay));
    }
}
