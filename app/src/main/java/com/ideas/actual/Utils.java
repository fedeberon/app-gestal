package com.ideas.actual;

import com.ideaas.services.domain.Colaborador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {


    public static String getDataColaborador(Colaborador colaborador){
        return "Colaborador: "
                .concat(colaborador.getName())
                .concat(" ")
                .concat(colaborador.getLastName().toUpperCase());
    }


    static SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.getDefault());
    public static String parseDate(Date date){
        return formatter.format(date);
    }

}
