import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.Objects;

public class MyFrame extends JFrame {

    Connection conn = null;
    PreparedStatement state = null;
    ResultSet result = null;

    int id = -1;
    int idcountry = -1;
    int idcar = -1;

    int idcarsearch = -1;
    int idorder = -1;

    //---------------------------------------------------    COUNTRY

    JTabbedPane tab = new JTabbedPane();

    JPanel searchPanel = new JPanel();
    JPanel searchPanelUP = new JPanel();
    JPanel searchPanelMid = new JPanel();
    JPanel searchPanelDown = new JPanel();

    JLabel fnameSearchL = new JLabel("Име:");
    JLabel countrySearchL = new JLabel("Държава:");

    JTextField fnameSearchTF = new JTextField();
    JTextField countrySearchTF = new JTextField();

    JComboBox<String> brandSearchCB = new JComboBox<String>();

    JButton doubleSearchBt = new JButton("Търсене");




    JPanel orderPanel = new JPanel();
    JPanel orderPanelUP = new JPanel();
    JPanel orderPanelMid = new JPanel();
    JPanel orderPanelDown = new JPanel();


    JLabel fnameL = new JLabel("Име:");
    JLabel lnameL = new JLabel("Фамилия:");
    JLabel orderCountryL = new JLabel("Държава:");
    JLabel orderBrandL = new JLabel("Марка:");
    JLabel quantityL = new JLabel("Количество:");
    JLabel priceL = new JLabel("Цена:");


    JTextField fnameTF = new JTextField();
    JTextField lnameTF = new JTextField();
    JTextField quantityTF = new JTextField();
    JTextField priceTF = new JTextField();
    JComboBox<String> countryCB = new JComboBox<String>();
    JComboBox<String> brandCB = new JComboBox<String>();


    JButton addBtOrders = new JButton("Добавяне");
    JButton deleteBtOrders = new JButton("Изтриване");
    JButton editBtOrders = new JButton("Редактиране");
    JButton refreshBtOrders = new JButton("Обнови");
    JButton searchBtOrders = new JButton("Търсене на име");


    JPanel countryPanel = new JPanel();
    JPanel countryPanelUP = new JPanel();
    JPanel countryPanelMid = new JPanel();
    JPanel countryPanelDown = new JPanel();


    JLabel countryL = new JLabel("Държава: ");
    JTextField countryTF = new JTextField();

    JButton addBtCountry = new JButton("Добавяне");
    JButton deleteBtCountry = new JButton("Изтриване");
    JButton editBtCountry = new JButton("Редактиране");
    JButton refreshBtCountry = new JButton("Обнови");
    JButton searchBtCountry = new JButton("Търсене на държава");

//    ----------------------------------------------------------------------------------------------

    JPanel CarPanel = new JPanel();
    JPanel CarPanelUP = new JPanel();
    JPanel CarPanelMid = new JPanel();
    JPanel CarPanelDown = new JPanel();


    JLabel CarL = new JLabel("Марка: ");
    JTextField CarTF = new JTextField();


    JButton addBtCar = new JButton("Добавяне");
    JButton deleteBtCar = new JButton("Изтриване");
    JButton editBtCar = new JButton("Редактиране");
    JButton refreshBtCar = new JButton("Обнови");
    JButton searchBtCar = new JButton("Търсене на марка автомобил");


    JTable countryTable = new JTable();
    JTable carTable = new JTable();
    JTable orderTable = new JTable();
    JTable searchTable = new JTable();

    JScrollPane myScrollSearch = new JScrollPane(searchTable);
    JScrollPane myScroll = new JScrollPane(countryTable);
    JScrollPane myScrollCar = new JScrollPane(carTable);
    JScrollPane myScrollOrder = new JScrollPane(orderTable);




    public MyFrame() {


        countryPanel.add(countryPanelUP);
        countryPanel.add(countryPanelMid);
        countryPanel.add(countryPanelDown);

        searchPanel.add(searchPanelUP);
        searchPanel.add(searchPanelMid);
        searchPanel.add(searchPanelDown);

        tab.add(countryPanel, "Държави");
        tab.add(CarPanel, "Марки автомобили");
        tab.add(orderPanel, "Поръчки");
        tab.add(searchPanel, "Двойно търсене");

        countryPanelUP.setLayout(new GridLayout(1, 2));
        CarPanelUP.setLayout(new GridLayout(1, 2));
        orderPanelUP.setLayout(new GridLayout(6, 2));
        searchPanelUP.setLayout(new GridLayout(2, 2));


        countryPanel.setLayout(new GridLayout(3, 1));
        CarPanel.setLayout(new GridLayout(3, 1));
        orderPanel.setLayout(new GridLayout(3, 1));
        searchPanel.setLayout(new GridLayout(3, 1));


        this.add(tab);

        this.setSize(400, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 1));

        searchPanelUP.add(fnameSearchL);
        searchPanelUP.add(fnameSearchTF);
        searchPanelUP.add(countrySearchL);
        searchPanelUP.add(countrySearchTF);

        searchPanelMid.add(doubleSearchBt);

        doubleSearchBt.addActionListener(new DoubleSearch());

        myScrollSearch.setPreferredSize(new Dimension(350, 150)); // razmer na scrola
        searchPanelDown.add(myScrollSearch);




        //Up panel

        countryPanelUP.add(countryL);
        countryPanelUP.add(countryTF);

        //Mid panel

        countryPanelMid.add(addBtCountry);
        countryPanelMid.add(deleteBtCountry);
        countryPanelMid.add(editBtCountry);
        countryPanelMid.add(refreshBtCountry);
        countryPanelMid.add(searchBtCountry);

        addBtCountry.addActionListener(new AddInCountry());
        deleteBtCountry.addActionListener(new DeleteCountryDB());
        editBtCountry.addActionListener(new EditCountryDB());
        searchBtCountry.addActionListener(new SearchCountry());
        refreshBtCountry.addActionListener(new RefreshCountry());


        //down panel

        myScroll.setPreferredSize(new Dimension(350, 150)); // razmer na scrola
        countryPanelDown.add(myScroll);

        refreshTableCountry();


//        ------------------------        Car          ----------------------------------------

        CarPanel.add(CarPanelUP);
        CarPanel.add(CarPanelMid);
        CarPanel.add(CarPanelDown);


        CarPanelUP.add(CarL);
        CarPanelUP.add(CarTF);

        //Mid panel

        CarPanelMid.add(addBtCar);
        CarPanelMid.add(deleteBtCar);
        CarPanelMid.add(editBtCar);
        CarPanelMid.add(refreshBtCar);
        CarPanelMid.add(searchBtCar);

        addBtCar.addActionListener(new AddInCar());
        deleteBtCar.addActionListener(new DeleteCarDB());
        editBtCar.addActionListener(new EditCarDB());
        searchBtCar.addActionListener(new SearchCar());
        refreshBtCar.addActionListener(new RefreshCar());


        //down panel

        myScrollCar.setPreferredSize(new Dimension(350, 150)); // razmer na scrola
        CarPanelDown.add(myScrollCar);


        refreshTableCar();

//        ----------------------------------------------- Orders -------------------------------------

        orderPanel.add(orderPanelUP);
        orderPanel.add(orderPanelMid);
        orderPanel.add(orderPanelDown);


        orderPanelUP.add(fnameL);
        orderPanelUP.add(fnameTF);
        orderPanelUP.add(lnameL);
        orderPanelUP.add(lnameTF);
        orderPanelUP.add(orderCountryL);
        orderPanelUP.add(countryCB); //country cb
        orderPanelUP.add(orderBrandL);
        orderPanelUP.add(brandCB); //country cb
        orderPanelUP.add(quantityL);
        orderPanelUP.add(quantityTF);
        orderPanelUP.add(priceL);
        orderPanelUP.add(priceTF);


        orderPanelMid.add(addBtOrders);
        orderPanelMid.add(deleteBtOrders);
        orderPanelMid.add(editBtOrders);
        orderPanelMid.add(refreshBtOrders);
        orderPanelMid.add(searchBtOrders);

        addBtOrders.addActionListener(new AddInOrders());
        deleteBtOrders.addActionListener(new DeleteOrderDB());
        editBtOrders.addActionListener(new EditOrderDB());
        searchBtOrders.addActionListener(new SearchOrder());
        refreshBtOrders.addActionListener(new RefreshOrder());


        myScrollOrder.setPreferredSize(new Dimension(350, 150)); // razmer na scrola
        orderPanelDown.add(myScrollOrder);

        refreshComboCar();
        refreshComboCountry();
        refreshComboCarSearch();
        refreshTableOrders();


        this.setVisible(true);

        countryTable.addMouseListener(new MouseActionCountryTable());
        carTable.addMouseListener(new MouseActionCarTable());
        orderTable.addMouseListener(new MouseActionOrderTable());

    }


    public void refreshComboCountry() {
        countryCB.removeAllItems();
//        idcountry = -1;

        conn = DBConnection.getConnection();
        String sql = "select id, country from countrytable";
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();

            while (result.next()){
                idcountry = Integer.parseInt(result.getString(1));
                item = result.getObject(2).toString();
                countryCB.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void refreshComboCar() {
        brandCB.removeAllItems();
//        idcar = -1;

        conn = DBConnection.getConnection();
        String sql = "select id, brand from cartable";
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();

            while (result.next()){
                idcar = Integer.parseInt(result.getString(1));
                item = result.getObject(2).toString();
                brandCB.addItem(item);
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void refreshComboCarSearch() {
        brandSearchCB.removeAllItems();
//        idcar = -1;

        conn = DBConnection.getConnection();
        String sql = "select id, brand from cartable";
        String item = "";

        try {
            state = conn.prepareStatement(sql);
            result = state.executeQuery();

            while (result.next()){
                idcarsearch = Integer.parseInt(result.getString(1));
                item = result.getObject(2).toString();
                brandSearchCB.addItem(item);
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void refreshTableCountry() {

        conn = DBConnection.getConnection();

        try {
            state = conn.prepareStatement("select * from countrytable");
            result = state.executeQuery();
            countryTable.setModel(new MyTable(result));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void refreshTableCar() {

        conn = DBConnection.getConnection();

        try {
            state = conn.prepareStatement("select * from cartable");
            result = state.executeQuery();
            carTable.setModel(new MyTable(result));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void refreshTableOrders() {

        conn = DBConnection.getConnection();

        try {
            state = conn.prepareStatement("select * from ordertable");
            result = state.executeQuery();
            orderTable.setModel(new MyTable(result));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void refreshTableSearch(){
        conn = DBConnection.getConnection();

        try {
            state = conn.prepareStatement("SELECT ordertable.fname, cartable.brand FROM ordertable, cartable");
            result = state.executeQuery();
            searchTable.setModel(new MyTable(result));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void clearFormCountry() {
        countryTF.setText("");
    }
    public void clearFormCar() {
        CarTF.setText("");
    }
    public void clearFormOrders() {
        fnameTF.setText("");
        lnameTF.setText("");
        quantityTF.setText("");
        priceTF.setText("");
    }


    class AddInCountry implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            conn = DBConnection.getConnection();
            String sql = "insert into countrytable(country) values (?)";

            try {
                state = conn.prepareStatement(sql);
                state.setString(1, countryTF.getText());
                state.execute();
                refreshTableCountry();
                clearFormCountry();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    class AddInCar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            conn = DBConnection.getConnection();
            String sql = "insert into cartable(brand) values (?)";

            try {
                state = conn.prepareStatement(sql);
                state.setString(1, CarTF.getText());
                state.execute();
                refreshTableCar();
                clearFormCar();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    class AddInOrders implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            conn = DBConnection.getConnection();

//            refreshComboCar();
//            refreshComboCountry();

//            String sql = "INSERT INTO ordertable (fname, lname, country_id, car_id, quantity, price) VALUES (?,?,?,?,?,?)";

            if (!fnameTF.getText().isEmpty()) {
                String sql = "INSERT INTO ordertable (fname, lname, idcountry, idcar, quantity, price) VALUES (?,?,?,?,?,?)";
                try {
                    state = conn.prepareStatement(sql);
                    state.setString(1, fnameTF.getText());
                    state.setString(2, lnameTF.getText());

                    state.setInt(3, idcountry);
                    state.setInt(4, idcar);

                    state.setInt(5, Integer.parseInt(quantityTF.getText()));
                    state.setDouble(6, Double.parseDouble(priceTF.getText()));

                    state.execute();

                    refreshTableOrders();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                idorder = -1;

//                fnameTF.setText("");
//                lnameTF.setText("");
//                quantityTF.setText("");
//                priceTF.setText("");
//                idcountry = -1;
//                idcar = -1;

                clearFormOrders();
            }


        }
    }


    class MouseActionCountryTable implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            int row = countryTable.getSelectedRow();

            idcountry = Integer.parseInt(countryTable.getValueAt(row, 0).toString());

            countryTF.setText(countryTable.getValueAt(row, 1).toString());

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    class MouseActionCarTable implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            int row = carTable.getSelectedRow();

            idcar = Integer.parseInt(carTable.getValueAt(row, 0).toString());

            CarTF.setText(carTable.getValueAt(row, 1).toString());

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    class MouseActionOrderTable implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            int row = orderTable.getSelectedRow();

            idorder = Integer.parseInt(orderTable.getValueAt(row, 0).toString());

            fnameTF.setText(orderTable.getValueAt(row, 1).toString());
            lnameTF.setText(orderTable.getValueAt(row, 2).toString());


            countryCB.setSelectedItem(orderTable.getValueAt(row, 3).toString());
            idcountry = Integer.parseInt(orderTable.getValueAt(row, 3).toString());


            brandCB.setSelectedItem(orderTable.getValueAt(row, 4).toString());
            idcar = Integer.parseInt(orderTable.getValueAt(row, 4).toString());


            quantityTF.setText(orderTable.getValueAt(row, 5).toString());
            priceTF.setText(orderTable.getValueAt(row, 6).toString());

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


    class DeleteCountryDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
			conn=DBConnection.getConnection();
            if (idcountry > 0) {
                String sql = "delete from countrytable where id=?";
                try {
                    state = conn.prepareStatement(sql);
                    state.setInt(1, idcountry);
                    state.execute();
                    refreshTableCountry();
                    refreshComboCountry();
                    //idpr=-1;

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                countryTF.setText("");
            }
        }


    }
    class DeleteCarDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
			conn=DBConnection.getConnection();
            if (idcar > 0) {
                String sql = "delete from cartable where id=?";
                try {
                    state = conn.prepareStatement(sql);
                    state.setInt(1, idcar);
                    state.execute();
                    refreshTableCar();
                    refreshComboCar();
                    //idpr=-1;

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                CarTF.setText("");
            }
        }


    }
    class DeleteOrderDB implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
            if (idorder>0) {
                String sql="delete from ordertable where id=?";
                try {
                    state=conn.prepareStatement(sql);
                    state.setInt(1, idorder);
                    state.execute();
                    refreshTableOrders();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

//                fnameTF.setText("");
//                lnameTF.setText("");
//                quantityTF.setText("");
//                priceTF.setText("");

                clearFormOrders();
                idorder=-1;
            }
        }
    }



    class EditCountryDB implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
            if(idcountry >0) {
                String sql="update countrytable set country=? where id=?";

                try {
                    state=conn.prepareStatement(sql);
                    state.setString(1, countryTF.getText());
                    state.setInt(2, idcountry);
                    state.execute();
                    refreshTableCountry();
                    refreshComboCountry();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                countryTF.setText("");
            }

        }
    }
    class EditCarDB implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
            if(idcar >0) {
                String sql="update cartable set brand=? where id=?";

                try {
                    state=conn.prepareStatement(sql);
                    state.setString(1, CarTF.getText());
                    state.setInt(2, idcar);
                    state.execute();
                    refreshTableCar();
                    refreshComboCar();
                    refreshComboCarSearch();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                CarTF.setText("");
            }

        }
    }
    class EditOrderDB implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
//			conn=DBConnection.getConnection();
            if(idorder>0) {
                String sql="update ordertable set fname=?,lname=?, idcountry=?, idcar=?, quantity=?, price=? where id=?";

                try {
                    state=conn.prepareStatement(sql);

                    state.setString(1, fnameTF.getText());
                    state.setString(2, lnameTF.getText());

                    state.setInt(3, idcountry);
                    state.setInt(4, idcar);

                    state.setInt(5, Integer.parseInt(quantityTF.getText()));
                    state.setDouble(6, Double.parseDouble(priceTF.getText()));

                    state.setInt(7, idorder);
                    state.execute();

                    refreshTableOrders();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

//                fnameTF.setText("");
//                lnameTF.setText("");
//                quantityTF.setText("");
//                priceTF.setText("");

                clearFormOrders();
            }

        }
    }



    class SearchCountry implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            conn=DBConnection.getConnection();
            String sql="select * from countrytable where country=?";

            try {
                state=conn.prepareStatement(sql);
//                state.setInt(1, Integer.parseInt(ageTF.getText()));
                state.setString(1,countryTF.getText());
                result=state.executeQuery();
                countryTable.setModel(new MyTable(result));
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        }

    }
    class SearchCar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            conn=DBConnection.getConnection();
            String sql="select * from cartable where brand=?";

            try {
                state=conn.prepareStatement(sql);
//                state.setInt(1, Integer.parseInt(ageTF.getText()));
                state.setString(1,CarTF.getText());
                result=state.executeQuery();
                carTable.setModel(new MyTable(result));
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        }

    }
    class SearchOrder implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            conn=DBConnection.getConnection();
            String sql="select * from ordertable where fname=?";

            try {
                state=conn.prepareStatement(sql);
//                state.setInt(1, Integer.parseInt(ageTF.getText()));
                state.setString(1,fnameTF.getText());
                result=state.executeQuery();
                orderTable.setModel(new MyTable(result));
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        }

    }



    class RefreshCountry implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            conn=DBConnection.getConnection();
            String sql="select * from countrytable";

            try {
                state=conn.prepareStatement(sql);
                result=state.executeQuery();
                countryTable.setModel(new MyTable(result));
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        }

    }
    class RefreshCar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            conn=DBConnection.getConnection();
            String sql="select * from cartable";

            try {
                state=conn.prepareStatement(sql);
                result=state.executeQuery();
                carTable.setModel(new MyTable(result));
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        }

    }
    class RefreshOrder implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            conn=DBConnection.getConnection();
            String sql="select * from ordertable";

            try {
                state=conn.prepareStatement(sql);
                result=state.executeQuery();
                orderTable.setModel(new MyTable(result));
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        }

    }



    class DoubleSearch implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            refreshComboCarSearch();
            conn=DBConnection.getConnection();
            String sql="SELECT ORDERTABLE.id, ORDERTABLE.fname, COUNTRYTABLE.country FROM ORDERTABLE INNER JOIN COUNTRYTABLE ON ORDERTABLE.id = COUNTRYTABLE.id WHERE ORDERTABLE.fname = ? AND COUNTRYTABLE.country = ?";
            try {
                state=conn.prepareStatement(sql);

                state.setString(1,fnameSearchTF.getText());
                state.setString(2,countrySearchTF.getText());


                result=state.executeQuery();
                searchTable.setModel(new MyTable(result));

//                refreshTableSearch();

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }

}

