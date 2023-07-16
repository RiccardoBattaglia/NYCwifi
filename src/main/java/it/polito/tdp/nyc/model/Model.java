package it.polito.tdp.nyc.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.nyc.db.NYCDao;


public class Model {
	

	
	private NYCDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	private Map<String, List<String>> ntaMap;
	private List<Hotspot> hotspotList;
	
	public Model() {
		
		this.dao = new NYCDao();
		
		this.hotspotList = this.dao.getAllHotspot();
		
//		this.retailersMap=new HashMap<>();
//		for(Retailers i : retailersList) {
//			this.retailersMap.put((i), this.dao.getProdottiPerRetailerInAnno(i, anno));
//		}
		
	}
	

	public void creaGrafo(String borgo) {
		// TODO Auto-generated method stub

	this.grafo = new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
	// Aggiunta VERTICI 
	List<String> vertici=new LinkedList<>();
	
	for(Hotspot i : this.hotspotList) {
		if(i.getBorough().equals(borgo)) {
			vertici.add(i.getNTACode());
		}
	}
	
	Graphs.addAllVertices(this.grafo, vertici);
	
	
	this.ntaMap=new HashMap<>();
	for(String i : this.grafo.vertexSet()) {
		this.ntaMap.put(i, this.dao.trovaSSID(borgo, i));
	}

	
	// Aggiunta ARCHI
	
	for (String v1 : vertici) {
		for (String v2 : vertici) {
			if(!v1.equals(v2) && this.comuni(v1, v2)!=0){ 

		      this.grafo.addEdge(v1,v2);
		      this.grafo.setEdgeWeight(this.grafo.getEdge(v1, v2), this.comuni(v1, v2));
			}
			}
			}

	}

public int nVertici() {
	return this.grafo.vertexSet().size();
}

public int nArchi() {
	return this.grafo.edgeSet().size();
}

public Set<String> getVertici(){
	
	Set<String> vertici=this.grafo.vertexSet();
	
	return vertici;
}

public Set<DefaultWeightedEdge> getArchi(){
	
	Set<DefaultWeightedEdge> archi=this.grafo.edgeSet();
	
	return archi;
}

//public List<Set<User>> getComponente() {
//	ConnectivityInspector<User, DefaultWeightedEdge> ci = new ConnectivityInspector<>(this.grafo) ;
//	return ci.connectedSets() ;
//}

//public Set<Retailers> getComponente(Retailers v) {
//	ConnectivityInspector<Retailers, DefaultWeightedEdge> ci = new ConnectivityInspector<>(this.grafo) ;
//	return ci.connectedSetOf(v) ;
//}

public List<String> getBorghi(){
	
	List<String> nomi=new LinkedList<>();
	
	for(Hotspot i : hotspotList) {
		if(!nomi.contains(i.getBorough())) {
		nomi.add(i.getBorough());
		}
	}
	
	Collections.sort(nomi);
	
	return nomi;
}

public int comuni(String v1, String v2) {
	
	List<String> comuni = new LinkedList<>();
	comuni.addAll(this.ntaMap.get(v1));
	
	for (String n : this.ntaMap.get(v2)) {
		if(!comuni.contains(n)) {
			comuni.add(n);
		}
			}
	return comuni.size();
	}

public List<Arco> popolaArchi() {
	
	List<Arco> archi = new LinkedList<>();
	
	for(DefaultWeightedEdge e : this.getArchi()) {
		archi.add(new Arco (new Pair<String, String>(this.grafo.getEdgeSource(e), this.grafo.getEdgeTarget(e)), this.grafo.getEdgeWeight(e)));
	}
	
	Collections.sort(archi);
	
	System.out.println(archi.toString());
	
	return archi;
}


}
	
