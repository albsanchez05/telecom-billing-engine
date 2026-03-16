package com.bios.core.domain;

public class Factura implements Exportable
{
    int id;
    String cliente;
    double monto;

    public Factura( String cliente, int id, double monto )
    {
        this.cliente = cliente;
        this.id = id;
        this.monto = monto;
    }

    public int getId()
    {
        return id;
    }

    public Factura setId( int id )
    {
        this.id = id;
        return this;
    }

    public String getCliente()
    {
        return cliente;
    }

    public Factura setCliente( String cliente )
    {
        this.cliente = cliente;
        return this;
    }

    public double getMonto()
    {
        return monto;
    }

    public Factura setMonto( double monto )
    {
        this.monto = monto;
        return this;
    }

    public String toCSV()
    {
        return id + "," + cliente + "," + monto;
    }

}
