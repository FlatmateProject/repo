package gui.guestBook;

import common.adapter.MouseListenerAdapter;
import common.tableBuilder.TableResult;
import dto.ColumnData;
import dto.guestBook.ReservationData;
import exception.IncorrectDataException;
import service.GuestBook;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ClientPanel extends JPanel {

    private final GuestBook guestBook;

    private final Specification specification;

    private JLabel[] clientLabel;

    private JTextField[] clientData;

    private JScrollPane dataTableScrollPane;

    private JScrollPane serviceTableScrollPane;

    private JTable serviceTable;

    private JTable dataTable;

    private JButton searchButton;

    private JButton cleanButton;

    private JButton updateButton;


    private final Color bgColor = new Color(224, 230, 233);

    private MouseListener dataTableMouseListener;

    private MouseListener reservationTableMouseListener;

    public ClientPanel(GuestBook guestBook, Specification specification) {
        this.guestBook = guestBook;
        this.specification = specification;
        create();
        addEvents();
        fillDataTable();
    }

    private void create() {
        Border border = BorderFactory.createLineBorder(new Color(60, 124, 142));

        setBounds(0, 20, 1200, 650);
        setBackground(bgColor);
        setLayout(null);

        List<ColumnData> columns = guestBook.getLabels(specification.getTable());
        Form clientForm = Factory.createClientForm(this, columns);
        clientLabel = clientForm.getClientLabels();
        clientData = clientForm.getClientData();
        JButton[] buttons = Factory.createButtons(this);
        searchButton = buttons[0];
        updateButton = buttons[1];
        cleanButton = buttons[2];

        dataTableScrollPane = new JScrollPane();
        dataTableScrollPane.setBorder(border);
        dataTableScrollPane.setBounds(specification.geDataTableBounds());
        add(dataTableScrollPane);

        serviceTableScrollPane = new JScrollPane();
        serviceTableScrollPane.setBounds(specification.getServiceTableBounds());
        serviceTableScrollPane.setBorder(border);
        serviceTableScrollPane.setVisible(false);
        add(serviceTableScrollPane);

        setVisible(true);
    }


    private void fillDataTable(String conditions) {
        dataTable = createTable(guestBook.createTable(specification.getTable(), conditions, specification.getClientDtoClass()));
        dataTable.addMouseListener(dataTableMouseListener);
        dataTableScrollPane.setViewportView(dataTable);
    }

    private void fillDataTable() {
        fillDataTable("");
    }

    private void addEvents() {
        dataTableMouseListener = new MouseListenerAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int selectedRow = dataTable.getSelectedRow();
                Long clientId = (Long) dataTable.getValueAt(selectedRow, 0);
                Object[] selectedRowData = getSelectedRowData();
                String conditions = "where " + specification.getPrimaryId() + "=" + clientId;
                TableResult tableResult = guestBook.createTable("rezerwacje", conditions, ReservationData.class);
                dataTable = createTable(tableResult);
                dataTable.addMouseListener(reservationTableMouseListener);
                dataTableScrollPane.setViewportView(dataTable);
                fillDataFields(selectedRowData);
            }

            private Object[] getSelectedRowData() {
                int columnCount = dataTable.getColumnCount();
                Object[] getSelectedRowData = new Object[columnCount];
                int selectedRow = dataTable.getSelectedRow();
                for (int i = 0; i < columnCount; i++) {
                    getSelectedRowData[i] = dataTable.getValueAt(selectedRow, i);
                }
                return getSelectedRowData;
            }

            private void fillDataFields(Object[] rowData) {
                for (int i = 0; i < clientData.length; i++) {
                    Object cell = rowData[i];
                    clientData[i].setText(cell.toString());
                }
            }
        };

        reservationTableMouseListener = new MouseListenerAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int selectedRow = dataTable.getSelectedRow();
                Long idRez = (Long) dataTable.getValueAt(selectedRow, 0);
                TableResult tableResult = guestBook.createRecreationTable(idRez);
                serviceTable = createTable(tableResult);
                serviceTableScrollPane.setViewportView(serviceTable);
                serviceTableScrollPane.setVisible(true);
            }
        };

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillDataTable();
                cleanFields();
                serviceTableScrollPane.setVisible(false);
            }

            private void cleanFields() {
                for (JTextField aClientData : clientData) {
                    aClientData.setText("");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String conditions = createConditions();
                if (!conditions.isEmpty()) {
                    conditions = " where " + conditions;
                    fillDataTable(conditions);
                    serviceTableScrollPane.setVisible(false);
                }
            }

            private String createConditions() {
                String conditions = "";
                for (int i = 0; i < clientData.length; i++) {
                    if (!isNotClientDataEmpty(i)) {
                        if (!conditions.isEmpty()) {
                            conditions = conditions + " and ";
                        }
                        conditions = conditions + addCondition(i);
                    }
                }
                return conditions;
            }

            private String addCondition(int i) {
                return String.format("%s=\"%s\"", clientLabel[i].getText(), clientData[i].getText());
            }

            private boolean isNotClientDataEmpty(int i) {
                return clientData[i].getText().isEmpty();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String[] labels = getLabels();
                    String[] data = getData();
                    guestBook.updateClientData(labels, data);
                    fillDataTable();
                } catch (IncorrectDataException e) {
                    JOptionPane.showMessageDialog(null, "Blad aktualizacji! Sprawdz dane!", "UWAGA!", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }

            private String[] getLabels() {
                int length = clientLabel.length;
                String[] labels = new String[length];
                for (int i = 0; i < length; i++) {
                    labels[i] = clientLabel[i].getText();
                }
                return labels;
            }

            private String[] getData() {
                int length = clientData.length;
                String data[] = new String[length];
                for (int i = 0; i < length; i++) {
                    data[i] = clientData[i].getText();
                }
                return data;
            }
        });
    }

    @Override
    public void setSize(int width, int height) {
        dataTableScrollPane.setBounds(0, 320, 1200, 150);
        serviceTableScrollPane.setBounds(0, 480, 1200, 150);
        super.setSize(width, height);
    }

    private JTable createTable(TableResult result) {
        JTable table = new JTable(result.getRowsData(), result.getColumnNames());
        table.setFillsViewportHeight(true);
        return table;
    }
}
