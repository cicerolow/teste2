package com.senai.apigastsix.repositories;

public enum TipoModel {
    ADMIN("admin"),
    SUPERVISOR("supervisor"),
    OPERADOR("operador");

    private String tipo;

    TipoModel(String tiporecebido){
        this.tipo = tiporecebido;
    }

    public String getRole(){
        return tipo;
    }
}
