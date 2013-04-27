package gui;

import dictionary.MONTH;
import service.Schedule;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class SchedulerPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private int d, m, y;
    private final JButton[] schDayButton = new JButton[31];
    private JButton schNext, schPrev;
    private final JLabel[] schDayLabel = new JLabel[7];
    private final JLabel schMonthLabel = new JLabel();
    private JScrollPane schScrollPane;
    private JTable schTable;
    private final Color callendarColor = new Color(255, 255, 255);
    private final Color bgColor = new Color(224, 230, 233);
    private final Border border = BorderFactory.createLineBorder(new Color(60, 124,
            142));

    private final String[] schDow = {"Pn", "Wt", "�r", "Cz", "Pt", "So", "Nd"};

    private final Schedule schedule;

    private final Calendar calendar;

    public SchedulerPanel(Calendar calendar, Schedule schedule) {
        this.calendar = calendar;
        this.schedule = schedule;
        create();
        addEvents();
    }

    public void create() {

        d = calendar.get(Calendar.DAY_OF_MONTH);
        m = calendar.get(Calendar.MONTH);
        y = calendar.get(Calendar.YEAR);

        setBackground(bgColor);
        setBounds(0, 0, getWidth(), getHeight());
        setLayout(new GridBagLayout());

        for (int i = 0; i < schDayButton.length; i++) {
            schDayButton[i] = new JButton(String.valueOf(i + 1));
            schDayButton[i].setBackground(callendarColor);
            schDayButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JButton tmp = (JButton) arg0.getSource();
                    schTable = schedule.getTable(Integer.valueOf(tmp.getText()),
                            m + 1, y);
                    schScrollPane.setViewportView(schTable);
                }
            });
            add(schDayButton[i]);
        }
        for (int i = 0; i < schDayLabel.length; i++) {
            schDayLabel[i] = new JLabel(schDow[i]);
            add(schDayLabel[i]);
        }
        add(schMonthLabel);
        schNext = new JButton("Nast�pny");
        schPrev = new JButton("Poprzedni");
        add(schPrev);
        add(schNext);

        schTable = schedule.getTable(0, m + 1, y);
        schScrollPane = new JScrollPane(schTable);
        schScrollPane.setBorder(border);
        add(schScrollPane);

        setVisible(true);
    }

    public void schInitCalendar(int day, int month, int year) {
        int tmp, dayTmp, dX, h = 0;

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        dX = getWidth() / 2 - 182;
        h = 70;
        dayTmp = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        tmp = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        dX += tmp * 51;
        for (int i = 0, k = 51; i < 31; i++, dX += k, tmp++) {
            if (i != 0 && tmp % 7 == 0) {
                h += 31;
                dX = getWidth() / 2 - 182;
            }
            schDayButton[i].setBounds(dX, h, 50, 30);
            if (i < MONTH.getDayOfMonth(month)) {
                schDayButton[i].setVisible(true);
            } else {
                schDayButton[i].setVisible(false);
            }
        }
        dX = getWidth() / 2 - 175;
        for (int i = 0; i < schDayLabel.length; i++, dX += 51) {
            schDayLabel[i].setBounds(dX + 10, schDayButton[0].getY() - 18, 50,
                    18);
        }
        schMonthLabel.setText(MONTH.getMonthName(month) + " " + year);
        schMonthLabel.setHorizontalAlignment(SwingConstants.CENTER);
        schMonthLabel.setBounds((getWidth() - 100) / 2,
                schDayLabel[0].getY() - 30, 100, 20);
        dX = getWidth() / 2 - 75;
        schPrev.setBounds(schDayLabel[0].getX() - 18, schMonthLabel.getY(),
                100, 20);
        schNext.setBounds(schDayLabel[6].getX() - 68, schMonthLabel.getY(),
                100, 20);
        calendar.set(Calendar.DAY_OF_MONTH, dayTmp);
        schDayButton[day - 1].requestFocus();

        schScrollPane.setBounds(20, 300, getWidth() - 50, getHeight() - 400);
    }

    private void addEvents() {
        schPrev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (--m < 0) {
                    m = 11;
                    y--;
                }
                schInitCalendar(d, m, y);
                schTable = schedule.getTable(0, m + 1, y);
                schScrollPane.setViewportView(schTable);
            }
        });
        schNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (++m > 11) {
                    m = 0;
                    y++;
                }
                schInitCalendar(d, m, y);
                schTable = schedule.getTable(0, m + 1, y);
                schScrollPane.setViewportView(schTable);
            }
        });
    }

    @Override
    public void setSize(int width, int height) {
        schInitCalendar(d, m, y);
        super.setSize(width, height);
    }

}
