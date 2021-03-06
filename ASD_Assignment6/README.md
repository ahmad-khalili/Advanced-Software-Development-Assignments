# Assignment 6
- Optimized the import statements by getting rid of the ones with more than one method from a library.
```ruby
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
```
-Renamed unclear variable names, and used final variables to get rid of magic numbers. Also added methods for unclear ready-to-use methods.
```ruby
public static final StringBuilder RS = new StringBuilder();
    public static final String APP_NAME = "Editor";
    public static final String MID_CONTAINER = "Center";
    public JEditorPane textPanel;
    final private JMenuBar menu;
    public boolean isChanged = false;
    private File file;


    void SetWindowSize(Rectangle r){
        setSize(r.width, r.height);
    }

    void ReadFile(){
        try (	FileReader fr = new FileReader(file);
                 BufferedReader reader = new BufferedReader(fr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                RS.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot read file !", "Error !", JOptionPane.ERROR_MESSAGE);
```
- Used the previously defined final variables to further explain their usage without comments as in "APP_NAME" and "MID_CONTAINER". Also created a class called "Rectangle" to reduce the parameters for the "SetWindowSize" method.
```ruby
public Editor() {
        super(APP_NAME);
        textPanel = new JEditorPane();
        add(new JScrollPane(textPanel), MID_CONTAINER);
        textPanel.getDocument().addDocumentListener(this);

        menu = new JMenuBar();
        setJMenuBar(menu);
        BuildMenu();
        Rectangle windowSize = new Rectangle(500,500);
        SetWindowSize(windowSize);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
```
```ruby
public class Rectangle {
    int width;
    int height;
    Rectangle(int width, int height){
        this.width = width;
        this.height = height;
```
- Renamed the method names to capital first letters to indicate they're methods. And fixed variable naming such as ( "sall" --> "selectAll" ), ( "saveas" --> "saveAs" ).
```ruby
 private void BuildMenu() {
        BuildFileMenu();
        BuildEditMenu();
    }

    private void BuildFileMenu() {
        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        menu.add(file);
        JMenuItem n = new JMenuItem("New");
        n.setMnemonic('N');
        n.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        n.addActionListener(this);
        file.add(n);
        JMenuItem open = new JMenuItem("Open");
        file.add(open);
        open.addActionListener(this);
        open.setMnemonic('O');
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        JMenuItem save = new JMenuItem("Save");
        file.add(save);
        save.setMnemonic('S');
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        JMenuItem saveAs = new JMenuItem("Save as...");
        saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        file.add(saveAs);
        saveAs.addActionListener(this);
        JMenuItem quit = new JMenuItem("Quit");
        file.add(quit);
        quit.addActionListener(this);
        quit.setMnemonic('Q');
        quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
    }

    private void BuildEditMenu() {
        JMenu edit = new JMenu("Edit");
        menu.add(edit);
        edit.setMnemonic('E');
        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        cut.setMnemonic('T');
        edit.add(cut);
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setMnemonic('C');
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        edit.add(copy);
        JMenuItem paste = new JMenuItem("Paste");
        paste.setMnemonic('P');
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        edit.add(paste);
        paste.addActionListener(this);
        JMenuItem find = new JMenuItem("Find");
        find.setMnemonic('F');
        find.addActionListener(this);
        edit.add(find);
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setMnemonic('A');
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        selectAll.addActionListener(this);
        edit.add(selectAll);
```
- Used switch statements instead of if and else if. Also ditched the magic numbers for "result" and "answer", and used the constant values from the "JOptionPane" library instead (I used comments to understand the meaning of each of the numbers and replaced it with its corresponding constant value). The redundant and unused if statements were also removed.
```ruby
public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "Quit":
                System.exit(0);
            case "Open":
                LoadFile();
                break;
            case "Save":
                int answer = JOptionPane.YES_OPTION;
                if (isChanged) {
                    answer = JOptionPane.showConfirmDialog(null, "The file has been changed. You want to save it?", "Save file",
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                }
                if (answer == JOptionPane.YES_OPTION) {
                    if (file == null) {
                        SaveAs("Save");
                    } else {
                        String text = textPanel.getText();
                        System.out.println(text);
                        try (PrintWriter writer = new PrintWriter(file)) {
                            if (!file.canWrite())
                                throw new Exception("Cannot write file!");
                            writer.write(text);
                            isChanged = false;
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                break;
            case "New":
                if (isChanged) {
                        answer = JOptionPane.showConfirmDialog(null, "The file has been changed. You want to save it?", "Save file",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (answer == JOptionPane.NO_OPTION)
                            return;

                    if (file == null) {
                        SaveAs("Save");
                        return;
                    }
                    String text = textPanel.getText();
                    System.out.println(text);
                    try (PrintWriter writer = new PrintWriter(file)) {
                        if (!file.canWrite())
                            throw new Exception("Cannot write file!");
                        writer.write(text);
                        isChanged = false;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                file = null;
                textPanel.setText("");
                isChanged = false;
                setTitle("Editor");
                break;
            case "Save as...":
                SaveAs("Save as...");
                break;
            case "Select All":
                textPanel.selectAll();
                break;
            case "Copy":
                textPanel.copy();
                break;
            case "Cut":
                textPanel.cut();
                break;
            case "Paste":
                textPanel.paste();
                break;
            case "Find":
                FindDialog find = new FindDialog(this, true);
                find.showDialog();
                break;
```
- Renamed the method to capital first letters to indicate its a method and replaced the magic numbers using the same library used in the previous method. Also implemented the file reading code into a method called "ReadFile" and called it in its place. The redundant if statements were also removed from this method.
```ruby
private void LoadFile() {
        JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
        dialog.setMultiSelectionEnabled(false);
        try {
            int result = dialog.showOpenDialog(this);
            if (result == JOptionPane.YES_NO_CANCEL_OPTION)
                return;
            if (result == JOptionPane.OK_OPTION) {
                if (isChanged){
                        int answer = JOptionPane.showConfirmDialog(null, "The file has been changed. You want to save it?", "Save file",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (answer == JOptionPane.NO_OPTION)
                            return;
                    if (file == null) {
                        SaveAs("Save");
                        return;
                    }
                    String text = textPanel.getText();
                    System.out.println(text);
                    try (PrintWriter writer = new PrintWriter(file)){
                        if (!file.canWrite())
                            throw new Exception("Cannot write file!");
                        writer.write(text);
                        isChanged = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                file = dialog.getSelectedFile();
                ReadFile();
                textPanel.setText(RS.toString());
                isChanged = false;
                setTitle("Editor - " + file.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
```
- This method was also renamed to indicate its status. The magic numbers were also replaced with constant values for this method too. Also used a direct comparsion if statement instead of using if not.
```ruby
private void SaveAs(String dialogTitle) {
        JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
        dialog.setDialogTitle(dialogTitle);
        int result = dialog.showSaveDialog(this);
        if (result == JOptionPane.OK_CANCEL_OPTION)
            return;
        file = dialog.getSelectedFile();
        try (PrintWriter writer = new PrintWriter(file)){
            writer.write(textPanel.getText());
            isChanged = false;
            setTitle("Editor - " + file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
```
