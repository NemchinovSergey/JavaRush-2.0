package com.javarush.task.task29.task2909.human;

public class Worker extends Human {
    private String company;
    private double salary;

    public Worker(String name, int age) {
        super(name, age);
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}