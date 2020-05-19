package practice.view;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

public class JPanelOperator extends JPanel{

	public JLabel createLabel(final String message, final int x, final int y, final int width, final int height) {
		 final JLabel label = new JLabel();
		label.setForeground(Color.WHITE);
		label.setText(message);
		label.setBounds(x, y, width, height);
		this.add(label);
		return label;
	}

	public JTextField createTextField(final String message, final int lines, final int x, final int y, final int width, final int height, final ActionListener al) {
		final JTextField textField = new JTextField(message, lines);
		textField.setBounds(x, y, width, height);
		textField.addActionListener(al);
		this.add(textField);
		return textField;
	}

	public JTextField createTextField(final int x, final int y, final int width, final int height, final ActionListener al) {
		final JTextField textField = new JTextField();
		textField.setBounds(x, y, width, height);
		textField.addActionListener(al);
		this.add(textField);
		return textField;
	}

	public JTextField createTextField(final int x, final int y, final int width, final int height) {
		final JTextField textField = new JTextField();
		textField.setBounds(x, y, width, height);
		this.add(textField);
		return textField;
	}

	public JScrollPane createScrollPane(final JList<String> list, final int x, final int y, final int width, final int height) {
		final JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(x, y, width, height);
		this.add(scrollPane);
		return scrollPane;
	}

	public JScrollPane createScrollPane(final JList<String> list, final int x, final int y, final int width, final int height, final ListSelectionListener listener) {
		final JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		list.addListSelectionListener(listener);
		scrollPane.setBounds(x, y, width, height);
		this.add(scrollPane);
		return scrollPane;
	}

	public JButton createButton(final String buttonLabel, final int x, final int y, final int width, final int height, final ActionListener al) {
		final JButton button = new JButton(buttonLabel);
		button.setBounds(x, y, width, height);
		button.addActionListener(al);
		this.add(button);
		return button;
	}

	public JTextArea createTextArea(final int x, final int y,  final int width, final int height, final int rows, final int columns, final Color color) {
		final JTextArea textArea = new JTextArea(rows, columns);
		textArea.setEditable(false);
		textArea.setBounds(x, y, width, height);
		textArea.setBackground(color);
		this.add(textArea);
		return textArea;
	}
}
