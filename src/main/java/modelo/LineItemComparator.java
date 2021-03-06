package modelo;

import java.util.Comparator;

public class LineItemComparator implements Comparator<LineItem> {

	@Override
	public int compare(LineItem li1, LineItem li2) {
		if (li1.getNumeroLinea() < li2.getNumeroLinea())
			return -1;
		if (li1.getNumeroLinea() == li2.getNumeroLinea())
			return 0;
		else
			return 1;
	}

}
