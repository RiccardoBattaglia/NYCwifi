package it.polito.tdp.nyc.model;

import java.util.Objects;

import org.jgrapht.alg.util.Pair;

public class Arco implements Comparable<Arco> {
	
	Pair<String, String> v;
	double peso;
	
	public Arco(Pair<String, String> v, double d) {
		super();
		this.v = v;
		this.peso = d;
	}

	public Pair<String, String> getV() {
		return v;
	}

	public void setV(Pair<String, String> v) {
		this.v = v;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(peso, v);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arco other = (Arco) obj;
		return Objects.equals(peso, other.peso) && Objects.equals(v, other.v);
	}

	@Override
	public int compareTo(Arco o) {
		// TODO Auto-generated method stub
		return Double.compare(o.peso, this.peso);
	}

	@Override
	public String toString() {
		return "Arco [v=" + v + ", peso=" + peso + "]";
	}
	
	
	
	

}
