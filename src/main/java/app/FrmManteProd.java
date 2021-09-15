package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Producto;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql8");
	EntityManager em = fabrica.createEntityManager();	
	private JTextArea txtSalida;
	private JTextField txtIdProducto;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrar();
			}
		});
		btnNewButton.setBounds(317, 33, 89, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 85, 414, 122);
		contentPane.add(scrollPane);
		
		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(172, 227, 89, 23);
		contentPane.add(btnListado);
		
		JLabel lblNewLabel = new JLabel("Id. Producto: ");
		lblNewLabel.setBounds(10, 11, 77, 14);
		contentPane.add(lblNewLabel);
		
		txtIdProducto = new JTextField();
		txtIdProducto.setBounds(83, 8, 86, 20);
		contentPane.add(txtIdProducto);
		txtIdProducto.setColumns(10);
		
		JComboBox cboCategoria = new JComboBox();
		cboCategoria.setBounds(83, 34, 124, 22);
		contentPane.add(cboCategoria);
		
		JLabel lblNewLabel_1 = new JLabel("Categoria:");
		lblNewLabel_1.setBounds(10, 37, 63, 14);
		contentPane.add(lblNewLabel_1);
	}


	void listado() {
		List<Producto> lstProducto = em.createQuery("select p from Producto p", Producto.class).getResultList();

		System.out.println("Nro de usuarios : " + lstProducto.size());
		txtSalida.setText(">>>Listado de Productos<<<\n");
		for (Producto p : lstProducto) {
			txtSalida.append(p.getIdprod() + "\t" + p.getDescripcion() + "\n");
		}
		txtSalida.append("-----------------------------------------\n");
	}
	
	void registrar() {
		//entradas
		String codigo = txtIdProducto.getText();
			
		// -- proceso -> grabar datos en la entidad Producto
		Producto p = new Producto();
		p.setIdprod(codigo);
		p.setDescripcion("Prueba50");
		p.setStock(500);
		p.setPrecio(0.99);
		p.setIdcategoria(1);
		p.setEstado(0);
		
		em.getTransaction().begin();
		
		em.persist(p);
		
		em.getTransaction().commit();
			
	}
}
