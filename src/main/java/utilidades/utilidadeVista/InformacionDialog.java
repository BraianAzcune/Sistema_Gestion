package utilidades.utilidadeVista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class InformacionDialog extends JDialog implements ActionListener {

  private final JPanel contentPanel = new JPanel();



  /**
   * Create the dialog.
   */
  public InformacionDialog(JPanel Padre, String mensaje) {
    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

    setBounds(100, 100, 463, 198);
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(new BorderLayout(0, 0));
    {
      // UTILIZA HTML
      JTextPane textPane = new JTextPane();
      textPane.setBorder(null);
      textPane.setOpaque(false);
      textPane.setContentType("text/html");
      textPane.setFocusable(false);
      textPane.setText("<html bgcolor=\"#f0f0f0\"><font face=\"arial\" size='5' color=rgb(1,1,1)>"
          + mensaje + "</font></html>");
      textPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
      textPane.setBackground(SystemColor.control);
      textPane.setEditable(false);


      contentPanel.add(textPane, BorderLayout.CENTER);
    }
    {
      JPanel buttonPane = new JPanel();
      getContentPane().add(buttonPane, BorderLayout.SOUTH);
      buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      {
        JButton okButton = new JButton("Aceptar");
        okButton.addActionListener(this);
        okButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
      }
    }


    this.setVisible(true);
    this.pack();
    setLocationRelativeTo(Padre);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getActionCommand().equals("OK")) {
      this.dispose();
    }

  }
}
