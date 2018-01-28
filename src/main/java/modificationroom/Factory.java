package modificationroom;

import database.entity.Room;

/**
 * Created by Kuba on 2018-01-14.
 */
public class Factory {


    public Room addOrModifyRoom(String action){
        Room room = null;
        if(action.equals("D")){
            room = new RoomAdded();
            room.display();
        }else if(action.equals("E")){
            room = new RoomModification();
            room.display();
        }else{
            System.out.println("Przypau");
        }

        return room;
    }

}
