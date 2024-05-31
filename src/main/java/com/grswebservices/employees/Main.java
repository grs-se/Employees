package com.grswebservices.employees;

import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;

public class Main {

    private static Set<IEmployee> employees;
    private static Map<String, Integer> salaryMap;
//    private static Map<String, Employee> empMap;

    public static void main(String[] args) {
        String peopleText = """
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=4000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=5000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=6000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=7000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=8000,yoe=10,iq=140}
            Flinstone, Fred, 1/1/1900, Programmerzzzzz, {locpd=9000,yoe=10,iq=140}
            Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
            Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
            Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=1630,yoe=3,iq=115}
            Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
            Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100,dr=4}
            Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200,dr=2}
            Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
            Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175,dr=20}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
            Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=4}
            Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
            Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=6}
            Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}
            Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
            """;

        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(peopleText);

        int totalSalaries = 0;
        IEmployee employee = null;
        Set<IEmployee> employees = populateEmployees(peopleMat);

        totalSalaries = tabulateEmployees(employees, totalSalaries);

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be %s%n", currencyInstance.format(totalSalaries));

        System.out.println(employees.size());
//        System.out.println(empMap);
//        System.out.println(salaryMap.values());
//        System.out.println(salaryMap.keySet());
        System.out.println(salaryMap.entrySet());
        for (Map.Entry<String, Integer> entry : salaryMap.entrySet()) {
            System.out.printf("Key = %s, Value= %s%n", entry.getKey(), entry.getValue());
        }

    }

    private static void sortEmployeesV3(List<IEmployee> employees) {
        Collections.sort(employees,(o1, o2) -> {
            if (o1 instanceof Employee emp1 && o2 instanceof Employee emp2) {
                int lnameResult = emp1.lastName.compareTo(emp2.lastName);
                return lnameResult != 0 ? lnameResult : Integer.compare(emp1.getSalary(), emp2.getSalary());
            }
            return 0;
        });
    }

    private static void sortEmployeesV2(List<IEmployee> employees) {
        // Lambda version
          employees.sort((o1, o2) -> {
            if (o1 instanceof Employee emp1 && o2 instanceof Employee emp2) {
                int lnameResult = emp1.lastName.compareTo(emp2.lastName);
                return lnameResult != 0 ? lnameResult : Integer.compare(emp1.getSalary(), emp2.getSalary());
            }
            return 0;
        });
    }

    private static void sortEmployeesV1(List<IEmployee> employees) {
        employees.sort(new Comparator<IEmployee>() {
            @Override
            // comparator - sorting algorithm (technique) - merge-sort - a number fo strategies of how to sort things
            // returns an integer of which one comes first or second, o1 comes before o2? = -1, o2 before o1 = 1, both equal = 0
            public int compare(IEmployee o1, IEmployee o2) {
                // if o1 is an instance of Employee then go ahead and create a variable of type Employee and use that recast o1 as an actual Employee
                if (o1 instanceof Employee emp1 && o2 instanceof Employee emp2) {
                    // if lastNames aren't equal then sort by lastName but if they are equal then sort by salary
                    int lnameResult = emp1.lastName.compareTo(emp2.lastName);
                    return lnameResult != 0 ? lnameResult : Integer.compare(emp1.getSalary(), emp2.getSalary());
                    // return emp1.dob.compareTo(emp2.dob);
                }
                return 0;
            }
        });
    }


    private static List<String> createUndesirablesList() {
        return new ArrayList<>(List.of("Wilma5", "Barney4", "Fred2"));
    }

    private static int tabulateEmployees(Set<IEmployee> employees, int totalSalaries) {
        for (IEmployee worker: employees) {
            System.out.println(worker.toString());
            totalSalaries += worker.getSalary();
        }
        return totalSalaries;
    }

    private static Set<IEmployee> populateEmployees(Matcher peopleMat) {
        IEmployee employee;
        employees = new TreeSet<>((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()));
        salaryMap = new LinkedHashMap<>();
        while (peopleMat.find()) {
            employee = Employee.createEmployee(peopleMat.group());
            Employee emp = (Employee) employee;
            employees.add(employee);
            salaryMap.putIfAbsent(emp.firstName, emp.getSalary());
        }
        return employees;
    }

    // remove an element from a collection while iterating over the collection (traditional loop)
    // better ways to do this
    private static void removeUndesirables(List<IEmployee> employees, List<String> removalNames) {
        for (Iterator<IEmployee> it = employees.iterator(); it.hasNext();) {
            IEmployee worker = it.next();
            if (worker instanceof Employee tmpWorker) {
                if (removalNames.contains(tmpWorker.firstName)) {
                    it.remove();
                }
            }
        }
    }

    public int getSalary(String firstName) {
        return salaryMap.getOrDefault(firstName, -1);
    }
}

///////////////////////////////////////////////////////////////
// NOTES
//List<String> undesirables = createUndesirablesList();
//
//removeUndesirables(employees, undesirables);
//
//// Additional List Methods
//// employees.add(0, new Programmer(""));
//
//IEmployee third = employees.get(2);
//        employees.indexOf(third);
//
//// sometimes old libraries will require you to pass in an array of objects rather than a collection
//// 2nd approach should be preferred as it is more type safe.
//Object[] genericArray = employees.toArray();
//// specify size of array
//IEmployee[] otherArray = employees.toArray(new IEmployee[0]);
//
////        List<IEmployee> sublist = employees.subList(0, 3);
////        System.out.printf("sublist = %s", sublist);
//
//// set = replace
//        employees.set(0, Employee.createEmployee("Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}"));
//
//// List.contains() & Object.equals()
//IEmployee myEmp = employees.get(5);
//        System.out.println(employees.contains(myEmp)); // true
//
//IEmployee employee1 = Employee.createEmployee("Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=101}");
//        System.out.println(employees.contains(employee1)); // false - duplicated an employee verbatim using the same data pamameters but we get false
//
//        System.out.println(myEmp.equals(employee1));
//
//sortEmployeesV1(employees);
//
//sortEmployeesV2(employees);
//
//sortEmployeesV3(employees);
//
////        undesirables.sort(Comparator.naturalOrder());
//        System.out.println(undesirables);
//
////        Collections.shuffle();
//        Collections.sort(employees, Comparator.reverseOrder());


//        Programmer p1 = new Programmer("");
//        Programmer p2 = new Programmer("");
// default implementation of equals method compares whether p1 and p2 are both pointing to the same address in memory
//        p1.equals(p2); // p1 == p2: equivalent - equals operator tests whether two object references point to the same address in memory, wich would mean effetcively that they are the same object
// however we may not want that. When we ask is p1 = to p2 what we usually actually mean is are the intrinsic and unchangeable properties of p1 actually the same, a different question to do p1 and p2 point to the same address in memory. Interesting distinction.
// if we do not overide the default equals method then we simply ask whether the two object point tot he same address in memory
// can provide own custom implementation of equals method.

//        List<String> newStrings = new ArrayList<>();
//        newStrings.addAll(undesirables);
//        System.out.println(newStrings.size());
//

//IEmployee first = employees.get(0);
//IEmployee second = employees.get(1);
//IEmployee third = employees.get(2);
//
//        employees.remove(first);
//        employees.remove(second);
//        employees.remove(third);
//
//        employees.remove(0);

// JAVA 17 switch
//            switch (employee) {
//                case Programmer prog -> System.out.println(prog.getIq());
//                case Manager man -> System.out.println(man.toString());
//                case Analyst analyst ->
//                    // older / conventional way before pattern matching
//                        System.out.println(analyst.toString());
//                case CEO ceo -> System.out.println();
//                default -> System.out.println("Default output");
//            }

// pattern matching available Java 16
//            if (employee instanceof Programmer prog) {
//                System.out.println(prog.getIq());
//            } else if (employee instanceof Manager man) {
//                System.out.println(man.toString());
//            } else if (employee instanceof Analyst) {
//                // older / conventional way before pattern matching
//                Analyst analyst = (Analyst) employee;
//                System.out.println(analyst.toString());
//            } else if (employee instanceof CEO) {
//                System.out.println();
//            } else {
//                System.out.println("Default output");
//            }
// Not idiomatic Java:
//            if (employee.getClass().equals(Programmer.class)) {
//                System.out.println();
//            } else if (employee.getClass().equals(Manager.class)) {
//                System.out.println();
//            } else {
//                System.out.println("Default output");
//            }
// Polymorphism
//            System.out.println(employee.toString());
//            totalSalaries+= employee.getSalary();

/*
/// BAD PROCEDURAL IMPLEMENTATION
package com.grswebservices.employees;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String peopleText = """
    Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
    Flinstone, Fred, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
    Flinstone, Fred, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
    Flinstone, Fred, 1/1/1900, Programmer, {locpd=1630,yoe=3,iq=115}
    Flinstone, Fred, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
    Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
    Rubble, Barney, 2/2/1905, Manager, {orgSize=100,dr=4}
    Rubble, Barney, 2/2/1905, Manager, {orgSize=200,dr=2}
    Rubble, Barney, 2/2/1905, Manager, {orgSize=500,dr=8}
    Rubble, Barney, 2/2/1905, Manager, {orgSize=175,dr=20}
    Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
    Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=4}
    Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=5}
    Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=6}
    Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=9}
    Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
    """;

        String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
        Pattern peoplePat = Pattern.compile(peopleRegex);
        Matcher peopleMat = peoplePat.matcher(peopleText);

        String progRegex = "\\w+=(?<locpd>\\w+),\\w+=(?<yoe>\\w),\\w+=(?<iq>\\w)";
        Pattern coderPat = Pattern.compile(progRegex);

        String mgrRegex = "\\w+=(?<orgSize>\\w+),\\w+=(?<dr>\\w+)";
        Pattern mgrPat = Pattern.compile(mgrRegex);

        String analystRegex = "\\w+=(?<projectCount>\\w+)";
        Pattern analystPat = Pattern.compile(analystRegex);

        String ceoRegex = "\\w+=(?<avgStockPrice>\\w+)";
        Pattern ceoPat = Pattern.compile(ceoRegex);

        int totalSalaries = 0;
        // peopleMat.find() returns a boolean
        while (peopleMat.find()) {
            totalSalaries+= switch (peopleMat.group("role")) {
                case "Programmer" -> {
                    Programmer programmer = new Programmer(peopleMat.group());
                    String details = peopleMat.group("details");
                    Matcher coderMat = coderPat.matcher(peopleMat.group("details"));
//                    System.out.println(details);
                    int salary = 0;
                    if (coderMat.find()) {
                        int locpd = Integer.parseInt(coderMat.group("locpd"));
                        int yoe = Integer.parseInt(coderMat.group("yoe"));
                        int iq = Integer.parseInt(coderMat.group("iq"));
                        // System.out.printf("Programmer loc: %s yoe: %s iq: %s%n", locpd, yoe, iq);
                        salary = 3000 + locpd * yoe * iq;
                    } else {
                        salary = 3000;
                    }
                    String lastName = peopleMat.group("lastName");
                    String firstName = peopleMat.group("firstName");
                    System.out.printf("%s: %s: %s%n", lastName, firstName, NumberFormat.getCurrencyInstance().format(salary));
                    yield salary;
                }
                case "Manager" -> {
                    String details = peopleMat.group("details");
                    Matcher mgrMat = mgrPat.matcher(details);
                    int salary = 0;
                    if (mgrMat.find()) {
                        int orgSize = Integer.parseInt(mgrMat.group("orgSize"));
                        int directReports = Integer.parseInt(mgrMat.group("dr"));
                        salary = 3500 + orgSize * directReports;
                    } else {
                        salary = 3500;
                    }
                    String lastName = peopleMat.group("lastName");
                    String firstName = peopleMat.group("firstName");
                    System.out.printf("%s: %s: %s%n", lastName, firstName, NumberFormat.getCurrencyInstance().format(salary));
                    yield salary;
                }
                case "Analyst" -> {
                    String details = peopleMat.group("details");
                    Matcher analystMat = analystPat.matcher(details);
                    int salary = 0;
                    if (analystMat.find()) {
                        int projectCount = Integer.parseInt(analystMat.group("projectCount"));
                        salary = 2500 + projectCount * 2;
                    } else {
                        salary = 2500;
                    }
                    String lastName = peopleMat.group("lastName");
                    String firstName = peopleMat.group("firstName");
                    System.out.printf("%s: %s: %s%n", lastName, firstName, NumberFormat.getCurrencyInstance().format(salary));
                    yield salary;
                }
                case "CEO" -> {
                    String details = peopleMat.group("details");
                    Matcher ceoMat = ceoPat.matcher(details);
                    int salary = 0;
                    if (ceoMat.find()) {
                        int avgStockPrice = Integer.parseInt(ceoMat.group("avgStockPrice"));
                        salary = 5000 * avgStockPrice;
                    } else {
                        salary = 5000;
                    }
                    String lastName = peopleMat.group("lastName");
                    String firstName = peopleMat.group("firstName");
                    System.out.printf("%s: %s: %s%n", lastName, firstName, NumberFormat.getCurrencyInstance().format(salary));
                    yield salary;
                }
                default -> 0;
            };
//            System.out.printf("%s %s %s %s%n", peopleMat.group("firstName"), peopleMat.group("lastName"), peopleMat.group("dob"), peopleMat.group("role"));
        }
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be %s%n", currencyInstance.format(totalSalaries));
        ;
    }
}

 */