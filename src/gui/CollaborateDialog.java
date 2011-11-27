package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CollaborateDialog extends JDialog {
    
    private GridBagConstraints _gridBagConstraints;
    private JFrame _frame;
    private int _mode; //Type of Collaborative Session to Open
    private JLabel _modeLabel;
    private final JComboBox _modeComboBox;
    private JLabel _serverURLLabel;
    private JTextField _serverURLField;
    private JLabel _serverPortLabel;
    private JTextField _serverPortField;
    private JLabel _documentNameLabel;
    private JTextField _documentNameField;
    private JLabel _userIDLabel;
    private JTextField _userIDField;
    private JLabel _documentPasswordLabel;
    private JPasswordField _documentPasswordField;
    private final JLabel _confirmDocumentPasswordLabel;
    private final JPasswordField _confirmDocumentPasswordField;
    private JButton _okButton;
    private JButton _cancelButton;
    public static final int SHARE_NEW = 1;
    public static final int SHARE_EXISTING = 2;
    
    /*
     * Helper method to add the Component to the main Frame with the specified
     * constraints.
     *  @Requires
     *      component != null
     *      x >= 0
     *      y >= 0
     *      gridWidth >= 0
     *      fillMethod is valid gridWidth fillMethod
     *  @Ensures
     *      frame contains the component
     */
    private void addComponent ( Component component, int x, int y, int gridWidth, int fillMethod ) {
        _gridBagConstraints.gridx = x;
        _gridBagConstraints.gridy = y;
        _gridBagConstraints.gridwidth = gridWidth;
        _gridBagConstraints.fill = fillMethod;
        _gridBagConstraints.anchor = GridBagConstraints.EAST;
        _frame.add(component, _gridBagConstraints);
    }

    /*
     * Clear the contents of the text fields:
     */
    public void clearContents(){
        _documentPasswordField.setText("");
        _confirmDocumentPasswordField.setText("");
        _documentNameField.setText("");
        _serverURLField.setText("");
        _serverPortField.setText("");
    }

    public void show(){
        _frame.setVisible(true);
    }
    
    public void hide(){
        _frame.setVisible(false);
    }    
    
    public CollaborateDialog ( Frame parent ) {
        _mode = SHARE_NEW;
        _frame = new JFrame("Collaborate");
        _gridBagConstraints = new GridBagConstraints();
        _gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        _frame.setLayout(new GridBagLayout());
        _frame.setPreferredSize(new java.awt.Dimension(400, 250));

        _modeLabel = new JLabel("Collaborate By");
        addComponent(_modeLabel, 0, 0, 1, GridBagConstraints.NONE);

        _modeComboBox = new JComboBox();
        String[] options = new String[]{"Share Current Document", "Connect to Shared Document"};
        _modeComboBox.setModel(new javax.swing.DefaultComboBoxModel(options));
        addComponent(_modeComboBox, 1, 0, 1, GridBagConstraints.NONE);

        _serverURLLabel = new JLabel("Server URL");
        addComponent(_serverURLLabel, 0, 1, 1, GridBagConstraints.NONE);

        _serverURLField = new JTextField();
        addComponent(_serverURLField, 1, 1, 7, GridBagConstraints.HORIZONTAL);
 
        _serverPortLabel = new JLabel("Server Port");
        addComponent(_serverPortLabel, 0, 2, 1, GridBagConstraints.NONE);

        _serverPortField = new JTextField();
        addComponent(_serverPortField, 1, 2, 7, GridBagConstraints.HORIZONTAL);
        
        _documentNameLabel = new JLabel("Document Name");
        addComponent(_documentNameLabel, 0, 3, 1, GridBagConstraints.NONE);

        _documentNameField = new JTextField();
        addComponent(_documentNameField, 1, 3, 7, GridBagConstraints.HORIZONTAL);

        _userIDLabel = new JLabel("User ID");
        addComponent(_userIDLabel, 0, 4, 1, GridBagConstraints.NONE);

        _userIDField = new JTextField();
        addComponent(_userIDField, 1, 4, 5, GridBagConstraints.HORIZONTAL);

        _documentPasswordLabel = new JLabel("Document Password");
        addComponent(_documentPasswordLabel, 0, 5, 1, GridBagConstraints.NONE);

        _documentPasswordField = new JPasswordField();
        addComponent(_documentPasswordField, 1, 5, 5, GridBagConstraints.HORIZONTAL);

        _confirmDocumentPasswordLabel = new JLabel("Confirm Password");
        addComponent(_confirmDocumentPasswordLabel, 0, 6, 1, GridBagConstraints.NONE);

        _confirmDocumentPasswordField = new JPasswordField();
        addComponent(_confirmDocumentPasswordField, 1, 6, 5, GridBagConstraints.HORIZONTAL);

        _okButton = new JButton("OK");
        addComponent(_okButton, 4, 7, 1, GridBagConstraints.NONE);

        _cancelButton = new JButton("Cancel");
        addComponent(_cancelButton, 5, 7, 1, GridBagConstraints.NONE);

        _frame.setSize(500, 270);
        _frame.setVisible(true);

        _okButton.addActionListener(new ActionListener() {
           public void actionPerformed( ActionEvent e ){
               _frame.setVisible(false);
               clearContents();
           }
            
            
            
        });
        
        _modeComboBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String optionName = _modeComboBox.getSelectedItem().toString();
                if (optionName == "Share Current Document") {
                    //Enable the confirm password label and text field
                    _confirmDocumentPasswordLabel.setEnabled(true);
                    _confirmDocumentPasswordField.setEnabled(true);
                    _mode = SHARE_NEW;
                } else if (optionName == "Connect to Shared Document") {
                    //Disable the confirm password label and text field
                    _confirmDocumentPasswordLabel.setEnabled(false);
                    _confirmDocumentPasswordField.setEnabled(false);
                    //Clear the field when disabled
                    _confirmDocumentPasswordField.setText("");
                    _mode = SHARE_EXISTING;
                }
            }
        });

    }

}
