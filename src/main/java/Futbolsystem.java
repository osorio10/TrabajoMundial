

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.futbolsystem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/**
 *
 * @author ICARIUS
 */

public class Futbolsystem extends JFrame {
 private final JPanel contentPanel;
    private JList<String> menuList;
    private DefaultListModel<String> menuModel;
    private JScrollPane menuScrollPane;
    private JPanel seleccionesPanel;
    private JTextField inputTextoSelecciones;
    private JButton botonBuscarSelecciones;
    private JButton botonCargarCSVSelecciones;
    private JTable tablaDatosSelecciones;
    private JScrollPane tablaScrollPaneSelecciones;
    private DefaultTableModel tablaModelSelecciones;
    private TableRowSorter<DefaultTableModel> rowSorterSelecciones;
    private JPanel resultadosPanel;
    private JTextField inputTextoResultados;
    private JButton botonBuscarResultados;
    private JButton botonCargarCSVResultados;
    private JTable tablaDatosResultados;
    private JScrollPane tablaScrollPaneResultados;
    private DefaultTableModel tablaModelResultados;
    private TableRowSorter<DefaultTableModel> rowSorterResultados;
    private JTextArea auditoriaTextArea;
    private JPanel auditoriaPanel;
    private JPanel seleccionPrincipalPanel;
    private Map<String, Integer> contadorClicks;
    private JPanel resultadoPrincipalPanel;
     private JPanel inicioPanel;
    
    
    public Futbolsystem() {
        // Configuración de la ventana principal
        setTitle("Mi Aplicación");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Creación del panel de contenido
        contentPanel = new JPanel(new BorderLayout());
        setContentPane(contentPanel);

        // Creación del modelo de lista para el menú
        menuModel = new DefaultListModel<>();
        menuModel.addElement("Inicio");
        menuModel.addElement("Selecciones");
        menuModel.addElement("Resultados");
        menuModel.addElement("Selección Principal");
        menuModel.addElement("Resultado General");
        menuModel.addElement("Auditoría");

        // Creación de la lista del menú
        menuList = new JList<>(menuModel);

        // Creación del desplazamiento para el menú
        menuScrollPane = new JScrollPane(menuList);
        menuScrollPane.setPreferredSize(new Dimension(150, getHeight()));

        // Configuración del panel de contenido
        contentPanel.add(menuScrollPane, BorderLayout.WEST);
  
        contadorClicks = new HashMap<>();
for (int i = 0; i < menuModel.size(); i++) {
    contadorClicks.put(menuModel.getElementAt(i), 1);
}
        
        // Listener para los elementos del menú
        menuList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedItem = menuList.getSelectedValue();
                if (selectedItem != null) {
                    System.out.println("Opción seleccionada: " + selectedItem);
                    
                    if (!contadorClicks.containsKey(selectedItem)) {
                        contadorClicks.put(selectedItem, 1);
                    } else {
                        int clicks = contadorClicks.get(selectedItem);
                        contadorClicks.put(selectedItem, clicks + 1);
                    }
                    
                    switch (selectedItem) {
                        case "Inicio":
                            mostrarPanelInicio();
                            ocultarPanelSelecciones();
                            ocultarPanelResultados();
                            ocultarPanelauditoria();
                            ocultarSeleccionPrincipal();
                            ocultarPanelResultadoGeneral();
                            break;
                        case "Selecciones":
                            mostrarPanelSelecciones();
                            ocultarPanelResultados();
                            ocultarPanelauditoria();
                            ocultarPanelInicio();
                            ocultarSeleccionPrincipal();
                            break;
                        case "Resultados":
                            mostrarPanelResultados();
                            ocultarPanelSelecciones();
                            ocultarPanelauditoria();
                            ocultarSeleccionPrincipal();
                            ocultarPanelResultadoGeneral();
                            ocultarPanelInicio();
                            break;
                        case "Auditoría":
                            mostrarPanelAuditoria();
                            ocultarPanelSelecciones();
                            ocultarPanelResultados();
                            ocultarSeleccionPrincipal();
                            ocultarPanelResultadoGeneral();
                            ocultarPanelInicio();
                            break;
                        case "Selección Principal":
                            mostrarPanelSeleccionPrincipal();
                            ocultarPanelSelecciones();
                            ocultarPanelResultados();
                            ocultarPanelauditoria();
                            ocultarPanelResultadoGeneral();
                            ocultarPanelInicio();
                            break;
                        case "Resultado General":
                            crearResultadoPrincipalPanel();
                            ocultarPanelSelecciones();
                            ocultarPanelResultados();
                            ocultarPanelauditoria();
                            ocultarSeleccionPrincipal();
                            ocultarPanelInicio();
                            break;
                        default:
                            ocultarPanelSelecciones();
                            ocultarPanelResultados();
                            ocultarPanelauditoria();
                            ocultarSeleccionPrincipal();
                            ocultarPanelResultadoGeneral();
                            ocultarPanelInicio();
                            break;
                    }
                }
            }
        });

        setVisible(true);
    }
    
private void mostrarPanelInicio() {
    if (inicioPanel == null) {
        inicioPanel = new JPanel(new BorderLayout());

        // Cargar y mostrar la imagen
       ImageIcon imagenInicio = new ImageIcon(getClass().getResource("prueba.png"));
        JLabel labelImagen = new JLabel(imagenInicio);
        inicioPanel.add(labelImagen, BorderLayout.CENTER);

        contentPanel.add(inicioPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    } else {
        inicioPanel.setVisible(true);
    }
}
     private void ocultarPanelInicio() {
        if (inicioPanel != null) {
            inicioPanel.setVisible(false);
        }
    }

    private void mostrarPanelSelecciones() {
        if (seleccionesPanel == null) {
            seleccionesPanel = new JPanel(new BorderLayout());

            // Creación del panel de búsqueda
            JPanel panelBusqueda = new JPanel();
            inputTextoSelecciones = new JTextField(20);
            botonBuscarSelecciones = new JButton("Buscar");
            botonBuscarSelecciones.addActionListener(e -> {
                // Acciones a realizar cuando se presiona el botón de buscar en Selecciones
                String texto = inputTextoSelecciones.getText();
                filtrarTablaSelecciones(texto);
            });
            panelBusqueda.add(inputTextoSelecciones);
            panelBusqueda.add(botonBuscarSelecciones);

            seleccionesPanel.add(panelBusqueda, BorderLayout.NORTH);

            // Creación del botón de carga de CSV
            botonCargarCSVSelecciones = new JButton("Cargar CSV");
            botonCargarCSVSelecciones.addActionListener(e -> {
                try {
                    cargarCSVSelecciones();
                } catch (CsvValidationException ex) {
                    Logger.getLogger(Futbolsystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            seleccionesPanel.add(botonCargarCSVSelecciones, BorderLayout.SOUTH);

            // Creación de la tabla de datos
            tablaModelSelecciones = new DefaultTableModel();
            tablaModelSelecciones.addColumn("ID");
            tablaModelSelecciones.addColumn("Equipo");
            tablaModelSelecciones.addColumn("Continente");
            tablaModelSelecciones.addColumn("Director");
            tablaModelSelecciones.addColumn("Nacionalidad");

            tablaDatosSelecciones = new JTable(tablaModelSelecciones);
            rowSorterSelecciones = new TableRowSorter<>(tablaModelSelecciones);
            tablaDatosSelecciones.setRowSorter(rowSorterSelecciones);

            tablaScrollPaneSelecciones = new JScrollPane(tablaDatosSelecciones);
            seleccionesPanel.add(tablaScrollPaneSelecciones, BorderLayout.CENTER);

            contentPanel.add(seleccionesPanel, BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        } else {
            seleccionesPanel.setVisible(true);
        }
    }

    private void ocultarPanelSelecciones() {
        if (seleccionesPanel != null) {
            seleccionesPanel.setVisible(false);
        }
    }

    private void filtrarTablaSelecciones(String texto) {
        if (texto.isEmpty()) {
            rowSorterSelecciones.setRowFilter(null);
        } else {
            rowSorterSelecciones.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        }
    }

    private void cargarCSVSelecciones() throws CsvValidationException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
        fileChooser.setFileFilter(filter);

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoCSV = fileChooser.getSelectedFile();
            cargarDatosDesdeCSVSelecciones(archivoCSV.getAbsolutePath());
        }
    }

    private void cargarDatosDesdeCSVSelecciones(String archivo) throws CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(archivo))) {
            String[] linea;
            while ((linea = reader.readNext()) != null) {
                tablaModelSelecciones.addRow(linea);
            }
        } catch (IOException e) {
        }
    }

    private void mostrarPanelResultados() {
        if (resultadosPanel == null) {
            resultadosPanel = new JPanel(new BorderLayout());

            // Creación del panel de búsqueda
            JPanel panelBusqueda = new JPanel();
            inputTextoResultados = new JTextField(20);
            botonBuscarResultados = new JButton("Buscar");
            botonBuscarResultados.addActionListener(e -> {
                // Acciones a realizar cuando se presiona el botón de buscar en Resultados
                String texto = inputTextoResultados.getText();
                filtrarTablaResultados(texto);
            });
            panelBusqueda.add(inputTextoResultados);
            panelBusqueda.add(botonBuscarResultados);

            resultadosPanel.add(panelBusqueda, BorderLayout.NORTH);

            // Creación del botón de carga de CSV
            botonCargarCSVResultados = new JButton("Cargar CSV");
            botonCargarCSVResultados.addActionListener(e -> {
                try {
                    cargarCSVResultados();
                } catch (CsvValidationException ex) {
                    Logger.getLogger(Futbolsystem.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            resultadosPanel.add(botonCargarCSVResultados, BorderLayout.SOUTH);

            // Creación de la tabla de datos
            tablaModelResultados = new DefaultTableModel();
            tablaModelResultados.addColumn("Grupo");
            tablaModelResultados.addColumn("Local");
            tablaModelResultados.addColumn("Visitante");
            tablaModelResultados.addColumn("Continente Local");
            tablaModelResultados.addColumn("Continente Visitante");
            tablaModelResultados.addColumn("Goles Local");
            tablaModelResultados.addColumn("Goles Visitante");

            tablaDatosResultados = new JTable(tablaModelResultados);
            rowSorterResultados = new TableRowSorter<>(tablaModelResultados);
            tablaDatosResultados.setRowSorter(rowSorterResultados);

            tablaScrollPaneResultados = new JScrollPane(tablaDatosResultados);
            resultadosPanel.add(tablaScrollPaneResultados, BorderLayout.CENTER);

            contentPanel.add(resultadosPanel, BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        } else {
            resultadosPanel.setVisible(true);
        }
    }

    private void ocultarPanelResultados() {
        if (resultadosPanel != null) {
            resultadosPanel.setVisible(false);
        }
    }

    private void filtrarTablaResultados(String texto) {
        if (texto.isEmpty()) {
            rowSorterResultados.setRowFilter(null);
        } else {
            rowSorterResultados.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        }
    }

    private void cargarCSVResultados() throws CsvValidationException {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
        fileChooser.setFileFilter(filter);

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoCSV = fileChooser.getSelectedFile();
            cargarDatosDesdeCSVResultados(archivoCSV.getAbsolutePath());
        }
    }
    

    

 

    private void cargarDatosDesdeCSVResultados(String archivo) throws CsvValidationException {
        try (CSVReader reader = new CSVReader(new FileReader(archivo))) {
            String[] linea;
            while ((linea = reader.readNext()) != null) {
                tablaModelResultados.addRow(linea);
            }
        } catch (IOException e) {
        }
    }
    
    private void mostrarAuditoria() {
    auditoriaTextArea.setText("Auditoría:\n");
    for (String opcion : contadorClicks.keySet()) {
        int clics = contadorClicks.get(opcion);
        auditoriaTextArea.append(opcion + ": " + clics + " clic(s)\n");
    }
}
    
     
    private void mostrarPanelAuditoria() {
  

    // Crear el panel de auditoría si aún no existe
    if (auditoriaPanel == null) {
        auditoriaPanel = new JPanel(new BorderLayout());

        // Crear el área de texto para la auditoría
        auditoriaTextArea = new JTextArea();
        auditoriaTextArea.setEditable(false);
        JScrollPane auditoriaScrollPane = new JScrollPane(auditoriaTextArea);

        // Agregar el área de texto al panel de auditoría
        auditoriaPanel.add(auditoriaScrollPane, BorderLayout.CENTER);

        // Agregar el panel de auditoría al panel de contenido
        contentPanel.add(auditoriaPanel, BorderLayout.CENTER);

        // Actualizar la auditoría
        mostrarAuditoria();
    } else {
        // Mostrar el panel de auditoría si ya existe
        auditoriaPanel.setVisible(true);
    }
    actualizarAuditoria();
    // Actualizar el contenido del panel
    contentPanel.revalidate();
    contentPanel.repaint();
}
    
        private void ocultarPanelauditoria() {
        if (auditoriaPanel != null) {
            auditoriaPanel.setVisible(false);
        }
    }
        
    private void actualizarAuditoria() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < menuModel.size(); i++) {
        String opcion = menuModel.getElementAt(i);
        int contador = contadorClicks.get(opcion);
        sb.append(opcion).append(": ").append(contador).append(" clics").append("\n");
    }
    auditoriaTextArea.setText(sb.toString());
}
    
    private void mostrarPanelSeleccionPrincipal() {
    if (seleccionPrincipalPanel == null) {
        seleccionPrincipalPanel = new JPanel(new BorderLayout());

        // Crear el área de texto para mostrar los datos
        JTextArea datosTextArea = new JTextArea();
        datosTextArea.setEditable(false);
        datosTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        datosTextArea.setMargin(new Insets(10, 10, 10, 10));

        // Obtener el total de selecciones
        int totalSelecciones = tablaModelSelecciones.getRowCount();

        // Obtener las selecciones por continentes
        Map<String, Integer> seleccionesPorContinente = obtenerAgrupacionContinentes();

        // Obtener las nacionalidades
        Map<String, Integer> nacionalidades = obtenerAgrupacionNacionalidades();

        // Construir el contenido del área de texto
        StringBuilder sb = new StringBuilder();
        sb.append("Total de selecciones: ").append(totalSelecciones).append("\n\n");
        sb.append("Selecciones por continente:\n");
        for (Map.Entry<String, Integer> entry : seleccionesPorContinente.entrySet()) {
            String continente = entry.getKey();
            int cantidad = entry.getValue();
            sb.append(continente).append(": ").append(cantidad).append("\n");
        }
        sb.append("\nNacionalidades:\n");
        for (Map.Entry<String, Integer> entry : nacionalidades.entrySet()) {
            String nacionalidad = entry.getKey();
            int cantidad = entry.getValue();
            sb.append(nacionalidad).append(": ").append(cantidad).append("\n");
        }

        datosTextArea.setText(sb.toString());

        // Agregar el área de texto al panel de selección principal
        JScrollPane scrollPane = new JScrollPane(datosTextArea);
        seleccionPrincipalPanel.add(scrollPane, BorderLayout.CENTER);

        // Establecer bordes y fondo al panel
        seleccionPrincipalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        seleccionPrincipalPanel.setBackground(Color.WHITE);

        contentPanel.add(seleccionPrincipalPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    } else {
        seleccionPrincipalPanel.setVisible(true);
    }
}

private Map<String, Integer> obtenerAgrupacionContinentes() {
    Map<String, Integer> seleccionesPorContinente = new HashMap<>();

    int columnaContinente = tablaModelSelecciones.findColumn("Continente");
    int rowCount = tablaModelSelecciones.getRowCount();

    for (int i = 0; i < rowCount; i++) {
        String continente = tablaModelSelecciones.getValueAt(i, columnaContinente).toString();
        seleccionesPorContinente.put(continente, seleccionesPorContinente.getOrDefault(continente, 0) + 1);
    }

    return seleccionesPorContinente;
}

private Map<String, Integer> obtenerAgrupacionNacionalidades() {
    Map<String, Integer> nacionalidades = new HashMap<>();

    int columnaNacionalidad = tablaModelSelecciones.findColumn("Nacionalidad");
    int rowCount = tablaModelSelecciones.getRowCount();

    for (int i = 0; i < rowCount; i++) {
        String nacionalidad = tablaModelSelecciones.getValueAt(i, columnaNacionalidad).toString();
        nacionalidades.put(nacionalidad, nacionalidades.getOrDefault(nacionalidad, 0) + 1);
    }

    return nacionalidades;
}

  private void ocultarSeleccionPrincipal() {
        if (seleccionPrincipalPanel != null) {
            seleccionPrincipalPanel.setVisible(false);
        }
    }
  
  
  private int obtenerTotalPartidos() {
        return tablaModelResultados.getRowCount();
    }
  
  private double obtenerPromedioGolesPorPartido() {
        int totalPartidos = obtenerTotalPartidos();
        int totalGoles = 0;

        for (int i = 0; i < totalPartidos; i++) {
            int golesLocal = Integer.parseInt(tablaModelResultados.getValueAt(i, 5).toString());
            int golesVisitante = Integer.parseInt(tablaModelResultados.getValueAt(i, 6).toString());
            totalGoles += golesLocal + golesVisitante;
        }

        return totalGoles / (double) totalPartidos;
    }

    private String obtenerPartidoMasGoles() {
        int maxGoles = 0;
        String partidoMasGoles = "";

        int totalPartidos = obtenerTotalPartidos();

        for (int i = 0; i < totalPartidos; i++) {
            int golesLocal = Integer.parseInt(tablaModelResultados.getValueAt(i, 5).toString());
            int golesVisitante = Integer.parseInt(tablaModelResultados.getValueAt(i, 6).toString());
            int totalGoles = golesLocal + golesVisitante;

            if (totalGoles > maxGoles) {
                maxGoles = totalGoles;
                String grupo = tablaModelResultados.getValueAt(i, 0).toString();
                String local = tablaModelResultados.getValueAt(i, 1).toString();
                String visitante = tablaModelResultados.getValueAt(i, 2).toString();
                partidoMasGoles = grupo + ": " + local + " vs " + visitante;
            }
        }

        return partidoMasGoles;
    }

    private String obtenerPartidoMenosGoles() {
        int minGoles = Integer.MAX_VALUE;
        String partidoMenosGoles = "";

        int totalPartidos = obtenerTotalPartidos();

        for (int i = 0; i < totalPartidos; i++) {
            int golesLocal = Integer.parseInt(tablaModelResultados.getValueAt(i, 5).toString());
            int golesVisitante = Integer.parseInt(tablaModelResultados.getValueAt(i, 6).toString());
            int totalGoles = golesLocal + golesVisitante;

            if (totalGoles < minGoles) {
                minGoles = totalGoles;
                String grupo = tablaModelResultados.getValueAt(i, 0).toString();
                String local = tablaModelResultados.getValueAt(i, 1).toString();
                String visitante = tablaModelResultados.getValueAt(i, 2).toString();
                partidoMenosGoles = grupo + ": " + local + " vs " + visitante;
            }
        }

        return partidoMenosGoles;
    }

    private int obtenerNumeroPartidosGanados() {
        int totalPartidos = obtenerTotalPartidos();
        int partidosGanados = 0;

        for (int i = 0; i < totalPartidos; i++) {
            String resultado = tablaModelResultados.getValueAt(i, 4).toString();
            if (resultado.equals("Ganador")) {
                partidosGanados++;
            }
        }

        return partidosGanados;
    }

    private int obtenerNumeroPartidosEmpatados() {
        int totalPartidos = obtenerTotalPartidos();
        int partidosEmpatados = 0;

        for (int i = 0; i < totalPartidos; i++) {
            String resultado = tablaModelResultados.getValueAt(i, 4).toString();
            if (resultado.equals("Empate")) {
                partidosEmpatados++;
            }
        }

        return partidosEmpatados;
    }

    private String obtenerGrupoMasPuntos() {
        Map<String, Integer> puntosGrupos = new HashMap<>();

        int totalPartidos = obtenerTotalPartidos();

        for (int i = 0; i < totalPartidos; i++) {
            String grupo = tablaModelResultados.getValueAt(i, 0).toString();
            int puntos = Integer.parseInt(tablaModelResultados.getValueAt(i, 7).toString());

            puntosGrupos.put(grupo, puntosGrupos.getOrDefault(grupo, 0) + puntos);
        }

        String grupoMasPuntos = "";
        int maxPuntos = 0;

        for (Map.Entry<String, Integer> entry : puntosGrupos.entrySet()) {
            String grupo = entry.getKey();
            int puntos = entry.getValue();

            if (puntos > maxPuntos) {
                maxPuntos = puntos;
                grupoMasPuntos = grupo;
            }
        }

        return grupoMasPuntos;
    }

    private String obtenerGrupoMenosPuntos() {
        Map<String, Integer> puntosGrupos = new HashMap<>();

        int totalPartidos = obtenerTotalPartidos();

        for (int i = 0; i < totalPartidos; i++) {
            String grupo = tablaModelResultados.getValueAt(i, 0).toString();
            int puntos = Integer.parseInt(tablaModelResultados.getValueAt(i, 7).toString());

            puntosGrupos.put(grupo, puntosGrupos.getOrDefault(grupo, 0) + puntos);
        }

        String grupoMenosPuntos = "";
        int minPuntos = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : puntosGrupos.entrySet()) {
            String grupo = entry.getKey();
            int puntos = entry.getValue();

            if (puntos < minPuntos) {
                minPuntos = puntos;
                grupoMenosPuntos = grupo;
            }
        }

        return grupoMenosPuntos;
    }

    private String obtenerPaisMasGoles() {
        Map<String, Integer> golesPaises = new HashMap<>();

        int totalPartidos = obtenerTotalPartidos();

        for (int i = 0; i < totalPartidos; i++) {
            String paisLocal = tablaModelResultados.getValueAt(i, 1).toString();
            String paisVisitante = tablaModelResultados.getValueAt(i, 2).toString();

            int golesLocal = Integer.parseInt(tablaModelResultados.getValueAt(i, 5).toString());
            int golesVisitante = Integer.parseInt(tablaModelResultados.getValueAt(i, 6).toString());

            golesPaises.put(paisLocal, golesPaises.getOrDefault(paisLocal, 0) + golesLocal);
            golesPaises.put(paisVisitante, golesPaises.getOrDefault(paisVisitante, 0) + golesVisitante);
        }

        String paisMasGoles = "";
        int maxGoles = 0;

        for (Map.Entry<String, Integer> entry : golesPaises.entrySet()) {
            String pais = entry.getKey();
            int goles = entry.getValue();

            if (goles > maxGoles) {
                maxGoles = goles;
                paisMasGoles = pais;
            }
        }

        return paisMasGoles;
    }

    private String obtenerPaisMenosGoles() {
        Map<String, Integer> golesPaises = new HashMap<>();

        int totalPartidos = obtenerTotalPartidos();

        for (int i = 0; i < totalPartidos; i++) {
            String paisLocal = tablaModelResultados.getValueAt(i, 1).toString();
            String paisVisitante = tablaModelResultados.getValueAt(i, 2).toString();

            int golesLocal = Integer.parseInt(tablaModelResultados.getValueAt(i, 5).toString());
            int golesVisitante = Integer.parseInt(tablaModelResultados.getValueAt(i, 6).toString());

            golesPaises.put(paisLocal, golesPaises.getOrDefault(paisLocal, 0) + golesLocal);
            golesPaises.put(paisVisitante, golesPaises.getOrDefault(paisVisitante, 0) + golesVisitante);
        }

        String paisMenosGoles = "";
        int minGoles = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : golesPaises.entrySet()) {
            String pais = entry.getKey();
            int goles = entry.getValue();

            if (goles < minGoles) {
                minGoles = goles;
                paisMenosGoles = pais;
            }
        }

        return paisMenosGoles;
    }
    
    
private void crearResultadoPrincipalPanel() {
    if (resultadoPrincipalPanel == null) {
        resultadoPrincipalPanel = new JPanel(new BorderLayout());
System.out.println("entroooo " );
        // Creación del área de texto para mostrar los resultados principales
        JTextArea resultadoPrincipalTextArea = new JTextArea();
        resultadoPrincipalTextArea.setEditable(false);

        JScrollPane resultadoPrincipalScrollPane = new JScrollPane(resultadoPrincipalTextArea);

        resultadoPrincipalPanel.add(resultadoPrincipalScrollPane, BorderLayout.CENTER);

        contentPanel.add(resultadoPrincipalPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    } else {
        resultadoPrincipalPanel.setVisible(true);
    }

    // Generar los resultados principales
    generarResultadosPrincipales2();
}

private void generarResultadosPrincipales2() {
    
    StringBuilder resultadosPrincipales = new StringBuilder();

    // Número de partidos cargados
    resultadosPrincipales.append("Resultados Generales:\n");
    
      resultadosPrincipales.append("=========================\n");
    resultadosPrincipales.append("Número de partidos cargados: ").append(tablaModelResultados.getRowCount()).append("\n");
     
    
        // Promedio de goles por partido
    int totalGoles = 0;
    for (int i = 0; i < tablaModelResultados.getRowCount(); i++) {
      
        var golesEquipoLocal =  tablaModelResultados.getValueAt(i, 5);
        var golesEquipoVisitante =  tablaModelResultados.getValueAt(i, 6);
        totalGoles += Integer.parseInt((String) golesEquipoLocal) + Integer.parseInt((String) golesEquipoVisitante);
    }
    double promedioGoles = (double) totalGoles / tablaModelResultados.getRowCount();
    resultadosPrincipales.append("Promedio de goles por partido: ").append(promedioGoles).append("\n");
    
    
      // Partidos con más goles y menos goles
    int maxGoles = Integer.MIN_VALUE;
    int minGoles = Integer.MAX_VALUE;
    String partidoMasGoles = "";
    String partidoMenosGoles = "";

    for (int i = 0; i < tablaModelResultados.getRowCount(); i++) {
        var golesEquipoLocal =  tablaModelResultados.getValueAt(i, 5);
        var golesEquipoVisitante =  tablaModelResultados.getValueAt(i, 6);
        int totalGolesPartido = Integer.parseInt((String) golesEquipoLocal) + Integer.parseInt((String) golesEquipoVisitante);

        if (totalGolesPartido > maxGoles) {
            maxGoles = totalGolesPartido;
            partidoMasGoles = "Equipo Local: " + tablaModelResultados.getValueAt(i, 0) + ", Equipo Visitante: " + tablaModelResultados.getValueAt(i, 3) + ", Goles: " + totalGolesPartido;
        }

        if (totalGolesPartido < minGoles) {
            minGoles = totalGolesPartido;
            partidoMenosGoles = "Equipo Local: " + tablaModelResultados.getValueAt(i, 0) + ", Equipo Visitante: " + tablaModelResultados.getValueAt(i, 3) + ", Goles: " + totalGolesPartido;
        }
    }
    
    resultadosPrincipales.append("Partido con más goles: ").append(partidoMasGoles).append("\n");
    resultadosPrincipales.append("Partido con menos goles: ").append(partidoMenosGoles).append("\n");
    
    
    // Número de partidos con ganador y empate
    int partidosGanados = 0;
    int partidosEmpate = 0;

    for (int i = 0; i < tablaModelResultados.getRowCount(); i++) {
            var golesEquipoLocal =  tablaModelResultados.getValueAt(i, 5);
        var golesEquipoVisitante =  tablaModelResultados.getValueAt(i, 6);

        if (Integer.parseInt((String) golesEquipoLocal) > Integer.parseInt((String) golesEquipoVisitante)) {
            partidosGanados++;
        } else if (Integer.parseInt((String) golesEquipoLocal) == Integer.parseInt((String) golesEquipoVisitante)) {
            partidosEmpate++;
        }
    }

    resultadosPrincipales.append("Número de partidos con ganador: ").append(partidosGanados).append("\n");
    resultadosPrincipales.append("Número de partidos con empate: ").append(partidosEmpate).append("\n");
    
    
    // Grupo con más puntos y menos puntos
    Map<String, Integer> puntosPorGrupo = new HashMap<>();

    for (int i = 0; i < tablaModelResultados.getRowCount(); i++) {
        String grupo = (String) tablaModelResultados.getValueAt(i, 0);
         var golesEquipoLocal =  tablaModelResultados.getValueAt(i, 5);
        var golesEquipoVisitante =  tablaModelResultados.getValueAt(i, 6);

        int puntos = 0;
        if (Integer.parseInt((String) golesEquipoLocal) > Integer.parseInt((String) golesEquipoVisitante)) {
            puntos = 3;
        } else if (Integer.parseInt((String) golesEquipoLocal) == Integer.parseInt((String) golesEquipoVisitante)) {
            puntos = 1;
        }

        puntosPorGrupo.put(grupo, puntosPorGrupo.getOrDefault(grupo, 0) + puntos);
    }

    String grupoMasPuntos = "";
    String grupoMenosPuntos = "";
    int maxPuntos = Integer.MIN_VALUE;
    int minPuntos = Integer.MAX_VALUE;

    for (Map.Entry<String, Integer> entry : puntosPorGrupo.entrySet()) {
        String grupo = entry.getKey();
        int puntos = entry.getValue();

        if (puntos > maxPuntos) {
            maxPuntos = puntos;
            grupoMasPuntos = grupo;
        }

        if (puntos < minPuntos) {
            minPuntos = puntos;
            grupoMenosPuntos = grupo;
        }
    }

    resultadosPrincipales.append("Grupo con más puntos: ").append(grupoMasPuntos).append(", Puntos: ").append(maxPuntos).append("\n");
    resultadosPrincipales.append("Grupo con menos puntos: ").append(grupoMenosPuntos).append(", Puntos: ").append(minPuntos).append("\n");

    
    
    // Países con más goles y menos goles
    Map<String, Integer> golesPorPais = new HashMap<>();

    for (int i = 0; i < tablaModelResultados.getRowCount(); i++) {
        String equipoLocal = (String) tablaModelResultados.getValueAt(i, 3);
        String equipoVisitante = (String) tablaModelResultados.getValueAt(i, 4);
        var golesEquipoLocal =  tablaModelResultados.getValueAt(i, 5);
        var golesEquipoVisitante =  tablaModelResultados.getValueAt(i, 6);


        golesPorPais.put(equipoLocal, golesPorPais.getOrDefault(equipoLocal, 0) + Integer.valueOf((String) golesEquipoLocal));
        golesPorPais.put(equipoVisitante, golesPorPais.getOrDefault(equipoVisitante, 0) + Integer.valueOf((String) golesEquipoVisitante));
    }

    String paisMasGoles = "";
    String paisMenosGoles = "";
    int maxGolesPais = Integer.MIN_VALUE;
    int minGolesPais = Integer.MAX_VALUE;

    for (Map.Entry<String, Integer> entry : golesPorPais.entrySet()) {
        String pais = entry.getKey();
        int goles = entry.getValue();

        if (goles > maxGolesPais) {
            maxGolesPais = goles;
            paisMasGoles = pais;
        }

        if (goles < minGolesPais) {
            minGolesPais = goles;
            paisMenosGoles = pais;
        }
    }

    resultadosPrincipales.append("País con más goles: ").append(paisMasGoles).append(", Goles: ").append(maxGolesPais).append("\n");
    resultadosPrincipales.append("País con menos goles: ").append(paisMenosGoles).append(", Goles: ").append(minGolesPais).append("\n");

    
    JTextArea resultadoPrincipalTextArea = (JTextArea) ((JScrollPane) resultadoPrincipalPanel.getComponent(0)).getViewport().getView();
    resultadoPrincipalTextArea.setText(resultadosPrincipales.toString());
}

private void generarResultadosPrincipales() {
    
    StringBuilder resultadosPrincipales = new StringBuilder();

    // Número de partidos cargados
    resultadosPrincipales.append("Resultados Generales:\n");
    
    
    resultadosPrincipales.append("=========================\n");
    resultadosPrincipales.append("Número de partidos cargados: ").append(tablaModelResultados.getRowCount()).append("\n");

    // Promedio de goles por partido
    int totalGoles = 0;
    for (int i = 0; i < tablaModelResultados.getRowCount(); i++) {
        int golesEquipoLocal = (int) tablaModelResultados.getValueAt(i, 1);
        int golesEquipoVisitante = (int) tablaModelResultados.getValueAt(i, 2);
        totalGoles += golesEquipoLocal + golesEquipoVisitante;
    }
    double promedioGoles = (double) totalGoles / tablaModelResultados.getRowCount();
    resultadosPrincipales.append("Promedio de goles por partido: ").append(promedioGoles).append("\n");

    // Partidos con más goles y menos goles
    int maxGoles = Integer.MIN_VALUE;
    int minGoles = Integer.MAX_VALUE;
    String partidoMasGoles = "";
    String partidoMenosGoles = "";

    for (int i = 0; i < tablaModelResultados.getRowCount(); i++) {
        int golesEquipoLocal = (int) tablaModelResultados.getValueAt(i, 1);
        int golesEquipoVisitante = (int) tablaModelResultados.getValueAt(i, 2);
        int totalGolesPartido = golesEquipoLocal + golesEquipoVisitante;

        if (totalGolesPartido > maxGoles) {
            maxGoles = totalGolesPartido;
            partidoMasGoles = "Equipo Local: " + tablaModelResultados.getValueAt(i, 0) + ", Equipo Visitante: " + tablaModelResultados.getValueAt(i, 3) + ", Goles: " + totalGolesPartido;
        }

        if (totalGolesPartido < minGoles) {
            minGoles = totalGolesPartido;
            partidoMenosGoles = "Equipo Local: " + tablaModelResultados.getValueAt(i, 0) + ", Equipo Visitante: " + tablaModelResultados.getValueAt(i, 3) + ", Goles: " + totalGolesPartido;
        }
    }

    resultadosPrincipales.append("Partido con más goles: ").append(partidoMasGoles).append("\n");
    resultadosPrincipales.append("Partido con menos goles: ").append(partidoMenosGoles).append("\n");

    // Número de partidos con ganador y empate
    int partidosGanados = 0;
    int partidosEmpate = 0;

    for (int i = 0; i < tablaModelResultados.getRowCount(); i++) {
        int golesEquipoLocal = (int) tablaModelResultados.getValueAt(i, 1);
        int golesEquipoVisitante = (int) tablaModelResultados.getValueAt(i, 2);

        if (golesEquipoLocal > golesEquipoVisitante) {
            partidosGanados++;
        } else if (golesEquipoLocal == golesEquipoVisitante) {
            partidosEmpate++;
        }
    }

    resultadosPrincipales.append("Número de partidos con ganador: ").append(partidosGanados).append("\n");
    resultadosPrincipales.append("Número de partidos con empate: ").append(partidosEmpate).append("\n");

    // Grupo con más puntos y menos puntos
    Map<String, Integer> puntosPorGrupo = new HashMap<>();

    for (int i = 0; i < tablaModelResultados.getRowCount(); i++) {
        String grupo = (String) tablaModelResultados.getValueAt(i, 4);
        int golesEquipoLocal = (int) tablaModelResultados.getValueAt(i, 1);
        int golesEquipoVisitante = (int) tablaModelResultados.getValueAt(i, 2);

        int puntos = 0;
        if (golesEquipoLocal > golesEquipoVisitante) {
            puntos = 3;
        } else if (golesEquipoLocal == golesEquipoVisitante) {
            puntos = 1;
        }

        puntosPorGrupo.put(grupo, puntosPorGrupo.getOrDefault(grupo, 0) + puntos);
    }

    String grupoMasPuntos = "";
    String grupoMenosPuntos = "";
    int maxPuntos = Integer.MIN_VALUE;
    int minPuntos = Integer.MAX_VALUE;

    for (Map.Entry<String, Integer> entry : puntosPorGrupo.entrySet()) {
        String grupo = entry.getKey();
        int puntos = entry.getValue();

        if (puntos > maxPuntos) {
            maxPuntos = puntos;
            grupoMasPuntos = grupo;
        }

        if (puntos < minPuntos) {
            minPuntos = puntos;
            grupoMenosPuntos = grupo;
        }
    }

    resultadosPrincipales.append("Grupo con más puntos: ").append(grupoMasPuntos).append(", Puntos: ").append(maxPuntos).append("\n");
    resultadosPrincipales.append("Grupo con menos puntos: ").append(grupoMenosPuntos).append(", Puntos: ").append(minPuntos).append("\n");

    // Países con más goles y menos goles
    Map<String, Integer> golesPorPais = new HashMap<>();

    for (int i = 0; i < tablaModelResultados.getRowCount(); i++) {
        String equipoLocal = (String) tablaModelResultados.getValueAt(i, 0);
        String equipoVisitante = (String) tablaModelResultados.getValueAt(i, 3);
        int golesEquipoLocal = (int) tablaModelResultados.getValueAt(i, 1);
        int golesEquipoVisitante = (int) tablaModelResultados.getValueAt(i, 2);

        golesPorPais.put(equipoLocal, golesPorPais.getOrDefault(equipoLocal, 0) + golesEquipoLocal);
        golesPorPais.put(equipoVisitante, golesPorPais.getOrDefault(equipoVisitante, 0) + golesEquipoVisitante);
    }

    String paisMasGoles = "";
    String paisMenosGoles = "";
    int maxGolesPais = Integer.MIN_VALUE;
    int minGolesPais = Integer.MAX_VALUE;

    for (Map.Entry<String, Integer> entry : golesPorPais.entrySet()) {
        String pais = entry.getKey();
        int goles = entry.getValue();

        if (goles > maxGolesPais) {
            maxGolesPais = goles;
            paisMasGoles = pais;
        }

        if (goles < minGolesPais) {
            minGolesPais = goles;
            paisMenosGoles = pais;
        }
    }

    resultadosPrincipales.append("País con más goles: ").append(paisMasGoles).append(", Goles: ").append(maxGolesPais).append("\n");
    resultadosPrincipales.append("País con menos goles: ").append(paisMenosGoles).append(", Goles: ").append(minGolesPais).append("\n");

    // Mostrar los resultados principales en el área de texto
    JTextArea resultadoPrincipalTextArea = (JTextArea) ((JScrollPane) resultadoPrincipalPanel.getComponent(0)).getViewport().getView();
    resultadoPrincipalTextArea.setText(resultadosPrincipales.toString());
}
     private void ocultarPanelResultadoGeneral() {
        if (resultadoPrincipalPanel != null) {
            resultadoPrincipalPanel.setVisible(false);
        }
    }    


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            new Futbolsystem().setVisible(true);
        });
    }
}