import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        //* for checking whether the restaurant is open or not...

        //sets a variable with current running time...
        LocalTime current_time = getCurrentTime();
        if(current_time.isBefore(closingTime) && current_time.isAfter(openingTime)) {
            return true;
        }else{
            return false;
        }
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        //* introducing/fetching the items in menu....
        menu = new ArrayList<Item>();
        menu.add(new Item("Punjabi Thali",699));
        menu.add(new Item("South Thali",899));
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }


    //calculation of ordered food items....
    int totalAmt = 0;
    public int getTotalPlacedOrder(List<String> selectedItems) {

        //it'll only execute when selectedItems has data.....
        if(selectedItems != null && !selectedItems.isEmpty()){

            for (String placedItemName : selectedItems) {

                //menu loop starts here ....
                for (Item item : menu) {
                    if (item.getName().equals(placedItemName)) {
                        totalAmt = totalAmt + item.getPrice();  // also use  => += item.getPrice();
                        break;
                    }
                }
                //...........................

            }
        }

        return totalAmt;
    }


}
