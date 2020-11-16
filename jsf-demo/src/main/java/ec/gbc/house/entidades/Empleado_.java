package ec.gbc.house.entidades;

import ec.gbc.house.converters.Estado;
import ec.gbc.house.enumeraciones.EstadoRegistro;

import javax.persistence.*;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import java.util.List;

@StaticMetamodel(Empleado.class)
public class Empleado_ {

    public static volatile SingularAttribute<Empleado, String> username;

    public static volatile SingularAttribute<Empleado, EstadoRegistro> estado;
}
