import ru.itis.inform.store.dao.ItemsDao;
import ru.itis.inform.store.dao.ItemsDaoFileBasedImpl;

import java.util.Scanner;

/**
 * Created by love on 10.02.16.
 */
public class Main  {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);//debug
        ItemsDao dao = new ItemsDaoFileBasedImpl();
        ((ItemsDaoFileBasedImpl) dao).printItems();
        for(;;){
            String m = sc.nextLine();
            if(m.length()>4 && m.substring(0,3).equals("add")){
                ((ItemsDaoFileBasedImpl) dao).add(m.substring(4,m.length()));
                System.out.println("added!");
            }else if(m.length()>7 && m.substring(0,6).equals("select")){
                if(dao.select(m.substring(7,m.length()))!=null)
                System.out.println("selected: " + dao.select(m.substring(7,m.length())).getName());
            } else if(m.length()>7 && m.substring(0,6).equals("delete")){
                dao.delete(m.substring(7,m.length()));
                System.out.println("deleted! (if exists)");
            }

            ((ItemsDaoFileBasedImpl) dao).printItems();

        }
    }
}
