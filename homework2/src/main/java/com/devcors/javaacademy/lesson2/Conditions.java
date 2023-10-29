package com.devcors.javaacademy.lesson2;

import java.util.Locale;

public class Conditions {

    /**
     * @return true in case 'a' is greater than 'b', false otherwise.
     */
    public boolean isGreaterThan(int a, int b) {
        return a > b;
    }

    /**
     * @return 1 in case 'a' is greater than 'b', -1 if smaller, 0 if they are equal.
     */
    public int compare(int a, int b) {
        if (a > b) {
            return 1;
        } else if (a < b) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Method should return true in case input string corresponds to one of working days of week.
     * Comparison should be case insensitive.
     *
     * <p>hint: see String.equalsIgnoreCase method
     */
    public boolean isWorkingDay(String day) {
        String[] workingDays = {
                Constants.MONDAY,
                Constants.TUESDAY,
                Constants.WEDNESDAY,
                Constants.THURSDAY,
                Constants.FRIDAY
        };

        for (String workingDay : workingDays) {
            if (workingDay.equalsIgnoreCase(day)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method should return number based on which day of week is 'day'. For example 'Thursday' should retrieve 4.
     * <p>In case input is not recognized as one of days, return -1.
     * <p>Implement functionality using if statement.
     */
    public int dayOfWeekUsingIf(String day) {
        if (day.equalsIgnoreCase(Constants.MONDAY)) {
            return 1;
        } else if (day.equalsIgnoreCase(Constants.TUESDAY)) {
            return 2;
        } else if (day.equalsIgnoreCase(Constants.WEDNESDAY)) {
            return 3;
        } else if (day.equalsIgnoreCase(Constants.THURSDAY)) {
            return 4;
        } else if (day.equalsIgnoreCase(Constants.FRIDAY)) {
            return 5;
        } else if (day.equalsIgnoreCase(Constants.SATURDAY)) {
            return 6;
        } else if (day.equalsIgnoreCase(Constants.SUNDAY)) {
            return 7;
        } else {
            return -1;
        }
    }

    /**
     * Method should return number based on which day of week is 'day'. For example 'Thursday' should retrieve 4.
     * <p>In case input is not recognized as one of days, return -1.
     * <p>Implement functionality using switch statement.
     */
    public int dayOfWeekUsingSwitch(String day) {
        int result;
        // There's not possible to use Constants directly (Constant expression required err)
        // like 'case Constants.MONDAY: ... '
        switch (day.toUpperCase()) {
            case "MONDAY":
                result = 1;
                break;
            case "TUESDAY":
                result = 2;
                break;
            case "WEDNESDAY":
                result = 3;
                break;
            case "THURSDAY":
                result = 4;
                break;
            case "FRIDAY":
                result = 5;
                break;
            case "SATURDAY":
                result = 6;
                break;
            case "SUNDAY":
                result = 7;
                break;
            default:
                result = -1;
                break;
        }

        return result;
    }

}
