package org.example;

import Clases.*;

import java.util.ArrayList;
import java.util.List;

import static Clases.Usuario.hashContrasena;

public class main {
    static void main(String[] args) {
        //   ENTRENAMIENTO COMPLETO DEL DÍA
        Entrenamiento entrenamiento = new Entrenamiento();
        entrenamiento.iniciarEntrenamiento();

        Ejercicio pressBanca = new Ejercicio("Press Banca", "FUERZA");
        pressBanca.iniciarEntrenamiento();
        pressBanca.registrarSerie(new Serie(
                "Press Banca", "FUERZA",
                1, 10, 60.0, 90, 75.0
        ));

        pressBanca.registrarSerie(new Serie(
                "Press Banca", "FUERZA",
                2, 8, 70.0, 120, 75.0
        ));

        pressBanca.registrarSerie(new Serie(
                "Press Banca", "FUERZA",
                3, 6, 80.0, 150, 75.0
        ));

        pressBanca.finalizarEntrenamiento();

        // Añadir las series del ejercicio al entrenamiento general
        pressBanca.getSeries().forEach(entrenamiento::registrarSerie);

        //  EJERCICIO 2: SENTADILLA
        Ejercicio sentadilla = new Ejercicio("Sentadilla", "FUERZA");
        sentadilla.iniciarEntrenamiento();

        sentadilla.registrarSerie(new Serie(
                "Sentadilla", "FUERZA",
                1, 12, 80.0, 120, 75.0
        ));

        sentadilla.registrarSerie(new Serie(
                "Sentadilla", "FUERZA",
                2, 10, 90.0, 150, 75.0
        ));

        sentadilla.finalizarEntrenamiento();

        // Añadir las series al entrenamiento general
        sentadilla.getSeries().forEach(entrenamiento::registrarSerie);

        //   FINALIZAR ENTRENAMIENTO COMPLETO
        entrenamiento.finalizarEntrenamiento();

        //   MOSTRAR RESÚMENES
        System.out.println("\n══════════════════════════════");
        System.out.println("RESUMEN EJERCICIO: PRESS BANCA");
        System.out.println(pressBanca.resumenEjercicio());

        System.out.println("\n══════════════════════════════");
        System.out.println("RESUMEN EJERCICIO: SENTADILLA");
        System.out.println(sentadilla.resumenEjercicio());

        System.out.println("\n══════════════════════════════");
        System.out.println("RESUMEN ENTRENAMIENTO COMPLETO");
        System.out.println("Fecha: " + entrenamiento.getFecha());
        System.out.println("Duración: " + entrenamiento.getDuracionMinutos() + " min");
        System.out.println("Calorías totales: " + entrenamiento.getCaloriasQuemadas());
        System.out.println("Series totales: " + entrenamiento.totalSeries());
        System.out.println("══════════════════════════════");
        }
    }




