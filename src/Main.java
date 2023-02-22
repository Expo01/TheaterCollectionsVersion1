public class Main {

    public static void main(String[] args) {
	    Theatre theatre = new Theatre("Olympian", 3, 5);
        theatre.getSeats();
        if(theatre.reserveSeat("B02")) {
            System.out.println("Please pay");
        } else {
            System.out.println("Sorry, seat is taken");
        }
        if(theatre.reserveSeat("B2")) {
            System.out.println("Please pay");
        } else {
            System.out.println("Sorry, seat is taken");
        }

    }
}
