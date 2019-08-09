/*
 * Copyright (C) 2019 University of South Florida
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.usf.cutr.tba.model;

import edu.usf.cutr.tba.utils.StringUtils;

public class TravelBehaviorRecord {

    public static final String[] CSV_HEADER = {"User ID",
            "Trip ID",
            "Region ID",
            "Google Activity",
            "Google Activity Confidence",
            "Vehicle type",
            "Activity Start Date and Time (UTC)",
            "Origin location Date and Time (UTC)",
            "Activity Start/Origin Time Diff (minutes)",
            "Origin latitude",
            "Origin longitude",
            "Origin Horizontal Accuracy",
            "Origin Location Provider",
            "Activity Destination Date and Time (UTC)",
            "Destination Location Date and Time (UTC)",
            "Activity End/Destination Time Diff (minutes)",
            "Destination latitude",
            "Destination longitude",
            "Destination Horizontal Accuracy",
            "Destination Location Provider",
            "Duration (minutes)",
            "Origin-Destination Bird-Eye Distance (meters)",
            "Chain ID",
            "Chain Index",
            "Tour ID",
            "Tour Index"
    };

    private String mUserId;
    private String mTripId;
    private String mRegionId;
    private String mGoogleActivity;
    private Float mGoogleConfidence;
    private String mVehicleType;
    private String mActivityStartDateAndTime;
    private Double mStartLat;
    private Double mStartLon;
    private String mActivityEndDateAndTime;
    private Double mEndLat;
    private Double mEndLon;
    private Float mActivityDuration;
    private Integer mChainId;
    private Integer mChainIndex;
    private Integer mTourId;
    private Integer mTourIndex;
    private String mOriginLocationDateAndTime;
    private Float mActivityStartOriginTimeDiff;
    private Float mOriginHorAccuracy;
    private String mOriginProvider;
    private String mDestinationLocationDateAndTime;
    private Float mOriginDestinationDistance;
    private Float mActivityEndDestinationTimeDiff;
    private Float mDestinationHorAccuracy;
    private String mDestinationProvider;

    // Internal usage
    private Long mActivityStartTimeMillis;
    private Long mActivityEndTimeMillis;
    private Long mLocationStartTimeMillis;
    private Long mLocationEndTimeMillis;

    public TravelBehaviorRecord(String userId) {
        mUserId = userId;
    }

    public TravelBehaviorRecord setRegionId(String regionId) {
        mRegionId = regionId;
        return this;
    }

    public TravelBehaviorRecord setTripId(String tripId) {
        mTripId = tripId;
        return this;
    }

    public String getGoogleActivity() {
        return mGoogleActivity;
    }

    public TravelBehaviorRecord setGoogleActivity(String googleActivity) {
        mGoogleActivity = googleActivity;
        return this;
    }

    public TravelBehaviorRecord setGoogleConfidence(Float googleConfidence) {
        mGoogleConfidence = googleConfidence;
        return this;
    }

    public TravelBehaviorRecord setVehicleType(String vehicleType) {
        mVehicleType = vehicleType;
        return this;
    }

    public TravelBehaviorRecord setStartLat(Double startLat) {
        mStartLat = startLat;
        return this;
    }

    public TravelBehaviorRecord setStartLon(Double startLon) {
        mStartLon = startLon;
        return this;
    }

    public TravelBehaviorRecord setEndLat(Double endLat) {
        mEndLat = endLat;
        return this;
    }

    public TravelBehaviorRecord setEndLon(Double endLon) {
        mEndLon = endLon;
        return this;
    }

    public TravelBehaviorRecord setActivityDuration(Float activityDuration) {
        mActivityDuration = activityDuration;
        return this;
    }

    public TravelBehaviorRecord setChainId(Integer chainId) {
        mChainId = chainId;
        return this;
    }

    public TravelBehaviorRecord setChainIndex(Integer chainIndex) {
        mChainIndex = chainIndex;
        return this;
    }

    public TravelBehaviorRecord setTourId(Integer tourId) {
        mTourId = tourId;
        return this;
    }

    public TravelBehaviorRecord setTourIndex(Integer tourIndex) {
        mTourIndex = tourIndex;
        return this;
    }

    public TravelBehaviorRecord setActivityStartDateAndTime(String activityStartDateAndTime) {
        mActivityStartDateAndTime = activityStartDateAndTime;
        return this;
    }

    public TravelBehaviorRecord setActivityEndDateAndTime(String activityEndDateAndTime) {
        mActivityEndDateAndTime = activityEndDateAndTime;
        return this;
    }

    public TravelBehaviorRecord setActivityStartTimeMillis(Long activityStartTimeMillis) {
        this.mActivityStartTimeMillis = activityStartTimeMillis;
        return this;
    }

    public TravelBehaviorRecord setLocationStartTimeMillis(Long locationStartTimeMillis) {
        mLocationStartTimeMillis = locationStartTimeMillis;
        return this;
    }

    public TravelBehaviorRecord setLocationEndTimeMillis(Long locationEndTimeMillis) {
        this.mLocationEndTimeMillis = locationEndTimeMillis;
        return this;
    }

    public TravelBehaviorRecord setActivityEndTimeMillis(Long activityEndTimeMillis) {
        this.mActivityEndTimeMillis = activityEndTimeMillis;
        return this;
    }

    public TravelBehaviorRecord setOriginLocationDateAndTime(String originLocationDateAndTime) {
        mOriginLocationDateAndTime = originLocationDateAndTime;
        return this;
    }

    public TravelBehaviorRecord setActivityStartOriginTimeDiff(Float activityStartOriginTimeDiff) {
        mActivityStartOriginTimeDiff = activityStartOriginTimeDiff;
        return this;
    }

    public TravelBehaviorRecord setOriginHorAccuracy(Float originHorAccuracy) {
        mOriginHorAccuracy = originHorAccuracy;
        return this;
    }

    public TravelBehaviorRecord setOriginProvider(String originProvider) {
        mOriginProvider = originProvider;
        return this;
    }

    public TravelBehaviorRecord setDestinationLocationDateAndTime(String destinationLocationDateAndTime) {
        mDestinationLocationDateAndTime = destinationLocationDateAndTime;
        return this;
    }

    public TravelBehaviorRecord setActivityEndDestinationTimeDiff(Float activityEndDestinationTimeDiff) {
        mActivityEndDestinationTimeDiff = activityEndDestinationTimeDiff;
        return this;
    }

    public TravelBehaviorRecord setDestinationHorAccuracy(Float destinationHorAccuracy) {
        mDestinationHorAccuracy = destinationHorAccuracy;
        return this;
    }

    public TravelBehaviorRecord setDestinationProvider(String destinationProvider) {
        mDestinationProvider = destinationProvider;
        return this;
    }

    public TravelBehaviorRecord setOriginDestinationDistance(Float originDestinationDistance) {
        mOriginDestinationDistance = originDestinationDistance;
        return this;
    }

    public String getRegionId() {
        return mRegionId;
    }

    public Double getStartLat() {
        return mStartLat;
    }

    public Double getStartLon() {
        return mStartLon;
    }

    public Long getLocationStartTimeMillis() {
        return mLocationStartTimeMillis;
    }

    public Long getLocationEndTimeMillis() {
        return mLocationEndTimeMillis;
    }

    public Long getActivityStartTimeMillis() {
        return mActivityStartTimeMillis;
    }

    public Long getActivityEndTimeMillis() {
        return mActivityEndTimeMillis;
    }

    public Double getEndLat() {
        return mEndLat;
    }

    public Double getEndLon() {
        return mEndLon;
    }

    public String getActivityStartDateAndTime() {
        return mActivityStartDateAndTime;
    }

    public String getActivityEndDateAndTime() {
        return mActivityEndDateAndTime;
    }

    public String getDestinationLocationDateAndTime() {
        return mDestinationLocationDateAndTime;
    }

    public Float getActivityEndDestinationTimeDiff() {
        return mActivityEndDestinationTimeDiff;
    }

    public Float getDestinationHorAccuracy() {
        return mDestinationHorAccuracy;
    }

    public Float getOriginHorAccuracy() {
        return mOriginHorAccuracy;
    }


    public String getDestinationProvider() {
        return mDestinationProvider;
    }

    public String getOriginProvider() {
        return mOriginProvider;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getTripId() {
        return mTripId;
    }

    public String[] toStringArray() {
        return new String[]{mUserId, mTripId, mRegionId, mGoogleActivity, StringUtils.valueOf(mGoogleConfidence), mVehicleType,
                mActivityStartDateAndTime, mOriginLocationDateAndTime, StringUtils.valueOf(mActivityStartOriginTimeDiff),
                StringUtils.valueOf(mStartLat), StringUtils.valueOf(mStartLon), StringUtils.valueOf(mOriginHorAccuracy), mOriginProvider,
                mActivityEndDateAndTime, mDestinationLocationDateAndTime, StringUtils.valueOf(mActivityEndDestinationTimeDiff),
                StringUtils.valueOf(mEndLat), StringUtils.valueOf(mEndLon), StringUtils.valueOf(mDestinationHorAccuracy), mDestinationProvider,
                StringUtils.valueOf(mActivityDuration), StringUtils.valueOf(mOriginDestinationDistance), StringUtils.valueOf(mChainId),
                StringUtils.valueOf(mChainIndex), StringUtils.valueOf(mTourId), StringUtils.valueOf(mTourIndex)};
    }
}