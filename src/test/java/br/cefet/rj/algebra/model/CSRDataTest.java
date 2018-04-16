package br.cefet.rj.algebra.model;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CSRDataTest {

	private CSRData data;

	@Before
	public void setUp() throws Exception {
		data = new CSRData(4, 5);
		data.register(0, 1, 1.0);
		data.register(0, 2, 1.0);
		data.register(0, 3, 2.0);
		data.register(0, 4, 12.0);
		data.register(1, 1, 1.0);
		data.register(1, 3, 2.0);
		data.register(1, 4, -1.0);
		data.register(2, 1, 3.0);
		data.register(2, 4, 1.0);
	}

	@Test
	public void a() {
		final CSRData a = data.a();
		assertThat(a.getData(), hasSize(6));
		assertThat(a, equalTo(expectedA()));
	}


	@Test
	public void b() {
		final CSRData b = data.b();
		assertThat(b.getData(), hasSize(3));
		assertThat(b, equalTo(expectedB()));
	}
	
	@Test
	public void print_values() {
		for (int i = 0; i < data.getN(); i++) {
			for (int j = 0; j < data.getM(); j++) {
				System.out.printf("%5.1f", data.read(i, j));
			}
			System.out.println();
		}
	}

	private CSRData expectedA() {
		CSRData a = new CSRData(4, 4);
		a.register(0, 1, 1.0);
		a.register(0, 2, 1.0);
		a.register(0, 3, 2.0);
		a.register(1, 1, 1.0);
		a.register(1, 3, 2.0);
		a.register(2, 1, 3.0);

		return a;
	}

	private CSRData expectedB() {
		CSRData b = new CSRData(4, 1);
		b.register(0, 4, 12.0);
		b.register(1, 4, -1.0);
		b.register(2, 4, 1.0);

		return b;
	}
}