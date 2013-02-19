package service.employeeManager;

import dao.EmployeeManagerDao;
import dao.impl.Singleton;
import dictionary.MONTH;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class EmployeeManager {

    private static final Logger log = Logger.getLogger(EmployeeManager.class);

    private Singleton singleton;

    private Calendar calendar;

    private final EmployeeManagerDao employeeManagerDao;

    private MonthSchedule monthSchedule;

    public EmployeeManager(EmployeeManagerDao employeeManagerDao) {
        this.employeeManagerDao = employeeManagerDao;
    }

    public String getPaysReport(int month) {
        String s = "";
        try {
            String resultText = "";
            String stmt = "";
            ResultSet result = singleton.query("SELECT p.idp_pesel, p.imie, p.nazwisko, count(g.nadgodziny)*8 , "
                    + "sum(s.podstawa)*8 podstawa, sum(s.premia)*8 premia FROM grafik g "
                    + "JOIN pracownicy p ON g.idp_pesel=p.idp_pesel JOIN stanowiska s "
                    + "ON p.id_stanowiska=s.id_stanowiska WHERE p.id_stanowiska=1 "
                    + "AND MONTH(data)="
                    + month
                    + " GROUP BY p.idp_pesel,  g.nadgodziny");
            if (result != null) {
                while (result.next()) {
                    s = result.getString(1);
                    if (s.equals(stmt)) {
                        resultText += "  godzin dodatkowych: "
                                + result.getString(4);
                        resultText += " pensja dodatkowa: "
                                + result.getString(6) + "\n";
                    } else {
                        resultText += "\n" + result.getString(2) + " "
                                + result.getString(3) + " " + s + "\n";
                        resultText += "  godzin podstawowych: "
                                + result.getString(4);
                        resultText += " pensja podstawowa: "
                                + result.getString(5) + "\n";
                    }
                    stmt = result.getString(1);
                }
                log.info("siema: " + resultText);
                return resultText;
            } else {
                log.info("Brak danych o zarobkach z danego miesi�ca");
                return "Brak danych o zarobkach z danego miesi�ca";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Wyst�pi� problem z baza danych";
        }
    }

    public String[] getDaySchedule(String date) {
        int j = 0;
        int extra = 0;
        String[] array = null;
        try {
            ResultSet result = singleton
                    .query("SELECT p.imie, p.nazwisko, g.zmiana, g.id_poko, g.id_pokd FROM grafik g "
                            + "LEFT JOIN  pracownicy p ON g.idp_pesel=p.idp_pesel WHERE g.data='"
                            + date + "'");
            log.info("SELECT p.imie, p.nazwisko, g.zmiana, g.id_poko, g.id_pokd FROM grafik g LEFT JOIN  pracownicy p ON g.idp_pesel=p.idp_pesel WHERE g.data='"
                    + date + "'");
            if (result != null) {
                result.last();
                int count = result.getRow();
                result.beforeFirst();
                array = new String[count + 9];
                j = 0;
                int shift = 1;
                // array[j]="Zmiana 1";
                // j++;
                while (result.next()) {
                    if (Integer.valueOf(result.getString(3)) == shift) {
                        array[j++] = " ";
                        array[j] = "Zmiana " + shift;
                        log.info(array[j]);
                        shift++;
                        j++;
                    }
                    array[j] = result.getString(1);
                    if (array[j] != null)
                        array[j] += " " + result.getString(2) + " pokoje od ";
                    else {
                        array[j] = "[" + extra + " nadgodziny] pokoje od ";
                        extra++;
                    }
                    array[j] += result.getString(4) + " do "
                            + result.getString(5);
                    log.info(array[j]);
                    j++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    public String[] updateSchedule(int i, String text, String date, String pesel, String id) {
        String[] array = null;
        try {
            singleton.query("UPDATE grafik SET idp_pesel=" + pesel + " WHERE data='"
                    + date + "' AND idp_pesel=" + id);
            log.info("UPDATE grafik SET idp_pesel=" + pesel + " WHERE data='"
                    + date + "' AND idp_pesel=" + id);
            array[i] = text
                    + array[i].substring(array[i].lastIndexOf("]") + 1,
                    array[i].length());
            log.info(text
                    + array[i].substring(array[i].lastIndexOf("]") + 1,
                    array[i].length()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    // /////////////////////////---------------b
    public boolean createSchedule(MONTH month) {
        try {
            if (!isScheduleExist(month)) {
                monthSchedule.calculateForMonth(month);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isScheduleExist(MONTH month) {
        try {
            ResultSet result = singleton.query("SELECT count(*) from grafik WHERE MONTH(data)=" + month);
            log.info("SELECT count(*) from grafik WHERE MONTH(data)=" + month);
            return result.next() && result.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // //////////////////----------------------------------e


    public boolean addEmployee(String p, String i, String n, String h, String w, String m, String u, String l, String s) {
        int j = 0;
        try {
            int count = 0;
            ResultSet result = singleton
                    .query("SELECT id_stanowiska FROM stanowiska WHERE nazwa='"
                            + s + "'");
            result.next();
            j = result.getInt(1);
            return singleton.update("INSERT INTO pracownicy VALUE(" + p + ",'" + i
                    + "','" + n + "',SHA1('" + h + "'),'" + w + "','" + m
                    + "','" + u + "'," + l + "," + j + ")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String[][] findEmployee(String p, String i, String n, String s) {
        String[][] matrix = null;
        try {
            int count = 0;
            log.info("p: " + p + " i: " + i + " n: " + n + " s: " + s);
            String stmt = "";
            int j = 0;
            if (!p.equals("")) {
                stmt += "p.idp_pesel=" + p;
                j++;
            }
            if (!i.equals("")) {
                if (j > 0)
                    stmt += " AND ";
                stmt += "p.imie='" + i + "'";
                j++;
            }
            if (!n.equals("")) {
                if (j > 0)
                    stmt += " AND ";
                stmt += "p.nazwisko='" + n + "'";
                j++;
            }
            if (!s.equals("")) {
                if (j > 0)
                    stmt += " AND ";
                stmt += "s.nazwa='" + s + "'";
            }
            if (stmt != "")
                stmt = "SELECT p.idp_pesel, p.imie, p.nazwisko, s.nazwa FROM pracownicy p "
                        + "JOIN stanowiska s ON p.id_stanowiska=s.id_stanowiska WHERE "
                        + stmt;
            else
                stmt = "SELECT p.idp_pesel, p.imie, p.nazwisko, s.nazwa FROM pracownicy p "
                        + "JOIN stanowiska s ON p.id_stanowiska=s.id_stanowiska";
            log.info(stmt);
            ResultSet result = singleton.query(stmt);
            if (result != null) {
                result.last();
                count = result.getRow();
                result.beforeFirst();
                matrix = new String[count][4];
                j = 0;
                while (result.next()) {
                    matrix[j][0] = result.getString(1);
                    matrix[j][1] = result.getString(2);
                    matrix[j][2] = result.getString(3);
                    matrix[j][3] = result.getString(4);
                    j++;
                }
            }
            if (j == 0) {
                matrix = new String[1][4];
                matrix[0][0] = "brak danych";
                matrix[0][1] = "";
                matrix[0][2] = "";
                matrix[0][3] = "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.info("lipa z insertemm");
        }
        return matrix;
    }

    public boolean delSchedule(int month) {
        boolean ret = false;
        try {
            ret = singleton.update("DELETE FROM grafik WHERE YEAR(data)="
                    + calendar.get(Calendar.YEAR) + " AND MONTH(data)=" + month);
            log.info("DELETE FROM grafik WHERE YEAR(data)="
                    + calendar.get(Calendar.YEAR) + " AND MONTH(data)=" + month);
            log.info("ret: " + ret);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delEmployee(String p) {
        try {
            log.info("DELETE FROM pracownicy WHERE idp_pesel=" + p);
            return singleton.update("DELETE FROM pracownicy WHERE idp_pesel=" + p);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getRowCount() {
        throw new RuntimeException("should return some count");
//        return count;
    }

    public void setSingleton(Singleton singleton) {
        this.singleton = singleton;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public void setMonthSchedule(MonthSchedule monthSchedule) {
        this.monthSchedule = monthSchedule;
    }
}
