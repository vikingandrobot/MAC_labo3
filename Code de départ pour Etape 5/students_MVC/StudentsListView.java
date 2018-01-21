package students_MVC;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import models.*;
import controllers.StudentsController;

public class StudentsListView extends JPanel implements Observer{

	private JLabel message;
	private StudentsController studentsController;
	private java.util.List<Etudiant> studentsList; // Référence sur la liste des étudiants

	private JTable table;
	private MyTableModel tableModel;


	class MyTableModel extends AbstractTableModel {
	    private String[] columnNames = {"id", "Prenom", "Nom", "Show", "Edit", "Delete"};
	    private ArrayList<Etudiant> data;

	    
	    public MyTableModel() {
	    	data = new ArrayList<Etudiant>();
	    }

	    public int getColumnCount() {
	        return columnNames.length;
	    }

	    public int getRowCount() {
	        return data.size();
	    }

	    public String getColumnName(int col) {
	        return columnNames[col];
	    }

	    public Object getValueAt(int row, int col) {
	    	Etudiant student = data.get(row);
	    	Object value="";
	    	switch (col) {
	    	case 0: value= student.getId(); break;
	    	case 1: value= student.getPrenom(); break;
	    	case 2: value= student.getNom(); break;
	    	case 3: value= student.getId(); break;
	    	case 4: value= student.getId(); break;
	    	case 5: value= student.getId(); break;
	    	}
	        return value;
	    }

	    public Class getColumnClass(int colonne) {
	    	if (colonne==2) {
	    		return "".getClass(); //return JButton.class; // OU: Class<JButton>
	    	}
	        else {return "".getClass();}
	    }

	    public boolean isCellEditable(int row, int column){
	    	// Les colonens de boutons sont éditables
      		return (column == 3) || (column == 4) || (column == 5);
    	}


	    public void clearAll() {
	    	data = new ArrayList<Etudiant>();
	    }
	    
	    public void addStudent(Etudiant student){
	    	data.add(student);
	    	this.fireTableDataChanged();
	    }

	}

	public class ButtonRenderer extends JButton implements TableCellRenderer {
	  
	  public ButtonRenderer() {
	    setOpaque(true);
	  }
	   
	  public Component getTableCellRendererComponent(JTable table, Object value,
	                   boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(table.getSelectionBackground());
	    } else{
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    //setText( (value ==null) ? "" : value.toString() );
	    setIcon( (value ==null) ? new ImageIcon(StudentsGUIMainView.class.getResource("/images/Question-icon.png")) : new ImageIcon(StudentsGUIMainView.class.getResource("/images/Show-icon.png")) );
	    return this;
	  }
	}
	public class ButtonShowRenderer extends ButtonRenderer {
	  
	  public ButtonShowRenderer() {
	    super();
	  }
	   
	  @Override public Component getTableCellRendererComponent(JTable table, Object value,
	                   boolean isSelected, boolean hasFocus, int row, int column) {
	  	super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    setIcon( (value ==null) ? new ImageIcon(StudentsGUIMainView.class.getResource("/images/Question-icon.png")) : new ImageIcon(StudentsGUIMainView.class.getResource("/images/Show-icon.png")) );
	    return this;
	  }
	}

	public class ButtonEditRenderer extends ButtonRenderer {
	  
	  public ButtonEditRenderer() {
	    super();
	  }
	   
	  public Component getTableCellRendererComponent(JTable table, Object value,
	                   boolean isSelected, boolean hasFocus, int row, int column) {
	  	super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    setIcon( (value ==null) ? new ImageIcon(StudentsGUIMainView.class.getResource("/images/Question-icon.png")) : new ImageIcon(StudentsGUIMainView.class.getResource("/images/Edit-icon.png")) );
	    return this;
	  }
	}

	public class ButtonDeleteRenderer extends ButtonRenderer {
	  
	  public ButtonDeleteRenderer() {
	    super();
	  }
	   
	  @Override public Component getTableCellRendererComponent(JTable table, Object value,
	                   boolean isSelected, boolean hasFocus, int row, int column) {
	  	super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	    setIcon( (value ==null) ? new ImageIcon(StudentsGUIMainView.class.getResource("/images/Question-icon.png")) : new ImageIcon(StudentsGUIMainView.class.getResource("/images/Delete-icon.png")) );
	    return this;
	  }
	}


	public class ButtonEditor extends DefaultCellEditor {
  		protected JButton button;
  		protected String    label;
  		protected int 		idValue;	// Identifiant de l'objet
  		protected boolean   isPushed;
  		
  		public ButtonEditor(JCheckBox checkBox) {
  		  super(checkBox);
  		  button = new JButton();
  		  button.setOpaque(true);
  		  button.addActionListener(new ActionListener() {
  		    public void actionPerformed(ActionEvent e) {
  		      fireEditingStopped();
  		    }
  		  });
  		}
  		
  		public Component getTableCellEditorComponent(JTable table, Object value,
  		                 boolean isSelected, int row, int column) {
  		  if (isSelected) {
  		    button.setForeground(table.getSelectionForeground());
  		    button.setBackground(table.getSelectionBackground());
  		  } else{
  		    button.setForeground(table.getForeground());
  		    button.setBackground(table.getBackground());
  		  }
  		  label = (value ==null) ? "" : value.toString();
  		  button.setText( label );

  		  idValue = (value ==null) ? 0 : (Integer)value;

  		  isPushed = true;
  		  return button;
  		}
  		
  		  
  		public boolean stopCellEditing() {
  		  isPushed = false;
  		  return super.stopCellEditing();
  		}
  		
  		protected void fireEditingStopped() {
  		  super.fireEditingStopped();
  		}
	}		

	public class ButtonShowEditor extends ButtonEditor {
  		
  		public ButtonShowEditor(JCheckBox checkBox) {
  			super(checkBox);
  		}

  		@Override public Object getCellEditorValue() {
  		  if (isPushed)  {
  		    //JOptionPane.showMessageDialog(button, "Afficher " + idValue);
  		    studentsController.showStudent(idValue);
  		  }
  		  isPushed = false;
  		  return new String( label ) ;
  		}
  	}

  	public class ButtonEditEditor extends ButtonEditor {
  		
  		public ButtonEditEditor(JCheckBox checkBox) {
  			super(checkBox);
  		}

  		@Override public Object getCellEditorValue() {
  		  if (isPushed)  {
  		    //JOptionPane.showMessageDialog(button, "Editer " + idValue);
  		    studentsController.editStudent(idValue);
  		  }
  		  isPushed = false;
  		  return new String( label ) ;
  		}
  	}

	public class ButtonDeleteEditor extends ButtonEditor {
  		
  		public ButtonDeleteEditor(JCheckBox checkBox) {
  			super(checkBox);
  		}

  		@Override public Object getCellEditorValue() {
  		  if (isPushed)  {
  		    //JOptionPane.showMessageDialog(button, "Afficher " + idValue);
  		    studentsController.deleteStudent(idValue);
  		  }
  		  isPushed = false;
  		  return new String( label ) ;
  		}
  	}


	public StudentsListView(StudentsController ce) {
		studentsController= ce;

		setLayout (new BorderLayout());
        
        tableModel = new MyTableModel();
	    table = new JTable(tableModel);
	    //Nous ajoutons notre tableau ˆ notre contentPane dans un scroll
	    //Sinon les titres des colonnes ne s'afficheront pas !
	    this.add(new JScrollPane(table), BorderLayout.CENTER);
    	table.getColumn("Show").setCellRenderer(new ButtonShowRenderer());
    	table.getColumn("Show").setCellEditor(new ButtonShowEditor(new JCheckBox()));
    	table.getColumn("Edit").setCellRenderer(new ButtonEditRenderer());
    	table.getColumn("Edit").setCellEditor(new ButtonEditEditor(new JCheckBox()));
    	table.getColumn("Delete").setCellRenderer(new ButtonDeleteRenderer());
    	table.getColumn("Delete").setCellEditor(new ButtonDeleteEditor(new JCheckBox()));
    	table.setRowHeight(25);

	}

	public void setStudents(java.util.List students){
		this.studentsList = students;
		//listeEtudiants.addObserver(this);
	}

	public void addStudent (Etudiant student) {
		tableModel.addStudent(student);
	}

	public void resetWith(java.util.List students){
		this.studentsList = students;
		tableModel.clearAll();
		for (int i=0; i< studentsList.size(); i++) {
			Etudiant student = studentsList.get(i);
			tableModel.addStudent(student);
		}
	}

	public void update (Observable o, Object arg){
	// La liste observée a changé
		if (arg!=null) {	// Ajout d'une personne
			Etudiant student = (Etudiant)arg;
			tableModel.addStudent(student);
		}
		else {	// Initialisation globale 
			tableModel.clearAll();
			for (int i=0; i< studentsList.size(); i++) {
			    Etudiant student = studentsList.get(i);
			    tableModel.addStudent(student);
			}
		}
		this.validate();
	}



}

	
