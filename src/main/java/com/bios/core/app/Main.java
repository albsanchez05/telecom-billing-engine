package com.bios.core.app;

import com.bios.core.domain.Factura;
import com.bios.core.repository.Repositorio;
import com.bios.core.repository.RepositorioArchivo;

import java.util.List;

public class Main
{
    public static void main( String[] args )
    {
        // Crea un repositorio de facturas utilizando el RepositorioArchivo
        // El constructor del RepositorioArchivo recibe el nombre del archivo y una función
        // lambda para convertir una línea CSV en un objeto Factura
        Repositorio<Factura> repo = new RepositorioArchivo<>( "facturas_produccion.csv",
            ( String lineaCSV ) -> {
            // La función lambda toma una línea de texto (CSV), la divide en partes usando la coma como separador,
            // y luego crea un nuevo objeto Factura con los datos extraídos
            String[] partes = lineaCSV.split( "," );
            int id = Integer.parseInt( partes[0] );
            String cliente = partes[1];
            double monto = Double.parseDouble( partes[2] );

            return new Factura( cliente, id, monto );
            }
            );

        // Crea tres facturas de ejemplo

//        Factura factura1 = new Factura( "ClienteA", 1, 100.50 );
//        Factura factura2 = new Factura( "ClienteB", 2, 250.75 );
//        Factura factura3 = new Factura( "ClienteC", 3, 300.00 );
//
//        // Guarda las facturas en el repositorio
//
//        repo.guardar( factura1 );
//        repo.guardar( factura2 );
//        repo.guardar( factura3 );

        List<Factura> facturasRecuperadas = repo.leerTodos();

        // 1. Ver en qué ruta exacta de tu disco duro está buscando Java el archivo
        System.out.println("Buscando el archivo en: " + java.nio.file.Path.of("facturas_produccion.csv").toAbsolutePath());

        // 2. Ver cuántos elementos logró recuperar la lista
        System.out.println("Total de facturas recuperadas: " + facturasRecuperadas.size());

        for ( Factura factura : facturasRecuperadas ) {
            System.out.println( "Factura ID: " + factura.getId() + ", Cliente: " + factura.getCliente() + ", Monto: " + factura.getMonto() );
        }

    }
}