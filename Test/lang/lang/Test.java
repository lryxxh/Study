package lang;

/**
 * Test.java
 * Created by liurenyong at 2013-8-28
 * @Copyright (c) 2013 China National Petroleum Corporation All Rights Reserved.
 */

/**
 * 
 * @author liurenyong 2013-8-28
 */
public class Test {

    /**
     * 
     * @param args
     * @author liurenyong 2013-8-28
     */
    public static void main(String[] args) {
//        try {
//            Object nulObject = LocatorService.locate("local", "realtime", "public", "locator_srv");
//            System.out.println(nulObject);
//        } catch (LocatorException e) {
//            e.printStackTrace();
//        }
//          Map<String, String> map = LoginCtrl.getInstance().getLoginMMIUser().getPermissionAreas(1);
//          System.out.println(map.size());
//          for (String key : map.keySet()) {
//              System.out.println(key  + " " + map.get(key));
//          }
//        String dbid = "local,null,realtime,fes,fes,scd-sun-2";
//        String attribName = "stid";
//        Long attribValue = 480477785245089795L;
//        Vector<String> property = new Vector<String>();
//        String str = "key,id,descr,psid,stid,stno,linkid,linkno,index_no,extendid,engmax,engmin,rawmax,rawmin,slope,intercepte,rawvalue,value,polarity,quality,phyno,chgflag,freshtime,pro,card_no,point_no,flag ,oo_station,fatherid,father_key,stationid";
//        String[] array = str.split(",");
//        for (String str2: array) {
//            property.add(str2);
//        }
//        
//        try {
//           Object object = DataClient.RTDBRead(dbid, "downai", property, attribName, attribValue);
//           System.out.println(object);
//        } catch (DataFaultException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (ServiceConnectionException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (LocatorException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (ProxyException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        /*
        JFrame frame = new JFrame();
        frame.setSize(400, 300);

        Object[] columns = new Object[] { "column1", "column2", "column3", "column4", "column5" };
        Object[][] datas = new Object[][] { { "一二", "2131", 333, 111, new Date() }, { "二三", "543", 841, 1234, new Date() },
                { "三四", "werw", 546, 445, new Date() }, { "七八", "6323", 234, 456, new Date() }, { "小", "rrwer", 123, 333, new Date() },
                { "四五", "jf2d", 3098, 5432, new Date() }, { "物流", "werww", 890, 789, new Date() } };
        CustomTableModel model = new CustomTableModel();
      
        model.setDataVector(datas, columns);
        final CustomTable table = new CustomTable(){
             (non-Javadoc)
             * @see javax.swing.JTable#isCellEditable(int, int)
             
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        table.setModel(model);
        // TableSorter sorter = new TableSorter(model);
        // table.setModel(sorter);
        model.setEditable(true);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                  int row =  table.getSelectedRow();
                  if(row != -1) {
                      System.out.println(table.getValueAt(row, 0));
                      
                  }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        frame.getContentPane().add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    */
       /* try {
            Object obj = DataClient.PRIVHasGivenFunc("local,null,realtime,public,priv,null", "cnpc", "", "QCS", "scd-sun-2", 1, 0);
            System.out.println(obj);
        } catch (DataFaultException e) {
            e.printStackTrace();
        } catch (ServiceConnectionException e) {
            e.printStackTrace();
        } catch (LocatorException e) {
            e.printStackTrace();
        } catch (ProxyException e) {
            e.printStackTrace();
        }    */
        
//        int t = getValue();
//        System.out.println(t);
    }
    
    public static int getValue() {
        int a = 0;
        try {
            a++;
            if(true) {
                throw new Exception();
            }
            System.out.println(a);
            return a;
        } catch (Exception e) {
            a++;
            return a;
        } finally {
            a++;
//            return a;
        }
    }
}


