package com.pascal.pray.android;

import com.pascal.pray.android.utils.common.CheckInfoUtils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
/**
 * Created by Pascal on 2018/1/13.
 */

public class StaticMethodTest {
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertThat(CheckInfoUtils.isEmailFormatAvailable("name@email.co"), is(true));
    }
}
