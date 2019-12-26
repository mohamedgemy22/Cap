package com.enggemy22.cap.models;

public class University {
    private String name;
    private String gpa;
    private String sat_score_range;
    private String est_score_range;
    private String finanial_aid;
    private String acceptance_rate;
    private String average_fees;
    private String country;
    private String qs_ranking;

    public University() {
    }

    public University(String name, String gpa, String sat_score_range, String est_score_range,
                      String finanial_aid, String acceptance_rate,
                      String average_fees, String country, String qs_ranking) {
        this.name = name;
        this.gpa = gpa;
        this.sat_score_range = sat_score_range;
        this.est_score_range = est_score_range;
        this.finanial_aid = finanial_aid;
        this.acceptance_rate = acceptance_rate;
        this.average_fees = average_fees;
        this.country = country;
        this.qs_ranking = qs_ranking;
    }

    public String getName() {
        return name;
    }

    public String getGpa() {
        return gpa;
    }

    public String getSat_score_range() {
        return sat_score_range;
    }

    public String getEst_score_range() {
        return est_score_range;
    }

    public String getFinanial_aid() {
        return finanial_aid;
    }

    public String getAcceptance_rate() {
        return acceptance_rate;
    }

    public String getAverage_fees() {
        return average_fees;
    }

    public String getCountry() {
        return country;
    }

    public String getQs_ranking() {
        return qs_ranking;
    }
}
