package com.ideas.actual;

import com.ideaas.services.domain.Colaborador;

public class Utils {


    public static String getDataColaborador(Colaborador colaborador){
        return "Colaborador: ".concat(colaborador.getName().toUpperCase())
                .concat(" ")
                .concat(". Rol: ")
                .concat(colaborador.getRol().getName().toUpperCase());
    }
}
