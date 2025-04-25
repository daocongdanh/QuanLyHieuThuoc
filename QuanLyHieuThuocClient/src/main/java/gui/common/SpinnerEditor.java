/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.common;

/**
 *
 * @author Hoang
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.EventObject;

public class SpinnerEditor extends DefaultCellEditor {

    JSpinner spinner;
    JSpinner.DefaultEditor editor;
    JTextField textField;
    boolean valueSet;

    public SpinnerEditor( JSpinner spinner ) {
        super(new JTextField());
        this.spinner = spinner;
        editor = ((JSpinner.DefaultEditor) spinner.getEditor());
        textField = editor.getTextField();
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                editor.requestFocus();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        textField.addActionListener((ActionEvent ae) -> {
            stopCellEditing();
        });
    }

    // Prepares the spinner component and returns it.
    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column
    ) {
        if (!valueSet) {
            spinner.setValue(value);
        }
        SwingUtilities.invokeLater(() -> {
            textField.requestFocus();
        });
        return spinner;
    }

    @Override
    public boolean isCellEditable(EventObject eo) {
        System.err.println("isCellEditable");
        if (eo instanceof KeyEvent ke) {
            System.err.println("key event: " + ke.getKeyChar());
            textField.setText(String.valueOf(ke.getKeyChar()));
            //textField.select(1,1);
            //textField.setCaretPosition(1);
            //textField.moveCaretPosition(1);
            valueSet = true;
        } else {
            valueSet = false;
        }
        return true;
    }

    // Returns the spinners current value.
    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    @Override
    public boolean stopCellEditing() {
        System.err.println("Stopping edit");
        try {
            editor.commitEdit();
            spinner.commitEdit();
        } catch (java.text.ParseException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid value, discarding.");
        }
        return super.stopCellEditing();
    }
}
