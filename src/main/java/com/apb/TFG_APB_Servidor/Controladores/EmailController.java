package com.apb.TFG_APB_Servidor.Controladores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que valida los emails
 */
public class EmailController {
    private Pattern patron;
    private Matcher matcher;

    private static final String PATRON_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailController() {
        patron = Pattern.compile(PATRON_EMAIL);
    }

    public boolean validar(final String email) {
        matcher = patron.matcher(email);
        return matcher.matches();
    }

}
