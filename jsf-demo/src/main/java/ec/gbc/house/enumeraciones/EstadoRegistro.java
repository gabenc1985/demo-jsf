package ec.gbc.house.enumeraciones;

public enum EstadoRegistro {

    ACTIVO(1),
    INACTIVO(2);

    Integer value;

    EstadoRegistro(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static EstadoRegistro getEnum(Integer value) {
        for(EstadoRegistro v : values())
            if(v.getValue().equals(value)) return v;
        return EstadoRegistro.INACTIVO;
    }
}
