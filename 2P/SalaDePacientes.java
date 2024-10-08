import java.time.LocalTime;

public class Paciente {
    private String cedula;
    private String categoria;
    private String servicioSolicitado;
    private LocalTime horaLlegada;

    public Paciente(String cedula, String categoria, String servicioSolicitado) {
        this.cedula = cedula;
        this.categoria = categoria;
        this.servicioSolicitado = servicioSolicitado;
        this.horaLlegada = LocalTime.now(); // Hora actual cuando se registra el paciente
    }

    public String getCedula() {
        return cedula;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getServicioSolicitado() {
        return servicioSolicitado;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    
    public String toString() {
        return "Paciente [Cedula: " + cedula + ", Categoria: " + categoria + 
               ", Servicio: " + servicioSolicitado + ", Hora Llegada: " + horaLlegada + "]";
    }
}


import java.util.LinkedList;
import java.util.Queue;

public class SalaDePacientes {
    private Queue<Paciente> colaPacientes;

    public SalaDePacientes() {
        colaPacientes = new LinkedList<>();
    }

    public void registrarPaciente(Paciente paciente) {
        colaPacientes.add(paciente);
    }

    public Paciente atenderPaciente() {
        return colaPacientes.poll(); // Atender al primer paciente en la cola
    }

    public boolean hayPacientes() {
        return !colaPacientes.isEmpty();
    }

    public Queue<Paciente> getColaPacientes() {
        return colaPacientes;
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class AtencionEPS extends JFrame {

    private SalaDePacientes sala;
    private JTextArea areaPacientes, areaProximoPaciente;
    private JTextField txtCedula, txtHoraLlegada;
    private JComboBox<String> comboCategoria, comboServicio;
    private JSlider sliderTiempoAtencion;
    private Timer timer;

    public AtencionEPS() {
        sala = new SalaDePacientes();
        timer = new Timer();

        setTitle("Atención al Cliente - EPS");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        JPanel panelRegistro = new JPanel();
        panelRegistro.setLayout(new GridLayout(6, 2));

        panelRegistro.add(new JLabel("Cédula:"));
        txtCedula = new JTextField();
        panelRegistro.add(txtCedula);

        panelRegistro.add(new JLabel("Categoría (Edad):"));
        comboCategoria = new JComboBox<>(new String[]{"Menor de 60 años", "Adulto mayor", "Persona con discapacidad"});
        panelRegistro.add(comboCategoria);

        panelRegistro.add(new JLabel("Servicio Solicitado:"));
        comboServicio = new JComboBox<>(new String[]{"Consulta médica general", "Consulta médica especializada", 
            "Prueba de laboratorio", "Imágenes diagnósticas"});
        panelRegistro.add(comboServicio);

        panelRegistro.add(new JLabel("Hora de Llegada (hh:mm:ss):"));
        txtHoraLlegada = new JTextField();
        txtHoraLlegada.setEditable(false);
        panelRegistro.add(txtHoraLlegada);

        JButton btnRegistrar = new JButton("Registrar");
        panelRegistro.add(btnRegistrar);

        add(panelRegistro, BorderLayout.NORTH);

        
        JPanel panelAtencion = new JPanel();
        panelAtencion.setLayout(new BorderLayout());

        panelAtencion.add(new JLabel("Próxima Atención:"), BorderLayout.NORTH);
        areaProximoPaciente = new JTextArea();
        areaProximoPaciente.setEditable(false);
        panelAtencion.add(new JScrollPane(areaProximoPaciente), BorderLayout.CENTER);

        JButton btnAtender = new JButton("Atender");
        panelAtencion.add(btnAtender, BorderLayout.SOUTH);

        add(panelAtencion, BorderLayout.EAST);

        
        JPanel panelCola = new JPanel();
        panelCola.setLayout(new BorderLayout());

        panelCola.add(new JLabel("Cola de Usuarios (Llegada | Cédula | Servicio):"), BorderLayout.NORTH);
        areaPacientes = new JTextArea();
        areaPacientes.setEditable(false);
        panelCola.add(new JScrollPane(areaPacientes), BorderLayout.CENTER);

        add(panelCola, BorderLayout.CENTER);

        
        JPanel panelSlider = new JPanel();
        panelSlider.setLayout(new BorderLayout());
        panelSlider.add(new JLabel("Velocidad de Atención (segundos):"), BorderLayout.NORTH);

        sliderTiempoAtencion = new JSlider(1, 10, 5); 
        sliderTiempoAtencion.setMajorTickSpacing(1);
        sliderTiempoAtencion.setPaintTicks(true);
        sliderTiempoAtencion.setPaintLabels(true);
        panelSlider.add(sliderTiempoAtencion, BorderLayout.CENTER);

        add(panelSlider, BorderLayout.SOUTH);

        
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPaciente();
            }
        });

        
        btnAtender.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                iniciarAtencion();
            }
        });


 }

    private void registrarPaciente() {
        String cedula = txtCedula.getText();
        String categoria = (String) comboCategoria.getSelectedItem();
        String servicio = (String) comboServicio.getSelectedItem();

        if (cedula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el número de cédula.");
            return;
        }

        Paciente paciente = new Paciente(cedula, categoria, servicio);
        sala.registrarPaciente(paciente);
        txtHoraLlegada.setText(LocalTime.now().toString());
        actualizarAreaPacientes();
        txtCedula.setText(""); 
    }

    private void actualizarAreaPacientes() {
        StringBuilder sb = new StringBuilder();
        for (Paciente paciente : sala.getColaPacientes()) {
            sb.append(paciente.getHoraLlegada()).append(" | ").append(paciente.getCedula())
                .append(" | ").append(paciente.getServicioSolicitado()).append("\n");
        }
        areaPacientes.setText(sb.toString());
    }

    private void iniciarAtencion() {
        if (!sala.hayPacientes()) {
            JOptionPane.showMessageDialog(this, "No hay pacientes en la sala.");
            return;
        }

        int intervalo = sliderTiempoAtencion.getValue() * 1000; // Convertir a milisegundos

        timer.scheduleAtFixedRate(new TimerTask() {
            
            public void run() {
                if (sala.hayPacientes()) {
                    Paciente atendido = sala.atenderPaciente();
                    areaProximoPaciente.setText("Cédula: " + atendido.getCedula() + "\n" +
                            "Categoría: " + atendido.getCategoria() + "\n" +
                            "Servicio: " + atendido.getServicioSolicitado());
                    actualizarAreaPacientes();
                } else {
                    JOptionPane.showMessageDialog(null, "Todos los pacientes han sido atendidos.");
                    timer.cancel();
                }
            }
        }, 0, intervalo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AtencionEPS().setVisible(true);
        });
    }
}





   
