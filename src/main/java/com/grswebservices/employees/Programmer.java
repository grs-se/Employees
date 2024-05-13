package com.grswebservices.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee implements IEmployee, Apple {
    private int linesOfCode = 0;
    private int yearsOfExp = 0;
    private int iq = 0;

    private final String progRegex = "\\w+=(?<locpd>\\w+),\\w+=(?<yoe>\\w),\\w+=(?<iq>\\w)";
    private final Pattern progPat = Pattern.compile(progRegex);

    public Programmer(String personText) {
        // calls the constructor of the super class
        super(personText);
        Matcher progMat = progPat.matcher(peopleMat.group("details"));
        if (progMat.find()) {
            this.linesOfCode = Integer.parseInt(progMat.group("locpd"));
            this.yearsOfExp = Integer.parseInt(progMat.group("yoe"));
            this.iq = Integer.parseInt(progMat.group("iq"));
        }
    }

    public int getSalary() {
        return 3000 + linesOfCode * yearsOfExp * iq;
    }

}
