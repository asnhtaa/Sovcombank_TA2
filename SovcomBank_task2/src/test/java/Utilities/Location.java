package Utilities;

public class Location {
        public Street street;
        public String city;
        public String state;
        public String country;
        public String postcode;
        public Coordinates coordinates;
        public Timezone timezone;

        public Street getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getCountry() {
            return country;
        }

        public String getPostcode() {
            return postcode;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }

        public Timezone getTimezone() {
            return timezone;
        }
    }
