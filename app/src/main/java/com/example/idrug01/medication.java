package com.example.idrug01;

public class medication {
    String name;
    String times;
    int dosage;
    int period;

    public medication(String name, String times, int dosage, int period) {
        this.name = name;
        this.times = times;
        this.dosage = dosage;
        this.period = period;
    }

    public String getName() {
        return name;
    }

    public String getTimes() {
        return times;
    }

    public int getDosage() {
        return dosage;
    }

    public int getPeriod() {
        return period;
    }
}
