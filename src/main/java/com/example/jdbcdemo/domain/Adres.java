package com.example.jdbcdemo.domain;

import java.util.List;

import com.example.jdbcdemo.domain.Prawnik;

public class Adres {

    private Long id;
    private String miejscowosc;
    private String ulica;
    private Integer nr;

    

    private List<Prawnik> uczniowie;

    public Adres(String miejscowosc ,String ulica,int nr) {
    	this.miejscowosc=miejscowosc;
    	this.ulica = ulica;
        this.nr = nr;
        
    }

    public int getNr() {
        return nr;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public List<Prawnik> getUczniowie() {
        return uczniowie;
    }

    public void setUczniowie(List<Prawnik> uczniowie) {
        this.uczniowie = uczniowie;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }
    
    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc =miejscowosc;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "id=" + id +
                ", miejscowosc=" + miejscowosc +
                ", ulica=" + ulica +
                ", nr=" + nr +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

