package com.ideas.actual;

import com.ideaas.services.domain.Colaborador;

public class Utils {


    public static String getDataColaborador(Colaborador colaborador){
        return "Colaborador: "
                .concat(colaborador.getName())
                .concat(" ")
                .concat(colaborador.getLastName().toUpperCase());
    }
}
