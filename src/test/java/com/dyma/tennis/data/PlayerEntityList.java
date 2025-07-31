package com.dyma.tennis;

import com.dyma.tennis.data.PlayerEntity;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class PlayerEntityList {

    public static PlayerEntity NADAL_RAFAEL = new PlayerEntity(
            "Rafael",
            "Nadal",
            LocalDate.of(1986,Month.JUNE, 3),
            5000,
            1
    );

    public static PlayerEntity NOVAK_DJOKOVIC = new PlayerEntity(
            "Novak",
            "Djokovic",
            LocalDate.of(1987, Month.MAY, 22),
            4000,
            3
    );

    public static PlayerEntity ROGER_FEDERER = new PlayerEntity(
            "Roger",
            "Federer",
            LocalDate.of(1981, Month.AUGUST, 8),
            3000,
            3
    );

    public static PlayerEntity ANDY_MURRAY = new PlayerEntity(
            "Andy",
            "Murray",
            LocalDate.of(1987, Month.MAY, 15),
            2000,
            4
    );

    public static List<PlayerEntity> ALL = Arrays.asList(ANDY_MURRAY, NOVAK_DJOKOVIC, NADAL_RAFAEL, ROGER_FEDERER );
}
