import java.util.*;

/**
 * Created by dev on 2/12/2015.
 */
public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<>(); //generic List Class used for collection of Seat objects
//    private Collection<Seat> seats = new ArrayList<>(); //can make this more generic by using a more generic variable type
    // but still using the same object type. Using an ArrayList is horribly inefficient here, since, supposing I was at
    //the very last row in the last seat, the code had to loop through all other items before reaching the seat

//    private Collection<Seat> seats = new LinkedList<>(); //can do linked list as object type instead and it still works
    // under the collections framework. Can also do this with HashSet and LinkedHashset



    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;

        int lastRow = 'A' + (numRows -1); //while the syntax here seems strange, adding a chart and an int to equal and
        //int, all "ascii" (american standard code for information interchange) characters can be represented as a digit
        //the intention of this statement is that beginning with 'A', there will be X number of additional rows, having
        //subtracted '1' since 'A' is already accounted for as the first row

//        int lastRow = 'A';    'A' on the ASCII table = 65
//        int lastRow = 'a';    'a' on th ASCII table = 97
//        System.out.println(lastRow + " is lastRow");

        for (char row = 'A'; row <= lastRow; row++) { //interestingly, it seems that the 'lastRow' int variable
            //is inferred and converted by Java back into its ASCII character to use in the 'char' type for loop.
            // a convenient but confusing shorthand at first glance.
            for(int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = new Seat(row + String.format("%02d", seatNum)); //generates seats in 2 digit format
                // such as A02, or B10
                seats.add(seat);
            }
        }
    }

    public String getTheatreName() {
        return theatreName;
    }

    public boolean reserveSeat(String seatNumber) {
//        Seat requestedSeat = null;
        Seat requestedSeat = new Seat(seatNumber); //strange that a new Seat is 'created' to check if that object actually
        //exists in the collection.
        int foundSeat = Collections.binarySearch(seats,requestedSeat,null);
        //binary search divides list into 2 groups and determines if search item is "below" or "above" the middle item which divides the groups
        //this is extremely fast. Returns the number of the item in the collection, such that 'A01' would return '1'
        System.out.println(foundSeat + " is found seat value ");
        if (foundSeat >= 0) {
            return seats.get(foundSeat).reserve(); //uses found # of item in collection, modifies boolean field of Seat object and returns it
        } else {
            System.out.println("there is no seat " + seatNumber);
            return false;
        }

 //THIS CONTENT IS ONLY REQUIRED WITHOUT THE COMPARABLE INTERFACE
//        for(Seat seat : seats) {
//            System.out.print("."); //note that .print vs .println means all on same line. Used to show how inefficient
//            // this code is by the number of iterations through the loop it takes to find the desired seat.
//            if(seat.getSeatNumber().equals(seatNumber)) { //could make this code better by not requiring user to input
//                // leading zero for single digit number, but whatever. Not the point here.
//                requestedSeat = seat;
//                break;
//            }
//        }
//
//        if(requestedSeat == null) {
//            System.out.println("There is no seat " + seatNumber);
//            return false;
//        }
//
//        return requestedSeat.reserve();'

    }

    // for testing
    public void getSeats() {
        for(Seat seat : seats) {
            System.out.println(seat.getSeatNumber());
        }
    }

    private class Seat implements Comparable<Seat>{ // Seat is appropriately an Inner Class of Theater, since having a Seat with no Theater is out of concept
        // Seat objects are only generated with initial generation of Theater object
        //with the addition of the Comparable interfacee and overriding its compareTo method, the efficiency of searching is increased/
        // not entirely sure how, but the Collections.binarySearch method needs this search object to have the Comparable interface, so
        // just going to accept it for now
        private final String seatNumber;
        private boolean reserved = false;

        public Seat(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

        public boolean reserve() {
            if(!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved");
                return true;
            } else {
                return false;
            }
        }

        public boolean cancel() {
            if(this.reserved) {
                this.reserved = false;
                System.out.println("Reservation of seat " + seatNumber + " cancelled");
                return true;
            } else {
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }
    }


}
