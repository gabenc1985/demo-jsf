package ec.gbc.house.entidades;

import ec.gbc.house.enumeraciones.EstadoIncidencia;
import ec.gbc.house.enumeraciones.EstadoRegistro;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Incidencia.class)
public class Incidencia_ {

    public static volatile SingularAttribute<Incidencia, EstadoIncidencia> estadoIncidencia;

    public static volatile SingularAttribute<Incidencia, EstadoRegistro> estado;
}
