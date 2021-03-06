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
package edu.usf.cutr.tba.utils;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import edu.usf.cutr.tba.model.DeviceInformation;

import java.util.Comparator;

/**
 * Comparator class implementation to be used to compare two DeviceInformation objects.
 * Overrides the compare method comparing the long value of timestamp string property.
 * If the timestamp property is not available, the document id is used. We assume the
 * document id by default have the time stamp as its name.
 */
public class QueryDocumentSnapshotDeviceComparator implements Comparator<QueryDocumentSnapshot> {

    private QueryDocumentSnapshot o;

    @Override
    public int compare(QueryDocumentSnapshot o1, QueryDocumentSnapshot o2) {
        long t1 = getDeviceComparableTime(o1);
        long t2 = getDeviceComparableTime(o2);

        return Long.compare(t1, t2);
    }

    private long getDeviceComparableTime(QueryDocumentSnapshot o) {
        DeviceInformation devInfo = o.toObject(DeviceInformation.class);

        String timeStamp = devInfo.getTimestamp();
        if (timeStamp == null) {
            timeStamp = StringUtils.parsableTimeStamp(o.getId());
        }
        return Long.parseLong(timeStamp);
    }
}
