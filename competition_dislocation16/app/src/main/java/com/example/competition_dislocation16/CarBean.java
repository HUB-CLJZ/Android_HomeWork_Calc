package com.example.competition_dislocation16;

import java.util.List;

public class CarBean {

    @Override
    public String toString() {
        return "CarBean{" +
                "car_name='" + car_name + '\'' +
                ", Illegal_details=" + Illegal_details +
                '}';
    }

    /**
     * car_name : 鲁B10001
     * Illegal_details : [{"time":"2017-01-01","place":"学院路","reason":"酒驾跑路","points":"2","fine":"100"},{"time":"2017-01-02","place":"医院路","reason":"超速","points":"5","fine":"200"}]
     */

    private String car_name;
    private List<IllegalDetailsBean> Illegal_details;

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public List<IllegalDetailsBean> getIllegal_details() {
        return Illegal_details;
    }

    public void setIllegal_details(List<IllegalDetailsBean> Illegal_details) {
        this.Illegal_details = Illegal_details;
    }

    public static class IllegalDetailsBean {
        /**
         * time : 2017-01-01
         * place : 学院路
         * reason : 酒驾跑路
         * points : 2
         * fine : 100
         */

        private String time;
        private String place;
        private String reason;
        private String points;
        private String fine;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
        }

        public String getFine() {
            return fine;
        }

        public void setFine(String fine) {
            this.fine = fine;
        }

        @Override
        public String toString() {
            return "IllegalDetailsBean{" +
                    "time='" + time + '\'' +
                    ", place='" + place + '\'' +
                    ", reason='" + reason + '\'' +
                    ", points='" + points + '\'' +
                    ", fine='" + fine + '\'' +
                    '}';
        }
    }
}
