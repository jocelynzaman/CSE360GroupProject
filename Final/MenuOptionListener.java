import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

class MenuOptionListener implements MenuListener {
    About aboutDialog = new About();

    @Override
    public void menuSelected(MenuEvent menuEvent) {
        System.out.println("Menu Selected");
        // About aboutDialog = new About();
        aboutDialog.prepareGUI();
    }

    @Override
    public void menuDeselected(MenuEvent menuEvent) {
        System.out.println("Menu Deselected");
    }

    @Override
    public void menuCanceled(MenuEvent menuEvent) {

    }

    
}