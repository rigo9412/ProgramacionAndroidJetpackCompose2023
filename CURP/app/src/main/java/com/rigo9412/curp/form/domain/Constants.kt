package com.rigo9412.curp.form.domain

import java.time.format.DateTimeFormatter

val BLACK_LIST = arrayListOf(
    "BACA", "BAKA", "BUEI", "BUEY",
    "CACA", "CACO", "CAGA", "CAGO", "CAKA", "CAKO", "COGE", "COGI", "COJA", "COJE", "COJI", "COJO", "COLA", "CULO",
    "FALO", "FETO",
    "GETA", "GUEI", "GUEY",
    "JETA", "JOTO",
    "KACA", "KACO", "KAGA", "KAGO", "KAKA", "KAKO", "KOGE", "KOGI", "KOJA", "KOJE", "KOJI", "KOJO", "KOLA", "KULO",
    "LILO", "LOCA", "LOCO", "LOKA", "LOKO",
    "MAME", "MAMO", "MEAR", "MEAS", "MEON", "MIAR", "MION", "MOCO", "MOKO", "MULA", "MULO",
    "NACA", "NACO",
    "PEDA", "PEDO", "PENE", "PIPI", "PITO", "POPO", "PUTA", "PUTO",
    "QULO",
    "RATA", "ROBA", "ROBE", "ROBO", "RUIN",
    "SENO",
    "TETA",
    "VACA", "VAGA", "VAGO", "VAKA", "VUEI", "VUEY",
    "WUEI", "WUEY"

)

val PATTERN_NAME = Regex("[a-zA-zñÑáéíóúÁÉÍÓÚÜ'° .,\\\\s]*")
val COMPOSTION_NAME =  arrayListOf("MARIA", "MA.", "MA", "JOSE", "J", "J." )
val PREPOSTION_CONJUNTION_CONTRADICTION = arrayListOf<String>("DA", "DAS", "DE", "DEL", "DER", "DI", "DIE", "DD", "EL", "LA", "LOS", "LAS", "LE", "LES", "MAC", "MC", "VAN", "VON", "Y", "J", "MA" );
val VOCAL = "AEIOU";
val CONSONANTS = " BCDFGHJKLMNÑPQRSTVXZWY";
val FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd")
val FORMATTER_CURP = DateTimeFormatter.ofPattern("yyMMdd")