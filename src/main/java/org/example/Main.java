package org.example;

import java.util.*;

class PhoneBook {
    public HashMap<String, HashSet<String>> book;

    public PhoneBook() {
        book = new HashMap<>();
    }

    public boolean addContact(String name, String phone) {
        if (book.containsKey(name)) {
            if(!book.get(name).contains(phone)) return book.get(name).add(phone);
            else return false;
        }
        HashSet<String> temp = new HashSet<>();
        temp.add(phone);
        book.put(name, temp);
        return true;
    }

    public void print() {
        HashMap<String, Integer> sizes = new HashMap<>();
        book.forEach((k, v) -> {
            sizes.put(k, v.size());
        });

        sizes.entrySet().stream()
                .sorted(Map.Entry
                        .comparingByValue(Comparator.reverseOrder()))
                            .forEach(e -> {
                                System.out.print(e.getKey() + "\n");
                                book.get(e.getKey()).forEach(System.out::println);
                            });

    }
}

public class Main {
    public static void main(String[] args) {
        /*Задание
        Реализуйте структуру телефонной книги с помощью HashMap. Программа также должна учитывать, что во входной структуре
        будут повторяющиеся имена с разными телефонами, их необходимо считать,
        как одного человека с разными телефонами. Вывод должен быть отсортирован по убыванию числа телефонов.*/

        PhoneBook phonebook = new PhoneBook();
        Scanner in = new Scanner(System.in);
        String person = "";

        // Заполняем людьми
        while (true) {
            System.out.println("Enter new record (FirstName MiddleName LastName, Phone): ");
            person = in.nextLine();
            if (person.equalsIgnoreCase("end")){
                System.out.println("-".repeat(15) + "EXIT" + "-".repeat(15));
                break;
            }
            String[] splittedPerson = person.split(", ");
            System.out.println(phonebook
                    .addContact(splittedPerson[0], splittedPerson[1])? "SUCCESS" : "ERROR");
        }

        phonebook.print();

    }
}