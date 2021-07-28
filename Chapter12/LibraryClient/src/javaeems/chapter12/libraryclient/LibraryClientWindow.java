package javaeems.chapter12.libraryclient;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.util.List;
import javax.json.*;


public class LibraryClientWindow extends JFrame {
    LibraryClient libraryClient = new LibraryClient();
    JComboBox genresChooser = new JComboBox();
    JList booksList = new JList();
    JLabel authorLabel;
    JLabel titleLabel;
    JLabel idLabel;
    JLabel genreLabel;

    public static void main(String[] args) {
        LibraryClientWindow cw = new LibraryClientWindow(); 
    }
    
    public LibraryClientWindow() {
        this.setTitle(this.libraryClient.getUriAsString());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(30,30,400,300);
        JPanel mainPanel = new JPanel();
        this.genresChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                genreChanged();
            }            
        });
        this.booksList.setCellRenderer(new BookListRenderer());
        this.booksList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent lse) {
                listSelectionChanged();
            }
        });
        
        JButton addButton = new JButton("Add title");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                addBook();
            }            
        });
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                deleteSelection();
            }            
        });
        
        mainPanel.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        
        inputPanel.add(genresChooser);
        inputPanel.add(addButton);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        JScrollPane jsp = new JScrollPane(this.booksList);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        idLabel = new JLabel("ID");
        infoPanel.add(idLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        titleLabel = new JLabel("title");
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        authorLabel = new JLabel("author");
        infoPanel.add(authorLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        genreLabel = new JLabel("genre");
        infoPanel.add(genreLabel);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(deleteButton);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.add(jsp);
        centerPanel.add(infoPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(mainPanel);
        this.getGenres();
        this.setVisible(true);
    }
    
    public void getBooks() {
        if (this.genresChooser.getSelectedItem() == null) return;
        List<JsonObject> books = this.libraryClient.getBooks((String) this.genresChooser.getSelectedItem());
        DefaultListModel lm = new DefaultListModel();
        for (JsonObject b : books) {
            lm.addElement(b);
        }
        this.booksList.setModel(lm);

    }
    
    public void getGenres() {
        List<String> genres = this.libraryClient.getGenres();
        this.genresChooser.removeAllItems();
        this.genresChooser.addItem("All");
        for (String next : genres ) {
            this.genresChooser.addItem(next);
        }
    }
    
    public void genreChanged() {
        this.getBooks();
    }
    
    public void listSelectionChanged() {
        JsonObject jsono = (JsonObject) this.booksList.getSelectedValue();
        if (jsono != null) {
            int id = jsono.getInt("id");
            JsonObject booko = this.libraryClient.getBookById(id);
            this.authorLabel.setText(booko.getString("author"));
            this.idLabel.setText("" + booko.getInt("id"));
            this.titleLabel.setText(booko.getString("title"));  
            this.genreLabel.setText(booko.getString("genre"));
        }
    }
    
    public void deleteSelection() {
        JsonObject jsono = (JsonObject) this.booksList.getSelectedValue();
        if (jsono != null) {
            int id = jsono.getInt("id");
            this.libraryClient.deleteBook(id);
            this.getBooks();
        }
    }
    
    public void addBook() {
        AddBookDialog bd = new AddBookDialog(this.libraryClient.getGenres(), this);
        bd.setVisible(true);
        if (bd.isOK) {
            String title = bd.titleField.getText();
            String author = bd.authorField.getText();
            String genre = (String) bd.genresChooser.getSelectedItem();
            JsonObject bookaddo = Json.createObjectBuilder()
                    .add("title", title)
                    .add("author", author)
                    .add("genre", genre)
                    .build();
            AddBookStatus abs = this.libraryClient.addBook(bookaddo);
            if (!abs.added) {
                JOptionPane.showMessageDialog(this, "The library could not add the book because:\n" + abs.errorMessage);
            } else {
                this.getGenres();
            }
        } 
    }
      
}

