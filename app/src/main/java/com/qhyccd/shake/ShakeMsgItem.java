package com.qhyccd.shake;

import java.util.List;

public class ShakeMsgItem
{
    public ShakeActItem shakeActivity;
    public List<AwardItem> awards;
    public static class ShakeActItem{
        public String id;
        public String storeId;
        public String startDate;
        public String endDate;
        public String publicityPicture;
        public String participateTimes;
        public String leftParticipateTimes;
        public String activityDescription;
        public String activityStatus;
    }
    public static class AwardItem {
        public String id;
        public String awardType;
        public String storeId;
        public String awardName;
        public String awardValue;
        public String deletedFlag;
    }
}
