package edu.usf.cutr.tba.test;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import edu.usf.cutr.tba.model.DeviceInformation;
import edu.usf.cutr.tba.model.TravelBehaviorInfo;
import edu.usf.cutr.tba.model.TravelBehaviorRecord;
import edu.usf.cutr.tba.utils.TravelBehaviorUtils;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests utilities for processing travel behavior data
 */
public class TravelBehaviorUtilsTest {

    final String userId = "test-user";

    @Test
    public void testIsInSameDayValidLocations() {
        List<TravelBehaviorRecord> list = new ArrayList<>();
        // Use Eastern time location
        double lat = 28.0587, lon = -82.4139;

        TravelBehaviorRecord day1a = new TravelBehaviorRecord(userId);
        day1a.setStartLat(lat);
        day1a.setStartLon(lon);
        day1a.setEndLat(lat);
        day1a.setEndLon(lon);
        TravelBehaviorRecord day1b = new TravelBehaviorRecord(userId);
        day1b.setStartLat(lat);
        day1b.setStartLon(lon);
        day1b.setEndLat(lat);
        day1b.setEndLon(lon);
        TravelBehaviorRecord day1c = new TravelBehaviorRecord(userId);
        day1c.setStartLat(lat);
        day1c.setStartLon(lon);
        day1c.setEndLat(lat);
        day1c.setEndLon(lon);
        TravelBehaviorRecord day1d = new TravelBehaviorRecord(userId);
        day1d.setStartLat(lat);
        day1d.setStartLon(lon);
        day1d.setEndLat(lat);
        day1d.setEndLon(lon);
        TravelBehaviorRecord day1e = new TravelBehaviorRecord(userId);
        day1e.setStartLat(lat);
        day1e.setStartLon(lon);
        day1e.setEndLat(lat);
        day1e.setEndLon(lon);
        TravelBehaviorRecord day1f = new TravelBehaviorRecord(userId);
        day1f.setStartLat(lat);
        day1f.setStartLon(lon);
        day1f.setEndLat(lat);
        day1f.setEndLon(lon);
        TravelBehaviorRecord day1g = new TravelBehaviorRecord(userId);
        day1g.setStartLat(lat);
        day1g.setStartLon(lon);
        day1g.setEndLat(lat);
        day1g.setEndLon(lon);

        TravelBehaviorRecord day2a = new TravelBehaviorRecord(userId);
        day2a.setStartLat(lat);
        day2a.setStartLon(lon);
        day2a.setEndLat(lat);
        day2a.setEndLon(lon);
        TravelBehaviorRecord day2b = new TravelBehaviorRecord(userId);
        day2b.setStartLat(lat);
        day2b.setStartLon(lon);
        day2b.setEndLat(lat);
        day2b.setEndLon(lon);

        // Day 1

        // Local time: Friday, August 9, 2019 4:29:42.198 PM
        // UTC time: Friday, August 9, 2019 8:29:42.198 PM
        day1a.setActivityEndTimeMillis(1565382582198L);

        // Local time: Friday, August 9, 2019 7:49:42.198 PM
        // UTC time: Friday, August 9, 2019 11:49:42.198 PM
        day1b.setActivityEndTimeMillis(1565394582198L);

        // Local time: Friday, August 9, 2019 8:39:42.198 PM
        // UTC time: Saturday, August 10, 2019 12:39:42.198 AM
        day1c.setActivityEndTimeMillis(1565397582198L);

        // Local time: Friday, August 9, 2019 10:43:02.198 PM
        // UTC time: Saturday, August 10, 2019 2:43:02.198 AM
        day1d.setActivityEndTimeMillis(1565404982198L);

        // Local time: Friday, August 9, 2019 11:33:02.198 PM
        // UTC time: Saturday, August 10, 2019 3:33:02.198 AM
        day1e.setActivityEndTimeMillis(1565407982198L);

        // Local time: Saturday, August 10, 2019 12:06:22.198 AM
        // UTC time: Saturday, August 10, 2019 4:06:22.198 AM
        day1f.setActivityEndTimeMillis(1565409982198L);

        // Local time:  Saturday, August 10, 2019 2:53:02.198 AM
        // UTC time: Saturday, August 10, 2019 6:53:02.198 AM
        day1g.setActivityEndTimeMillis(1565419982198L);

        // Day 2

        // Local time:  Saturday, August 10, 2019 3:09:52.198 AM
        // UTC time: Saturday, August 10, 2019 7:09:52.198 AM
        day2a.setActivityEndTimeMillis(1565420992198L);

        // Local time: Saturday, August 10, 2019 11:36:22.198 PM
        // UTC time: Sunday, August 11, 2019 3:36:22.198 AM
        day2b.setActivityEndTimeMillis(1565494582198L);

        // Days are split at midnight + SAME_DAY_TIME_DIFF, using local time.  We use 3am local time to split days in the below tests.
        final long SAME_DAY_TIME_DIFF = 3;

        // Add the times in the same day one-by-one and confirm that they all belong in the same day (returns true)
        list.add(day1a);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1b, SAME_DAY_TIME_DIFF));

        list.add(day1b);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1c, SAME_DAY_TIME_DIFF));

        list.add(day1c);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1d, SAME_DAY_TIME_DIFF));

        list.add(day1d);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1e, SAME_DAY_TIME_DIFF));

        list.add(day1e);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1f, SAME_DAY_TIME_DIFF));

        list.add(day1f);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1g, SAME_DAY_TIME_DIFF));
        list.add(day1g);

        // Test the times in day two against the day 1 list, and confirm that they all return false (not in the same day)
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day2a, SAME_DAY_TIME_DIFF));
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day2b, SAME_DAY_TIME_DIFF));
    }

    /**
     * Same as testIsInSameDayValidLocations(), but tests without a valid start location but with a valid end location
     */
    @Test
    public void testIsInSameDayNoStartLocation() {
        List<TravelBehaviorRecord> list = new ArrayList<>();
        // Use Eastern time location
        double lat = 28.0587, lon = -82.4139;

        // Only set end locations, not start locations (i.e., test a NULL start location)
        TravelBehaviorRecord day1a = new TravelBehaviorRecord(userId);
        day1a.setEndLat(lat);
        day1a.setEndLon(lon);
        TravelBehaviorRecord day1b = new TravelBehaviorRecord(userId);
        day1b.setEndLat(lat);
        day1b.setEndLon(lon);
        TravelBehaviorRecord day1c = new TravelBehaviorRecord(userId);
        day1c.setEndLat(lat);
        day1c.setEndLon(lon);
        TravelBehaviorRecord day1d = new TravelBehaviorRecord(userId);
        day1d.setEndLat(lat);
        day1d.setEndLon(lon);
        TravelBehaviorRecord day1e = new TravelBehaviorRecord(userId);
        day1e.setEndLat(lat);
        day1e.setEndLon(lon);
        TravelBehaviorRecord day1f = new TravelBehaviorRecord(userId);
        day1f.setEndLat(lat);
        day1f.setEndLon(lon);
        TravelBehaviorRecord day1g = new TravelBehaviorRecord(userId);
        day1g.setEndLat(lat);
        day1g.setEndLon(lon);

        TravelBehaviorRecord day2a = new TravelBehaviorRecord(userId);
        day2a.setEndLat(lat);
        day2a.setEndLon(lon);
        TravelBehaviorRecord day2b = new TravelBehaviorRecord(userId);
        day2b.setEndLat(lat);
        day2b.setEndLon(lon);

        // Day 1

        // Local time: Friday, August 9, 2019 4:29:42.198 PM
        // UTC time: Friday, August 9, 2019 8:29:42.198 PM
        day1a.setActivityEndTimeMillis(1565382582198L);

        // Local time: Friday, August 9, 2019 7:49:42.198 PM
        // UTC time: Friday, August 9, 2019 11:49:42.198 PM
        day1b.setActivityEndTimeMillis(1565394582198L);

        // Local time: Friday, August 9, 2019 8:39:42.198 PM
        // UTC time: Saturday, August 10, 2019 12:39:42.198 AM
        day1c.setActivityEndTimeMillis(1565397582198L);

        // Local time: Friday, August 9, 2019 10:43:02.198 PM
        // UTC time: Saturday, August 10, 2019 2:43:02.198 AM
        day1d.setActivityEndTimeMillis(1565404982198L);

        // Local time: Friday, August 9, 2019 11:33:02.198 PM
        // UTC time: Saturday, August 10, 2019 3:33:02.198 AM
        day1e.setActivityEndTimeMillis(1565407982198L);

        // Local time: Saturday, August 10, 2019 12:06:22.198 AM
        // UTC time: Saturday, August 10, 2019 4:06:22.198 AM
        day1f.setActivityEndTimeMillis(1565409982198L);

        // Local time:  Saturday, August 10, 2019 2:53:02.198 AM
        // UTC time: Saturday, August 10, 2019 6:53:02.198 AM
        day1g.setActivityEndTimeMillis(1565419982198L);

        // Day 2

        // Local time:  Saturday, August 10, 2019 3:09:52.198 AM
        // UTC time: Saturday, August 10, 2019 7:09:52.198 AM
        day2a.setActivityEndTimeMillis(1565420992198L);

        // Local time: Saturday, August 10, 2019 11:36:22.198 PM
        // UTC time: Sunday, August 11, 2019 3:36:22.198 AM
        day2b.setActivityEndTimeMillis(1565494582198L);

        // Days are split at midnight + SAME_DAY_TIME_DIFF, using local time.  We use 3am local time to split days in the below tests.
        final long SAME_DAY_TIME_DIFF = 3;

        // Add the times in the same day one-by-one and confirm that they all belong in the same day (returns true)
        list.add(day1a);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1b, SAME_DAY_TIME_DIFF));

        list.add(day1b);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1c, SAME_DAY_TIME_DIFF));

        list.add(day1c);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1d, SAME_DAY_TIME_DIFF));

        list.add(day1d);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1e, SAME_DAY_TIME_DIFF));

        list.add(day1e);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1f, SAME_DAY_TIME_DIFF));

        list.add(day1f);
        assertTrue(TravelBehaviorUtils.isInSameDay(list, day1g, SAME_DAY_TIME_DIFF));
        list.add(day1g);

        // Test the times in day two against the day 1 list, and confirm that they all return false (not in the same day)
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day2a, SAME_DAY_TIME_DIFF));
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day2b, SAME_DAY_TIME_DIFF));
    }

    /**
     * Same as testIsInSameDayValidLocations(), but tests without a valid start or end location.
     */
    @Test
    public void testIsInSameDayNoStartorEndLocation() {
        List<TravelBehaviorRecord> list = new ArrayList<>();
        // Don't set any start or end locations - this isn't valid data, and all assertions should be false
        TravelBehaviorRecord day1a = new TravelBehaviorRecord(userId);
        TravelBehaviorRecord day1b = new TravelBehaviorRecord(userId);
        TravelBehaviorRecord day1c = new TravelBehaviorRecord(userId);
        TravelBehaviorRecord day1d = new TravelBehaviorRecord(userId);
        TravelBehaviorRecord day1e = new TravelBehaviorRecord(userId);
        TravelBehaviorRecord day1f = new TravelBehaviorRecord(userId);
        TravelBehaviorRecord day1g = new TravelBehaviorRecord(userId);

        TravelBehaviorRecord day2a = new TravelBehaviorRecord(userId);
        TravelBehaviorRecord day2b = new TravelBehaviorRecord(userId);

        // Day 1

        // Local time: Friday, August 9, 2019 4:29:42.198 PM
        // UTC time: Friday, August 9, 2019 8:29:42.198 PM
        day1a.setActivityEndTimeMillis(1565382582198L);

        // Local time: Friday, August 9, 2019 7:49:42.198 PM
        // UTC time: Friday, August 9, 2019 11:49:42.198 PM
        day1b.setActivityEndTimeMillis(1565394582198L);

        // Local time: Friday, August 9, 2019 8:39:42.198 PM
        // UTC time: Saturday, August 10, 2019 12:39:42.198 AM
        day1c.setActivityEndTimeMillis(1565397582198L);

        // Local time: Friday, August 9, 2019 10:43:02.198 PM
        // UTC time: Saturday, August 10, 2019 2:43:02.198 AM
        day1d.setActivityEndTimeMillis(1565404982198L);

        // Local time: Friday, August 9, 2019 11:33:02.198 PM
        // UTC time: Saturday, August 10, 2019 3:33:02.198 AM
        day1e.setActivityEndTimeMillis(1565407982198L);

        // Local time: Saturday, August 10, 2019 12:06:22.198 AM
        // UTC time: Saturday, August 10, 2019 4:06:22.198 AM
        day1f.setActivityEndTimeMillis(1565409982198L);

        // Local time:  Saturday, August 10, 2019 2:53:02.198 AM
        // UTC time: Saturday, August 10, 2019 6:53:02.198 AM
        day1g.setActivityEndTimeMillis(1565419982198L);

        // Day 2

        // Local time:  Saturday, August 10, 2019 3:09:52.198 AM
        // UTC time: Saturday, August 10, 2019 7:09:52.198 AM
        day2a.setActivityEndTimeMillis(1565420992198L);

        // Local time: Saturday, August 10, 2019 11:36:22.198 PM
        // UTC time: Sunday, August 11, 2019 3:36:22.198 AM
        day2b.setActivityEndTimeMillis(1565494582198L);

        // Days are split at midnight + SAME_DAY_TIME_DIFF, using local time.  We use 3am local time to split days in the below tests, but because they are missing locations, all will return false
        final long SAME_DAY_TIME_DIFF = 3;

        // Add the times in the same day one-by-one
        list.add(day1a);
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day1b, SAME_DAY_TIME_DIFF));

        list.add(day1b);
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day1c, SAME_DAY_TIME_DIFF));

        list.add(day1c);
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day1d, SAME_DAY_TIME_DIFF));

        list.add(day1d);
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day1e, SAME_DAY_TIME_DIFF));

        list.add(day1e);
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day1f, SAME_DAY_TIME_DIFF));

        list.add(day1f);
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day1g, SAME_DAY_TIME_DIFF));
        list.add(day1g);

        // Test the times in day two against the day 1 list
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day2a, SAME_DAY_TIME_DIFF));
        assertFalse(TravelBehaviorUtils.isInSameDay(list, day2b, SAME_DAY_TIME_DIFF));
    }

    /**
     * Same as testIsInSameDayValidLocations(), but tests without a valid start or end location.
     */
    @Test
    public void testExcludeYorkFromExport() {
        TravelBehaviorRecord allowed = new TravelBehaviorRecord("1234")
                .setRegionId("1");
        assertTrue(TravelBehaviorUtils.isAllowedToExport(allowed));

        TravelBehaviorRecord notAllowed = new TravelBehaviorRecord("9876")
                .setRegionId("5");
        assertFalse(TravelBehaviorUtils.isAllowedToExport(notAllowed));

        TravelBehaviorRecord nullRegion = new TravelBehaviorRecord("9876");
        assertTrue(TravelBehaviorUtils.isAllowedToExport(nullRegion));
    }

    /**
     * Given a list of  QueryDocumentSnapshot and a endActivityTime,
     * verify the behavior of GetClosestDeviceInfo
     */
    @Test
    public void testGetClosestDeviceInfo() {
        //Mock 5 DeviceInformation classes
        DeviceInformation dev1 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev1.getTimestamp()).thenReturn("123456770");
        DeviceInformation dev2 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev2.getTimestamp()).thenReturn("123456775");
        DeviceInformation dev3 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev3.getTimestamp()).thenReturn("123456780");
        DeviceInformation dev4 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev4.getTimestamp()).thenReturn("123456785");
        DeviceInformation dev5 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev5.getTimestamp()).thenReturn("123456790");
        DeviceInformation dev6 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev6.getTimestamp()).thenReturn("123456795");

        //Mock 5 QueryDocumentSnapshot to add to the list
        QueryDocumentSnapshot qDoc1 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc1.toObject(DeviceInformation.class)).thenReturn(dev1);
        QueryDocumentSnapshot qDoc2 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc2.toObject(DeviceInformation.class)).thenReturn(dev2);
        QueryDocumentSnapshot qDoc3 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc3.toObject(DeviceInformation.class)).thenReturn(dev3);
        QueryDocumentSnapshot qDoc4 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc4.toObject(DeviceInformation.class)).thenReturn(dev4);
        QueryDocumentSnapshot qDoc5 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc5.toObject(DeviceInformation.class)).thenReturn(dev5);
        QueryDocumentSnapshot qDoc6 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc6.toObject(DeviceInformation.class)).thenReturn(dev6);

        // Create ArrayList to pass as parameter
        List<QueryDocumentSnapshot> userDevInfoById = new ArrayList<>();
        userDevInfoById.add(qDoc1);
        userDevInfoById.add(qDoc2);
        userDevInfoById.add(qDoc3);
        userDevInfoById.add(qDoc4);
        userDevInfoById.add(qDoc5);
        userDevInfoById.add(qDoc6);

        // Verify for a endTime in the middle of the list
        Long actEndTime = 123456788L;
        assertSame(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime), dev4);

        // Verify if endTime is previous to the firs timeStamp on list, return the first element on list
        actEndTime = 1234567L;
        assertSame(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime), dev1);

        // Verify for a endTime after the last timeStamp on the list
        actEndTime = 123456799L;
        assertSame(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime), dev6);

        // Verify for a endTime before the second element on the list
        actEndTime = 123456772L;
        assertSame(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime), dev1);

        // Verify for a endTime existent on the list
        actEndTime = 123456790L;
        assertSame(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime), dev5);

        // Verify for a arrayList size 0
        actEndTime = 123456788L;
        userDevInfoById.clear();
        assertNull(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime));

    }

    /**
     * Given a list of  QueryDocumentSnapshot and a endActivityTime,
     * verify the behavior of GetClosestDeviceInfo
     */
    @Test
    public void testRealDataGetClosestDeviceInfo() {
        //Mock 6 DeviceInformation classes
        DeviceInformation dev1 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev1.getTimestamp()).thenReturn(null);
        DeviceInformation dev2 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev2.getTimestamp()).thenReturn("1564623362560");
        DeviceInformation dev3 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev3.getTimestamp()).thenReturn("1565737305141");
        DeviceInformation dev4 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev4.getTimestamp()).thenReturn("1565743240407");
        DeviceInformation dev5 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev5.getTimestamp()).thenReturn("1565744016204");
        DeviceInformation dev6 = Mockito.mock(DeviceInformation.class);
        Mockito.when(dev6.getTimestamp()).thenReturn("1565744501832");
        //Mock 6 QueryDocumentSnapshot to add to the list
        QueryDocumentSnapshot qDoc1 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc1.toObject(DeviceInformation.class)).thenReturn(dev1);
        Mockito.when(qDoc1.getId()).thenReturn("0-eb01e0cd-b143-467e-94c9-a25edbefc122");
        QueryDocumentSnapshot qDoc2 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc2.getId()).thenReturn("1564623362560");
        Mockito.when(qDoc2.toObject(DeviceInformation.class)).thenReturn(dev2);
        QueryDocumentSnapshot qDoc3 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc3.toObject(DeviceInformation.class)).thenReturn(dev3);
        QueryDocumentSnapshot qDoc4 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc4.toObject(DeviceInformation.class)).thenReturn(dev4);
        QueryDocumentSnapshot qDoc5 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc5.toObject(DeviceInformation.class)).thenReturn(dev5);
        QueryDocumentSnapshot qDoc6 = Mockito.mock(QueryDocumentSnapshot.class);
        Mockito.when(qDoc6.toObject(DeviceInformation.class)).thenReturn(dev6);
        // Create ArrayList to pass as parameter
        List<QueryDocumentSnapshot> userDevInfoById = new ArrayList<>();
        userDevInfoById.add(qDoc1);
        userDevInfoById.add(qDoc2);
        userDevInfoById.add(qDoc3);
        userDevInfoById.add(qDoc4);
        userDevInfoById.add(qDoc5);
        userDevInfoById.add(qDoc6);
        // Verify for a endTime in the middle of the list
        Long actEndTime = 1565743240500L;
        assertSame(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime), dev4);
        // Verify if endTime is previous to the firs timeStamp on list, return the first element on list
        actEndTime = -1234567L;
        assertSame(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime), dev1);
        // Verify for a endTime after the last timeStamp on the list
        actEndTime = 1565744501840L;
        assertSame(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime), dev6);
        // Verify for a endTime before the second element on the list
        actEndTime = 100L;
        assertSame(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime), dev1);
        // Verify for a endTime existent on the list
        actEndTime = 1565744016204L;
        assertSame(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime), dev5);
        // Verify for a arrayList size 0
        actEndTime = 123456788L;
        userDevInfoById.clear();
        assertNull(TravelBehaviorUtils.getClosestDeviceInfo(userDevInfoById, actEndTime));
    }

    /**
     * Given a time stamp in milliseconds, verify that the function
     * getDateAndTimeFromMillis return a UTC date formatted in the format
     * "yyyy-MM-dd'T'HH:mm:ss'Z'"
     */
    @Test
    public  void testGetDateAndTimeFromMillis() {
        // UTC time: Friday, August 9, 2019 8:29:42.198 PM
        long testDate1 = 1565382582198L;
        // UTC time: Friday, August 9, 2019 11:49:42.198 PM
        long testDate2 = 1565394582198L;
        // UTC time: Saturday, August 10, 2019 7:09:52.198 AM
        long testDate3 = 1565420992198L;
        // UTC time: Sunday, August 11, 2019 3:36:22.198 AM
        long testDate4 = 1565494582198L;

        assertEquals("2019-08-09T20:29:42Z", TravelBehaviorUtils.getDateAndTimeFromMillis(testDate1));
        assertEquals("2019-08-09T23:49:42Z", TravelBehaviorUtils.getDateAndTimeFromMillis(testDate2));
        assertEquals("2019-08-10T07:09:52Z", TravelBehaviorUtils.getDateAndTimeFromMillis(testDate3));
        assertEquals("2019-08-11T03:36:22Z", TravelBehaviorUtils.getDateAndTimeFromMillis(testDate4));
    }

    /**
     * Given different list of LocationInfo objects, verify that the getLocationInfo method
     * return an objects that matches the location provider or the null object if not match
     * provider is found.
     */
    @Test
    public void testGetLocationInfo() {
        List<TravelBehaviorInfo.LocationInfo> locationInfoEmptyList = null;
        List<TravelBehaviorInfo.LocationInfo> locationInfoFullList = null;
        List<TravelBehaviorInfo.LocationInfo> locationInfoPartialList = null;

        TravelBehaviorInfo.LocationInfo fused = new TravelBehaviorInfo.LocationInfo();
        fused.provider = "fused";
        fused.lat = 37.5540478;
        fused.lon = -122.2586887;
        TravelBehaviorInfo.LocationInfo gps = new TravelBehaviorInfo.LocationInfo();
        gps.provider = "gps";
        gps.lat = 37.55404742;
        gps.lon = -122.2586892;
        TravelBehaviorInfo.LocationInfo network = new TravelBehaviorInfo.LocationInfo();
        network.provider = "network";
        network.lat = 37.5540953;
        network.lon = -122.2587029;

        // If the list is null, the function must return null
        assertNull(TravelBehaviorUtils.getLocationInfo(locationInfoEmptyList, TravelBehaviorRecord.FUSED));

        // Create a full list and verify the proper returned values for provider and lat-lon
        locationInfoFullList = new ArrayList<>();
        locationInfoFullList.add(fused);
        locationInfoFullList.add(gps);
        locationInfoFullList.add(network);
        assertEquals(fused, TravelBehaviorUtils.getLocationInfo(locationInfoFullList, TravelBehaviorRecord.FUSED));
        assertEquals(gps, TravelBehaviorUtils.getLocationInfo(locationInfoFullList, TravelBehaviorRecord.GPS));
        assertEquals(network, TravelBehaviorUtils.getLocationInfo(locationInfoFullList, TravelBehaviorRecord.NETWORK));

        // Create a partial list and verify the proper returned values for provider and lat-lon
        locationInfoPartialList = new ArrayList<>();
        locationInfoPartialList.add(fused);
        locationInfoPartialList.add(network);
        // Check for an available provider in the partial list
        assertEquals(fused, TravelBehaviorUtils.getLocationInfo(locationInfoPartialList, TravelBehaviorRecord.FUSED));
        // Check for an unavailable provider in the partial list
        assertNull(TravelBehaviorUtils.getLocationInfo(locationInfoPartialList, TravelBehaviorRecord.GPS));
        assertEquals(network, TravelBehaviorUtils.getLocationInfo(locationInfoPartialList, TravelBehaviorRecord.NETWORK));
        // Check for null provider as argument
        assertNull(TravelBehaviorUtils.getLocationInfo(locationInfoPartialList, null));
    }
}
