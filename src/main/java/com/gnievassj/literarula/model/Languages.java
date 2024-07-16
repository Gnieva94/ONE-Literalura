package com.gnievassj.literarula.model;

public enum Languages {
    EN("Ingles","en"),
    ES("Español","es");
    private final String languageEspanol;
    private final String languageData;
    Languages(String languageEspanol,String languageData){
        this.languageEspanol = languageEspanol;
        this.languageData = languageData;
    }
    public static Languages fromEspanol(String text){
        for(Languages languages : Languages.values()){
            if(languages.languageEspanol.equalsIgnoreCase(text)){
                return languages;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: "+text);
    }
    public static Languages fromData(String text){
        for(Languages languages : Languages.values()){
            if(languages.languageData.equalsIgnoreCase(text)){
                return languages;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: "+text);
    }
}
