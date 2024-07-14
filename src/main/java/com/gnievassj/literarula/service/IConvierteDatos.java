package com.gnievassj.literarula.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
