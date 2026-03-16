package com.bios.core.repository;

import com.bios.core.domain.Exportable;

import java.util.List;

public interface Repositorio<T extends Exportable>
{
    void guardar( T entidad );

    List<T> leerTodos();
}
