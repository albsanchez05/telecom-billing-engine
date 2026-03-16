package com.bios.core.repository;

import com.bios.core.domain.Exportable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RepositorioArchivo<T extends Exportable> implements Repositorio<T>
{

    private String rutaArchivo;
    private Function<String, T> transformador;

    public RepositorioArchivo( String rutaArchivo, Function<String, T> transformador )
    {
        this.rutaArchivo = rutaArchivo;
        this.transformador = transformador;
    }

    @Override
    public void guardar( T entidad )
    {
        // Asegúrate de que el directorio exista antes de escribir el archivo
        Path path = Path.of( this.rutaArchivo );

        try {
            // Convierte la entidad a una línea de texto (CSV) y añádele un salto de línea al final
            String linea = entidad.toCSV() + System.lineSeparator();
            // CREATE = Si no existe, créalo. APPEND = Si ya existe, ponlo al final (no lo borres).
            Files.writeString( path, linea, StandardOpenOption.CREATE, StandardOpenOption.APPEND );

            System.out.println( "Datos guardados en el disco fisico.");
        } catch ( IOException e ) {
            System.out.println( "Error al guardar los datos en el disco fisico: " + e.getMessage() );
        }
    }

    @Override
    public List<T> leerTodos()
    {
        Path path = Path.of( this.rutaArchivo );

        // Si el archivo no existe, devuelve una lista vacía en lugar de lanzar una excepción
        if ( !Files.exists( path ) ) {
            return new ArrayList<>();
        }

        try {
            // Lee todas las líneas del archivo,
            // transforma cada línea a una entidad T usando el transformador,
            // y colecta todo en una lista

            return Files.lines( path )
                .map( this.transformador )
                .collect( Collectors.toList() );
        } catch ( IOException e ) {
            System.out.println( "Error al leer los datos del disco fisico: " + e.getMessage() );
            return new ArrayList<>();
        }
    }
}
