package tests.Modelo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CajaTests.class, LineItemComparatorTests.class,
		OfertaTests.class, ProductosRepositoryTests.class, VentaTests.class,
		VentaYOfertaTests.class, VentasRepositoryTests.class })
public class AllModeloTest {
	// Clase creada por JUnit para ejecutar todos los test simultánemante. 
}
