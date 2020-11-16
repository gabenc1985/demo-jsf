package ec.gbc.house.enumeraciones;

public enum PrioridadIncidencia {

	BAJO(1),
	MEDIO(2),
	ALTO(3);

	Integer value;

	PrioridadIncidencia(Integer value){
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	 public static PrioridadIncidencia getEnum(Integer value) {
         for(PrioridadIncidencia v : values())
             if(v.getValue().equals(value)) return v;
        return PrioridadIncidencia.BAJO;
     }
}
