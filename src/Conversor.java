import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JDesktopPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Conversor {

	private JFrame frame;
	private JTextField txt;
	private JButton btn;
	private JComboBox<Moneda> cmb;
	private JLabel lbl;
	
	public enum Moneda{
		//PesoChileno
		Pesos_a_Dolar,
		Pesos_a_Euro,
		Dolar_a_Pesos,
		Euro_a_Pesos,

	}
	
	public double dolar = 869.57;
	public double euro = 869.57;
	
	public double valorInput = 0.00;
	private JLabel lblNewLabel;
	private JLabel lblIngreseCantidad;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversor window = new Conversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Conversor() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(155, 255, 255));
		desktopPane.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(desktopPane);
		
		cmb = new JComboBox<Moneda>();
		cmb.setBackground(new Color(204, 255, 255));
		cmb.setToolTipText("");
		cmb.setBounds(10, 87, 123, 22);
		desktopPane.add(cmb);
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		
		btn = new JButton("Convertir");
		btn.setIcon(null);
		btn.setFont(new Font("Tahoma", Font.BOLD, 12));
		desktopPane.setLayer(btn, 15);
		btn.setForeground(new Color(0, 153, 153));
		btn.setToolTipText("");
		btn.setBackground(new Color(153, 255, 204));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn.setBounds(10, 145, 123, 23);
		desktopPane.add(btn);
		
		txt = new JTextField();
		txt.setBounds(162, 88, 123, 20);
		desktopPane.add(txt);
		txt.setColumns(10);
		
		lbl = new JLabel("00.00");
		lbl.setBackground(new Color(51, 153, 153));
		lbl.setToolTipText("");
		lbl.setLabelFor(frame);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		desktopPane.setLayer(lbl, 1);
		lbl.setBounds(162, 146, 123, 20);
		desktopPane.add(lbl);
		
		JLabel lblNewLabel_1 = new JLabel("Conversor de Moneda");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setBounds(10, 11, 241, 43);
		desktopPane.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("Conversion");
		lblNewLabel.setBounds(10, 65, 123, 20);
		desktopPane.add(lblNewLabel);
		
		lblIngreseCantidad = new JLabel("Ingrese Cantidad");
		lblIngreseCantidad.setBounds(162, 65, 123, 20);
		desktopPane.add(lblIngreseCantidad);
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
	}

	public void Convertir() {

		if(Validar(txt.getText())) {
			Moneda moneda = (Moneda) cmb.getSelectedItem();
			
			switch (moneda) {
			
			case Pesos_a_Dolar: 
				PesosAMoneda(dolar);
				break;
			case Pesos_a_Euro: 
				PesosAMoneda(euro);
				break;
			case Dolar_a_Pesos: 
				MonedaAPesos(dolar);
				break;
			case Euro_a_Pesos: 
				MonedaAPesos(euro);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + moneda);
			
			}		
		}
	}
	
	public void PesosAMoneda(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
	}
	
	public void MonedaAPesos(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));
	}
	
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if(x > 0);
			valorInput = x;
			return true;
		}catch(NumberFormatException e) {
			lbl.setText("Solamente números !!");
			return false;
		}
	}
}