package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        List<LogRecord> listR = getAllRecords();
        Set<String> set = new HashSet<>();

        for (LogRecord R : listR) {
            if (isDateInside(after, before, R.getDate())) {
                set.add(R.getIp());
            }
        }

        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && user.equals(R.getUser())) {
                set.add(R.getIp());
            }
        }

        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && event.equals(R.getEvent())) {
                set.add(R.getIp());
            }
        }

        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && status.equals(R.getStatus())) {
                set.add(R.getIp());
            }
        }

        return set;
    }

    private List<LogRecord> getAllRecords() {
        List<LogRecord> list = new ArrayList<>();

        try {
            for (File f : logDir.toFile().listFiles()) {
                if (f.isFile() && f.getName().toLowerCase().endsWith("log")) {
                    for (String s : Files.readAllLines(f.toPath())) {
                        list.add(new LogRecord(s));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private boolean isDateInside(Date after, Date before, Date currentDate) {
        if (after != null) {
            if (currentDate.getTime() <= after.getTime())
                return false;
        }
        if (before != null) {
            if (currentDate.getTime() >= before.getTime())
                return false;
        }
        return true;
    }

    @Override
    public Set<String> getAllUsers() {
        List<LogRecord> listR = getAllRecords();
        Set<String> set = new HashSet<>();

        for (LogRecord R : listR) {
            set.add(R.getUser());
        }

        return set;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        List<LogRecord> listR = getAllRecords();
        Set<String> set = new HashSet<>();

        for (LogRecord R : listR) {
            if (isDateInside(after, before, R.getDate()))
                set.add(R.getUser());
        }

        return set.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        List<LogRecord> listR = getAllRecords();
        Set<Event> set = new HashSet<>();

        for (LogRecord R : listR) {
            if (isDateInside(after, before, R.getDate()) && user.equals(R.getUser()))
                set.add(R.getEvent());
        }

        return set.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && ip.equals(R.getIp())) {
                set.add(R.getUser());
            }
        }

        return set;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Event.LOGIN.equals(R.getEvent())) {
                set.add(R.getUser());
            }
        }

        return set;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Event.DOWNLOAD_PLUGIN.equals(R.getEvent())) {
                set.add(R.getUser());
            }
        }

        return set;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Event.WRITE_MESSAGE.equals(R.getEvent())) {
                set.add(R.getUser());
            }
        }

        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Event.SOLVE_TASK.equals(R.getEvent())) {
                set.add(R.getUser());
            }
        }

        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Event.SOLVE_TASK.equals(R.getEvent()) && String.valueOf(task).equals(R.getTaskNumber())) {
                set.add(R.getUser());
            }
        }

        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Event.DONE_TASK.equals(R.getEvent())) {
                set.add(R.getUser());
            }
        }

        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Event.DONE_TASK.equals(R.getEvent()) && String.valueOf(task).equals(R.getTaskNumber())) {
                set.add(R.getUser());
            }
        }

        return set;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && user.equals(R.getUser()) && event.equals(R.getEvent())) {
                set.add(R.getDate());
            }
        }

        return set;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Status.FAILED.equals(R.getStatus())) {
                set.add(R.getDate());
            }
        }

        return set;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Status.ERROR.equals(R.getStatus())) {
                set.add(R.getDate());
            }
        }

        return set;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Date date = null;

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && user.equals(R.getUser()) && Event.LOGIN.equals(R.getEvent())) {
                if (date == null) date = R.getDate();
                else date = date.compareTo(R.getDate()) > 0 ? R.getDate() : date;
            }
        }

        return date;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Date date = null;

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && user.equals(R.getUser()) && Event.SOLVE_TASK.equals(R.getEvent()) && String.valueOf(task).equals(R.getTaskNumber())) {
                if (date == null) date = R.getDate();
                else date = date.compareTo(R.getDate()) > 0 ? R.getDate() : date;
            }
        }

        return date;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Date date = null;

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && user.equals(R.getUser()) && Event.DONE_TASK.equals(R.getEvent()) && String.valueOf(task).equals(R.getTaskNumber())) {
                if (date == null) date = R.getDate();
                else date = date.compareTo(R.getDate()) > 0 ? R.getDate() : date;
            }
        }

        return date;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Event.WRITE_MESSAGE.equals(R.getEvent()) && user.equals(R.getUser())) {
                set.add(R.getDate());
            }
        }

        return set;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Event.DOWNLOAD_PLUGIN.equals(R.getEvent()) && user.equals(R.getUser())) {
                set.add(R.getDate());
            }
        }

        return set;
    }


    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate())) {
                set.add(R.getEvent());
            }
        }

        return set;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && ip.equals(R.getIp())) {
                set.add(R.getEvent());
            }
        }

        return set;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && user.equals(R.getUser())) {
                set.add(R.getEvent());
            }
        }

        return set;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Status.FAILED.equals(R.getStatus())) {
                set.add(R.getEvent());
            }
        }

        return set;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> set = new HashSet<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate()) && Status.ERROR.equals(R.getStatus())) {
                set.add(R.getEvent());
            }
        }

        return set;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int i = 0;

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate())
                    && (Event.SOLVE_TASK.equals(R.getEvent()))
                    && R.getTaskNumber() != null
                    && !R.getTaskNumber().isEmpty()
                    && String.valueOf(task).equals(R.getTaskNumber())) {
                i++;
            }
        }

        return i;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int i = 0;

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate())
                    && Event.DONE_TASK.equals(R.getEvent())
                    && R.getTaskNumber() != null
                    && !R.getTaskNumber().isEmpty()
                    && String.valueOf(task).equals(R.getTaskNumber())) {
                i++;
            }
        }

        return i;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate())
                    && Event.SOLVE_TASK.equals(R.getEvent())
                    && R.getTaskNumber() != null
                    && !R.getTaskNumber().isEmpty()) {
                int task = Integer.parseInt(R.getTaskNumber());
                if (!map.containsKey(task))
                    map.put(task, getNumberOfAttemptToSolveTask(task, after, before));
            }
        }

        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> map = new HashMap<>();

        for (LogRecord R : getAllRecords()) {
            if (isDateInside(after, before, R.getDate())
                    && Event.DONE_TASK.equals(R.getEvent())
                    && R.getTaskNumber() != null
                    && !R.getTaskNumber().isEmpty()) {
                int task = Integer.parseInt(R.getTaskNumber());
                if (!map.containsKey(task))
                    map.put(task, getNumberOfSuccessfulAttemptToSolveTask(task, after, before));
            }
        }

        return map;
    }

    @Override
    public Set<Object> execute(String query) {
        Set<Object> result = new HashSet<>();
        if (query == null || query.isEmpty()) return result;

        Pattern p = Pattern.compile("get (ip|user|date|event|status)"
                + "( for (ip|user|date|event|status) = \"(.*?)\")?"
                + "( and date between \"(.*?)\" and \"(.*?)\")?");

        Matcher m = p.matcher(query);

        String s1 = null;
        String s2 = null;
        String v  = null;
        Date dateafter = null;
        Date datebefore = null;

        if (m.find()) {
            s1 = m.group(1);
            s2 = m.group(3);
            v  = m.group(4);
            String d1 = m.group(6);
            String d2 = m.group(7);
            try {
                dateafter = new SimpleDateFormat("d.M.yyyy H:m:s").parse(d1);
            } catch (Exception e) {
                dateafter = null;
            }
            try {
                datebefore = new SimpleDateFormat("d.M.yyyy H:m:s").parse(d2);
            } catch (Exception e) {
                datebefore = null;
            }

            switch (s1) {
                case "ip": {
                    result.addAll(getIps(s2, v, dateafter, datebefore));
                    break;
                }
                case "user": {
                    result.addAll(getAllUsers(s2, v, dateafter, datebefore));
                    break;
                }
                case "date": {
                    result.addAll(getAllDates(s2, v, dateafter, datebefore));
                    break;
                }
                case "event": {
                    result.addAll(getAllEvents(s2, v, dateafter, datebefore));
                    break;
                }
                case "status": {
                    result.addAll(getAllStatuses(s2, v, dateafter, datebefore));
                    break;
                }
            }

        }

        return result;
    }

    private Set<String> getIps(String field, String value, Date after, Date before) {
        Set<String> ips = new HashSet<>();
        for (LogRecord record : getAllRecords()) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record))
                    ips.add(record.getIp());
            } catch (ParseException ignored) {
            }
        }
        return ips;
    }

    private boolean isFieldMatch(String field, String value, LogRecord record) throws ParseException {
        boolean criteria = false;
        if (field == null) return true;
        if (value == null) return false;
        switch (field) {
            case "ip": {
                criteria = record.getIp().equals(value);
                break;
            }
            case "user": {
                criteria = record.getUser().equals(value);
                break;
            }
            case "date": {
                criteria = record.getDate().equals(new SimpleDateFormat("d.M.yyyy H:m:s").parse(value));
                break;
            }
            case "event": {
                criteria = record.getEvent().equals(Event.valueOf(value));
                break;
            }
            case "status": {
                criteria = record.getStatus().equals(Status.valueOf(value));
                break;
            }
        }
        return criteria;
    }

    private Set<Date> getAllDates(String field, String value, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LogRecord record : getAllRecords()) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    dates.add(record.date);
                }
            } catch (ParseException ignored) {
            }
        }
        return dates;
    }

    private Set<Status> getAllStatuses(String field, String value, Date after, Date before) {
        Set<Status> set = new HashSet<>();
        for (LogRecord record : getAllRecords()) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    set.add(record.getStatus());
                }
            } catch (ParseException ignored) {
            }
        }
        return set;
    }

    private Set<Event> getAllEvents(String field, String value, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (LogRecord record : getAllRecords()) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    set.add(record.getEvent());
                }
            } catch (ParseException ignored) {
            }
        }
        return set;
    }

    private Set<String> getAllUsers(String field, String value, Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getAllRecords()) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    users.add(record.getUser());
                }
            } catch (ParseException ignored) {
            }
        }
        return users;
    }


    private class LogRecord {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private String taskNumber;
        private Status status;

        public LogRecord(String ip, String user, Date date, Event event, String taskNumber, Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.taskNumber = taskNumber;
            this.status = status;
        }

        public LogRecord(String record) {
            String[] strings = record.split("\t");
            this.ip = strings[0].trim();
            this.user = strings[1];
            SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy H:m:s");
            try {
                date = dateFormat.parse(strings[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String eventAndParameter[] = strings[3].split(" ");
            event = Event.valueOf(eventAndParameter[0]);
            if (eventAndParameter.length > 1) taskNumber = eventAndParameter[1];
            status = Status.valueOf(strings[4]);
        }

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public String getTaskNumber() {
            return taskNumber;
        }

        public Status getStatus() {
            return status;
        }
    }
}