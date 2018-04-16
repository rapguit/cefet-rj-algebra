package br.cefet.rj.algebra.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CSRContent {
	private double value;
	private int colIndex; //i
	private int rowIndex; //j

	public CSRContent() {
	}

	public CSRContent(int rowIndex, int colIndex, double value) {
		this.value = value;
		this.colIndex = colIndex;
		this.rowIndex = rowIndex;
	}

	//GET SET

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getColIndex() {
		return colIndex;
	}

	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	// equals e hash


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		CSRContent that = (CSRContent) o;

		return new EqualsBuilder()
				.append(value, that.value)
				.append(colIndex, that.colIndex)
				.append(rowIndex, that.rowIndex)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(value)
				.append(colIndex)
				.append(rowIndex)
				.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("value", value)
				.append("colIndex", colIndex)
				.append("rowIndex", rowIndex)
				.toString();
	}
}
