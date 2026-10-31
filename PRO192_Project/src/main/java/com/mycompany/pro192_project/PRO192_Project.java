/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pro192_project;

import java.util.ArrayList;
/**
 *
 * @author LOQ
 */
public class PRO192_Project {

    public static void main(String[] args) {
        PriceConfig config = new PriceConfig();
        RoomService roomService = new RoomService(config, new ArrayList<>());
        
        Room a101 = roomService.addStandardRoom("A101", 2);
        Room a102 = roomService.addServiceRoom("A102", 3);
        
        System.out.println("Tien phong A101(Standard): " + a101.calculateRoomCharge());
        System.out.println("Tien phong A102(Service): " + a102.calculateRoomCharge());
    }
}
