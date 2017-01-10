package es.elprincipe.madridguide.model.mapper;


import java.util.LinkedList;
import java.util.List;

import es.elprincipe.madridguide.manager.net.ActivityEntity;
import es.elprincipe.madridguide.model.activity.Activity;

public class ActivityEntityMapper {

    List<Activity> result = new LinkedList<>();

    public List<Activity> map(List<ActivityEntity> activityEntity){

        for(ActivityEntity entity:activityEntity){
            Activity activity = new Activity(entity.getId(), entity.getName());
            activity.setBooking_operation(entity.getBooking_operation());
            activity.setBooking_data(entity.getBooking_data());
            activity.setTelephone(entity.getTelephone());
            activity.setEmail(entity.getEmail());
            activity.setAddress(entity.getAddress());
            activity.setImageUrl(entity.getImg());
            activity.setLogoImgUrl(entity.getLogimg());
            activity.setLatitude(entity.getGps_lat());
            activity.setLongitude(entity.getGps_lon());
            result.add(activity);

        }

        return  result;

    }


}
