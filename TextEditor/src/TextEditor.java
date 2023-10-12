import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit; //File and Menu are JMenu Objects

    JMenuItem newFile, openFile, saveFile; //File Menu items

    JMenuItem cut, copy, paste, selectAll, close;//Edit Menu items

    JTextArea textArea;//Text Area where we write
    TextEditor()//make constructor
    {
        //initialize the frame
        frame = new JFrame();

        //initialize the menuBar
        menuBar = new JMenuBar(); //there is a function inside JFrame Class there we can directly set menuBar in it

        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu( "Edit");

        //Initialize File menu Items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Add Action Listeners to File menu Items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Add Menu items to File
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize Edit menu Items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //Add Action Listeners to Edit menu Items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Add Menu items to Edit
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //Initialize Text Area
        textArea = new JTextArea();

        //i have to set menuBar in Frame
        frame.setJMenuBar(menuBar);
        //set dimensions of frame
        frame.setBounds(0,0,400,400); //setBounds is used to give position(x,y)cordinates as well as height and width of window
        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add text Area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //create scrollPane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add ScrollPane to the Panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);//setLayout(null)...means ki if we want to add components in window/frame at specific position for that we have to make setLayout as NULL becoz by default JAVASWING try to add components in window according to its suitability and i don't want that...
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == cut)
        {
             //we have to cut the text message
            textArea.cut();
        }
        if(actionEvent.getSource() == copy)
        {
            //we have to copy the text message
            textArea.copy();
        }
        if(actionEvent.getSource() == paste)
        {
            //we have to paste the text message
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll)
        {
            //we have to selectAll the text message
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close)
        {
            //we have to close opration on TextEditor
            System.exit(0);//to close the Window
        }
        if(actionEvent.getSource() == openFile)
        {
            //open file chooser
            JFileChooser fileChooser = new JFileChooser("D:");//it will always open D folder
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have click on Open Button
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                //getting the selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filepath = file.getPath();
                try
                {
                    //Initialize the file Reader
                    FileReader fileReader = new FileReader(filepath);
                    //Initialize Buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    //we need to create the content of file from the file
                    String intermediate = "", output = ""; //intermediate String will contains/holding the content of current line and output will contain complete String
                    //Read content of file line by line
                    while((intermediate = bufferedReader.readLine())!=null)
                    {
                        output+=intermediate+"\n";//String Concatenation
                    }
                    //set the output screen to TextArea
                    textArea.setText(output);
                }
                catch(IOException ioException)
                {
                     ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile)
        {
            //Initialize file chooser
            JFileChooser fileChooser = new JFileChooser("D:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION)
            {
                //create a new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    //As we have to write instead of Read...we use FileWriter
                    //Initialize FileWriter
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize BufferedWriter
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile)
        {
            TextEditor textEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor()
;    }
}