package by.romanovich.it.loader;

import by.romanovich.it.dao.PersonDao;
import by.romanovich.it.dao.exeptions.DaoExeption;
import by.romanovich.it.pojos.Person;
import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * This is class Menu
 * @author Romanovich Andrey
 * @version 1.0
 */
public class MenuLoader {

    private static final Logger log = Logger.getLogger(MenuLoader.class);

    private static PersonDao personDao = null;

    public static Boolean needMenu = true;

    public static void printMenu() {
        System.out.println(" Options:");
        System.out.println("         0. Exit");
        System.out.println("         1. Delete Person");
        System.out.println("         2. Get Person");
        System.out.println("         3. Save or Update Person");
    }

    public static PersonDao getPersonDao() {
        if(personDao == null) {
            personDao = new PersonDao();
        }
        return personDao;
    }

    public static Person findPerson() {
        System.out.println("Get by Id. Please enter person id:");
        System.out.print("Id - ");

        Scanner scanner = new Scanner(System.in);
        Person person = null;
        Long id = scanner.nextLong();
        try {
            person = getPersonDao().get(id);
        } catch (DaoExeption e) {
            log.error("Get Person in the database failed." + e);
        } catch (NullPointerException e) {
            log.error("Person value null." + e);
        }
        System.out.print(person);
        return person;
    }

    public static Person createPerson(Person person) {
        System.out.println("Please enter person description:");
        System.out.print("Name - ");

        if(person == null)
            person = new Person();
        Scanner scanner = new Scanner(System.in);
        person.setName(scanner.nextLine());
        System.out.print("Surname - ");
        person.setSurname(scanner.nextLine());
        System.out.print("Age - ");
        person.setAge(scanner.nextInt());
        return person;
    }

    public static void menu() throws DaoExeption {
        Person person = null;
        while (needMenu) {
            printMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    break;
                case 2:
                    person = findPerson();
                    break;
                case 3:
                    person = createPerson(person);
                    getPersonDao().saveOrOpdate(person);
                    break;
                default:
                    needMenu = true;
                    break;
            }
            needMenu = true;
        }
    }
}
