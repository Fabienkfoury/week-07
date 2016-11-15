package com.example.kfouryf.mytest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by kfouryf on 15/11/2016.
 */

@SmallTest
@RunWith(MockitoJUnitRunner.class)

public class DeadlineTest {

    @Mock
    Context context;

    @Mock
    Activity activity;

    @Mock
    SharedPreferences sharedPreferences;

    @Mock
    SharedPreferences.Editor editor;

    private Deadline deadline;

    @Test
    public void testCalculate() throws Exception {
        Deadline deadline = new Deadline("12/12/16");
        assertEquals(deadline.calculate(), 1);
    }

    @Test
    public void testCalculate2() throws Exception {
        Deadline anotherDeadline = new Deadline("11/12/16", context);
        assertThat("check if time interval is calculated properly", anotherDeadline.calculate(), is(equalTo(2)) );

    }

    @Before
    public void initTests() {
        deadline = new Deadline("10/12/16", activity);
    }

    @Test
    public void testSave() throws Exception {

        when(activity.getPreferences(Context.MODE_PRIVATE)).thenReturn(sharedPreferences);
        when(sharedPreferences.edit()).thenReturn(editor);
        when(editor.commit()).thenReturn(true);
        assertThat(deadline.save(), is(true));

    }


}

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JunitTestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}


