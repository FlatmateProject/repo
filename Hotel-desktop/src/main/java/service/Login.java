package service;

import dao.impl.Singleton;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    private static final Logger log = Logger.getLogger(Login.class);

    private final Singleton sing = Singleton.getInstance();

    public Boolean check(String login, char[] pass) {
        log.info(login + " " + String.valueOf(pass));
        try {
            ResultSet rset = sing.query("select haslo from pracownicy where idp_pesel = \"" + login + "\"");
            rset.next();
            return String.valueOf(pass).equals(rset.getString(1));
        } catch (SQLException e) {
            return false;
        }
    }

}