/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pro192project;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Thái Quang Huy
 */
public class PRO192Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Room> rooms = new ArrayList<Room>();
        rooms.add(new StandardRoom("A101", 2, 1_200_000));
        rooms.add(new ServiceRoom("A102", 3, 1_800_000, 300_000));
        
        System.out.println("=== K01: tinh tien phong qua tham chieu Room ===");
        for(Room r : rooms){
            System.out.printf("%s (%s) -> %,d VND%n", r.getRoomId(), r.getRoomType(),
                    r.calculateRoomCharge());
        }
        System.out.println("Mong doi: A101 = 1,200,000 ; A102 = 2,100,000");
        
        System.out.println("\n=== Dau vao sai (phai bi tu choi) ===");
        try{
            new StandardRoom("", 2, 1_200_000);
        }catch(IllegalArgumentException e){
            System.out.println("OK: " + e.getMessage());
        }
        
        try{
            new StandardRoom("B 1", 2, 1_200_000);
        }catch(IllegalArgumentException e){
            System.out.println("OK: " + e.getMessage());
        }
        
        try{
            new StandardRoom("B1", 11, 1_200_000);
        }catch(IllegalArgumentException e){
            System.out.println("OK: " + e.getMessage());
        }
        
        try{
            new ServiceRoom("B2", 2, 1_800_000, 10_000_001L);
        }catch(IllegalArgumentException e){
            System.out.println("OK: " + e.getMessage());
        }
    }
    
}
