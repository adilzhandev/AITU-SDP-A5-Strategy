
package navigation;

public class Main {

    public static void main(String[] args) {

        Location start = new Location(
                "Home",
                51.1282,
                71.4304
        );

        Location destination = new Location(
                "University",
                51.0900,
                71.4180
        );

        Navigator navigator = new Navigator(new CarStrategy());

        System.out.println("=== CAR ROUTE ===");
        System.out.println(navigator.buildRoute(start, destination));

        navigator.setStrategy(new WalkStrategy());

        System.out.println("\n=== WALKING ROUTE ===");
        System.out.println(navigator.buildRoute(start, destination));

        navigator.setStrategy(new CycleStrategy());

        System.out.println("\n=== BICYCLE ROUTE ===");
        System.out.println(navigator.buildRoute(start, destination));
    }
}
