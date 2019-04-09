package io.pivotal.pal.tracker;

import org.apache.tomcat.jni.Time;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{


    private  HashMap<Long,TimeEntry> timeEntryHashMap = new HashMap<>();
    private  long incrementId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry){
        Long id = incrementId++;

        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        timeEntryHashMap.put(id,newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(Long id){
        return timeEntryHashMap.get(id);
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if(find(id)==null){
            return  null;
        }

        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntryHashMap.replace(id,updatedEntry);

        return updatedEntry;
    }

    @Override
    public void delete(Long id) {
        timeEntryHashMap.remove(id);
    }


    @Override
    public List<TimeEntry> list() {
//        List<TimeEntry> timeEntryList = new ArrayList<>();
//        timeEntryHashMap.keySet();
//        for (long key : timeEntryHashMap.keySet()) {
//
//            timeEntryList.add(timeEntryHashMap.get(key));
//
//        }

        return new ArrayList<>(timeEntryHashMap.values());
    }


}
