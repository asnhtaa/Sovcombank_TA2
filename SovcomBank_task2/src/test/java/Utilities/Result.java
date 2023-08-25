package Utilities;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Result {
        public String gender;
        public Name name;
        public Location location;
        public String email;
        public Login login;
        public Dob dob;
        public Registered registered;
        public String phone;
        public String cell;
        public Id id;
        public Picture picture;
        public String nat;

        public String getGender() {
            return gender;
        }

        public Name getName() {
            return name;
        }

        public Location getLocation() {
            return location;
        }

        public String getEmail() {
            return email;
        }

        public Login getLogin() {
            return login;
        }

        public Dob getDob() {
            return dob;
        }

        public Registered getRegistered() {
            return registered;
        }

        public String getPhone() {
            return phone;
        }

        public String getCell() {
            return cell;
        }

        public Id getId() {
            return id;
        }

        public Picture getPicture() {
            return picture;
        }

        public String getNat() {
            return nat;
        }
    public void validateFieldNotNull(String fieldPath) {
        switch (fieldPath) {
            case "gender":
                assertNotNull(gender);
                break;
            case "name":
                assertNotNull(name);
                break;
            case "location":
                assertNotNull(location);
                break;
            case "email":
                assertNotNull(email);
                break;
            case "login":
                assertNotNull(login);
                break;
            case "dob":
                assertNotNull(dob);
                break;
            case "registered":
                assertNotNull(registered);
                break;
            case "phone":
                assertNotNull(phone);
                break;
            case "cell":
                assertNotNull(cell);
                break;
            case "id":
                assertNotNull(id);
                break;
            case "picture":
                assertNotNull(picture);
                break;
            case "nat":
                assertNotNull(nat);
                break;
            default:
                throw new IllegalArgumentException("Invalid fieldPath: " + fieldPath);
        }
    }
}
