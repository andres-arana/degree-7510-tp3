package modelo.condiciones;

import java.util.Calendar;

import modelo.CondicionNoRecurrente;
import modelo.DiasSemanaEnum;
import modelo.IVenta;

public class CondicionDiasSemana extends CondicionNoRecurrente {

	private DiasSemanaEnum[] diasSemana;

	public CondicionDiasSemana(DiasSemanaEnum[] dias) {
		this.diasSemana = dias;
	}

	@Override
	public boolean chequearCondicion(IVenta venta) {
		int dia;
		DiasSemanaEnum diaFecha = DiasSemanaEnum.DOMINGO;
		boolean condicion = false;
		Calendar fechaVenta = venta.getFecha();
		dia = fechaVenta.get(Calendar.DAY_OF_WEEK);

		switch (dia) {
		case 1:
			diaFecha = DiasSemanaEnum.DOMINGO;
			break;
		case 2:
			diaFecha = DiasSemanaEnum.LUNES;
			break;
		case 3:
			diaFecha = DiasSemanaEnum.MARTES;
			break;
		case 4:
			diaFecha = DiasSemanaEnum.MIERCOLES;
			break;
		case 5:
			diaFecha = DiasSemanaEnum.JUEVES;
			break;
		case 6:
			diaFecha = DiasSemanaEnum.VIERNES;
			break;
		case 7:
			diaFecha = DiasSemanaEnum.SABADO;
			break;
		}

		for (int i = 0; i < diasSemana.length; i++) {
			condicion = condicion || (diaFecha == diasSemana[i]);
		}

		return condicion;
	}

	@Override
	public void aplicarCondicion(IVenta venta) {
	}

}
