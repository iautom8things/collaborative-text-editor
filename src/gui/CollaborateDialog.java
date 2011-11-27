package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CollaborateDialog extends JDialog {
    
    private static final boolean DEBUG = true;
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

    public void showDialog(){
        _frame.setVisible(true);
    }
    
    public void hideDialog(){
        _frame.setVisible(false);
        setModal(false);
    }
    
    private void print(String message){
        if (DEBUG) { 
            System.out.println(message); 
        }        
    }
    
    /*
     * Make sure that the password and the confirm password are the same.
     *  
     */
    private String validatePassword() {
        String errorMessage = "";
        String password = new String(_documentPasswordField.getPassword());
        if(password.length() < 4){
            errorMessage = "Password must be at least 4 characters.\n";
        }
        String confirm = new String(_confirmDocumentPasswordField.getPassword());
        if(!(password.equals(confirm))){
            errorMessage = errorMessage + "Passwords are not equal.\n";
        }
        return errorMessage;
    }
    
    private String validateComponents(){
        String errorMessage = "";
        errorMessage = errorMessage + validateTextComponent("Server URL", _serverURLField);
        errorMessage = errorMessage + validateTextComponent("Server Port", _serverPortField);
        errorMessage = errorMessage + validateTextComponent("Document Name", _documentNameField);
        errorMessage = errorMessage + validateTextComponent("User ID", _userIDField);
        if(_mode == SHARE_NEW){
            errorMessage = errorMessage + validatePassword();
        }
        return errorMessage;
    }
    
    private String validateTextComponent ( String fieldName, JTextField textField ){
        String componentText = textField.getText();
        if(componentText.equals("")){
            return fieldName + " must be populated.\n";
        }
        else{
            return "";
        }
    }

    private void popUpMessage( String message ){
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);        
    }
    
    public String getDocumentName(){
        return _documentNameField.getText();
    }
    
    public String getServerURL(){
        return _serverURLField.toString();
    }
    
    public String getServerPort(){
        return _serverPortField.toString();
    }
    
    public String getUserID(){
        return _userIDField.toString();
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
        //_frame.setVisible(true);
        hideDialog();

        _okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String errorMessage = validateComponents();
                if (errorMessage.length() > 0) {
                    popUpMessage(errorMessage);
                } else {
                    hideDialog();
                }
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
