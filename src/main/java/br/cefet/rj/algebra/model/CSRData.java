package br.cefet.rj.algebra.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CSRData {
	private int n;
	private int m;
	private List<CSRContent> data;

	public CSRData(int n, int m) {
		this.n = n;
		this.m = m;
		this.data = new ArrayList<>();
	}

	public void register(int n, int m, double value) {
		data.add(new CSRContent(n, m, value));
	}

	public double read(int i, int j) {
		return data.stream()
				.filter(d -> d.getRowIndex() == i)
				.filter(d -> d.getColIndex() == j)
				.findFirst()
				.map(CSRContent::getValue)
				.orElse(0.0d);
	}

	public CSRData a() {
		CSRData a = new CSRData(n-1, m-1);
		CSRContent lastData = data.get(0);
		for (int i = 1; i < data.size(); i++) {
			CSRContent current = data.get(i);
			if (lastData.getRowIndex() == current.getRowIndex()){
				a.register(lastData.getRowIndex(), lastData.getColIndex(), lastData.getValue());
				lastData = current;
			}else{
				lastData = current;
			}
		}

		return a;
	}

	public CSRData b() {
		CSRData b = new CSRData(n, 1);
		CSRContent lastData = data.get(0);
		int lastElIdx = -1;
		for (int i = 1; i < data.size(); i++) {
			CSRContent current = data.get(i);
			if (lastData.getRowIndex() == current.getRowIndex()){
				lastData = current;
				if ( i+1 == data.size() && lastElIdx == current.getColIndex()){
					b.register(lastData.getRowIndex(), lastData.getColIndex(), lastData.getValue());
				}
			}else{
				b.register(lastData.getRowIndex(), lastData.getColIndex(), lastData.getValue());
				if(lastElIdx == -1) lastElIdx = lastData.getColIndex();
				lastData = current;
			}
		}

		return b;
	}

	public List<CSRContent> getData() {
		return data;
	}

	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}

	// equals e hash

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		CSRData csrData = (CSRData) o;

		for (int i = 0; i < csrData.getData().size(); i++) {
			final boolean equals = this.getData().get(i).equals(csrData.getData().get(i));
			if (!equals) return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
				.append(data)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("data", data)
				.toString();
	}
}
