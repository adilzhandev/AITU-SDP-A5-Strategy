
# Navigation System — Strategy Design Pattern

## Project Overview

Navigation System is a Java 17 application that demonstrates the **Strategy Design Pattern**, a behavioral software design pattern.

The application simulates a navigation system that estimates travel distance and time using three transportation methods:

- Car
- Walking
- Bicycle

Each transportation method implements its own route calculation algorithm. The Strategy Pattern allows the application to switch between these algorithms at runtime without modifying the Navigator class.

This project was developed for Assignment #5 — Strategy Pattern in the Software Design Patterns course at Astana IT University.

## Technologies Used

- Java 17
- IntelliJ IDEA
- Git and GitHub
- Strategy Design Pattern
- Object-Oriented Programming (OOP)

No external libraries or dependencies are required.

## Features

- Calculate geographical distance using the Haversine formula.
- Estimate travel distance for different transportation methods.
- Calculate estimated travel time based on average speed.
- Account for transportation-specific delays.
- Switch navigation strategies at runtime.
- Add new navigation strategies without modifying the Navigator class.
- Demonstrate polymorphism and clean code principles.

## Project Structure

```text
NavigationStrategy/
│
├── src/
│   └── navigation/
│       ├── Location.java
│       ├── Route.java
│       ├── RouteStrategy.java
│       ├── CarStrategy.java
│       ├── WalkStrategy.java
│       ├── CycleStrategy.java
│       ├── Navigator.java
│       └── Main.java
│
├── .gitignore
└── README.md
```

## Strategy Pattern Architecture

The Strategy Pattern defines a family of algorithms, encapsulates each algorithm in a separate class, and makes them interchangeable.

### Strategy Interface — RouteStrategy

The `RouteStrategy` interface defines a common method for all route calculation algorithms.

```java
public interface RouteStrategy {
    Route buildRoute(Location start, Location destination);
}
```

Each concrete strategy implements this interface.

### Concrete Strategies

The application contains three strategies.

#### CarStrategy

Calculates estimated driving distance and travel time.

Parameters:

- Road adjustment factor: 1.30
- Average speed: 40 km/h
- Traffic delay: 8 minutes

#### WalkStrategy

Calculates estimated walking distance and travel time.

Parameters:

- Pedestrian path adjustment factor: 1.15
- Average speed: 5 km/h
- Crossing delay: 3 minutes

#### CycleStrategy

Calculates estimated bicycle distance and travel time.

Parameters:

- Bicycle path adjustment factor: 1.20
- Average speed: 16 km/h
- Traffic light delay: 2 minutes

### Context — Navigator

The `Navigator` class stores a reference to the `RouteStrategy` interface.

It delegates route calculations to the currently selected strategy.

The strategy can be changed dynamically using the `setStrategy()` method.

```java
Navigator navigator = new Navigator(new CarStrategy());

System.out.println(navigator.buildRoute(start, destination));

navigator.setStrategy(new WalkStrategy());

System.out.println(navigator.buildRoute(start, destination));
```

The Navigator class does not depend on concrete strategy implementations.

### Client — Main

The `Main` class demonstrates how different strategies can be selected and switched during runtime.

### Supporting Classes

#### Location

The `Location` record stores geographical coordinates:

- Location name
- Latitude
- Longitude

It also calculates the geographical distance between two locations using the Haversine formula.

#### Route

The `Route` record stores the calculation result:

- Transportation method
- Starting location
- Destination
- Estimated distance
- Estimated travel time
- Route description

## Mathematical Calculations

### 1. Haversine Formula

The application uses the Haversine formula to calculate the shortest geographical distance between two points on Earth's surface.

The approximate Earth radius is 6371 kilometers.

### 2. Route Distance

The calculated geographical distance is adjusted depending on the transportation method.

```text
Route Distance = Geographic Distance × Route Factor
```

Different transportation methods use different route factors.

### 3. Estimated Travel Time

Travel time is calculated using the following formula:

```text
Time = (Distance / Average Speed) × 60 + Delay
```

Where:

- Distance is measured in kilometers.
- Average speed is measured in km/h.
- Delay is measured in minutes.
- Time is expressed in minutes.

The result is rounded to a whole number.

### Limitations

This application is an educational navigation simulation.

It does not use actual GPS routing, real road networks, live traffic information, or map APIs.

Distances and travel times are estimates based on geographical coordinates and predefined transportation parameters.

## How to Run

### Option 1: IntelliJ IDEA

1. Clone or download the GitHub repository.
2. Open IntelliJ IDEA.
3. Select `File → Open`.
4. Select the project directory.
5. Make sure Java JDK 17 or newer is installed.
6. Mark the `src` directory as Sources Root if necessary.
7. Open `Main.java`.
8. Click `Run Main.main()`.

### Option 2: Command Line

Check the Java version:

```bash
java -version
javac -version
```

Compile the project from its root directory:

```bash
javac -d out src/navigation/*.java
```

Run the application:

```bash
java -cp out navigation.Main
```

## Example Output

```text
=== CAR ROUTE ===
Transport: Car
From: Home
To: University
Distance: 5.64 km
Estimated time: 16 minutes
Details: Driving on roads with estimated traffic delays

=== WALKING ROUTE ===
Transport: Walking
From: Home
To: University
Distance: 4.99 km
Estimated time: 63 minutes
Details: Using pedestrian paths and crossings

=== BICYCLE ROUTE ===
Transport: Bicycle
From: Home
To: University
Distance: 5.20 km
Estimated time: 22 minutes
Details: Using bicycle-friendly roads and cycle lanes
```

## How to Add a New Strategy

The Strategy Pattern allows new transportation methods to be introduced without modifying the existing Navigator class.

For example, we can add a `BusStrategy`.

### Step 1: Create BusStrategy.java

```java
package navigation;

public class BusStrategy implements RouteStrategy {

    @Override
    public Route buildRoute(Location start, Location destination) {

        double distance = start.distanceTo(destination) * 1.4;

        long minutes = Math.round(distance / 25.0 * 60) + 10;

        return new Route(
                "Bus",
                start,
                destination,
                distance,
                minutes,
                "Public transportation with estimated waiting time"
        );
    }
}
```

### Step 2: Select the new strategy

```java
navigator.setStrategy(new BusStrategy());

System.out.println(navigator.buildRoute(start, destination));
```

No changes to `Navigator` or existing concrete strategies are required.

## Clean Code Principles

The project applies several Clean Code principles.

### 1. Single Responsibility Principle

Each concrete strategy is responsible for one type of route calculation.

For example, `CarStrategy` calculates driving routes, while `WalkStrategy` calculates walking routes.

### 2. Meaningful Naming

Classes, methods, and variables have descriptive names.

Examples:

- `RouteStrategy`
- `CarStrategy`
- `Navigator`
- `buildRoute()`
- `estimatedMinutes`
- `distanceKm`

### 3. Polymorphism Instead of Conditional Logic

The Navigator delegates route calculations to the strategy interface.

There is no need for `if/else` or `switch` statements inside the Navigator to select transportation algorithms.

### 4. Dependency on Abstractions

The Navigator depends on the `RouteStrategy` interface rather than concrete strategy classes.

This reduces coupling between components.

### 5. Open/Closed Principle

The system is open for extension but closed for modification.

New strategies can be implemented without modifying the Navigator or existing strategies.

## Strategy vs. State Pattern

Both Strategy and State are behavioral design patterns that use composition and polymorphism.

The Strategy Pattern allows the client to select and switch between different algorithms.

The State Pattern allows an object to change its behavior depending on its internal state.

In this project, Strategy is more appropriate because the transportation algorithm is explicitly selected by the client.

The Navigator does not automatically transition between internal states.

## Advantages

- Flexible algorithm selection
- Runtime strategy switching
- Improved code readability
- Reduced coupling
- Easy extensibility
- Separation of responsibilities
- Better maintainability

## Disadvantages

- Additional classes and interfaces
- Increased complexity for very small applications
- The client must know which strategy to select

## Conclusion

This project demonstrates the implementation of the Strategy Design Pattern in Java.

The navigation system supports multiple transportation algorithms through a common interface.

Each strategy encapsulates its own calculation logic, while the Navigator delegates the execution to the selected strategy.

Using this approach makes the application more flexible, extensible, and maintainable.

The project also demonstrates important object-oriented programming concepts, including interfaces, encapsulation, composition, and polymorphism.

## References

1. Freeman, E., & Robson, E. (2021). *Head First Design Patterns* (2nd ed.).
2. Martin, R. C. (2008). *Clean Code: A Handbook of Agile Software Craftsmanship*.
3. Refactoring.Guru — [Strategy Design Pattern](https://refactoring.guru/design-patterns/strategy)

## Academic Information

**University:** Astana IT University

**Course:** Software Design Patterns

**Assignment:** Assignment #5 — Strategy Pattern

**Option:** A — Navigation / Route Planning

**Programming Language:** Java 17

**Academic Year:** 2026–2027
