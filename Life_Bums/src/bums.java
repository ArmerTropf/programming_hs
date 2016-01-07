//Aufgabe 5.1 Machen Sie Ihe Life Spiel aus dem ersten Semester grafikfähig.
//Die Ausgabe soll nicht mehr auf der Konsole erfolgen, sondern in einem Frame.

//Aufgabe 6.2 Modifizieren sie Ihr Life Spiel, so dass das MVC Muster eingehalten wird.
//Beim Starten des Life Spiels soll ein Fenster ohne Rahmen in der Mitte des Bildschirms gestartet werden,
//in dem ein Text von links nach rechts und zurück läuft.
//Danach soll das Fenster geschlossen und das Hauptfenster des Life Spiels gestartet werden.
//Achten Sie darauf dass das Fenster (Window) für die Laufschrift im Konstruktor das Hauptfenster (Frame) des Life Spiels übergeben bekommt.
//Sie dürfen keinen neuen Frame erzeugen.

//Aufgabe 7.1 Implementieren Sie für Ihr Life Spiel den "Schließen" Button des Frames. Wird er gedrückt, soll das Spiel beendet werden.
// Implementieren Sie ein weiteres Fenster, dass zusammen mit dem Spiel geöffnet wird und einen Button enthält.
// Wird dieser Button gedrückt, soll das Spiel genau eine neue Spielsituation berechnen und im Hauptfenster darstellen.

//Aufgabe 8.1 Erweitern Sie ihr Life Spiel um ein Menü mit einem Menüeintrag "Beenden".
//Dieser Menüeintrag soll das Programm beenden. Ein weiterer Menüeintrag soll die nächste Spielsituation berechnen und darstellen.
//Fangen Sie die Rezise-Events des Hauptfensters ab und berechnen Sie neue Größen für die Spielsteine,
//so dass das Hauptfenster optimal ausgenutzt wird.

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.acl.Owner;


public class bums {
    public static void main(String[] args) {
        Controller c = new Controller();
        c.Splashscreen();
    }

}

class Controller {
    private View view;
    private Model model;
    private Splashscreen splashscreen;
    private ControlWindow_View controlWindow;
    private int timespan;

    public Controller() {
        model = new Life(300, 300);
        view = new View(model, 1200, 900);
        splashscreen = new Splashscreen(view);
        controlWindow = new ControlWindow_View(this);
        model.Simulation();

        view.getSimulateMenuItem().addActionListener(e -> {
            model.Simulation();
            view.repaint();
        });
    }

    public void Simulate() {
        model.Simulation();
        view.repaint();
    }

    public void Splashscreen() {
        while(true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {}

            if(splashscreen.isVisible()) {
                splashscreen.repaint();
            } else {
                view.setVisible(true);
                controlWindow.setVisible(true);
                break;
            }
// for continuous simulation

//            else {
//                if(view.isVisible()) {
//                    model.Simulation();
//                    view.repaint();
//                } else {
//                    break;
//                }
//            }


        }
    }
}

class View extends Frame {
    Model model;
    MenuItem SimulateMenuItem;

    public View(Model model, int window_width, int window_height) {
        super("GameOfLife");
        this.model = model;
        setSize(window_width, window_height);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
                System.exit(0);

            }
        });

        //Menu - exercise 8.1
        MenuBar MenuBar = new MenuBar();
        setMenuBar(MenuBar);
        Menu OptionsMenu = new Menu("Options");
        MenuItem ExitMenuItem = new MenuItem("Exit");
        OptionsMenu.add(ExitMenuItem);
        SimulateMenuItem = new MenuItem("Simulate");
        OptionsMenu.add(SimulateMenuItem);
        MenuBar.add(OptionsMenu);
        ExitMenuItem.addActionListener(e -> System.exit(0));
    }
    public MenuItem getSimulateMenuItem() {return SimulateMenuItem;}

    @Override
    public void paint(Graphics g) {

        int offset_x = getInsets().left;
        int offset_y = getInsets().top;


        int window_width = getWidth()-offset_x*2;
        int window_height = getHeight()-offset_y-getInsets().bottom;

        boolean[][] array = model.getArray();

        double cell_height = window_height / array.length;

        for (int i = 0; i < array.length; i++) {
            double cell_width = window_width / array[i].length;
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j]) {
                    g.fillRect((int) (cell_width * i + offset_x), (int)(cell_height * j + offset_y), (int) cell_width, (int)cell_height);
                }
            }
        }
    }
}

class ControlWindow_View extends Frame {
        Button Calculate_Button;
        Controller controller;
    public ControlWindow_View(Controller controller) {
        super("Controlpanel");

        this.controller = controller;

        setSize(200, 100);
        Calculate_Button = new Button("Calculate");
        add(Calculate_Button);
        Calculate_Button.addActionListener(e -> {
            controller.Simulate();
        });

    }
}

class Splashscreen extends Window {

    Window Owner;
    int position;
    boolean back;

    public Splashscreen(Window Owner) {
        super(Owner);
        this.Owner = Owner;
        position = 0;
        back = false;
        Dimension Screen = getToolkit().getScreenSize();
        setLocation(Screen.width / 2 - Screen.width / 10, Screen.height / 2 - Screen.height / 10);
        setSize(Screen.width / 5, Screen.height / 5);
        setBackground(Color.black);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        //draw some text
        g.setColor(Color.white);
        g.setFont(new Font("Impact", Font.PLAIN, 20));
        FontMetrics fontm = g.getFontMetrics();
        String Title = "Conways Game Of Life";
        int Title_width = fontm.stringWidth(Title);
        int Title_height = fontm.getHeight();
        g.drawString(Title,position, Title_height/2+getHeight()/2);
        if(position >= getWidth()-Title_width || back) {
            position--;
            back = true;
        } else {
            position++;
        }
        if(position < 0) {
            setVisible(false);
            dispose();
        }
    }
}

abstract class Model {
    abstract public void Simulation();
    abstract public boolean[][] getArray();
}

class Life extends Model {

        private boolean[][] array;

        public Life(int width, int height) {
            array = new boolean[height][width];
            generateRandomBoard();
        }

        private void generateBoard() {
            for (int y = 0; y < array.length; ++y) {
                for (int x = 0; x < array[y].length; ++x) {
                    if (x % (y + 2) == 0) {
                        array[y][x] = true;
                    } else {
                        array[y][x] = false;
                    }
                }
            }
        }

        private void generateRandomBoard() {
            for (int y = 0; y < array.length; ++y) {
                for (int x = 0; x < array[y].length; ++x) {
                    if (Math.random() > .5) {
                        array[y][x] = true;
                    } else {
                        array[y][x] = false;
                    }
                }
            }
        }

        private int Neighbors(int x, int y) {

            int neighbors = 0;
            int line = array.length;
            int linewidth = array[y].length;

            for (int neighbors_y = (y - 1); neighbors_y <= (y + 1); neighbors_y++) {
                int currentneighbor_y = neighbors_y;
                if (neighbors_y < 0) {
                    currentneighbor_y = line - 1;
                } else if (neighbors_y > line - 1) {
                    currentneighbor_y = 0;
                }

                for (int neighbors_x = (x - 1); neighbors_x <= (x + 1); neighbors_x++) {
                    int currentneighbor_x = neighbors_x;
                    if (neighbors_x < 0) {
                        currentneighbor_x = linewidth - 1;
                    } else if (neighbors_x > linewidth - 1) {
                        currentneighbor_x = 0;
                    }
                    if (!(currentneighbor_y == y && currentneighbor_x == x)) {

                        neighbors+= (array[currentneighbor_y][currentneighbor_x]) ? 1 : 0;
                    }
                }
            }

            return neighbors;
        }

        public void Simulation() {

            boolean[][] new_array = new boolean[array.length][array[0].length];

            int line = array.length;

            for (int y = 0; y < line; ++y) {
                int linewidth = array[y].length;

                for (int x = 0; x < linewidth; ++x) {

                    int neighbors = Neighbors(x, y);
                    if ((neighbors > 3) || (neighbors < 2)) {
                        new_array[y][x] = false;
                    } else if ((!array[y][x]) && (neighbors == 3)) {
                        new_array[y][x] = true;
                    } else if ((array[y][x]) && (neighbors == 3 || neighbors == 2)) {
                        new_array[y][x] = true;
                    } else {
                        new_array[y][x] = false;
                    }
                }
            }
            array = new_array;
        }

        public boolean getCellState(int x, int y) {
            return array[x][y];
        }

        public boolean[][] getArray() {
            return array;
        }

        public int[][] getCellsWithXNeighbors(int quantity) {
            int[][] cells = new int[(array.length*array[0].length)][2];

            int line = array.length;

            int counter = 0;

            for (int y = 0; y < line; ++y) {
                int linewidth = array[y].length;

                for (int x = 0; x < linewidth; ++x) {

                    if(Neighbors(x, y) == quantity) {
                        cells[counter][0] = y;
                        cells[counter][1] = x;
                        ++counter;
                    }
                }
            }
            return cells;
        }

        public int[][] getCellsWithNNeighbors(int quantity) {
            return getCellsWithXNeighbors_recursive(quantity, 0, 0);
        }

        private int[][] getCellsWithXNeighbors_recursive(int quantity, int x, int y) {

            int [][] temp = new int[0][0];

            if (x+1 < array[y].length)
                temp = getCellsWithXNeighbors_recursive(quantity, x + 1, y);
            else if (y+1 < array.length)
                temp = getCellsWithXNeighbors_recursive(quantity, 0, y + 1);

            if(Neighbors(x, y) == quantity) {
                int [][] new_temp = new int [temp.length+1][2];
                for (int i = 0; i < temp.length; i++) {
                    new_temp[i][0] = temp[i][0];
                    new_temp[i][1] = temp[i][1];
                }
                new_temp[temp.length][0] = y;
                new_temp[temp.length][1] = x;
                return new_temp;
            }

            return temp;
        }

}