
package HomeFramePackage;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class MenuList<E extends Object> extends JList<E>{
    private final DefaultListModel model;
    private int selectedIndex = -1;
    public MenuList(){
        model = new DefaultListModel();
        setModel(model);
        this.setOpaque(false);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                if(SwingUtilities.isLeftMouseButton(me)){
                    int index = locationToIndex(me.getPoint());
                    Object o = model.getElementAt(index);
                    if(o instanceof MenuModel menu){
                        if(menu.getType() == MenuModel.MenuType.MENU){
                            selectedIndex = index;
                        }
                    }else{
                        selectedIndex = index;
                    }
                    repaint();
                }
            }
        });
    }
    
    @Override
    public ListCellRenderer<? super E> getCellRenderer(){
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean selected, boolean focus){
                MenuModel data;
                if(o instanceof MenuModel menuModel){
                    data=menuModel;
                }else{
                    data= new MenuModel("",o+"",MenuModel.MenuType.EMPTY);
                }
                MenuItem item = new MenuItem(data);
                item.setSelected(selectedIndex==i);
                return item;
            }
        };
        
    }
    public void addItem(MenuModel data){
        model.addElement(data);
    }
}
