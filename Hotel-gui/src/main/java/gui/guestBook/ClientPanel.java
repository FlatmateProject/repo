package gui.guestBook;

import common.adapter.MouseListenerAdapter;
import common.tableBuilder.TableResult;
import dto.ColumnData;
import exception.IncorrectDataException;
import service.UpdateService;
import service.guessBook.GuestBook;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ClientPanel extends JPanel {

    private JLabel[] clientLabel;

    private JTextField[] clientData;

    private JScrollPane dataTableScrollPane;

    private JScrollPane serviceTableScrollPane;

    private JTable dataTable;

    private JButton searchButton;

    private JButton cleanButton;

    private JButton updateButton;

    private final Color bgColor = new Color(224, 230, 233);

    private MouseListener dataTableMouseListener;

    private MouseListener reservationTableMouseListener;

    private final GuestBook guestBook;

    private final UpdateService updateService;

    private final Specification specification;

    public ClientPanel(GuestBook guestBook, UpdateService updateService, Specification specification) {
        this.guestBook = guestBook;
        this.updateService = updateService;
        this.specification = specification;
        create();
        addEvents();
        createClientTable();
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

    private void addEvents() {
        dataTableMouseListener = new MouseListenerAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int selectedRow = dataTable.getSelectedRow();
                Long clientId = (Long) dataTable.getValueAt(selectedRow, 0);
                Object[] selectedRowData = getSelectedRowData();
                TableResult tableResult = guestBook.createReservationTable(specification.getPrimaryId(), clientId);
                dataTable = createTable(tableResult, dataTableScrollPane, reservationTableMouseListener);
                fillDataFields(selectedRowData);
            }

            private Object[] getSelectedRowData() {
                int columnCount = dataTable.getColumnCount();
                Object[] selectedRowData = new Object[columnCount];
                int selectedRow = dataTable.getSelectedRow();
                for (int i = 0; i < columnCount; i++) {
                    selectedRowData[i] = dataTable.getValueAt(selectedRow, i);
                }
                return selectedRowData;
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
                createTable(tableResult, serviceTableScrollPane, null);
                serviceTableScrollPane.setVisible(true);
            }
        };

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanFields();
                TableResult tableResult = guestBook.createTable(specification.getTable());
                dataTable = createTable(tableResult, dataTableScrollPane, dataTableMouseListener);
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
                String[] labels = getLabels();
                String[] data = getData();
                TableResult tableResult = guestBook.createTable(specification.getTable(), labels, data);
                dataTable = createTable(tableResult, dataTableScrollPane, dataTableMouseListener);

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String[] labels = getLabels();
                    String[] data = getData();
                    updateService.updateClientData(specification.getTable(), labels, data);
                    TableResult tableResult = guestBook.createTable(specification.getTable());
                    dataTable = createTable(tableResult, dataTableScrollPane, dataTableMouseListener);
                } catch (IncorrectDataException e) {
                    JOptionPane.showMessageDialog(null, "Blad aktualizacji! Sprawdz dane!", "UWAGA!", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
    }

    private void createClientTable() {
        TableResult tableResult = this.guestBook.createTable(this.specification.getTable());
        dataTable = createTable(tableResult, dataTableScrollPane, dataTableMouseListener);
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

    @Override
    public void setSize(int width, int height) {
        dataTableScrollPane.setBounds(0, 320, 1200, 150);
        serviceTableScrollPane.setBounds(0, 480, 1200, 150);
        super.setSize(width, height);
    }

    private JTable createTable(TableResult result, JScrollPane scrollPane, MouseListener mouseListener) {
        JTable table = new JTable(result.getRowsData(), result.getColumnNames());
        table.setFillsViewportHeight(true);
        table.addMouseListener(mouseListener);
        scrollPane.setViewportView(table);
        return table;
    }
}
