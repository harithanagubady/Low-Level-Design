## Requirements
1. The parking lot should have a capacity of 10.
2. Three parking spots: car, large, motorbike, electric, ebike, etc.
3. Parking lot should have multiple entry and exit points.
4. Types of Vehicles allowed to park: car, truck, electric, van, motorbike, ebike, etc.
5. Customers should collect a ticket at entrance and pay at exit.
6. Payment can be made using either cash or card
7. Payment should be calculated at hourly or per-minute basis.

### &nbsp; Follow-up:
1. Customer can choose near-entrance or near-lift parking spot.

## Details
1. Singleton Pattern: for Parking lot instance
2. Strategy Pattern: for Payment strategies (card or cash), for Pricing strategies (hourly or per-minute), for ParkingSpot location type (near-entrance or near-exit)