package ec.gbc.house.enumeraciones;

public enum EstadoIncidencia {

	ABIERTA(1),
	ESCALADA(2),
	CERRADA(3);

	Integer value;

	EstadoIncidencia(Integer value){
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	 public static EstadoIncidencia getEnum(Integer value) {
         for(EstadoIncidencia v : values())
             if(v.getValue().equals(value)) return v;
        return EstadoIncidencia.ABIERTA;
     }
}
